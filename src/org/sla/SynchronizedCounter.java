package org.sla;

public class SynchronizedCounter {
    Integer count;

    SynchronizedCounter() {
        count = 0;
    }

    synchronized boolean increment(int id) {
        if (count < 100) {
            System.out.print("Incrementer thread " + id + ": Incrementing from " + count);
            count = count + 1;
            System.out.println(" to " + count);
            // tell caller that DID increment
            return true;
        }

        // tell caller that DID NOT increment
        return false;
    }

    synchronized boolean decrement(int id) {
        if (count > 0) {
            System.out.print("Decrementer thread " + id + ": Decremented from " + count);
            count = count - 1;
            System.out.println(" to " + count);
            // tell caller that DID decrement
            return true;
        }

        // tell caller that DID NOT decrement
        return false;
    }

}
