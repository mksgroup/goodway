/**
 * Licensed to FA Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * FA licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package mksgroup.goodway.app.restcontroler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mksgroup.goodway.app.AppApplication;

/**
 * @author ThachLN
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class VehicleRestControllerTest {
  //Required to Generate JSON content from Java objects
    public static final ObjectMapper objectMapper = new ObjectMapper();
    
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    
    @Value("${local.server.port}")
    private int port;
    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * [Give the description for method].
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
     * Test method for {@link mksgroup.goodway.app.restcontroler.VehicleRestController#add(mksgroup.goodway.domain.valueobject.VehicleVO)}.
     */
    @Test
    void testAdd() throws JsonProcessingException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Truck A1");
        requestBody.put("volume", "300.0");
        requestBody.put("capacity", "2000");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        objectMapper.findAndRegisterModules();
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

        ResponseEntity<Map> responseE = restTemplate.exchange("http://localhost:" + port + "/v1/vehicle", HttpMethod.POST, entity, Map.class, Collections.EMPTY_MAP);

        assertNotNull(responseE);

        // Should return created (status code 201)
        assertEquals(HttpStatus.CREATED, responseE.getStatusCode());

        //validating the newly created user using API call
        Map<String, Object> response
                = restTemplate.getForObject("http://localhost:" + port + "/v1/user/3", Map.class);

        assertNotNull(response);

        //Asserting API Response
        String id = response.get("id").toString();
        assertNotNull(id);
        // assertEquals("3", id);
        String name = response.get("name").toString();
        assertNotNull(name);
        assertEquals("Truck A1", name);
        
        String volume = response.get("volume").toString();
        assertNotNull(volume);
        assertEquals("300.0", volume);
        
        String capacity = response.get("capacity").toString();
        assertNotNull(capacity);
        assertEquals("2000", capacity);
    }

}
