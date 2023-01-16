package com.kongstore.kongstore.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@CrossOrigin(value = "*")
public class Kongapicontroller {
	@Value("${adminurl}")
	private String adminurl;

	@Value("${kongurl}")
	private String kongurl;

	@GetMapping("/api/getallservice") // using for getting all services in kong
	public ResponseEntity<?> getAllservics() {
		JSONObject responseJson = new JSONObject();
		String url = adminurl + "/services";
		WebClient webClient = WebClient.create("http://192.168.11.62:9090");// getting response of api with web client
		ResponseEntity<String> data = webClient.get().uri(url).exchange().flatMap(r -> r.toEntity(String.class))
				.block();

//		System.out.println("data=================================================" + data.getBody());
		Object json = new JSONTokener(data.getBody()).nextValue();

//		System.out.println("this is from response body ++++++++++++++++++++>" + json);
		JSONObject responseBody = new JSONObject(data.getBody());

		responseJson.put("dataResponse", responseBody);
//		System.out.println("this is from response body ++++++++++++++++++++>" + responseBody);

//		System.out.println("response body ==========================>" + responseBody);

		responseJson.put("status", "Success");
		responseJson.put("statusCode", 101);
		responseJson.put("message", "Request Completed Successfully.");
		System.out.println("the response json is here -------------->" + responseJson);

		return new ResponseEntity<>(data.getBody(), HttpStatus.OK);

	}

	@GetMapping("/api/getsingleservice/{id}")
	public ResponseEntity<?> getAllSingleservice(@PathVariable String id) {
		JSONObject responseJson = new JSONObject();
		String url = adminurl + "/services" + "/" + id;
		WebClient webClient = WebClient.create("http://192.168.11.62:9090");
		ResponseEntity<String> data = webClient.get().uri(url).exchange().flatMap(r -> r.toEntity(String.class))
				.block();

		System.out.println("data=================================================" + data.getBody());
		Object json = new JSONTokener(data.getBody()).nextValue();

		System.out.println("this is from response body ++++++++++++++++++++>" + json);
		JSONObject responseBody = new JSONObject(data.getBody());

		responseJson.put("dataResponse", responseBody);
		responseJson.put("status", "Success");
		responseJson.put("statusCode", 101);
		responseJson.put("message", "Request Completed Successfully.");
		System.out.println("the response json is here -------------->" + responseJson);

		return new ResponseEntity<>(data.getBody(), HttpStatus.OK);

	}

	@GetMapping("/api/getallroutes")
	public ResponseEntity<?> getAllRoutes() {
		JSONObject responseJson = new JSONObject();
		String url = adminurl + "/routes";
		WebClient webClient = WebClient.create("http://192.168.11.62:9090");
		ResponseEntity<String> data = webClient.get().uri(url).exchange().flatMap(r -> r.toEntity(String.class))
				.block();

		System.out.println("data=================================================" + data.getBody());
		Object json = new JSONTokener(data.getBody()).nextValue();

		System.out.println("this is from response body ++++++++++++++++++++>" + json);
		JSONObject responseBody = new JSONObject(data.getBody());

		responseJson.put("dataResponse", responseBody);
		System.out.println("this is from response body ++++++++++++++++++++>" + responseBody);

		System.out.println("response body ==========================>" + responseBody);

		responseJson.put("status", "Success");
		responseJson.put("statusCode", 101);
		responseJson.put("message", "Request Completed Successfully.");
		System.out.println("the response json is here -------------->" + responseJson);

		return new ResponseEntity<>(data.getBody(), HttpStatus.OK);

	}

	@GetMapping("/api/getsingleroute/{id}")
	public ResponseEntity<?> getSingleRoute(@PathVariable String id) {
		JSONObject responseJson = new JSONObject();

		String url = adminurl + "/services/" + id + "/routes";
		WebClient webClient = WebClient.create("http://192.168.11.62:9090");
		ResponseEntity<String> data = webClient.get().uri(url).exchange().flatMap(r -> r.toEntity(String.class))
				.block();

		System.out.println("data=================================================" + data.getBody());
		Object json = new JSONTokener(data.getBody()).nextValue();

		System.out.println("this is from response body ++++++++++++++++++++>" + json);
		JSONObject responseBody = new JSONObject(data.getBody());

		responseJson.put("dataResponse", responseBody);
		System.out.println("this is from response body ++++++++++++++++++++>" + responseBody);

		System.out.println("response body ==========================>" + responseBody);

		responseJson.put("status", "Success");
		responseJson.put("statusCode", 101);
		responseJson.put("message", "Request Completed Successfully.");
		System.out.println("the response json is here -------------->" + responseJson);

		return new ResponseEntity<>(data.getBody(), HttpStatus.OK);

	}

	@GetMapping("/api/targeturls/{id}")
	public Map<String, Object> getTargetUrls(@PathVariable String id) {
		JSONObject responseJson = new JSONObject();
		String url = adminurl + "/services/" + id + "/routes";
		WebClient webClient = WebClient.create("http://192.168.11.62:9090");
		ResponseEntity<String> data = webClient.get().uri(url).exchange().flatMap(r -> r.toEntity(String.class))
				.block();

		System.out.println("data=========================>" + data.getBody());
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray list = new JSONArray();
		JSONArray paths = new JSONArray();
		JSONArray methods = new JSONArray();
		map.put("data", data.getBody());

		System.out.println("map---------->" + map);
		System.out.println("map---------->" + map.get("data"));

		JSONObject responseBody = new JSONObject(data.getBody());
		System.out.println("map---------->" + responseBody.get("data"));

		list = (JSONArray) responseBody.get("data");

		JSONObject route = new JSONObject();
		String targetUrl = null;
		List<Map<String, Object>> targetUrls = new ArrayList<>();
		for (int i = 0; i < list.length(); i++) {

			route = (JSONObject) list.get(i);

			System.out.println("route----->" + route.get("paths"));
			System.out.println("methods----->" + route.get("methods"));
			methods = (JSONArray) route.get("methods");
			paths = (JSONArray) route.get("paths");
			System.out.println("paths.length()" + paths.length());

			Map<String, Object> urlAndMethod = new HashMap<String, Object>();

			for (int j = 0; j < paths.length(); j++) {

				String path = (String) paths.get(j);
				System.out.println("path----->" + path);
				targetUrl = kongurl + path;
				urlAndMethod.put("targetURl", targetUrl);

			}
			for (int l = 0; l < methods.length(); l++) {

				String method = (String) methods.get(l);
				System.out.println("path----->" + method);
				urlAndMethod.put("method", method);

			}
			targetUrls.add(urlAndMethod);
			System.out.println("targetUrl----->" + targetUrl);

		}
		Map<String, Object> targeturlsmap = new HashMap<>();
		targeturlsmap.put("targetUrl", targetUrls);

		responseJson.put("dataResponse", responseBody);

		return targeturlsmap;

	}

//	@PostMapping("/api/targeturls")
//	public Map<String, Object> callUrl(@RequestParam(required = false) String url,
//			@RequestParam(required = false) String method,
//			@RequestParam(required = false) Map<String, Object> requestData, @RequestBody Map<String, Object> payload) {
//		System.out.println("The Playload===>>" + payload);
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		RestTemplate restTemplate = new RestTemplate();
//		WebClient webClient = WebClient.create("http://192.168.11.62:9090");
//
//		if (method.equalsIgnoreCase("GET")) {
//
//			ResponseEntity<String> data = webClient.get().uri(url).exchange().flatMap(r -> r.toEntity(String.class))
//					.block();
//
//			System.out.println("data------>" + data.getBody());
//			map.put("Data", data.getBody());
//		} else if (method.equalsIgnoreCase("POST")) {
//			System.out.println("request data ------------>" + requestData);
//			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(payload);
//
//			Mono<String> data = webClient.post().uri(url).body(Mono.just(payload), Map.class).retrieve()
//					.bodyToMono(String.class);
//			String resp = data.block();
//			map.put("Data", resp);
//		}
//
//		return map;
//
//	}

	@PostMapping("/api/targeturls")
	public ResponseEntity<?> callUrl(@RequestParam(required = false) String url,
			@RequestParam(required = false) String method, @RequestBody(required = false) Map<String, Object> payload) {
//		insertIntoSwagger();

		Map<String, Object> map = new HashMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();

		if (method.equalsIgnoreCase("GET")) {
			System.out.println("inside get loop");
			org.springframework.http.ResponseEntity<String> data = restTemplate.getForEntity(URI.create(url),
					String.class);
			System.out.println("data------>" + data.getBody());
			map.put("Data", data.getBody());
			map.put("statusCode", data.getStatusCode());
			map.put("statusCode", data);
			map.put("Headers", data.getHeaders());
			return new ResponseEntity<>(data, HttpStatus.OK);
		} else if (method.equalsIgnoreCase("POST")) {
			System.out.println("The Playload here of param ===>>" + payload);

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(payload);
			System.out.println("requestEntity--------->" + requestEntity);
			org.springframework.http.ResponseEntity<?> data = restTemplate.postForEntity(URI.create(url), requestEntity,
					String.class);
			System.out.println("whole data ------------->" + data);
			System.out.println("status code ----->" + data.getStatusCode());
			System.out.println("Headers ----->" + data.getHeaders());
			System.out.println("ContentType------->" + data.getHeaders().getContentType());
			System.out.println("data------------------>" + data.getBody());
			JSONObject responseBody = new JSONObject(data.getBody());

			map.put("Data", data.getBody());
//			map.put("responseBody", responseBody.toMap());
			map.put("statusCode", data.getStatusCode());
			map.put("Headers", data.getHeaders());
			System.out.println("map---------->" + map);

			return new ResponseEntity<>(data, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/api/insertintoswagger")
	public void insertIntoSwagger() throws IOException {
		String url = adminurl + "/routes";
		WebClient webClient = WebClient.create("http://192.168.11.62:9090");
		ResponseEntity<String> data = webClient.get().uri(url).exchange().flatMap(r -> r.toEntity(String.class))
				.block();

//		System.out.println("kartavyadata=================================================" + data.getBody());

//
		JSONObject jsonObject = new JSONObject(data.getBody());
//
		JSONArray jsonArray = new JSONArray(jsonObject.get("data").toString());
		JSONArray list = new JSONArray();
		JSONArray paths = new JSONArray();
		JSONArray methods = new JSONArray();
		JSONArray services = new JSONArray();
		list = (JSONArray) jsonObject.get("data");
		JSONObject route = new JSONObject();
		for (int i = 0; i < list.length(); i++) {
			System.out.println("iterating------+---------------+-+-+-+-+-+-+-+-->" + i);

			JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
			System.out.println("jsonobject------>" + jsonObject2);
			route = (JSONObject) list.get(i);

			System.out.println("route----->" + route.get("paths"));
			System.out.println("methods----->" + route.get("methods"));
			System.out.println("service----->" + route.get("service"));

//	methods = (JSONArray) route.get("methods");
			JSONObject map = new JSONObject();
			map = (JSONObject) route.get("service");

			System.out.println("service----->" + map.get("id"));

			paths = (JSONArray) route.get("paths");
			System.out.println("paths.length()" + paths.length());
			String path = "";
			String serviceid = (String) map.get("id");

			for (int j = 0; j < paths.length(); j++) {

				path = (String) paths.get(j);
			}
			System.out.println("all path in string -------->" + path);

			System.out.println("name----->" + route.get("name"));
			String name = route.get("name").toString() != null ? route.get("name").toString() : "NO-NAME-ROUTE";

			String templateName = "\\src\\main\\resources\\templates\\swagger";
			VelocityEngine velocityEngine = new VelocityEngine();// context.getBean(VelocityEngine.class);
			velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
			velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

			VelocityContext velocityContext = new VelocityContext();

			velocityContext.put("url", kongurl);
			velocityContext.put("path", path);
			velocityContext.put("tags", name);
			velocityContext.put("title", name);
//			velocityContext.put("path1", "/weather/{name}");			

			Template t = velocityEngine.getTemplate(templateName + ".vm");
			StringWriter stringWriter = new StringWriter();
			t.merge(velocityContext, stringWriter);

			final String finalRequest = stringWriter.toString();
//			System.out.println("finalRequest-------->"+finalRequest);
			String strFilePath = "C:\\Users\\3189\\Desktop\\swaggerfile";
			String filepath = "C:\\STSworkspace\\kongstore\\src\\main\\resources\\static";
			String strFileName = serviceid;
//			System.out.println("jsonobejct get service ------>"+jsonObject2.get(service));
			try {
				FileWriter file = new FileWriter(filepath + "/" + strFileName + ".json");
				file.write(finalRequest.toString());
				file.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(strFilePath + "/" + strFileName + ".json");

		}

	}

}
