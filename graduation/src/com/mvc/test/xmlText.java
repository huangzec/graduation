package com.mvc.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class xmlText {

	@Test
	public void text() throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder 	= factory.newDocumentBuilder();
		System.out.println("come in ");
		Document document 			= builder.parse("src/config/text.xml");
		NodeList list 	= document.getElementsByTagName("bean");
		Node node 	= list.item(1);
		String content 	= node.getTextContent();
		System.out.println("content " + content);
		System.out.println(document + " douc  " + document.getElementsByTagName("bean"));
		
	}
}
