package com.example.javahacks;

public class SynchronizedLocking {
    public static void main(String[] args) {
        Worker worker = new Worker(new Printer());
        Thread t1 = new Thread(worker);
        Thread t2 = new Thread(worker);
        t1.start();
        t2.start();
    }
    static class Worker implements Runnable{
        final Printer printer;

        Worker(Printer printer) {
            this.printer = printer;
        }

        @Override
        public void run() {
            try {
                printer.printing(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static class Printer{
        public synchronized void printing(String name) throws InterruptedException {
            System.out.println(name);
            Thread.sleep(1000);
            printing(name);
        }
    }
}
