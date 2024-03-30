package org.example;

import java.util.concurrent.Semaphore;

public class Main {

    private static final int philosopherCount = 5;
    private static final Philosopher[] philosophers = new Philosopher[philosopherCount];
    private static final Chopstick[] chopsticks = new Chopstick[philosopherCount];
    private static final Semaphore table = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        fillChopsticksArray();
        for (int i = 0; i < philosopherCount; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % philosopherCount], table);
            philosophers[i].start();
//            Thread.sleep(500);
        }
    }

    private static void fillChopsticksArray() {
        for (int i = 0; i < philosopherCount; i++) {
            chopsticks[i] = new Chopstick();
        }
    }
}