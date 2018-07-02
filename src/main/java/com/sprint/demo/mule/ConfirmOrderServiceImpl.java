package com.sprint.demo.mule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sprint.demo.models.ConfirmOrderLVOResponse;
import com.sprint.demo.models.OrderDetails;

@Service
public class ConfirmOrderServiceImpl {
	
	@Value("${mule.host}")
	private String MULE_HOST;
	@Value("${mule.port}")
	private String MULE_PORT;
	
	

	public ConfirmOrderLVOResponse confirmOrder(OrderDetails reqBody) throws Exception{
		
		int statusCode;
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate;
			HashMap<String, String> urlParams = new HashMap<String, String>();
			String muleUrl = "http://" + MULE_HOST + ':' + MULE_PORT + "/api/digital/confirm/order";
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(muleUrl);
				restTemplate = new RestTemplate(
						new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
				ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
				restTemplate.setInterceptors(interceptors);
				MultiValueMap<String, OrderDetails> map= new LinkedMultiValueMap<String, OrderDetails>();
				map.add("reqestBody", reqBody);
				HttpEntity<MultiValueMap<String, OrderDetails>> request = new HttpEntity<MultiValueMap<String, OrderDetails>>(map, httpHeaders);
			HttpEntity  httpEntity = new HttpEntity (reqBody,httpHeaders);
			
			ConfirmOrderLVOResponse response = new ConfirmOrderLVOResponse();
			try {
				LinkedHashMap res = restTemplate.postForObject(builder.buildAndExpand(urlParams).toUri(), request, LinkedHashMap.class);
				response.setStatus(200);
			} catch( RestClientException restEx ) {
				System.out.println( restEx.getMessage() );
//				response.setCategory(res.get("category").toString());
//				response.setErrorCode(res.get("errorCode").toString());
				response.setStatus(500);
			}
//			LinkedHashMap res = restTemplate.postForObject(builder.buildAndExpand(urlParams).toUri(),
//					request, LinkedHashMap.class);
//			
//			ConfirmOrderLVOResponse response = new ConfirmOrderLVOResponse();
//			if("success".equals(res.get("status"))){
//				statusCode = 200;
//				response.setStatus(200);
//
//			}else{
//				response.setCategory(res.get("category").toString());
//				response.setErrorCode(res.get("errorCode").toString());
//				statusCode = 500;
//				response.setStatus(500);
//
//			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("MULE_LVO",e);
		}
	}

}
