public interface Queue<T> {

    public T serve();
    public void enqueue(T value);
    public int length();
    public boolean full();
    public void print();
}
