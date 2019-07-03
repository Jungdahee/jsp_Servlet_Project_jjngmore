package com.webinfo.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AddCertiSchedule {

	public void addInfo() throws IOException, Exception {

		String seriesCode = "04";

		String param = "";
		
		//기술사: getPEList, 기능장: getMCList, 기사: getEList, 기능사: getCList
		String addr = "http://openapi.q-net.or.kr/api/service/rest/InquiryTestInformationNTQSVC/getCList?ServiceKey=";
		String serviceKey = "LzJ73NnpJ9i8FwimSqcbaJpLp6x9nN4TCDnDBSPhf8TEA05I5fi6G%2FIhjRdbQcD5FZ%2FH778Vpm4vE%2F9OTB6D6Q%3D%3D";
		addr = addr + serviceKey + param;

		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(addr);

		System.out.println(doc);

		Element eRoot = doc.getDocumentElement();
		System.out.println("Root element :: " + eRoot.getNodeName());

		NodeList nList = doc.getElementsByTagName("item");
		System.out.println("파싱할 리스트 수 :: "+ nList.getLength());

		for(int i = 0; i < nList.getLength(); i++){
			Node nNode = nList.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){

				Element eElement = (Element) nNode;

				NodeList descriptionInfo = eElement.getElementsByTagName("description").item(0).getChildNodes();
				Node descriptionData = (Node) descriptionInfo.item(0);
				String description  = descriptionData.getNodeValue();
				System.out.println("description :: " + description);

				NodeList docregstartdtInfo = eElement.getElementsByTagName("docregstartdt").item(0).getChildNodes();
				Node docregstartdt = (Node) docregstartdtInfo.item(0);
				String docRegStart  = docregstartdt.getNodeValue();

				NodeList docregenddtInfo = eElement.getElementsByTagName("docregenddt").item(0).getChildNodes();
				Node docregenddt = (Node) docregenddtInfo.item(0);
				String docRegEnd  = docregenddt.getNodeValue();

				NodeList docexamdtInfo = eElement.getElementsByTagName("docexamdt").item(0).getChildNodes();
				Node docexamdt = (Node) docexamdtInfo.item(0);
				String docExam  = docexamdt.getNodeValue();

				NodeList docpassdtInfo = eElement.getElementsByTagName("docpassdt").item(0).getChildNodes();
				Node docpassdt = (Node) docpassdtInfo.item(0);
				String docPass  = docpassdt.getNodeValue();

				NodeList pracregstartdtInfo = eElement.getElementsByTagName("pracregstartdt").item(0).getChildNodes();
				Node pracregstartdt = (Node) pracregstartdtInfo.item(0);
				String pracRegStart  = pracregstartdt.getNodeValue();

				NodeList pracregenddtInfo = eElement.getElementsByTagName("pracregenddt").item(0).getChildNodes();
				Node pracregenddt = (Node) pracregenddtInfo.item(0);
				String pracRegEnd  = pracregenddt.getNodeValue();

				NodeList pracexamstartdtInfo = eElement.getElementsByTagName("pracexamstartdt").item(0).getChildNodes();
				Node pracexamstartdt = (Node) pracexamstartdtInfo.item(0);
				String pracExamStart  = pracexamstartdt.getNodeValue();

				NodeList pracexamenddtInfo = eElement.getElementsByTagName("pracexamenddt").item(0).getChildNodes();
				Node pracexamenddt = (Node) pracexamenddtInfo.item(0);
				String pracExamEnd  = pracexamenddt.getNodeValue();

				NodeList pracpassdtInfo = eElement.getElementsByTagName("pracpassdt").item(0).getChildNodes();
				Node pracpassdt = (Node) pracpassdtInfo.item(0);
				String passEnd  = pracpassdt.getNodeValue();

				//DB연결해서 데이터 insert
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("드라이버 검색 OK");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jjngmore", "root", "qkqh3635^^");				
				System.out.println("접속 성공");

				PreparedStatement preparedStatement = conn.prepareStatement("insert into schedule values(?,?,?,?,?,?,?,?,?,?,?)"); 

				preparedStatement.setString(1, seriesCode);
				preparedStatement.setString(2, description);
				preparedStatement.setString(3, docRegStart);
				preparedStatement.setString(4, docRegEnd);
				preparedStatement.setString(5, docExam);
				preparedStatement.setString(6, docPass);
				preparedStatement.setString(7, pracRegStart);
				preparedStatement.setString(8, pracRegEnd);
				preparedStatement.setString(9, pracExamStart);
				preparedStatement.setString(10, pracExamEnd);
				preparedStatement.setString(11, passEnd);

				preparedStatement.executeUpdate();
				preparedStatement.close(); 

				conn.close();

			}
		}
	}

} 


