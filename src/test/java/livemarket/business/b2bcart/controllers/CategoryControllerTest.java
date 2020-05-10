package livemarket.business.b2bcart.controllers;


import livemarket.business.b2bcart.B2bcartApplication;
import livemarket.business.b2bcart.models.items.Category;
import livemarket.business.b2bcart.utils.TokenSerializer;
import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = B2bcartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryControllerTest {
    @Autowired
    TestRestTemplate restTemplate;

    HttpEntity<Category> entity;
    String token = "";

    @Value("${security.passwordencoder}")

    private String passwordencoder;

    @Value("${security.withclient}")
    private String withclient;

    @Before
    public void setUp() throws Exception {

        this.token = this.dologin();
    }

    private String dologin() {
        String plainCreds = String.format("%s:%s", this.withclient, this.passwordencoder);
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        body.add("username", "admin");
        body.add("password", "12345");
        body.add("grant_type", "password");
        HttpEntity<?> request = new HttpEntity<Object>(body, headers);
        ResponseEntity<TokenSerializer> response = restTemplate.exchange("/oauth/token", HttpMethod.POST, request, TokenSerializer.class);
        return response.getBody().getAccess_token();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTetCategories() {


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.token);


        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();


        HttpEntity<?> request = new HttpEntity<Object>(body, headers);

        ResponseEntity<?> result = restTemplate.exchange("/api/v1/categories/", HttpMethod.GET, request, String.class);
        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());


    }

}
