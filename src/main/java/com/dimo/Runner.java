package com.dimo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dimo.service.MazeService;

public class Runner implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    private MazeService mazeService;

    private InstructionHolder holder;

    public Runner(MazeService mazeService, InstructionHolder holder) {
        this.mazeService = mazeService;
        this.holder = holder;
    }

    public void run() {
        LOG.info("Runner at the start! Waiting for instructions...");
        while (true) {
            Position pos = holder.get();
            mazeService.move(pos);
            if (holder.isOut()) {
                LOG.info("Yay! I'm out!");
                break;
            }
        }
    }
}
