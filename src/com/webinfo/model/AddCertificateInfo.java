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

public class AddCertificateInfo {

	public void addInfo() throws IOException, Exception {

		String jmCode = "";

		String param = "";

		String addr = "http://openapi.q-net.or.kr/api/service/rest/InquiryListNationalQualifcationSVC/getList?ServiceKey=";
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

				NodeList qualgbcd = eElement.getElementsByTagName("qualgbcd").item(0).getChildNodes();
				Node qualgbcdInfo = (Node) qualgbcd.item(0);
				String qualgbCode = qualgbcdInfo.getNodeValue();

				NodeList qualgbnm = eElement.getElementsByTagName("qualgbnm").item(0).getChildNodes();
				Node qualgbnmInfo = (Node) qualgbnm.item(0);
				String qualgbName = qualgbnmInfo.getNodeValue();

				NodeList seriescd = eElement.getElementsByTagName("seriescd").item(0).getChildNodes();
				Node seriescdInfo = (Node) seriescd.item(0);
				String seriesCode = seriescdInfo.getNodeValue();

				NodeList seriesnm = eElement.getElementsByTagName("seriesnm").item(0).getChildNodes();
				Node seriesnmInfo = (Node) seriesnm.item(0);
				String seriesName = seriesnmInfo.getNodeValue();

				NodeList jmcd = eElement.getElementsByTagName("jmcd").item(0).getChildNodes();
				Node jmcdInfo = (Node) jmcd.item(0);
				jmCode = jmcdInfo.getNodeValue();

				NodeList jmfldnm = eElement.getElementsByTagName("jmfldnm").item(0).getChildNodes();
				Node jmfldnmInfo = (Node) jmfldnm.item(0);
				String jmName = jmfldnmInfo.getNodeValue();

				NodeList obligfldcd = eElement.getElementsByTagName("obligfldcd").item(0).getChildNodes();
				Node obligfldcdInfo = (Node) obligfldcd.item(0);
				String obligfldCode = obligfldcdInfo.getNodeValue();

				NodeList obligfldnm = eElement.getElementsByTagName("obligfldnm").item(0).getChildNodes();
				Node obligfldnmInfo = (Node) obligfldnm.item(0);
				String obligfldName = obligfldnmInfo.getNodeValue();

				NodeList mdobligfldcd = eElement.getElementsByTagName("mdobligfldcd").item(0).getChildNodes();
				Node mdobligfldInfo = (Node) mdobligfldcd.item(0);
				String mdobligfldCode = mdobligfldInfo.getNodeValue();

				NodeList mdobligfldnm = eElement.getElementsByTagName("mdobligfldnm").item(0).getChildNodes();
				Node mdobligfldnmInfo = (Node) mdobligfldnm.item(0);
				String mdobligfldName = mdobligfldnmInfo.getNodeValue();


				System.out.println("qualgbCode :: " + qualgbCode);
				System.out.println("jmName :: " + jmName);
				System.out.println("mdobligfldName :: " + mdobligfldName);

				//DB연결해서 데이터 insert
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("드라이버 검색 OK");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jjngmore", "root", "qkqh3635^^");				
				System.out.println("접속 성공");
				
				PreparedStatement preparedStatement = conn.prepareStatement("insert into certificate(qualiCode,qualiName,seriesCode,seriesName,certiCode,certiName,firstSecCode, firstSecName,secondSecCode,secondSecName) value(?,?,?,?,?,?,?,?,?,?)"); 
				
				preparedStatement.setString(1, qualgbCode);
				preparedStatement.setString(2, qualgbName);
				preparedStatement.setString(3, seriesCode);
				preparedStatement.setString(4, seriesName);
				preparedStatement.setString(5, jmCode);
				preparedStatement.setString(6, jmName);
				preparedStatement.setString(7, obligfldCode);
				preparedStatement.setString(8, obligfldName);
				preparedStatement.setString(9, mdobligfldCode);
				preparedStatement.setString(10, mdobligfldName);
				
				preparedStatement.executeUpdate();
				preparedStatement.close(); 
				
				conn.close();
			} 

		}

	}

}

