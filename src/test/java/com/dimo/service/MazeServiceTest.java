package com.dimo.service;

import com.dimo.service.impl.MazeServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.junit.*;

public class MazeServiceTest {

    private MazeService mazeService;

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        mazeService = new MazeServiceImpl(restTemplate,"", "", "", "", "");
    }

    @Test
    public void testGettingMap() {
        ResponseEntity<String> resp = Mockito.mock(ResponseEntity.class);
        Mockito.when(resp.getBody()).thenReturn("0 0 0");
        Mockito.when(restTemplate.getForEntity(Matchers.eq(""), Matchers.eq(String.class)))
            .thenReturn(resp);

        String map = mazeService.getMap();

        Assert.assertEquals("0 0 0", map);
    }
}
