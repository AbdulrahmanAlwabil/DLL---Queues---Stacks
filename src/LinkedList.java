
public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private Node<T> current;
    
    LinkedList() {
        head = current = null;
    }

    public void findFirst() {
        current = head;
    }

    
    public void findNext() {
        current = current.next;
    }

    
    public T retrieve() {
        return current.data;
    }

    
    public void update(T e) {
        current.data = e;
    }

    
    public void insert(T e) {
        Node<T> temp;
        if (empty()) {
            head = current = new Node<T>(e);
        } else {
            temp = current.next;
            current.next = new Node<T>(e);
            current = current.next;
            current.next = temp;
        }
    }

    
    public void remove() {
        if (head == current) {
            head = head.next;
        } else {
            Node<T> tmp = head;
            while (tmp.next != current) 
                tmp = tmp.next;
            tmp.next = current.next;
        }
        
        if (current.next == null)
            current = head;
        else
            current = current.next;
        
    }

    
    public boolean full() {
        return false;
    }

    
    public boolean empty() {
        return head == null;
    }

    
    public boolean last() {
        return current.next == null;
    }

    // Problem set methods 
    public void clear() {
        head = current = null;
    }

    public void print() {
        current = head;
        while (current.next != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println(current.data);
    }

    public void reverse() {
        Node<T> p = null, c = head, n = null;
        while (c != null) {
            n = c.next;
            c.next = p;
            p = c;
            c = n;
        }
        head = p;
    }

    public void moveHeadToLast() {
        Node<T> tmp = head;

        while (current.next != null) 
            current = current.next;
        
        head = head.next;
        current.next = tmp;
        // tmp.next = null;
    }
    
}
