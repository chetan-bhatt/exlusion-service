package com.ex.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ex.ExclusionServiceApplication;
import com.ex.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ExclusionServiceApplication.class)
public class RegistrationControllerTest {

	@LocalServerPort
	private int randomServerPort;
	
	private String baseUrl;
	
	private URI uri;
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	
	@Before
	public void setUp() {
		 baseUrl = "http://localhost:" + randomServerPort + "/register";
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			fail();
		}
	}
	
	@Test
	public void testRegisterNullUser() {
		User user = new User();
		exception.expect(HttpClientErrorException.BadRequest.class);
		restTemplate.postForEntity(uri, user, User.class);
	}
	
	@Test
	public void testRegisterWithImproperUsername() {
		User user = new User("user 1", "Password1", "2000-10-10", "85385078");
		exception.expect(HttpClientErrorException.BadRequest.class);
		restTemplate.postForEntity(uri, user, User.class);
	}
	
	@Test
	public void testRegisterWithImproperPassword() {
		User user = new User("user1", "Password", "2000-10-10", "85385078");
		exception.expect(HttpClientErrorException.BadRequest.class);
		restTemplate.postForEntity(uri, user, User.class);
	}

	@Test
	public void testRegisterWithImproperDateOfBirth() {
		User user = new User("user1", "Password1", "2000/10/10", "85385078");
		exception.expect(HttpClientErrorException.BadRequest.class);
		restTemplate.postForEntity(uri, user, User.class);
	}
	
	@Test
	public void testRegisterWithExcludedUser() {
		User user = new User("alanTuring", "eniGmA123", "1912-06-23", "123456789");
		exception.expect(HttpClientErrorException.BadRequest.class);
		restTemplate.postForEntity(uri, user, User.class);
	}
	
	@Test
	public void testRegisterWithValidUser() {
		User user = new User("alanTuring", "eniGmA123", "1912-06-24", "123456790");
		ResponseEntity<User> result = restTemplate.postForEntity(uri, user, User.class);
		assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void testUserAlreadyExists() {
		User user = new User("alanTuring", "eniGmA123", "1912-06-25", "123456791");
		ResponseEntity<User> result = restTemplate.postForEntity(uri, user, User.class);
		assertEquals(200, result.getStatusCodeValue());
		
		exception.expect(HttpClientErrorException.BadRequest.class);
		result = restTemplate.postForEntity(uri, user, User.class);
		assertEquals(200, result.getStatusCodeValue());
	}
}
