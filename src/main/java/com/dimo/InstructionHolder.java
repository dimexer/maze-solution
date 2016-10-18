package com.dimo;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstructionHolder {
    private static final Logger LOG = LoggerFactory.getLogger(InstructionHolder.class);

    private final Stack<Position> holder;
    private boolean isOut;

    public InstructionHolder() {
        this.holder = new Stack<>();
        this.holder.setSize(1);
    }

    public synchronized Position get() {
        while(holder.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Position ret = holder.pop();
        LOG.info("Position {} taken from {}", ret, Thread.currentThread().getName());
        notifyAll();
        return ret;
    }

    public synchronized void put(Position newPosition) {
        while(holder.size() != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        holder.push(newPosition);
        LOG.info("Position {} given from {}", newPosition, Thread.currentThread().getName());
        notifyAll();
    }

    public synchronized boolean isOut() {
        return isOut;
    }

    public synchronized void out() {
        this.isOut = true;
    }
}
