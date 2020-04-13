package lesson_5;

import java.util.Arrays;

public class HomeworkFive {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {

        System.out.printf("%n1 thread:%n");

        method1();

        System.out.printf("%n2 threads:%n");

        try {
            method2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void method1() {
        float[] arr1 = new float[size];
        Arrays.fill(arr1, 1);

        //System.out.printf("Original array:%n%s%n", Arrays.toString(arr1));

        long a = System.currentTimeMillis();

        calcArr(arr1);

        System.out.printf("Runtime - %,d ms.%n", System.currentTimeMillis() - a);

        //System.out.printf("Result array:%n%s%n", Arrays.toString(arr1));
    }

    public static void method2() throws InterruptedException {
        float[] arr2 = new float[size];
        Arrays.fill(arr2, 1);

        //System.out.printf("Original array:%n%s%n", Arrays.toString(arr2));

        long a = System.currentTimeMillis();

        float[] arr2h1 = new float[h];
        float[] arr2h2 = new float[h];

        System.arraycopy(arr2, 0, arr2h1, 0, h);
        System.arraycopy(arr2, h, arr2h2, 0, h);

        Thread thread1 = new Thread(() -> calcArrHalf1(arr2h1), "T1");
        Thread thread2 = new Thread(() -> calcArrHalf2(arr2h2), "T2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(arr2h1, 0, arr2, 0, h);
        System.arraycopy(arr2h2, 0, arr2, h, h);

        System.out.printf("Runtime - %,d ms.%n", System.currentTimeMillis() - a);

        //System.out.printf("Result array:%n%s%n", Arrays.toString(arr2));
    }

    public static void calcArr(float[] arr) {
        //System.out.println("T start.");
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        //System.out.println("T end.");
    }

    public static void calcArrHalf1(float[] arr) {
        //System.out.println("T1 start.");
        for (int i = 0; i < h; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        //System.out.println("T1 end.");
    }

    public static void calcArrHalf2(float[] arr) {
        //System.out.println("T2 start.");
        for (int i = 0; i < h; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + h) / 5) * Math.cos(0.2f + (i + h) / 5) * Math.cos(0.4f + (i + h) / 2));
        }
        //System.out.println("T2 end.");
    }
}