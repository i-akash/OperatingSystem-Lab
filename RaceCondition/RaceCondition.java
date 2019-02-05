public class RaceCondition {
    int sharedValue;
    boolean available;

    RaceCondition() {
        this.sharedValue = 0;
        this.available = true;
    }

    void acquireLock() {
        while (!available)
            ;
        available = false;
    }

    void releaseLock() {
        available = true;
    }

    void increment() {
        acquireLock();
        // Synchronized part
        sharedValue++;
        releaseLock();
    }

    public static void main(String[] args) throws Exception {
        RaceCondition race = new RaceCondition();
        int process = 1000;
        Thread[] threadPool = new Thread[process];

        for (int i = 0; i < process; i++) {
            threadPool[i] = new Thread(new Runnable() {

                @Override
                public void run() {
                    race.increment();
                }

            });
            threadPool[i].start();
        }
        for (int i = 0; i < process; i++) {
            threadPool[i].join();
        }

        System.out.println(race.sharedValue);
    }
}