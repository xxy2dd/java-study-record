package JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xxy
 * @date 2019/7/19
 * @description
 * FutureTask �̳�Runnable��Future�ӿڣ����Լȿ��Ե���һ������ִ�У�Ҳ�����з���ֵ
 * �����첽��ȡִ�н����ȡ��ִ������ĳ�����
 * ��һ������������Ҫִ�кܳ�ʱ��ʱ��������FutureTask����װ�������
 */
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException{
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for(int i=0;i<100;i++){
                    Thread.sleep(10);
                    result += i;
                }
                return result;
            }
        });
        Thread computeThread = new Thread(futureTask);
        computeThread.start();
        Thread otherThread = new Thread(()->{
            System.out.println("other task is running...");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        otherThread.start();
        int res = 0;
        try {
           res =  futureTask.get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(res);
    }
}
