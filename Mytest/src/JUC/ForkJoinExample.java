package JUC;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author xxy
 * @date 2019/7/19
 * @description
 * ��Ҫ���ڲ��м���
 * ��MapReduceԭ�����ƣ����ǰѴ�ļ��������ֳɶ��С���ﲢ�м���
 *
 */
public class ForkJoinExample extends RecursiveTask<Integer> {
    private final int threshold = 5;
    private int first;
    private int last;

    public ForkJoinExample(int first,int last){
        this.first = first;
        this.last = last;
    }
    @Override
    protected Integer compute(){
        int result = 0;
        if(last-first<=threshold){
            // �����㹻С��ֱ�Ӽ���
            for(int i=first;i<=last;i++){
                result +=i;
            }
        }else{
            // ��ֳ�С����
            int middle=first+(last-first)/2;
            ForkJoinExample leftTask = new ForkJoinExample(first,middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle,last);
            leftTask.fork();
            rightTask.fork();
            result = leftTask.join()+rightTask.join();
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException,InterruptedException{
        ForkJoinExample example = new ForkJoinExample(1,10000);
        // ForkJoinPool�̳߳�������������̳߳أ��߳�����ȡ����CPU����
        // ʵ�ֹ�����ȡ�㷨���CPU������
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future future = forkJoinPool.submit(example);
        System.out.println(future.get());
    }
}
