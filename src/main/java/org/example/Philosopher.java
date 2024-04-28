package org.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {

    private final int number;
    private final Chopstick leftchopstick;
    private final Chopstick rightchopstick;
    private final Semaphore dish;

    Philosopher(int num, Chopstick left, Chopstick right, Semaphore dish) {
        number = num;
        leftchopstick = left;
        rightchopstick = right;
        this.dish = dish;
    }

    public void run() {
        while (true) {

            acquireTableSemaphore();

            leftchopstick.grab();
            System.out.println("Philosopher " + (number + 1) + " grabs left chopstick.");

            if (dish.tryAcquire())
                rightchopstick.grab();
            System.out.println("Philosopher " + (number + 1) + " grabs right chopstick.");

            dish.release();

            eat();

            leftchopstick.release();
            System.out.println("Philosopher " + (number + 1) + " releases left chopstick.");

            rightchopstick.release();
            System.out.println("Philosopher " + (number + 1) + " releases right chopstick.");
        }
    }

    private void acquireTableSemaphore() {
        try {
            dish.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    void eat() {
        try {
            int sleepTime = ThreadLocalRandom.current().nextInt(0, 1000);
            System.out.println("Philosopher " + (number + 1) + " eats for " + sleepTime + "ms");
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
