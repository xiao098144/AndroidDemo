package com.example.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Description：
 * Created on 2017/5/18
 * Author : 萧
 */
public class ConcurrentUtil {

    int count;
    TreeSet<String> set = new TreeSet<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        new ConcurrentUtil().exec();
        new ConcurrentUtil().concurrentTest();
    }

    void concurrentTest() {
        System.out.println("concurrentTest,  []  currentThread   " + Thread.currentThread().getName());
        ExecutorService exeCache = Executors.newCachedThreadPool();
        ExecutorService exeFixed = Executors.newFixedThreadPool(5);
        ExecutorService exeSingle = Executors.newSingleThreadExecutor();
        ExecutorService exeSchedule = Executors.newScheduledThreadPool(2);

        for (int i = 0; i < 1000; i++) {
            System.out.println("concurrentTest,  i = " + i);
            Call call = new Call(i);
            try {
                Future<Integer> submitCache = exeCache.submit(call);
//                System.out.println("concurrenttest  submitcache.get() = " + submitCache.get());
                Future<Integer> submitFixed = exeFixed.submit(call);
//                System.out.println("concurrentTest " + submitFixed.get());
                Future<Integer> submitSingle = exeSingle.submit(call);
//                System.out.println("concurrentTest " + submitSingle.get());
                Future<Integer> submitSchedule = exeSchedule.submit(call);
//                System.out.println("concurrentTest  " + submitSchedule.get(100, TimeUnit.MILLISECONDS));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("concurrentTest Thread names " + set);
//        exeCache.shutdown();
//        exeFixed.shutdown();
//        exeSingle.shutdown();
//        exeSchedule.shutdown();
    }

    class Call implements Callable<Integer> {

        int id;

        public Call(int id) {
            this.id = id;
        }

        @Override
        public Integer call() throws Exception {
            count++;
            System.out.println("call id = " + id + " count = " + count + " currentThread " + Thread.currentThread().getName());
            set.add(Thread.currentThread().getName());
            Thread.sleep(10);
            return id;
        }
    }

    void exec() throws InterruptedException, ExecutionException {
        //进行异步任务列表
        List<FutureTask<Integer>> futureTasks = new ArrayList<FutureTask<Integer>>();
        //线程池 初始化十个线程 和JDBC连接池是一个意思 实现重用
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        //类似与run方法的实现 Callable是一个接口，在call中手写逻辑代码
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Integer res = new Random().nextInt(100);
                Thread.sleep(1000);
                System.out.println("任务执行:获取到结果 :" + res);
                return res;
            }
        };

        for (int i = 0; i < 10; i++) {
            //创建一个异步任务
            FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
            futureTasks.add(futureTask);
            //提交异步任务到线程池，让线程池管理任务 特爽把。
            //由于是异步并行任务，所以这里并不会阻塞
            executorService.submit(futureTask);
        }

        int count = 0;
        for (FutureTask<Integer> futureTask : futureTasks) {
            //futureTask.get() 得到我们想要的结果
            //该方法有一个重载get(long timeout, TimeUnit unit) 第一个参数为最大等待时间，第二个为时间的单位
            count += futureTask.get();
        }
        long end = System.currentTimeMillis();
        System.out.println("线程池的任务全部完成:结果为:" + count + "，main线程关闭，进行线程的清理");
        System.out.println("使用时间：" + (end - start) + "ms");
        //清理线程池
        executorService.shutdown();

    }

    class Exec implements ExecutorService {

        @Override
        public void shutdown() {

        }

        @Override
        public List<Runnable> shutdownNow() {
            return null;
        }

        @Override
        public boolean isShutdown() {
            return false;
        }

        @Override
        public boolean isTerminated() {
            return false;
        }

        @Override
        public boolean awaitTermination(long l, TimeUnit timeUnit) throws InterruptedException {
            return false;
        }

        @Override
        public <T> Future<T> submit(Callable<T> callable) {
            return null;
        }

        @Override
        public <T> Future<T> submit(Runnable runnable, T t) {
            return null;
        }

        @Override
        public Future<?> submit(Runnable runnable) {
            return null;
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
            return null;
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long l, TimeUnit timeUnit) throws InterruptedException {
            return null;
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
            return null;
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return null;
        }

        @Override
        public void execute(Runnable runnable) {

        }
    }

}
