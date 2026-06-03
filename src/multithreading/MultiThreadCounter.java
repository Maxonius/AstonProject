package multithreading;

import collection.CustomArrayList;
import model.Car;

public class MultiThreadCounter {

    public static int countOccurrences(CustomArrayList<Car> list, Car target, int threadCount) {
        if (list == null || list.isEmpty()) return 0;

        int size = list.size();
        if (threadCount > size) threadCount = size;

        int[] results = new int[threadCount];
        Thread[] threads = new Thread[threadCount];
        int chunk = size / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int from = i * chunk;
            int to = (i == threadCount - 1) ? size : from + chunk;
            int id = i;

            threads[i] = new Thread(() -> {
                int count = 0;
                for (int j = from; j < to; j++) {
                    if (list.get(j).equals(target)) count++;
                }
                results[id] = count;
            });
            threads[i].start();
        }

        int total = 0;
        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Поток прерван: " + e.getMessage());
            }
            total += results[i];
        }

        return total;
    }
}