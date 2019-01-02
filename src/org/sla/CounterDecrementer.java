package org.sla;

public class CounterDecrementer implements Runnable {
    private SynchronizedCounter sharedCounter;
    private int decId;
    private int decrements;

    CounterDecrementer(SynchronizedCounter theCounter, int id, int howMany) {
        sharedCounter = theCounter;
        decId = id;
        decrements = howMany;
    }

    @Override
    public void run() {
        for (int j = 0; j < decrements; j++) {
            // try decrementing
            boolean decrementSuccessful = sharedCounter.decrement(decId);
            // check if decrement actually happened
            while (!decrementSuccessful) {
                // let other threads make some progress; hopefully another thread will increment beyond 0
                Thread.currentThread().yield();
                // try decrementing again
                decrementSuccessful = sharedCounter.decrement(decId);
            }
        }
        System.out.println("Decrementer thread " + decId + ": DONE DECREMENTING");
    }}