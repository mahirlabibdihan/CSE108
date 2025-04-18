//Complete the following Java code that finds the total number of square numbers from a 100000 random integers array using an array of 20 threads in parallel. You cannot write any more classes.

import java.util.Random;

class ParallelSquareCounter implements Runnable {
    private int[] numbers;
    private int startIndex;
    private static final int NUMBER_COUNT = 5000;
    private int squareCount;
    // you are not allowed to add any more fields
    
    // you are not allowed to change this constructor, and you are not allowed to add any more constructor
    ParallelSquareCounter(int[] numbers, int startIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.squareCount = 0;
    }

    // your code here

    // Overriding run method
    @Override
    public void run(){
        count();
    }

    // Count the total square numbers from startIndex to startIndex+NUMBER_COUNT
    public void count(){
        for(int i=startIndex;i<startIndex+NUMBER_COUNT;i++){
            int s=(int)Math.sqrt(numbers[i]);
            if(s*s==numbers[i]){
                increment();
            }
        }
    }

    // Incremeting the count of total square numbers
    public void increment(){
        squareCount++;
    }

    // Returning the count of total square numbers
    public int get(){
        return squareCount;
    }
}

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        final int NUMBER_COUNT = 100000;
        final int THREAD_COUNT = 20;
        int[] numbers = new int[NUMBER_COUNT];
        for (int i = 0; i < numbers.length; i++) {
           numbers[i] = Math.abs(random.nextInt());
        }
        ParallelSquareCounter[] parallelSquareCounters = new ParallelSquareCounter[THREAD_COUNT];
        Thread[] threads = new Thread[THREAD_COUNT];
        // your code here

        for(int i=0;i<THREAD_COUNT;i++){

            parallelSquareCounters[i]=new ParallelSquareCounter(numbers,i*(NUMBER_COUNT/THREAD_COUNT));
           
            threads[i]=new Thread(parallelSquareCounters[i],"T-"+i);     // Creating threads

            threads[i].start(); // Starting threads       
        }

        for(int i=0;i<THREAD_COUNT;i++){
            try{
                threads[i].join();  // Joining threads to main thread
            }            
            catch (InterruptedException e) {
                System.out.println("Main thread Interrupted");
            }
        }
        
        // Adding count of all counters
        int count=0;
        for(int i=0;i<THREAD_COUNT;i++){
            count+=parallelSquareCounters[i].get();
        }

        System.out.println(count);    // Printing the total number of square numbers
    }
}