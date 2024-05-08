package com.social.eventservice;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	public void	testMakeEvent() {
		ResponseEntity<Void> response = restTemplate.getForEntity("/event/1", Void.class);
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
	}


}

