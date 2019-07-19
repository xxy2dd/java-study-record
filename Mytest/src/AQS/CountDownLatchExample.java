package AQS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xxy
 * @date 2019/7/19
 * @description
 * CountDownLatch ��������һ���̵߳ȴ�����߳�
 * ά��һ��������cnt,ÿ�ε���countDown()��������cnt-1,
 * ����0��ʱ����Щ�������await()�������ڵȴ����߳̾ͻᱻ����
 */
public class CountDownLatchExample {
    /**
     * �߳�����
      */
    private static int threadCount = 10;

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i = 1;i<=threadCount;i++){
            final int threadNum = i;
            executorService.execute(()->{
                try{
                    test(threadNum);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    countDownLatch.countDown();
                }
            });
        }
        try {
            // ������ȴ�������������ɵ�ʱ�� �ټ������½��С�
            countDownLatch.await();
            // �����߳������10 ������δ��ɣ����п��ܻ�ִ�����߳�
//            countDownLatch.await(10, TimeUnit.MILLISECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Finished!");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException{
        Thread.sleep(10);
        System.out.println("run: Thread" + threadNum);
        Thread.sleep(10);
    }
}
