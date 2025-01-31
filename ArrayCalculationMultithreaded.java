package student.java.homework.weeks.week3_2_2;

public class ArrayCalculationMultithreaded {

    private static final int ARRAY_LENGTH = 100_000_000;
    private static final double[] array = new double[ARRAY_LENGTH];

    public static void main(String[] args) {
        long startTime = System.nanoTime(); // Start time measurement

        // Create and start threads
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> fillArray(threadIndex));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.nanoTime(); // End time measurement
        long duration = endTime - startTime; // Calculate the duration

        // Convert to seconds
        double seconds = duration / 1_000_000_000.0;
        System.out.println("Time taken: " + seconds + " seconds");
    }

    private static void fillArray(int threadIndex) {
        int start = threadIndex * (ARRAY_LENGTH / 4);
        int end = start + (ARRAY_LENGTH / 4);

        for (int i = start; i < end; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }
}

