package com.amadeus.ssd.hosttraceanalyzer.input;

import java.util.ArrayList;
import java.util.List;

public class Element {
	int index;
	
	List <Segment> segments = new ArrayList<Segment>();

	public Element(int index, List<Segment> segments) {
		super();
		this.index = index;
		this.segments = segments;
	}

	public Element(int index){
		this.index = index;
	}
	
	
	public void addSegment(Segment segment){
		segments.add(segment);
	}

	@Override
	public String toString() {
		return "Element [index=" + index + ", segments=" + segments + "]";
	}
	
	
	
}
