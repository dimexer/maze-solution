package com.dimo.service;

import com.dimo.Position;

import java.util.List;

public interface MazeService {

    String getMap();

    List<Position> getPath();

    void move(Position newPosition);

    boolean isOut();
}
