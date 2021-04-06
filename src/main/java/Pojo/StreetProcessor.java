package Pojo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Utilities.CommonHelpers;
import io.restassured.response.Response;

public class StreetProcessor {

	static List<StreetProcessorRequestPojo> requestPojo = null;
	static List<StreetProcessorResponsePojo> responsePojo = null;
	String inputJsonString = null;
	
	public String readJsonRequest() {		
		String path = "src/main/resources/Json/"+getJsonFileName()+".txt";
		String fileString = CommonHelpers.readNotepadContent(path);
		setInputJsonAsPojo(fileString);
		return fileString;
	}
	
	public String getJsonFileName() {
		Properties pro = CommonHelpers.readPropertiesFile("suite.properties");
		return pro.getProperty("WebServiceJsonFileName");
	}
	
	@SuppressWarnings("unchecked")
	public void setInputJsonAsPojo(String inputJson) {
		Type collectionType = new TypeToken<List<StreetProcessorRequestPojo>>() {
		}.getType();
		requestPojo = (List<StreetProcessorRequestPojo>) new Gson().fromJson(inputJson, collectionType);
	}
	
	@SuppressWarnings({ "unchecked" })
	private void setResponseJsonAsPojo(Response responseJson) {
		String responseString = responseJson.body().asString();
		Type collectionType = new TypeToken<List<StreetProcessorResponsePojo>>() {
		}.getType();
		responsePojo = (List<StreetProcessorResponsePojo>) new Gson().fromJson(responseString, collectionType);
	}
	
	public void sendResponseJson(Response reponseData) {
		setResponseJsonAsPojo(reponseData);
	}
	
	public boolean validateFirmId() {
		return(Objects.equals(requestPojo.get(0).getFirmId(), responsePojo.get(0).getFirmId()));
	}
	
	public static void convertJsonRequestToList(String transactionName) {
		
		HashMap<String, Object> transactionTable = new HashMap<String, Object>();
		transactionTable.put("FIRM_ID", requestPojo.get(0).getFirmId());
		//Entering entire request to hashMap and validating with DB in verifyTransaction method
	}
}
