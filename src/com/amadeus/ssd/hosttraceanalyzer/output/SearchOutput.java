package com.amadeus.ssd.hosttraceanalyzer.output;

import java.util.ArrayList;
import java.util.List;

public class SearchOutput {
	List<OutputElement> output = new ArrayList<OutputElement>();
	
	public SearchOutput(){
		
	}
	

}


class OutputElement {
	String fileName;
	
	String msg;

	public OutputElement(String fileName, String msg) {
		super();
		this.fileName = fileName;
		this.msg = msg;
	}
	
	

}


