package com.amadeus.ssd.hosttraceanalyzer.input;

public class Segment {
	int index;
	
	String airlineCode;
	int flightNumber;
	String rbd;
	String corporateCode;
	String fareBasis;
	
	public Segment(int index, String airlineCode, int flightNumber, String rbd,
			String corporateCode, String fareBasis) {
		super();
		this.index = index;
		this.airlineCode = airlineCode;
		this.flightNumber = flightNumber;
		this.rbd = rbd;
		this.corporateCode = corporateCode;
		this.fareBasis = fareBasis;
	}	
	
	
	public Segment(int index, String airlineCodeAndNumber, String rbd,
			String corporateCode, String fareBasis) {
		super();
		this.index = index;
		this.airlineCode = airlineCodeAndNumber.substring(0, 2) ;
		String number = airlineCodeAndNumber.substring(2);
		this.flightNumber = Integer.parseInt(number);		
		this.rbd = rbd;
		this.corporateCode = corporateCode;
		this.fareBasis = fareBasis;
	}	

	@Override
	public String toString() {
		return "Segment [index=" + index + ", airlineCode=" + airlineCode
				+ ", flightNumber=" + flightNumber + ", rbd=" + rbd
				+ ", corporateCode=" + corporateCode + ", fareBasis="
				+ fareBasis + "]";
	}


	public static void main(String[] args){
		Segment fl = new Segment(1, "CX555", "sds", "0123", "ABCDED");
		
		System.out.println(fl);
	}
	
}
