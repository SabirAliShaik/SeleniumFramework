package APIRequests;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class RESTUtilities {

	public static String ENDPOINT;
	public static RequestSpecBuilder REQUEST_BUILDER;
	public static RequestSpecification REQUEST_SPEC;
	public static ResponseSpecBuilder RESPONSE_BUILDER;
	public static ResponseSpecification RESPONSE_SPEC;
	
	public static void setEndPoint(String endPoint) {
		ENDPOINT = endPoint;
	}
	
	public static RequestSpecification getRequestSpecification() {
		REQUEST_BUILDER = new RequestSpecBuilder();
		REQUEST_BUILDER.setBaseUri(Path.BASE_URI);
		REQUEST_BUILDER.setContentType(ContentType.JSON);
		
		REQUEST_SPEC = REQUEST_BUILDER.build();
		return REQUEST_SPEC;
	}

	public static ResponseSpecification getResponseSpecification() {
		
		RESPONSE_BUILDER = new ResponseSpecBuilder();
		RESPONSE_BUILDER.expectStatusCode(200);
		RESPONSE_BUILDER.expectContentType(ContentType.JSON);
		
		RESPONSE_SPEC = RESPONSE_BUILDER.build();
		return RESPONSE_SPEC;
	}

	public static RequestSpecification createQueryParam(RequestSpecification reqSpec, Map<String, String> queryMap) {
		return reqSpec.queryParams(queryMap);
	}

	public static RequestSpecification createPathParam(RequestSpecification reqSpec, String param, String value) {
		return reqSpec.pathParam(param, value);
	}

	public static Response getResponse() {
		return given().get(ENDPOINT);
	}

	public static Response getResponse(RequestSpecification reqSpec, String httpType, String payLoad) {
			
		REQUEST_SPEC.spec(reqSpec);
		Response response = null;
		
		if(httpType.equalsIgnoreCase("get")) {
			response = given().spec(reqSpec).get(ENDPOINT);
		}
		else if(httpType.equalsIgnoreCase("Post")) {
			response = given().spec(reqSpec).body(payLoad).post(Path.BASE_PATH);
		}
		else if(httpType.equalsIgnoreCase("put")) {
			response = given().spec(reqSpec).put(ENDPOINT);
		}
		else if(httpType.equalsIgnoreCase("delete")) {
			response = given().spec(reqSpec).delete(ENDPOINT);
		}
		else {
			System.out.println("HTTP Type is not supported");
		}
		return response;
	}

	public static JsonPath getJsonPath(Response response) {
		
		JsonPath js = new JsonPath(response.asString());
		return js;		
	}

	public static String getJsonPath(Response response, String key) {
		JsonPath js = new JsonPath(response.asString());
		return js.get(key).toString();
		
	}

	public static void resetBasePath() {
		RestAssured.basePath = null;
	}
	
	public static void setContentType(ContentType type) {
		given().contentType(type);
	}
}
