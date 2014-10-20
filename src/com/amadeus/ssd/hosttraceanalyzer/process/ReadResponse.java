package com.amadeus.ssd.hosttraceanalyzer.process;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import com.amadeus.ocg.objectmodel.air.AirElementAvailabilityContext;
import com.amadeus.ocg.objectmodel.common.journey.TransportationSegment;
import com.amadeus.ocg.objectmodel.common.journey.availability.AvailabilityElementInfo;
import com.amadeus.ocg.objectmodel.common.journey.availability.AvailabilitySegmentInfo;
import com.amadeus.ocg.objectmodel.common.journey.availability.CombiningAvailabilitySolution;
import com.amadeus.ocg.objectmodel.common.journey.availability.FareDrivenAvailabilityProposal;
import com.amadeus.ocg.objectmodel.common.journey.availability.SingleAvailabilitySolution;
import com.amadeus.ocg.objectmodel.middleware.response.DayProposal;
import com.amadeus.ocg.objectmodel.middleware.response.Panel;
import com.amadeus.ocg.standard.access.FunctionalException;
import com.amadeus.ocg.standard.business.farecommon.FareInvalidConfigException;
import com.amadeus.ocg.standard.business.farecommon.FareSQLException;
import com.amadeus.ocg.util.xml.TransformationException;

public class ReadResponse {

  private static java.util.logging.Logger s_logger;

  /**
   * @param args
   * @throws IOException
   * @throws TransformationException
   * @throws FareInvalidConfigException
   * @throws ParseException
   * @throws FareSQLException
   * @throws FunctionalException
   */
  public static void main(String[] args) throws TransformationException, IOException, FareInvalidConfigException,
      FareSQLException, ParseException, FunctionalException {

    s_logger = java.util.logging.Logger.getLogger("MyLog");
    FileHandler fh = new FileHandler("server.log");
    s_logger.addHandler(fh);
    SimpleFormatter formatter = new SimpleFormatter();
    fh.setFormatter(formatter);


    ReadResponse reader = new ReadResponse();

    File dir = new File("./resource");
    File[] files = dir.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".xml") && name.contains("Amadeus");
      }
    });

    for (File file : files) {
      System.out.println(file);
    }

    for (File file : files) {

      String[] flighs = { "3091", "1465", "1263" };
      String[] rbds = { "M", "M", "M" };

      String[] flighs1 = { "1260", "448" };
      String[] rbds1 = { "Y", "T" };

      XMLFileToSEMConverter converter = new XMLFileToSEMConverter();
      Panel panel = converter.getSEM(file);
      boolean recommendationFound1 = findRecommendation(panel, flighs, rbds);
      boolean recommendationFound2 = findRecommendation(panel, flighs1, rbds1);

      if(recommendationFound1){
        s_logger.info(file.getName());
        System.out.println(file.getCanonicalPath());
      }

    }
  }

  public static boolean findRecommendation(Panel panel, String[] flghtsNumber, String[] rbds) {

    for (int i1 = 0; i1 < panel.getTabsSize(); i1++) {
      DayProposal dayProposal = panel.getTabAt(i1);

      for (int i2 = 0; i2 < dayProposal.getProposalsSize(); i2++) {
        FareDrivenAvailabilityProposal proposal = (FareDrivenAvailabilityProposal)dayProposal.getProposalAt(i2);
        CombiningAvailabilitySolution combine = (CombiningAvailabilitySolution)proposal.getSolution();

//        PricingSolution pricing = (PricingSolution)proposal.getPricingAt(0);
//        com.amadeus.ocg.objectmodel.common.ticket.Ticket ticket = (Ticket)pricing.getPricingBreakdownAt(0).getTravellerPricingAt(0);
//        boolean corporateCodeMatched = false;
//
//        for (int j1 = 0; j1 < ticket.getFareSegmentInfosSize(); j1++) {
//          AirFareSegmentPricingInfo pricingInfo = (AirFareSegmentPricingInfo)ticket.getFareSegmentInfoAt(0).getPricingInfo();
//          String fareType = pricingInfo.getFareType().getCode();
//          if(fareType.equals("RP")){
//            return false;
//          }
//        }

        for (int i3 = 0; i3 < combine.getCombinationsSize(); i3++) {
          SingleAvailabilitySolution singleSolution = combine.getCombinationAt(i3);

          for (int i4 = 0; i4 < singleSolution.getElementsSize(); i4++) {
            AvailabilityElementInfo elementInfo = singleSolution.getElementAt(i4);
            boolean matched = true;

            AirElementAvailabilityContext availContect = (AirElementAvailabilityContext)elementInfo
                .getAvailabilityContextAt(0);

            if(elementInfo.getSegmentsSize() == flghtsNumber.length){
              for (int i = 0; i < elementInfo.getSegmentsSize(); i++) {
                AvailabilitySegmentInfo segment = elementInfo.getSegmentAt(i);
                TransportationSegment transportationSegment = (TransportationSegment)segment.getSegment();

                if (transportationSegment.getTransportNumber().equals(flghtsNumber[i]) &&
                    availContect.getRbdCodeAt(i).equals(rbds[i])) {
                  matched &= true;
                }
                else {
                  matched = false;
                  break;
                }
              }
            }else{
              matched = false;
            }

            if (matched) {
              String msg = "Panel.Tab[" + i1 + "]" + ".Proposal[" + i2 + "]" + ".Combination[" + i3 + "]" + ".Element[" + i4 + "]";
              System.out.println(msg);
              s_logger.info(msg);
              return true;
            }

          }

        }
      }
    }

    return false;
  }


}
