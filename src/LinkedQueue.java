
public class LinkedQueue<T> implements Queue<T> {

    private QNode<T> head;
    private QNode<T> tail;
    private int size;

    LinkedQueue() {
        head = tail = null;
        size = 0;
    }

    public T serve() {
        T temp = head.data;
        head = head.next;
        --size;
        if (size == 0)
            tail = null;
        return temp;
    }

    
    public void enqueue(T value) {
        if (head == null) {
            head = tail = new QNode<T>(value);
        } else {
            tail.next = new QNode<T>(value);
            tail = tail.next;
        }
        size++;
    }

    
    public int length() {
        return size;
    }

    
    public boolean full() {
        return false;
    }

    public void print() {
        QNode<T> tmp = head;
        for (int i = 0; i < length(); i++) {
            if (tmp.next != null)
                System.out.print(tmp.data + " -> ");
            else 
                System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    public void reverse() {
        QNode<T> p = null, c = head, n = null;
        while (c != null) {
            n = c.next;
            c.next = p;
            p = c;
            c = n;
        }
        head = p;
    }
}
