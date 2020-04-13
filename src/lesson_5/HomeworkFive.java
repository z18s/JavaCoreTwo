package lesson_5;

import java.util.Arrays;

public class HomeworkFive {

    static final int SIZE = 10_000_000;
    static final int H = SIZE / 2;

    public static void main(String[] args) {

        float[] arr1;
        float[] arr2;

        System.out.printf("%n1 thread:%n");

        arr1 = method1();

        System.out.printf("%n2 threads:%n");

        arr2 = method2();

        System.out.printf("%nArrays are equal: %s.%n", (Arrays.equals(arr1, arr2) ? "true" : "false"));
    }

    public static float[] method1() {
        float[] arr1 = createAndFillArr(SIZE);

        //System.out.printf("Original array:%n%s%n", Arrays.toString(arr1));

        long a = System.currentTimeMillis();

        calcArr(arr1);

        System.out.printf("Runtime - %,d ms.%n", System.currentTimeMillis() - a);

        //System.out.printf("Result array:%n%s%n", Arrays.toString(arr1));

        return arr1;
    }

    public static float[] method2() {
        float[] arr2 = createAndFillArr(SIZE);

        //System.out.printf("Original array:%n%s%n", Arrays.toString(arr2));

        long a = System.currentTimeMillis();

        float[] arr2h1 = new float[H];
        float[] arr2h2 = new float[H];

        System.arraycopy(arr2, 0, arr2h1, 0, H);
        System.arraycopy(arr2, H, arr2h2, 0, H);

        Thread thread1 = new Thread(() -> calcArrHalf1(arr2h1), "T1");
        Thread thread2 = new Thread(() -> calcArrHalf2(arr2h2), "T2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr2h1, 0, arr2, 0, H);
        System.arraycopy(arr2h2, 0, arr2, H, H);

        System.out.printf("Runtime - %,d ms.%n", System.currentTimeMillis() - a);

        //System.out.printf("Result array:%n%s%n", Arrays.toString(arr2));

        return arr2;
    }

    public static float[] createAndFillArr(int size) {
        float[] arr = new float[size];
        Arrays.fill(arr, 1.0f);
        return arr;
    }

    public static void calcArr(float[] arr) {
        //System.out.println("T start.");
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5.0f) * Math.cos(0.2f + i / 5.0f) * Math.cos(0.4f + i / 2.0f));
        }
        //System.out.println("T end.");
    }

    public static void calcArrHalf1(float[] arr) {
        //System.out.println("T1 start.");
        for (int i = 0; i < H; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5.0f) * Math.cos(0.2f + i / 5.0f) * Math.cos(0.4f + i / 2.0f));
        }
        //System.out.println("T1 end.");
    }

    public static void calcArrHalf2(float[] arr) {
        //System.out.println("T2 start.");
        for (int i = 0; i < H; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + H) / 5.0f) * Math.cos(0.2f + (i + H) / 5.0f) * Math.cos(0.4f + (i + H) / 2.0f));
        }
        //System.out.println("T2 end.");
    }
}