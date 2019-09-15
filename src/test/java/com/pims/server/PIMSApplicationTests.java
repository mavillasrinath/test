package com.pims.server;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.pims.server.model.Question;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PIMSApplicationTests {
	
	@LocalServerPort
    int randomServerPort;
	
	@Test
	public void findAllQuestions() throws Exception {
		/*Question qtn = new Question();
		qtn.setDescription("what is your name");
		qtn.setTitle("Test");
		qtn.setCreatedAt(new Date());
		qtn.setUpdatedAt(new Date());
		qtn.setId(UUID.randomUUID()); //1f1d564b-34cb-4ce8-9073-8fc73b9713d3
		List<Question> qtnList = java.util.Arrays.asList(qtn);
		given(questionService.findAll()).willReturn(qtnList);
		
		this.mockmvc.perform(get("/questions"))
						.andExpect(status().isOk())
						.andExpect(content().json("[{'id':1,'description':what is your name,'title': Test}]"));*/
		
		
	}
	
	@SuppressWarnings("deprecation")
	//@Test
	public void testGetEmployeeListSuccess() throws URISyntaxException
	{
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" + randomServerPort + "/questions";
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<Question> result = restTemplate.getForEntity(uri, Question.class);
	     
	    //Verify request succeed
	    System.out.println("Status code:" + result.getStatusCode());
	    System.out.println("Response data : " + result.getBody());
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().getDescription().contains("what is your name"));
	}

	@SuppressWarnings("deprecation")
	//@Test
	public void testAddEmployeeMissingHeader() throws URISyntaxException
	{
	    RestTemplate restTemplate = new RestTemplate();
	    final String baseUrl = "http://localhost:"+randomServerPort+"/questions/";
	    URI uri = new URI(baseUrl);
	    Question qtn = new Question();
		qtn.setDescription("what is your name");
		qtn.setTitle("Test");
		qtn.setCreatedAt(new Date());
		qtn.setUpdatedAt(new Date());
		qtn.setId(UUID.randomUUID());
	     
	    HttpHeaders headers = new HttpHeaders();
	 
	    HttpEntity<Question> request = new HttpEntity<>(qtn, headers);
	     
	    try
	    {
	    	ResponseEntity<Question> result = restTemplate.postForEntity(uri, request, Question.class);
	    	System.out.println("Inserted response : " + result.getBody().toString().contains("what is your name"));
	        Assert.fail();
	    }
	    catch(HttpClientErrorException ex)
	    {
	        //Verify bad request and missing header
	        Assert.assertEquals(400, ex.getRawStatusCode());
	        Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
	    }
	}
}
