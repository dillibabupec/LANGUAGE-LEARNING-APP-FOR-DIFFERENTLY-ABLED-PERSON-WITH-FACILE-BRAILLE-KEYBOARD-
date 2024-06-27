package com.example.languagelearn;

import java.io.IOException;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class CallService {
	
	private static String NAMESPACE="http://blind/";
	private static String URL="http://cd4e49ec.ngrok.io/BlindService/NewWebService?WSDL";
	
	public static String registerService(String username,String type,String method) {
		String restex=null;
		SoapObject soap=new SoapObject(NAMESPACE, method);
		soap.addProperty("username",username);
		soap.addProperty("type",type);
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http=new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
			restex=primitive.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return "error";
			
		}
		
		return restex;
	}

	public static String languageService(String username,String language,String method) {
		String restex=null;
		SoapObject soap=new SoapObject(NAMESPACE, method);
		soap.addProperty("username",username);
		soap.addProperty("language",language);
		SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http=new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
			restex=primitive.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return "error";
			
		}
		
		return restex;
	}
	
	public static String getDeviceList1(String tablename) {
		String list=null;
		String method = tablename;
		String report = "report";
		//String userid1="userid";
		SoapObject soap = new SoapObject(NAMESPACE, method);
		//SoapSerializationEnvelope envelope = null;
		soap.addProperty("report", report);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soap);
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			http.call(NAMESPACE+method, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			list = primitive.toString();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (XmlPullParserException e) {			
			e.printStackTrace();
		}
		return list;
	}
}
