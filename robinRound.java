import java.util.*;

public class robinRound {
    static void WaitingTime(int[] process, int[] burstTime, int[] waiting, int quantum, int length) {
        int[] rem_burst = new int[length];

        for (int i = 0; i < length; i++) {
            rem_burst[i] = burstTime[i];
        }
        int time = 0;
        while (true) {
            boolean done = true;

            for (int i = 0; i < length; i++) {
                if (rem_burst[i] > quantum) {
                    time += quantum;
                    rem_burst[i] -= quantum;
                    done = false;
                } else if (rem_burst[i] > 0) {
                    time += rem_burst[i];
                    rem_burst[i] = 0;
                    waiting[i] = time - burstTime[i];
                    done = false;
                }
            }

            if (done)
                break;
        }
    }

    static void turnAroundTime(int[] turnTime, int[] burstTime, int[] waiting, int length) {
        for (int i = 0; i < length; i++)
            turnTime[i] = burstTime[i] + waiting[i];
    }

    static void AvgTime(int[] process, int[] burstTime, int quantum, int length) {
        int[] waiting = new int[length];
        int[] turnTime = new int[length];
        int avgTime = 0;
        WaitingTime(process, burstTime, waiting, quantum, length);

        System.out.println("waiting time");
        for (int i = 0; i < length; i++) {
            System.out.print(waiting[i] + " ");
        }
        System.out.println();

        turnAroundTime(turnTime, burstTime, waiting, length);
        System.out.println("turnTime time");
        for (int i = 0; i < length; i++) {
            avgTime += turnTime[i];
            System.out.print(turnTime[i] + " ");
        }

        System.out.println();
        System.out.println("avg time : " + ((float) avgTime / length));

    }

    public static void main(String[] args) {
        int[] process = new int[] { 1, 2, 3 };
        int[] burstTime = new int[] { 10, 5, 8 };
        int length = process.length;
        int quantum = 2;

        AvgTime(process, burstTime, quantum, length);
    }
}