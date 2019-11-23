package com.odont.odont;

import com.odont.odont.bot.MainBot;
import com.odont.odont.models.entity.TreatmentEntity;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainBot.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TreatmentApplicationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/v1";
    }

    @Test
    public void contextLoads() {
    }
    @Test
    public void testGetAllTreatments() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/treatment",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }
    @Test
    public void testGetTreatmentById() {
        TreatmentEntity treatmentEntity = restTemplate.getForObject(getRootUrl() + "/treatment/1", TreatmentEntity.class);
        System.out.println(treatmentEntity.getNameTreatment());
        System.out.println(treatmentEntity.getCostTreatment());
        System.out.println(treatmentEntity.getDuration());
        Assert.assertNotNull(treatmentEntity);
    }
    @Test
    public void testCreateTreatment() {
        TreatmentEntity treatmentEntity = new TreatmentEntity();
        treatmentEntity.setNameTreatment("brakents");
        treatmentEntity.setCostTreatment(1000);
        treatmentEntity.setDuration("6 meses");

        ResponseEntity<TreatmentEntity> postResponse = restTemplate.postForEntity(getRootUrl() + "/treatment", treatmentEntity, TreatmentEntity.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }
    @Test
    public void testUpdateTreatmet() {
        int id = 1;
        TreatmentEntity treatmentEntity = restTemplate.getForObject(getRootUrl() + "/treatment/" + id, TreatmentEntity.class);
        treatmentEntity.setNameTreatment("muelas del juicio");
        treatmentEntity.setCostTreatment(650);
        treatmentEntity.setDuration("2 semenas");

        restTemplate.put(getRootUrl() + "/treatment/" + id, treatmentEntity);

        TreatmentEntity updatedTreatment = restTemplate.getForObject(getRootUrl() + "/treatment/" + id, TreatmentEntity.class);
        Assert.assertNotNull(updatedTreatment);
    }
    @Test
    public void testDeleteCar() {
        int id = 2;
        TreatmentEntity treatmentEntity = restTemplate.getForObject(getRootUrl() + "/cars/" + id, TreatmentEntity.class);
        Assert.assertNotNull(treatmentEntity);

        restTemplate.delete(getRootUrl() + "/cars/" + id);

        try {
            treatmentEntity = restTemplate.getForObject(getRootUrl() + "/cars/" + id, TreatmentEntity.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }


}
