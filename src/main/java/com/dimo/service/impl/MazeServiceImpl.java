package com.dimo.service.impl;

import com.dimo.Position;
import com.dimo.service.MazeService;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class MazeServiceImpl implements MazeService {

    private final RestTemplate rest;

    private final String basePath;

    private final String getMapPath;

    private final String getPathPath;

    private final String movePath;

    private final String isOutPath;
    
    public MazeServiceImpl(RestTemplate restTemplate,
                           String basePath,
                           String getMapPath,
                           String getPathPath,
                           String movePath,
                           String isOutPath) {
        this.rest = restTemplate;
        this.basePath = basePath;
        this.getMapPath = getMapPath;
        this.getPathPath = getPathPath;
        this.movePath = movePath;
        this.isOutPath = isOutPath;
    }

    @Override
    public String getMap() {
        return this.rest.getForEntity(this.basePath+this.getMapPath, String.class).getBody();
    }

    @Override
    public List<Position> getPath() {
        Position[] path = this.rest.getForEntity(this.basePath+this.getPathPath, Position[].class).getBody();
        return Arrays.asList(path);
    }

    @Override
    public void move(Position newPosition) {
        this.rest.postForEntity(this.basePath+this.movePath, newPosition, null);
    }

    @Override
    public boolean isOut() {
        return this.rest.getForEntity(this.basePath+this.isOutPath, boolean.class).getBody();
    }
}
