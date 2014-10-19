package com.amadeus.ssd.hosttraceanalyzer.input;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {
	
	boolean isOneShot;
	
	List <Element> elements = new ArrayList<Element>();

	FilteringCondition condition;

	public SearchCriteria(boolean isOneShot, List<Element> elements, FilteringCondition condition) {
		super();
		this.isOneShot = isOneShot;
		this.elements = elements;
		this.condition = condition;
	}
	
	public SearchCriteria() {
		super();
	}
	
	public void addElement(Element element){
		elements.add(element);
	}

	@Override
	public String toString() {
		return "SearchCriteria [isOneShot=" + isOneShot + ", elements="
				+ elements + ", condition=" + condition + "]";
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public FilteringCondition getCondition() {
		return condition;
	}

	public void setCondition(FilteringCondition condition) {
		this.condition = condition;
	}

	public boolean isOneShot() {
		return isOneShot;
	}

	public void setOneShot(boolean isOneShot) {
		this.isOneShot = isOneShot;
	}

	
	
	
	
}
