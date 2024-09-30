
public class ArrayList<T> implements List<T>{

    private int maxSize;
    private int size;
    private int current;
    private T[] nodes;
    
    @SuppressWarnings("unchecked")
    ArrayList(int n) {
        nodes = (T[]) new Object[n];
        maxSize = n;
        size = 0;
        current = -1;
    }

    public void findFirst() {
        current = 0;
    }

    
    public void findNext() {
        current++;
    }

    
    public T retrieve() {
        return nodes[current];
    }

    
    public void update(T e) {
        nodes[current] = e;
    }

    
    public void insert(T e) {
        for (int i = size - 1; i > current; i--) {
            nodes [i + 1] = nodes[i];
        }
        current++;
        nodes[current] = e;
        size++;
    }

    
    public void remove() {
        for (int i = current + 1; i < size; i++) {
            nodes[i - 1] = nodes[i];
        }
        --size;
        if (size == 0)
        current = -1;
        else if (current == size)
            current = 0;
    }

    
    public boolean full() {
        return size == maxSize;
    }

    
    public boolean empty() {
        return size == 0;
    }

    
    public boolean last() {
        return current == size - 1;
    }

    // Problem set methods
    @SuppressWarnings("unchecked")
    public void clear() {
        if (empty())
            return;
        nodes = (T[]) new Object[maxSize];
        current = -1;
        size = 0;
    }

    public void print() {
        
        current = 0;
        while (!last()) {
            System.out.println(nodes[current] + " -> ");
            current++;
        }
        System.out.println(nodes[current]);
    }
}
