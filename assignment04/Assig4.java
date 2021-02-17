/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
import java.io.*;

/**
 *
 * @author jakesalmon
 */
public class Assig4 extends SortAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Integer[] arr = null;
        float average = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an array size:");
        int arrSize = scanner.nextInt();
        System.out.println("Enter the number of trials:");
        int numberOfTrials = scanner.nextInt();
        System.out.println("Please enter the file name:");
        String fileName = scanner.next();
        File timeData = new File(fileName);
        if (timeData.createNewFile()) {
            System.out.println("File created: " + fileName);
        } else {
            System.out.println("File already exists.");
        }
        PrintWriter output = new PrintWriter(new FileWriter(timeData));
        Integer[][] randomArrays = fillRandomArrays(arrSize, numberOfTrials);
        Integer[][] randomArraysCopy = new Integer[numberOfTrials][arrSize];
        for (int i = 0; i < numberOfTrials; i++) {
            for (int x = 0; x < arrSize; x++) {
                randomArraysCopy[i][x] = randomArrays[i][x];
            }
        }
        long total = 0;
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                setMinSize(5);
            }
            if (i == 1) {
                setMinSize(50);
            }
            if (i == 2) {
                setMinSize(150);
            }
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 4; y++) {
                    if (y == 0) {
                        if (arrSize <= 20) {
                            traceOutputMode(y, arrSize, x);
                        }
                        output.println("Algorithm: Simple Quicksort");
                        output.println("Array Size: " + arrSize);
                        output.println("Base case less than: " + MIN_SIZE);
                        if (x == 0) {
                            output.println("Data setup: Random");
                        }
                        if (x == 1) {
                            output.println("Data setup: Sorted");
                            arr = fillSortedArray(arrSize);
                        }
                        if (x == 2) {
                            output.println("Data setup: Reverse Sorted");
                            arr = fillReversedArray(arrSize);
                        }
                        output.println("Number of trials: " + numberOfTrials);
                        for (int z = 0; z < numberOfTrials; z++) {
                            if (arrSize <= 20) {
                                System.out.println("Array prior to being sorted: Trial #" + z);
                                if (x != 0) {
                                    System.out.println(Arrays.toString(arr));
                                } else {
                                    System.out.println(Arrays.toString(randomArraysCopy[z]));
                                }
                            }
                            long start = System.nanoTime();
                            if (x != 0) {
                                quickSort(arr, arrSize);
                            } else {
                                quickSort(randomArraysCopy[z], arrSize);
                            }
                            long finish = System.nanoTime();
                            long delta = finish - start;
                            total = total + delta;
                            if (arrSize <= 20) {
                                System.out.println("Array after being sorted: Trial #" + z);
                                if (x != 0) {
                                    System.out.println(Arrays.toString(arr));
                                } else {
                                    System.out.println(Arrays.toString(randomArraysCopy[z]));
                                }
                            }
                        }
                        for (int p = 0; p < numberOfTrials; p++) {
                            for (int o = 0; o < arrSize; o++) {
                                randomArraysCopy[p][o] = randomArrays[p][o];
                            }
                        }
                        average = total / 10;
                        output.printf("Average time per trial (in seconds): %f\n", average / 1000000000);
                        output.println();
                        total = 0;
                    }
                    if (y == 1) {
                        if (arrSize <= 20) {
                            traceOutputMode(y, arrSize, x);
                        }
                        output.println("Algorithm: Median of 3 Quicksort");
                        output.println("Array Size: " + arrSize);
                        output.println("Base case less than: " + MIN_SIZE);
                        if (x == 0) {
                            output.println("Data setup: Random");
                        }
                        if (x == 1) {
                            output.println("Data setup: Sorted");
                            arr = fillSortedArray(arrSize);
                        }
                        if (x == 2) {
                            output.println("Data setup: Reverse Sorted");
                            arr = fillReversedArray(arrSize);
                        }

                        output.println("Number of trials: " + numberOfTrials);
                        for (int z = 0; z < numberOfTrials; z++) {
                            if (arrSize <= 20) {
                                System.out.println("Array prior to being sorted: Trial #" + z);
                                if (x != 0) {
                                    System.out.println(Arrays.toString(arr));
                                } else {
                                    System.out.println(Arrays.toString(randomArraysCopy[z]));
                                }
                            }
                            long start = System.nanoTime();
                            if (x != 0) {
                                quickSortMedian(arr, arrSize);
                            } else {
                                quickSortMedian(randomArraysCopy[numberOfTrials - 1], arrSize);
                            }
                            long finish = System.nanoTime();
                            long delta = finish - start;
                            total = total + delta;
                            if (arrSize <= 20) {
                                System.out.println("Array after being sorted: Trial #" + z);
                                if (x != 0) {
                                    System.out.println(Arrays.toString(arr));
                                } else {
                                    System.out.println(Arrays.toString(randomArraysCopy[z]));
                                }
                            }
                        }
                        for (int p = 0; p < numberOfTrials; p++) {
                            for (int o = 0; o < arrSize; o++) {
                                randomArraysCopy[p][o] = randomArrays[p][o];
                            }
                        }
                        average = total / 10;
                        output.printf("Average time per trial (in seconds): %f\n", average / 1000000000);
                        output.println();
                        total = 0;

                    }
                    if (y == 2) {
                        if (arrSize <= 20) {
                            traceOutputMode(y, arrSize, x);
                        }
                        output.println("Algorithm: Random Quicksort");
                        output.println("Array Size: " + arrSize);
                        output.println("Base case less than: " + MIN_SIZE);
                        if (x == 0) {
                            output.println("Data setup: Random");
                        }
                        if (x == 1) {
                            output.println("Data setup: Sorted");
                            arr = fillSortedArray(arrSize);
                        }
                        if (x == 2) {
                            output.println("Data setup: Reverse Sorted");
                            arr = fillReversedArray(arrSize);
                        }

                        output.println("Number of trials: " + numberOfTrials);
                        for (int z = 0; z < numberOfTrials; z++) {
                            if (arrSize <= 20) {
                                System.out.println("Array prior to being sorted: Trial #" + z);
                                if (x != 0) {
                                    System.out.println(Arrays.toString(arr));
                                } else {
                                    System.out.println(Arrays.toString(randomArraysCopy[z]));
                                }
                            }
                            long start = System.nanoTime();
                            if (x != 0) {
                                quickSortRandom(arr, arrSize);
                            } else {
                                quickSortRandom(randomArraysCopy[numberOfTrials - 1], arrSize);
                            }
                            long finish = System.nanoTime();
                            long delta = finish - start;
                            total = total + delta;
                            if (arrSize <= 20) {
                                System.out.println("Array after being sorted: Trial #" + z);
                                if (x != 0) {
                                    System.out.println(Arrays.toString(arr));
                                } else {
                                    System.out.println(Arrays.toString(randomArraysCopy[z]));
                                }
                            }
                        }
                        for (int p = 0; p < numberOfTrials; p++) {
                            for (int o = 0; o < arrSize; o++) {
                                randomArraysCopy[p][o] = randomArrays[p][o];
                            }
                        }
                        average = total / 10;
                        output.printf("Average time per trial (in seconds): %f\n", average / 1000000000);
                        output.println();
                        total = 0;

                    }
                    if (y == 3) {
                        if (arrSize <= 20) {
                            traceOutputMode(y, arrSize, x);
                        }
                        output.println("Algorithm: MergeSort");
                        output.println("Array Size: " + arrSize);
                        output.println("Base case less than: " + MIN_SIZE);
                        if (x == 0) {
                            output.println("Data setup: Random");
                        }
                        if (x == 1) {
                            output.println("Data setup: Sorted");
                            arr = fillSortedArray(arrSize);
                        }
                        if (x == 2) {
                            output.println("Data setup: Reverse Sorted");
                            arr = fillReversedArray(arrSize);
                        }

                        output.println("Number of trials: " + numberOfTrials);
                        for (int z = 0; z < numberOfTrials; z++) {
                            if (arrSize <= 20) {
                                System.out.println("Array prior to being sorted: Trial #" + z);
                                if (x != 0) {
                                    System.out.println(Arrays.toString(arr));
                                } else {
                                    System.out.println(Arrays.toString(randomArraysCopy[z]));
                                }
                            }
                            long start = System.nanoTime();
                            if (x != 0) {
                                mergeSort(arr, arrSize);
                            } else {
                                mergeSort(randomArraysCopy[numberOfTrials - 1], arrSize);
                            }
                            long finish = System.nanoTime();
                            long delta = finish - start;
                            total = total + delta;
                            if (arrSize <= 20) {
                                System.out.println("Array after being sorted: Trial #" + z);
                                if (x != 0) {
                                    System.out.println(Arrays.toString(arr));
                                } else {
                                    System.out.println(Arrays.toString(randomArraysCopy[z]));
                                }
                            }
                        }
                        for (int p = 0; p < numberOfTrials; p++) {
                            for (int o = 0; o < arrSize; o++) {
                                randomArraysCopy[p][o] = randomArrays[p][o];
                            }
                        }
                        average = total / 10;
                        output.printf("Average time per trial (in seconds): %f\n", average / 1000000000);
                        output.println();
                        total = 0;
                    }
                }
            }
        }
        output.close();
    }

    public static Integer[][] fillRandomArrays(int arrSize, int numberOfTrials) {
        Integer[][] randomArrs = new Integer[numberOfTrials][arrSize];
        for (int i = 0; i < numberOfTrials; i++) {
            for (int x = 0; x < arrSize; x++) {
                randomArrs[i][x] = new Random().nextInt(arrSize);
            }
        }
        return randomArrs;
    }

    public static Integer[] fillSortedArray(int arrSize) {
        Integer[] arr = new Integer[arrSize];
        for (int i = 0; i < arrSize; i++) {

            arr[i] = i;
        }
        return arr;
    }

    public static Integer[] fillReversedArray(int arrSize) {
        Integer[] arr = new Integer[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = arrSize - i;
        }
        return arr;
    }

    public static void traceOutputMode(int alg, int arrSize, int setup) {
        if (alg == 0) {
            System.out.println("Algorithm: Simple Quicksort");
        } else if (alg == 1) {
            System.out.println("Algorithm: Median of 3 Quicksort");
        } else if (alg == 2) {
            System.out.println("Algorithm: Random Pivot Quicksort");
        } else if (alg == 3) {
            System.out.println("Algorithm: Mergesort");
        }
        System.out.println("Array size: " + arrSize);
        System.out.println("Base case less than: " + MIN_SIZE);
        if (setup == 0) {
            System.out.println("Data setup: Random");
        } else if (setup == 1) {
            System.out.println("Data setup: Sorted");
        } else if (setup == 2) {
            System.out.println("Data setup: Reverse Sorted");
        }
    }

}
