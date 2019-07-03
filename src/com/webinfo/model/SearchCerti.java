package com.webinfo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SearchCerti {

	public String search(String certiCode) throws Exception {

		String addr = "http://openapi.q-net.or.kr/api/service/rest/InquiryInformationTradeNTQSVC/getList?jmCd=";
		String jmCd = certiCode;
		String serviceKey = "&ServiceKey=LzJ73NnpJ9i8FwimSqcbaJpLp6x9nN4TCDnDBSPhf8TEA05I5fi6G%2FIhjRdbQcD5FZ%2FH778Vpm4vE%2F9OTB6D6Q%3D%3D";
		addr = addr + jmCd + serviceKey;
		
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(addr);

		System.out.println(doc);
		
		Element eRoot = doc.getDocumentElement();
		System.out.println("Root element :: " + eRoot.getNodeName());

		NodeList nList = doc.getElementsByTagName("item");
		System.out.println("파싱할 리스트 수 :: "+ nList.getLength());
		
		String contents = "";

		for(int i = 0; i < nList.getLength(); i++){
			Node nNode = nList.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){

				Element eElement = (Element) nNode;

				NodeList contentsData = eElement.getElementsByTagName("contents").item(0).getChildNodes();
				Node contentsStr = (Node) contentsData.item(0);
				
				System.out.println("contentsStr :: " + contentsStr.getNodeValue());
				contents += contentsStr.getNodeValue();

			} 

		}
		return contents;

	}
	
}
