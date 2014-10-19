package com.amadeus.ssd.hosttraceanalyzer.input;

import java.util.ArrayList;
import java.util.List;

public class FilteringCondition {

	String rqwData;
	
	List<String> xpaths = new ArrayList<String>();

	public FilteringCondition(String rqwData0){
		rqwData = rqwData0;
	}

	@Override
	public String toString() {
		return "FilteringCondition [rqwData=" + rqwData + ", xpaths=" + xpaths
				+ "]";
	}
	
	
	

}
