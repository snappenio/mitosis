package com.snappenio.mitosis.controller;

import java.net.URL;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerIT {

    @LocalServerPort
    private int port;

    private URL url;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.url = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void testWelcomePage() throws Exception {
        ResponseEntity<String> response = template.getForEntity(url.toString(),String.class);
        Assert.assertThat(response.getBody(), Matchers.equalTo("Welcome to snappen.io!"));
    }
    
}
