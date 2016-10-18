package com.dimo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dimo.service.MazeService;

public class Brain implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(Brain.class);

    private MazeService mazeService;

    private InstructionHolder holder;

    public Brain(MazeService mazeService, InstructionHolder holder) {
        this.mazeService = mazeService;
        this.holder = holder;
    }

    public void run() {
        LOG.info("Brain started!");
        List<Position> path = mazeService.getPath();
        LOG.info("Brain calculated/extracted path: " + path);
        LOG.info("Start giving instructions!");
        for(Position pos : path) {
            holder.put(pos);
        }
        holder.put(null);
        holder.out();
    }
}
