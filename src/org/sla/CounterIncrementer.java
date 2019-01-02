package org.sla;

public class CounterIncrementer implements Runnable {
    private SynchronizedCounter sharedCounter;
    private int myId;
    private int increments;

    CounterIncrementer(SynchronizedCounter theCounter, int id, int howMany) {
        sharedCounter = theCounter;
        myId = id;
        increments = howMany;
    }

    @Override
    public void run() {
        for (int i = 0; i < increments; i++) {
            // try incrementing
            boolean incrementSuccessful = sharedCounter.increment(myId);
            // check if increment actually happened
            while (!incrementSuccessful) {
                // let other threads make some progress; hopefully another thread will decrement below 100
                Thread.currentThread().yield();
                // try incrementing again
                incrementSuccessful = sharedCounter.increment(myId);
            }
        }
        System.out.println("Incrementer thread " + String.valueOf(myId) + ": DONE INCREMENTING");
    }
}