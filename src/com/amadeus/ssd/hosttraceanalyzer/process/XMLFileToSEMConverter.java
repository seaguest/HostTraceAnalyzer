package com.amadeus.ssd.hosttraceanalyzer.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

import com.amadeus.ocg.objectmodel.common.journey.RequestedTransportationElement;
import com.amadeus.ocg.objectmodel.common.traveller.Traveller;
import com.amadeus.ocg.objectmodel.helper.middleware.request.AvailabilityInputHelper;
import com.amadeus.ocg.objectmodel.helper.middleware.request.settings.PricingOptionHelper;
import com.amadeus.ocg.objectmodel.helper.middleware.request.settings.ProductPreferenceHelper;
import com.amadeus.ocg.objectmodel.helper.middleware.response.PanelHelper;
import com.amadeus.ocg.objectmodel.middleware.request.CoreAvailabilityInput;
import com.amadeus.ocg.objectmodel.middleware.request.DistanceUnitEnum;
import com.amadeus.ocg.objectmodel.middleware.request.TripTypeEnum;
import com.amadeus.ocg.objectmodel.middleware.request.settings.FareSelection;
import com.amadeus.ocg.objectmodel.middleware.request.settings.GlobalAvailabilityPreference;
import com.amadeus.ocg.objectmodel.middleware.request.settings.PricingOption;
import com.amadeus.ocg.objectmodel.middleware.request.settings.ProductPreference;
import com.amadeus.ocg.objectmodel.middleware.request.settings.ProductTypeEnum;
import com.amadeus.ocg.objectmodel.middleware.request.settings.RequestedElementSet;
import com.amadeus.ocg.objectmodel.middleware.response.Panel;
import com.amadeus.ocg.objectmodel.middleware.response.PanelTypeEnum;
import com.amadeus.ocg.standard.access.FunctionalException;
import com.amadeus.ocg.standard.access.powered.fare.faredriven.output.MockFareDrivenResponse;
import com.amadeus.ocg.standard.access.powered.fare.faredriven.output.MockFunctionalResponse;
import com.amadeus.ocg.standard.access.powered.fare.masterpricer.output.MasterPricerResponseTags;
import com.amadeus.ocg.standard.business.farecommon.FareInvalidConfigException;
import com.amadeus.ocg.standard.business.farecommon.FareSQLException;
import com.amadeus.ocg.standard.business.farecommon.entity.Search;
import com.amadeus.ocg.standard.business.farecommon.wrapper.FareDrivenAccessWrapperResponse;
import com.amadeus.ocg.standard.business.faredriven.command.MockSplitProductPreferenceHelper;
import com.amadeus.ocg.standard.business.faredriven.context.FareDrivenSingleRequestContext;
import com.amadeus.ocg.standard.business.faredriven.context.RequestMappingImpl;
import com.amadeus.ocg.standard.business.faredriven.filler.FareDrivenPanelFiller;
import com.amadeus.ocg.standard.business.flexpricer.api.AbstractFlexPricerAvailabilityRequest;
import com.amadeus.ocg.standard.business.masterpricer.context.MockFareDrivenCommandContext;
import com.amadeus.ocg.standard.business.masterpricer.filler.UnitTestableMasterPricerFiller;
import com.amadeus.ocg.standard.business.masterpricer.request.MockAvailabilityObjectsBuilder;
import com.amadeus.ocg.standard.business.masterpricer.request.MockAvailabilityObjectsBuilder.GlobalAvailabilityPreferenceAndRequestedElementSets;
import com.amadeus.ocg.standard.business.middleware.context.CommandContext;
import com.amadeus.ocg.standard.business.middleware.error.ErrorList;
import com.amadeus.ocg.util.dictionary.DataMap;
import com.amadeus.ocg.util.dictionary.LinkedHashMap;
import com.amadeus.ocg.util.xml.TransformationException;
import com.amadeus.ocg.util.xml.XmlToDataMapParser;

public class XMLFileToSEMConverter {

  public static final int OUTBOUND = 0;
  public static final int INBOUND = 1;
  public AbstractFlexPricerAvailabilityRequest[] roundTrip;
  public AbstractFlexPricerAvailabilityRequest requestOutbound;
  public AbstractFlexPricerAvailabilityRequest requestInbound;
  public AbstractFlexPricerAvailabilityRequest requestRT;
  public Search[] search;
  public int numberOfTravellers;
  public int numberOfRequestedElements;
  public String[] globallyIncludedSuppliers;
  public String[] globallyPreferredSuppliers;
  public String[] globallyExcludedSuppliers;
  public String[][] perElementIncludedSuppliers;
  public String[][] perElementPreferredSuppliers;
  public String[][] perElementExcludedSuppliers;
  public CoreAvailabilityInput m_coreAvailityInput;
  public ProductPreference m_productOption;

  public Panel getSEM(File file) throws FileNotFoundException, TransformationException, FareInvalidConfigException,
      FunctionalException, FareSQLException, ParseException {
    InputStream urlInputStream = new FileInputStream(file);
    XmlToDataMapParser parser = new XmlToDataMapParser(false, false, false, true, false, false, true);
    DataMap parsedDM = parser.parser(urlInputStream);
    DataMap reply = parsedDM.getDataMap("Trace").getDataMap("Verb_1").getDataMap("Response").getDataMap("Body")
        .getDataMap("Fare_MasterPricerTravelBoardSearchReply");
    MockFunctionalResponse response = new MockFunctionalResponse(reply);
    initializeTestDataObjects();
    CommandContext mockCommandContext = new MockFareDrivenCommandContext(m_coreAvailityInput);
    createProd(mockCommandContext);

    MockFareDrivenResponse fareDrivenResponse = new MockFareDrivenResponse(
        createReplyStatusDM(MasterPricerResponseTags.PoweredLowestFare_TravelBoardSearchReply),
        MasterPricerResponseTags.PoweredLowestFare_TravelBoardSearchReply);
    fareDrivenResponse.setRequestId(0);
    FareDrivenAccessWrapperResponse fareDrivenAccessWrapperResponse = new FareDrivenAccessWrapperResponse(
        fareDrivenResponse);

    fareDrivenResponse.setResponse(response);
    FareDrivenPanelFiller panelFiller = new UnitTestableMasterPricerFiller(true);
    Panel panel = PanelHelper.createPanel(PanelTypeEnum.DETAILED);
    panelFiller.updateFromResponse(panel, fareDrivenAccessWrapperResponse, mockCommandContext);
    return panel;
  }

  private void initializeTestDataObjects() {
    numberOfTravellers = RandomUtils.nextInt(9) + 1;
    numberOfRequestedElements = RandomUtils.nextInt(3) + 1;
    globallyIncludedSuppliers = new String[] { "AF", "LH", "DL" };
    globallyPreferredSuppliers = new String[] {};
    globallyExcludedSuppliers = new String[] { "DH", "PK", "TT" };

    perElementIncludedSuppliers = new String[numberOfRequestedElements][0];
    perElementPreferredSuppliers = new String[numberOfRequestedElements][0];
    perElementExcludedSuppliers = new String[numberOfRequestedElements][0];

    // Core availability input stuff
    Traveller[] travellers = MockAvailabilityObjectsBuilder.createTravellers(numberOfTravellers);
    RequestedTransportationElement[] requestedElements = MockAvailabilityObjectsBuilder
        .createRequestedElements(numberOfRequestedElements);
    // Product preference stuff
    GlobalAvailabilityPreferenceAndRequestedElementSets globalAvailPrefAndReqElemSets = MockAvailabilityObjectsBuilder
        .createAvailabilityOptionsWithSupplierPreferences(requestedElements, globallyIncludedSuppliers,
            globallyPreferredSuppliers, globallyExcludedSuppliers, perElementIncludedSuppliers,
            perElementPreferredSuppliers, perElementExcludedSuppliers);
    FareSelection fareSelection = MockAvailabilityObjectsBuilder.createRandomFareSelection();
    PricingOption pricingOption = PricingOptionHelper.createPricingOption(fareSelection, null, null, "USD", "MUC",
        "MUC", "EUR");
    // Call to parent method, for creation of test data members of super class.
    setupMembers(travellers, requestedElements, globalAvailPrefAndReqElemSets.getM_globalAvailPreference(),
        globalAvailPrefAndReqElemSets.getM_requestedElementSets(), 100, pricingOption);
  }

  /** Initialize #m_coreAvailityInput #m_productOption members, given some generated finer-grained information. */
  protected final void setupMembers(Traveller[] travellers, RequestedTransportationElement[] requestedElements,
      GlobalAvailabilityPreference globalAvailabilityPreference, RequestedElementSet[] requestedElementSets,
      int maxNumRecomm, PricingOption pricingOption) {
    m_coreAvailityInput = AvailabilityInputHelper.createCoreAvailabilityInput(null, travellers, requestedElements,
        TripTypeEnum.COMPLEX_ITINERARY, null, DistanceUnitEnum.KILOMETERS);
    m_productOption = ProductPreferenceHelper.createProductPreference(null, ProductTypeEnum.STANDARD_AIR, null, false,
        new Integer(maxNumRecomm), globalAvailabilityPreference, requestedElementSets, null, pricingOption);
  }

  public static void createProd(CommandContext context) {
    MockSplitProductPreferenceHelper helper = new MockSplitProductPreferenceHelper();
    helper.setAFSubscriber();

    ErrorList errorList0 = new ErrorList();
    RequestMappingImpl requestMapping = new RequestMappingImpl();

    FareDrivenSingleRequestContext fdsrc0 = new FareDrivenSingleRequestContext(new Integer(0));
    fdsrc0.addProductPreferenceId(new Integer(0));
    fdsrc0.addFareGroupMapping("FG0", new Integer(0));
    fdsrc0.addErrorList(new Integer(0), errorList0);

    List requests0 = new ArrayList();
    requests0.add(fdsrc0);

    requestMapping.addSubGroup(new Integer(0), requests0);
    ((MockFareDrivenCommandContext)context).setRequestMapping(requestMapping);

    helper.updateRequestMapping(context);

  }

  public static DataMap createReplyStatusDM(String tag) {
    DataMap temp = new DataMap();
    DataMap dm = new DataMap(new LinkedHashMap());
    dm.put("advisorTypeInfo", "FQX");
    DataMap dm2 = new DataMap(new LinkedHashMap());
    dm2.put("status", dm);
    DataMap dm3 = new DataMap(new LinkedHashMap());
    dm3.put("replyStatus", dm2);
    temp.put(tag, dm3);
    return temp;
  }
}
