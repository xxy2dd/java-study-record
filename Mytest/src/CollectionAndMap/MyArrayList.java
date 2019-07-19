package CollectionAndMap;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author xxy
 * @date 2019/5/8
 * @description
 */
public class MyArrayList<E>  {
    private static final long serialVersionUID = 8683452581122892189L;
    /**
     * ��ʼ����Ϊ10
     */
    private static final int  DEFAULT_CAPACITY = 10;
    /**
     * ������
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     * ����Ŀ�����ʵ���������Ƕ���Ĭ�ϴ�С�Ŀ����飬ʹ��������
     * EMPTY_ELEMENTDATA����������֪������ӵ�һ��Ԫ��ʱ������Ҫ���Ӷ��١�
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    /**
     * �洢ArrayListԪ�ص����飬ArrayList���������������������������
     ��elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATAʱ��
     ��һ�����Ԫ�غ�������Ĭ������10����˽�������Ƕ����ķ��ʡ�

     ������Ȼ��������transient���Σ�������ʵ����readObject��writeObject
     for (int i = 0; i < size; i++)
     s.writeObject(elementData[i]);
     �鿴Դ���֪,ʵ�������л�
     */
    transient Object[] elementData;

    private int size;
    /**
     * ��¼List���޸ĵĴ���
     */
    protected transient int modCount = 0;

    /**
     * �޲ι��캯��
     */
    public MyArrayList(){
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * ����ʼ�����Ĺ��캯��
     * @param initialCapacity
     */
    public MyArrayList(int initialCapacity){
        if(initialCapacity > 0){
            // ��������Ϊ����ֵ��С������
            this.elementData = new Object[initialCapacity];
        }else if(initialCapacity == 0){
            // ����������
            this.elementData = EMPTY_ELEMENTDATA;
        }else{
            // �����Ϸ����ж�
            throw new IllegalArgumentException("Illegal Capacity:" + initialCapacity);
        }
    }

    /**
     *  ����һ������ָ�����ϵ�Ԫ�ص��б����������ɼ��ϵĵ��������ص�˳��
     * @param c
     */
    public MyArrayList(Collection<? extends E> c){
        // Arrays �� copyOf �����ϻ����� System.arraycopy ʵ�ֵģ���JVM �ṩ�����鿽��ʵ��
        elementData = c.toArray();
        // ���鳤���Ƿ�Ϊ0 �ж� Ϊ0��ֵΪ������
        if((size=elementData.length)!=0){
            // �����и�bug��c.toArray()���ܲ��᷵��Object[] ,
            if(elementData.getClass()!=Object[].class){
                elementData = Arrays.copyOf(elementData,size,Object[].class);
            }
        }else{
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * ��Ԫ��׷�ӵ�Listβ��
     * @param e
     * @return
     */
    public boolean add(E e){
        // �ж������Ƿ��㹻
        ensureCapacityInternal(size+1);
        elementData[size++] = e;
        return true;

    }
    private void ensureCapacityInternal(int minCapacity){
        if(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            minCapacity = Math.max(DEFAULT_CAPACITY,minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }
    private void ensureExplicitCapacity(int minCapacity){
        modCount++;
        if(minCapacity - elementData.length > 0){
            // ����
            grow(minCapacity);
        }

    }
    private int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private void grow(int minCapacity){
        // ������ԭ��������1.5��
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity>>1);
        if(newCapacity - minCapacity < 0 ){
            newCapacity = minCapacity;
        }
        if(newCapacity < MAX_ARRAY_SIZE){
            newCapacity = hugeCapacity(minCapacity);
        }
        // ����ԭ������Ϣ���ײ㻹�ǵ��õ� System.arraycopy ���ۺܸ�
        elementData = Arrays.copyOf(elementData,newCapacity);
    }
    private int hugeCapacity(int minCapacity){
        if(minCapacity<0){
            throw new OutOfMemoryError();
        }
        return (minCapacity>MAX_ARRAY_SIZE)?Integer.MAX_VALUE:MAX_ARRAY_SIZE;
    }
    public E remove(int index){
        rangeCheck(index);
        modCount++;

        E oldValue = elementData(index);

        int numMoved = size-index-1;
        if(numMoved>0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }
    private void rangeCheck(int index){
        if(index>=size){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }
    private String outOfBoundsMsg(int index){
        return "Index: " + index +", Size: " +size;
    }
    @SuppressWarnings("unchecked")
    private  E elementData(int index){
        return (E) elementData[index];
    }
}
