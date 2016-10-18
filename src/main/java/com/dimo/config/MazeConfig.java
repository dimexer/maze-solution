package com.dimo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.dimo.service.MazeService;
import com.dimo.service.impl.MazeServiceImpl;

@Configuration
@PropertySource("classpath:javatest.properties")
public class MazeConfig {
    @Value("${maze.baseUrl}")
    private String basePath;

    @Value("${maze.isout.path}")
    private String isOutPath;

    @Value("${maze.getmap.path}")
    private String getMapPath;

    @Value("${maze.move.path}")
    private String movePath;

    @Value("${maze.getpath.path}")
    private String getPathPath;

    @Bean
    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    @Bean
    public MazeService createMazeService() {
        return new MazeServiceImpl(createRestTemplate(),
                                   basePath,
                                   getMapPath,
                                   getPathPath,
                                   movePath,
                                   isOutPath);
    }
}
