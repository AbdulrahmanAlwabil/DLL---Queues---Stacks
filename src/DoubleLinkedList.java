
public class DoubleLinkedList<T> {
    
    private Node<T> head;
    private Node<T> current;

    DoubleLinkedList() {
        head = current = null;
    }

    public boolean empty() {
        return head == null;
    }

    public boolean last() {
        return current.next == null;
    }

    public boolean first() {
        return current.previous == null;
    }

    public boolean full() {
        return false;
    }

    public void findFirst() {
        current = head;
    }

    public void findNext() {
        current = current.next;
    }

    public void findPrevious() {
        current = current.previous;
    }

    public T retrieve() {
        return current.data;
    }

    public void update(T value) {
        current.data = value;
    }

    public void insert(T value) {
        Node<T> tmp = new Node<T>(value);
        if(empty()) {
            current = head = tmp;
        }
        else {
            tmp.next = current.next;
            tmp.previous = current;
            if(current.next != null)
            current.next.previous = tmp;
            current.next = tmp;
            current = tmp;
        }
    }

    public void remove() {
        if (current == head) {
            head = head.next;
            if (head != null) {
                head.previous = null;
            }
        } else {
            current.previous.next = current.next;
            if (current.next != null)
                current.next.previous = current.previous;
        }

        if (current.next == null)
            current = head;
        else
            current = current.next;
    }

    public void print() {
        current = head;
        while (current.next != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        } System.out.println(current.data);
    }

    public void removeBetween(T e1, T e2) {
        current = head;
        Node<T> e1Node = null, e2Node = null;
        while (current.next != null) {
            if (e1.equals(current.data))
                e1Node = current;
            else if (e2.equals(current.data))
                e2Node = current;
            if (e1Node != null && e2Node != null)
                break;
            current = current.next;
        }

        if (e1Node == null || e2Node == null)
            return;

        e1Node.next = e2Node;
        e2Node.previous = e1Node;
        current = head;
    }

}
