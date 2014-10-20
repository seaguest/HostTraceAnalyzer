package com.amadeus.ssd.hosttraceanalyzer.process;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XPathFileFilter {
  XPath xpath;
  DocumentBuilder builder;

  public XPathFileFilter() throws ParserConfigurationException {
    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
    domFactory.setNamespaceAware(true);
    builder = domFactory.newDocumentBuilder();
    xpath = XPathFactory.newInstance().newXPath();
  }

  public boolean fiterOut(File file, String xpathExpression) throws SAXException, IOException, XPathExpressionException {
    Document doc = builder.parse(file.getCanonicalPath());
    // XPath Query for showing all nodes value
    XPathExpression expr = xpath.compile(normalizeXPathWithNameSpace(xpathExpression));
    Object result = expr.evaluate(doc, XPathConstants.BOOLEAN);
    return (Boolean)result;
  }

  public String normalizeXPathWithNameSpace(String xpath) {
    String result = "/";
    int index = xpath.indexOf("=");
    String expression = xpath.substring(0, index);
    String condition = xpath.substring(index);
    String[] list = expression.split("/");
    for (int i = 0; i < list.length; i++) {
      if (list[i] != null && !list[i].equals("")) {
        result += "/*[local-name()='" + list[i] + "']";
      }
    }
    result += condition;
    return result;
  }

}
