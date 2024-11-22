
public class BT<T> {
    BTNode<T> root, current;

    BT() {
        root = current = null;
    }
    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {
        return current.data;
    }

    public void update(T val) {
        current.data = val;
    }

    public boolean insert(Relative rel, T val) {
        switch(rel) {
            case Root:
                if (root != null)
                    return false;
                current = root = new BTNode<T>(val);
                return true;
            case Parent: // Impossible case to insert a new node as current's parent
                return false;
            case LeftChild:
                if (current.left != null)
                    return false;
                current.left = new BTNode<T>(val);
                current = current.left;
                return true;
            case RightChild:
                if (current.right != null)
                    return false;
                current.right = new BTNode<T>(val);
                current = current.right;
                return true;
            default:
                return false;
        }
    }

    public void deleteSubtree() {
        if (current == root)
            current = root = null;
        else {
            BTNode<T> tmp = current;
            find(Relative.Parent);
            if (current.left == tmp)
                current.left = null;
            else
                current.right = null;
            current = root;
        }
    }

    public boolean find(Relative rel) {
        switch(rel) {
            case Root:
                current = root;
                return true;
            case LeftChild:
                if (current.left == null)
                    return false;
                current = current.left;
                return true;
            case RightChild:
                if (current.right == null)
                    return false;
                current = current.right;
                return true;
            case Parent:
                if (current == root)
                    return false;
            current = iterativeFindParent(current, root);
                return true;
            default:
                return false;
        }
    }

    public BTNode<T> recursiveFindParent(BTNode<T> node, BTNode<T> treeRoot) {
        if (treeRoot == null)
            return null;
            
        if (treeRoot.right == null && treeRoot.left == null)
            return null;
        else if (treeRoot.right == node || treeRoot.left == node)
            return node;
        else {
            BTNode<T> q = recursiveFindParent(node, treeRoot.left);
            if (q.right != null)
                return q;
            else
                return recursiveFindParent(node, treeRoot.right);
        }   
    }

    private BTNode<T> iterativeFindParent(BTNode<T> node, BTNode<T> treeRoot) {
        if (treeRoot == null || treeRoot == node) {
            return null;
        }
        LinkedStack<BTNode<T>> stack = new LinkedStack<>();
        stack.push(treeRoot);
        
        while (!stack.empty()) {
            BTNode<T> currentNode = stack.pop();
            if (currentNode.left == node || currentNode.right == node) {
                return currentNode;
            }
            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
        return null;
    }
    
}