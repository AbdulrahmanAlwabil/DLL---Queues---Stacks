
public class QNode<T> {

    public T data;
    public QNode<T> next;

    public QNode() {
        data = null;
        next = null;
    }

    public QNode(T value) {
        data = value;
        next = null;
    }        

}
