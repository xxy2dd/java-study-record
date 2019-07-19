package AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xxy
 * @date 2019/7/19
 * @description
 * Semaphore �����ڲ���ϵͳ���ź��������Կ��ƶԻ�����Դ�ķ��ʽ�����
 * ά���˵�ǰ���ʵĸ�����ͨ���ṩͬ������������ͬʱ���ʵĸ���
 * acquire()������Դ
 * release()�ͷ���Դ
 */
public class SemaphoreExample {
    // Semaphore �������󲢷�ִ���߳���
    private static int clientCount = 3;
    private static int totalRequestCount = 10;

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(clientCount);

        for(int i=1;i<=totalRequestCount;i++){
            final int threadNum = i;
            executorService.execute(()->{
                try{
                    // ÿ�λ�ȡһ����ɣ�Ҳ�����ò���acquire(3)ÿ�λ�ȡ������ɣ����ò���ʱ�൱�ڵ��߳�
                    semaphore.acquire();
                    test(threadNum);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
    }
    private static void test(int threadNum) throws InterruptedException{
        System.out.println("run: "+threadNum);
        Thread.sleep(1000);
    }
}
