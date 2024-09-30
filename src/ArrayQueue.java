
public class ArrayQueue<T> implements Queue<T> {

    private int head, tail;
    private int size, maxSize;
    private T[] nodes;
    
    @SuppressWarnings("unchecked")
    ArrayQueue(int n) {
        head = tail = 0;
        maxSize = n;
        size = 0;
        nodes = (T[]) new Object[n];
    }

    public T serve() {
        T temp = nodes[head];
        head = (head + 1) % maxSize;
        --size;
        return temp;
    }

    
    public void enqueue(T value) {
        nodes[tail] = value;
        tail = (tail + 1) % maxSize;
        ++size;
    }

    
    public int length() {
        return size;
    }

    
    public boolean full() {
        return maxSize == size;
    }

    public void print() {
        if (size == 0)
            return;
            
        int tmp = head;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(nodes[tmp] + " -> ");
            tmp = (tmp + 1) % maxSize;
        } System.out.println(nodes[tmp]);
    }

    public boolean isReverse(List<T> l) {

        l.findFirst();
        int index = tail;
        do {
            if (!nodes[index].equals(l.retrieve()))
                return false;
            if (index == 0)
                index = (index + 1) % maxSize;
            else
                index--;
            l.findNext();
        } while (index != head - 1);
         return true;
    }
    
}
