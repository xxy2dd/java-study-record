package Concurrent;

import java.util.concurrent.*;

/**
 * @author xxy
 * @date 2019/7/5
 * @description
 */
public  final class MyThreadPool {

    // �̳߳�ά�����߳���������
    private static final int SIZE_CORE_POOL = 5;
    // �̳߳�ά�����߳��������
    private static final int SIZE_MAX_POOL = 10;
    // �̳߳�ά���߳�������Ŀ���ʱ��
    private static final int TIME_KEEP_ALIVE = 5000;
    // �̳߳���ʹ�õĻ�����д�С
    private static final int SIZE_WORK_QUEUE = 500;
    // ��������ȴ�ִ������Ķ���
    private static final BlockingQueue<Runnable> BLOCKING_QUEUE = new ArrayBlockingQueue<Runnable>(10);

    public static class MyTask implements Runnable{
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()+"�������У�");
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                System.out.println("�ж��쳣");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"���н�����");
        }
    }
    public static class MyThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable runnable){
            Thread t = new Thread(runnable);
            System.out.println("�����߳�"+t);
            return t;
        }

    }
    public static void main(String[] args){
        MyThreadFactory factory = new MyThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(SIZE_CORE_POOL,SIZE_MAX_POOL,TIME_KEEP_ALIVE, TimeUnit.MILLISECONDS, BLOCKING_QUEUE,factory);

        for(int i = 0;i<5;i++){
            MyTask runnable = new MyTask();
            threadPoolExecutor.execute(runnable);
        }
        threadPoolExecutor.shutdown();
    }




}
