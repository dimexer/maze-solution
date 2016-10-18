package com.dimo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.dimo.service.MazeService;

@SpringBootApplication
public class App {

    @Autowired
    private MazeService mazeService;

    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(App.class).run(args);
        
        context.getBean(App.class).start();;
    }

    private void start() {
        InstructionHolder ih = new InstructionHolder();
        Thread runner = new Thread(new Runner(mazeService, ih), "Runner");
        Thread brain = new Thread(new Brain(mazeService, ih), "Brain");
        runner.start();
        brain.start();
    }
}
