package CollectionAndMap;

import java.util.*;

/**
 * @author xxy
 * @date 2019/7/16
 * @description
 */
public class MyLinkedList<E> {
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    transient int size = 0;
    protected transient int modeCount = 0;
    transient Node<E> first;
    transient Node<E> last;

    public MyLinkedList() {
    }

    private void linkLast(E e) {
        // ��¼��ǰβ�ڵ�
        final Node<E> l = last;
        // �½��½ڵ㣬preָ��l
        final Node<E> newNode = new Node<>(l, e, null);
        // β�ڵ�ָ��newNode
        last = newNode;
        // �ж�β�ڵ��Ƿ�Ϊ��
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modeCount++;
    }

    private E unlink(Node<E> x) {
        // ����x!=null
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;
        // �ж��Ƿ�Ϊͷ���
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        // �ж��Ƿ���β�ڵ�
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        modeCount++;
        return element;
    }

    private E unlinkFirst(Node<E> f) {
        // ���� f == first && f != null
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        // help GC
        f.next = null;
        // �ı�firstָ��ָ��
        first = next;
        // �ж�next�Ƿ�Ϊnull
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        modeCount++;
        return element;
    }

    public int size(){
        return size;
    }

    public boolean add(E e) {
        // ��β�����Ԫ��
        linkLast(e);
        return true;
    }

    public boolean remove(Object o) {
        // ��ɾ����Ԫ�����⴦��
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * ��ȡͷ����ֵ
     */
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * ��ȡͷ�ڵ㲢ɾ��
     */
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    public E getFirst(){
        final Node<E> f = first;
        if(f==null){
            throw new NoSuchElementException();
        }
        return f.item;
    }

    public E element(){
        return getFirst();
    }

    public ListIterator<E> listIterator(int index) {
        return null;
    }
}
