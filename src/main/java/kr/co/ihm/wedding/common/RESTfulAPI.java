package kr.co.ihm.wedding.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.ihm.wedding.common.exception.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Component
public class RESTfulAPI {

	private static final Logger logger = LoggerFactory.getLogger(RESTfulAPI.class);
	
	/**
	 * RESTful API GET
	 * @param token
	 * @param url
	 * @param argMap
	 * @return
	 * @throws JsonProcessingException
	 * @throws RuntimeException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> get(String token, String url, Map<String, Object> argMap) throws JsonProcessingException, RuntimeException {

		logger.info("/******** RESTful API Call ********/");
		logger.info("/** RequestMethod : GET");
		logger.info("/** URL           : {}", url);
		logger.info("/** Params        : {}", argMap);
		logger.info("/**********************************/");

		ObjectMapper mapper = new ObjectMapper();

		HttpHeaders headers = new HttpHeaders();
		headers.add(ServiceConstant.Keys.AUTHORIZATION, token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		if (argMap != null) {
			for (Map.Entry<String, Object> entry : argMap.entrySet()) {
				builder = builder.queryParam(entry.getKey(), entry.getValue());
			}
		}

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);

		if (response.getStatusCode() != HttpStatus.OK) {
			String errMsg = String.format("RESTful API(GET) 연동시 문제가 발생했습니다. \nStatus code : %s \nURL : %s", response.getStatusCode(), url);
			logger.error(errMsg);

			throw new InternalServerErrorException(errMsg);
		}

		return mapper.readValue(response.getBody(), Map.class);
	}

	/**
	 * RESTful API POST
	 * @param token
	 * @param url
	 * @param arg List<Map<String, Object>>, List<String>, Map<String, Object> 객체만 사용 가능
	 * @return
	 * @throws JsonProcessingException
	 * @throws RuntimeException
	 */
	public static Map<String, Object> post(String token, String url, Object arg) throws JsonProcessingException, RuntimeException {
		return send(token, url, HttpMethod.POST, arg);
	}

	/**
	 * RESTful API PUT
	 * @param token
	 * @param url
	 * @param arg List<Map<String, Object>>, List<String>, Map<String, Object> 객체만 사용 가능
	 * @return
	 * @throws JsonProcessingException
	 * @throws RuntimeException
	 */
	public static Map<String, Object> put(String token, String url, Object arg) throws JsonProcessingException, RuntimeException {
		return send(token, url, HttpMethod.PUT, arg);
	}

	/**
	 * RESTful API DELETE
	 * @param token
	 * @param url
	 * @param arg List<Map<String, Object>>, List<String>, Map<String, Object> 객체만 사용 가능
	 * @return
	 * @throws JsonProcessingException
	 * @throws RuntimeException
	 */
	public static Map<String, Object> delete(String token, String url, Object arg) throws JsonProcessingException, RuntimeException {
		return send(token, url, HttpMethod.DELETE, arg);
	}

	/**
	 * RESTful API POST/PUT/DELETE
	 * @param token
	 * @param url
	 * @param httpMethod
	 * @param arg
	 * @return
	 * @throws JsonProcessingException
	 * @throws RuntimeException
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> send(String token, String url, HttpMethod httpMethod, Object arg) throws JsonProcessingException, RuntimeException {

		String method = getTypeToString(httpMethod);

		logger.info("/******** RESTful API Call ********/");
		logger.info("/** RequestMethod : {}", method);
		logger.info("/** URL           : {}", url);
		logger.info("/** arg           : {}", arg);
		logger.info("/**********************************/");
		
		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders headers = new HttpHeaders();
		headers.add(ServiceConstant.Keys.AUTHORIZATION, token);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = null;
		
		if (arg instanceof Map) {
			entity = new HttpEntity<Map<String, Object>>((Map<String, Object>)arg, headers);
		} else if (arg instanceof List) {
			entity = new HttpEntity<List<Map<String, Object>>>((List<Map<String, Object>>)arg, headers);
		} else {
			entity = new HttpEntity<List<String>>((List<String>)arg, headers);
		}

		ResponseEntity<String> response = new RestTemplate().exchange(url, httpMethod, entity, String.class);

		if (HttpStatus.OK != response.getStatusCode()) {

			String errMsg = String.format("RESTful API(%s) 연동시 문제가 발생했습니다. \nStatus code : %s \nURL : %s", method, response.getStatusCode(), url);
			logger.error(errMsg);

			throw new InternalServerErrorException(errMsg);
		}

		return mapper.readValue(response.getBody(), Map.class);
	}

	/**
	 * HttpMethod 타입 to String
	 * @param httpMethod
	 * @return
	 */
	private static String getTypeToString(HttpMethod httpMethod) {
		
		String method = "GET";
		if (httpMethod == HttpMethod.POST) {
			method = "POST";
		} else if (httpMethod == HttpMethod.PUT) {
			method = "PUT";
		} else if (httpMethod == HttpMethod.DELETE) {
			method = "DELETE";
		}

		return method;
	}
}
