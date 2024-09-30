
public class LinkedPQ<T> {
    
    private int size;
    private PQNode<T> head;

    LinkedPQ() {
        head = null;
        size = 0;
    }

    public void enqueue(T value, int pty) {
        PQNode<T> temp = new PQNode<T>(value, pty);
        if (size == 0 || (pty > head.priority)) {
            temp.next = head;
            head = temp;
        } else {
            PQNode<T> p = head;
            PQNode<T> q = null;
            while (p != null && pty <= p.priority) {
                q = p;
                p = p.next;
            }
            temp.next = p;
            q.next = temp;
        }
        size++;
    }

    public PQElement<T> serve() {
        PQNode<T> temp = head;
        PQElement<T> pqe = new PQElement<T>(temp.data, temp.priority);
        head = head.next;
        --size;
        return pqe;
    }

    public int length() {
        return size;
    }

    public boolean full() {
        return false;
    }

    public void print() {
        PQNode<T> tmp = head;
        while (tmp.next != null) {
            System.out.print(tmp.data + " -> ");
            tmp = tmp.next;
        } System.out.println(tmp.data);
    }

}
