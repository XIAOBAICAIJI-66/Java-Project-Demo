package com.xufei.demo.test.thread.demo;

public class ThreadDemo2 {
    public static void main(String[] args) {
        //卖车票案例
        demo1();
    }

    private static void demo1() {
        Runnable runnable = new Runnable() {
            private int total = 100;
            private boolean flag = true;

            @Override
            public void run() {
                while (flag) {
                    synchronized (ThreadDemo2.class){
                        if (total > 0) {
                            System.out.println(Thread.currentThread().getName() + "--->卖出第" + total + "张票");
                            total--;
                            if (total <= 0) {
                                flag = false;
                            }
                        }
                    }

                }
            }
        };

        Thread thread1 = new Thread(runnable, "线程1");
        Thread thread2 = new Thread(runnable, "线程2");
        Thread thread3 = new Thread(runnable, "线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
