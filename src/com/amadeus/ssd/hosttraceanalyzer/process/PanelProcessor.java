package com.amadeus.ssd.hosttraceanalyzer.process;

import java.util.List;

import com.amadeus.ocg.objectmodel.air.AirElementAvailabilityContext;
import com.amadeus.ocg.objectmodel.common.journey.TransportationSegment;
import com.amadeus.ocg.objectmodel.common.journey.availability.AvailabilityElementInfo;
import com.amadeus.ocg.objectmodel.common.journey.availability.AvailabilitySegmentInfo;
import com.amadeus.ocg.objectmodel.common.journey.availability.CombiningAvailabilitySolution;
import com.amadeus.ocg.objectmodel.common.journey.availability.FareDrivenAvailabilityProposal;
import com.amadeus.ocg.objectmodel.common.journey.availability.SingleAvailabilitySolution;
import com.amadeus.ocg.objectmodel.middleware.response.DayProposal;
import com.amadeus.ocg.objectmodel.middleware.response.Panel;
import com.amadeus.ssd.hosttraceanalyzer.input.Element;

public class PanelProcessor {

	

	  public static boolean findRecommendation(Panel panel, List <Element> elments) {
		  
		  String[] flghtsNumber = null;
		  String[] rbds = null;
		  

	    for (int i1 = 0; i1 < panel.getTabsSize(); i1++) {
	      DayProposal dayProposal = panel.getTabAt(i1);

	      for (int i2 = 0; i2 < dayProposal.getProposalsSize(); i2++) {
	        FareDrivenAvailabilityProposal proposal = (FareDrivenAvailabilityProposal)dayProposal.getProposalAt(i2);
	        CombiningAvailabilitySolution combine = (CombiningAvailabilitySolution)proposal.getSolution();

//	        PricingSolution pricing = (PricingSolution)proposal.getPricingAt(0);
//	        com.amadeus.ocg.objectmodel.common.ticket.Ticket ticket = (Ticket)pricing.getPricingBreakdownAt(0).getTravellerPricingAt(0);
//	        boolean corporateCodeMatched = false;
	//
//	        for (int j1 = 0; j1 < ticket.getFareSegmentInfosSize(); j1++) {
//	          AirFareSegmentPricingInfo pricingInfo = (AirFareSegmentPricingInfo)ticket.getFareSegmentInfoAt(0).getPricingInfo();
//	          String fareType = pricingInfo.getFareType().getCode();
//	          if(fareType.equals("RP")){
//	            return false;
//	          }
//	        }

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

	              return true;
	            }

	          }

	        }
	      }
	    }

	    return false;
	  }

}
