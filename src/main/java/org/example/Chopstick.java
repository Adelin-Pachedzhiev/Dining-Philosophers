package org.example;

import java.util.concurrent.Semaphore;

public class Chopstick {
    public Semaphore mutex = new Semaphore(1);

    void grab() {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    void release() {
        mutex.release();
    }
}
