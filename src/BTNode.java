
public class BTNode<T> {
    T data;
    BTNode<T> left, right;

    BTNode(T val) {
        data = val;
        left = right = null;
    }

    BTNode(T val, BTNode<T> l, BTNode<T> r) {
        data = val;
        left = l;
        right = r;
    }
}