package com.amadeus.ssd.hosttraceanalyzer.input;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {
	
	List <Element> elements = new ArrayList<Element>();

	FilteringCondition condition;

	public SearchCriteria(List<Element> elements, FilteringCondition condition) {
		super();
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
		return "SearchCriteria [elements=" + elements + ", condition="
				+ condition + "]";
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

	
	
	
}
