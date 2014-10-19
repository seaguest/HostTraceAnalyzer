package com.amadeus.ssd.hosttraceanalyzer.input;

public class Segment {
	int index;
	
	String airlineCode;
	int flightNumber;
	String rbd;
	String corporateCode;
	
	public Segment(int index, String airlineCode, int flightNumber, String rbd,
			String corporateCode) {
		super();
		this.index = index;
		this.airlineCode = airlineCode;
		this.flightNumber = flightNumber;
		this.rbd = rbd;
		this.corporateCode = corporateCode;
	}	
	
	
	public Segment(int index, String airlineCodeAndNumber, String rbd,
			String corporateCode) {
		super();
		this.index = index;
		this.airlineCode = airlineCodeAndNumber.substring(0, 2) ;
		String number = airlineCodeAndNumber.substring(2);
		this.flightNumber = Integer.parseInt(number);		
		this.flightNumber = flightNumber;
		this.rbd = rbd;
		this.corporateCode = corporateCode;
	}	
	

	@Override
	public String toString() {
		return "Segment [index=" + index + ", airlineCode=" + airlineCode
				+ ", flightNumber=" + flightNumber + ", rbd=" + rbd
				+ ", corporateCode=" + corporateCode + "]";
	}


	public static void main(String[] args){
		Segment fl = new Segment(1, "CX555", "sds", "0123");
		
		System.out.println(fl);
	}
	
}
