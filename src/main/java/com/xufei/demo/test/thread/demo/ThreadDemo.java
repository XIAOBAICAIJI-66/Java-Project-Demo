package com.xufei.demo.test.thread.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "主线程");

        //继承Thread实现多线程
        extendThread();

        //实现Runnable接口实现多线程
        implementsRunnable();

        //实现Callable接口实现多线程
        implementsCallable();
    }

    private static void implementsCallable() {
        //1:创建Callable类
        Thread3 thread3 = new Thread3();
        //2:创建FutureTask类
        FutureTask<String> futureTask = new FutureTask<>(thread3);
        //3:创建Thread类
        Thread thread = new Thread(futureTask);
        thread.setName("实现Callable接口实现多线程");
        thread.start();
        //接受返回参数
        try {
            String s = futureTask.get();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //使用new的方式
        Callable<String> callable = new Callable<>() {
            @Override
            public String call() throws Exception {
                return "测试多线程123";
            }
        };
        //创建FutureTask类
        FutureTask<String> stringFutureTask = new FutureTask<>(callable);
        Thread thread1 = new Thread(stringFutureTask);
        thread1.setName("使用callable的方式");
        thread1.start();
        try {
            String s = stringFutureTask.get();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void implementsRunnable() {
        //实现Runnable接口实现多线程
        Thread thread = new Thread(new Thread2());
        thread.setName("实现接口的线程");
        thread.start();
    }

    private static void extendThread() {
        //继承Thread实现多线程
        new Thread("thread实现多线程") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "子线程");
            }
        }.start();
        Thread1 thread1 = new Thread1();
        thread1.setName("继承的线程1");
        thread1.start();
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("继承Thread类实现多线程：" + Thread.currentThread().getName() + "子线程");
    }
}

class Thread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("实现Runnable接口实现多线程：" + Thread.currentThread().getName() + "子线程");
    }
}

class Thread3 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "测试多线程";
    }
}