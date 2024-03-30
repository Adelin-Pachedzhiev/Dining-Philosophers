package org.example;

import java.util.concurrent.Semaphore;

public class Main {

    static int philosopher = 5;
    static Philosopher philosophers[] = new Philosopher[philosopher];
    static Chopstick chopsticks[] = new Chopstick[philosopher];
    static Semaphore table = new Semaphore(1);
    public static void main(String[] args) throws InterruptedException{
        fillChopsticksArray();
        for (int i = 0; i < philosopher; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % philosopher], table);
            philosophers[i].start();
//            Thread.sleep(500);
        }
    }

    private static void fillChopsticksArray() {
        for (int i = 0; i < philosopher; i++) {
            chopsticks[i] = new Chopstick();
        }
    }
}