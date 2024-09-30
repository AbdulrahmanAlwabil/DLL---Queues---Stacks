
public class PQNode<T> {
    public T data;
    public int priority;
    public PQNode<T> next;
    
    PQNode() {
        next = null;
    }

    PQNode(T value, int priority) {
        data = value;
        this.priority = priority;
    }

}
