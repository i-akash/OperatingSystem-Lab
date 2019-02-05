public class Peterson {
    int process1, process2, turn, sharedValue;
    boolean[] flag;

    Peterson() {
        process1 = 0;
        process2 = 1;
        sharedValue = 0;
        flag = new boolean[3];
    }

    void acquireLock(int process) {
        flag[process] = true;
        int op = 1 - process;
        turn = op;

        while (flag[op] && turn == op)
            ;
    }

    void releaseLock(int process) {
        flag[process] = false;
    }

    void increment(int process) {
        acquireLock(process);
        for (int i = 0; i < 10000000; i++)
            sharedValue++;
        releaseLock(process);
    }

    public static void main(String[] args) throws Exception {
        Peterson pt = new Peterson();

        Thread[] threadpool = new Thread[2];

        for (int i = 0; i < 2; i++) {
            threadpool[i] = new Thread(new Mythread(i, pt));
            threadpool[i].start();
        }

        for (int i = 0; i < 2; i++) {
            threadpool[i].join();
        }

        System.out.println(pt.sharedValue);
    }

}

class Mythread implements Runnable {
    int process;
    Peterson pt;

    Mythread(int process, Peterson pt) {
        this.process = process;
        this.pt = pt;
    }

    @Override
    public void run() {
        System.out.println("process : " + process);
        pt.increment(process);

    }
}