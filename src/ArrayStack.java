public class ArrayStack<T> implements Stack<T> {

    private int maxsize;
    private int top;
    private T[] nodes;
    
    @SuppressWarnings("unchecked")
    ArrayStack(int n) {
        maxsize = n;
        top = -1;
        nodes = (T[]) new Object[n];
    }

    public T pop() {
        return nodes[top--];
    }

    
    public void push(T e) {
        nodes[++top] = e;
    }

    
    public boolean empty() {
        return top == -1;
    }


    public boolean full() {
        return top == maxsize - 1;
    }

    public void print() {
        if (top == -1)
            return;
        for (int i = top; i > 0; i--)
            System.out.print(nodes[i] + " -> ");
        System.out.println(nodes[0]);
    }
    
}
