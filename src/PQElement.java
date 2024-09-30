public class PQElement<T> {
    
    public T data;
    public int priority;

    PQElement(T value, int p) {
        data = value;
        priority = p;
    }

}
