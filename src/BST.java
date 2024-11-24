
import java.util.LinkedList;
import java.util.Queue;
public class BST<T> {

    public int countNodesInLevel(int level) {
        return countNodesInLevelHelper(root, level, 0);
    }

    private int countNodesInLevelHelper(BSTNode<T> node, int targetLevel, int currentLevel ) {
        if (node == null)
            return 0;
        
        int count = countNodesInLevelHelper(node.left, targetLevel, currentLevel + 1) + 
        countNodesInLevelHelper(node.right, targetLevel, currentLevel + 1);

        if (currentLevel == targetLevel)
            count++;

        return count;
        
    }

    public int countNodesIn(int k) {
        BSTNode<T> tmp = root;

        while (tmp != null) {
            if (tmp.key > k)
                tmp = tmp.right;
            else if (tmp.key < k)
                tmp = tmp.left;
            else
                return countNodesInHelper(tmp);
        }
        return 0;
    }

    private int countNodesInHelper(BSTNode<T> node) {
        if (node == null)
            return 0;

        int count = countNodesInHelper(node.left) + countNodesInHelper(node.right);

        return count + 1;
    }

    public int deepestKey(BSTNode<T> node) {
        if (empty())
            return 0;

        return deepestKeyHelper(node, 0);
    }

    private int deepestKeyHelper(BSTNode<T> node, int depth) {
        if (node == null)
            return 0;
        
        int depth1 = deepestKeyHelper(node.left, depth + 1) ;
        int depth2 = deepestKeyHelper(node.right, depth + 1);


        
        return depth1 > depth2 ? node.left.key : node.right.key;

        
    }

    public int maxKey(int k) {
        findkey(k);

        while (current.right != null) {
            current = current.right;
        }

        return current.key;
    }

    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(BSTNode<T> node) {
        if (node == null)
            return 0;

        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);
        
        
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
        
    }

    public boolean isLeaf() {
        if (empty())
            return false;
        return current.left == null && current.right == null;
    }

    public int countLeafs() {
        return countLeafsHelper(root);
    }

    private int countLeafsHelper(BSTNode<T> node) {
        if (node == null)
            return 0;
        int count = countLeafsHelper(node.left) + countLeafsHelper(node.right);
        
        if (node.left == null && node.right == null)
            return count + 1;
        
        return count;
        
    }

    public boolean inSubtree(int k1, int k2) {
        if (!findkey(k1)) {
            return false;
        }

        BSTNode<T> tmp = current;
        while (tmp != null) {
            if (tmp.key > k2)
                tmp = tmp.left;
            else if (tmp.key < k2)
                tmp = tmp.right;
            else
                return true;
        }
        
        return false;
    }

    public void swapData(int k) {
        if (root.key == k)
            return;
        
        BSTNode<T> child = root, parent = null;

        while (child != null && child.key != k) {
            parent = child;
            if (k > child.key)
                child = child.right;
            else if (k < child.key)
                child = child.left;
        }

        if (child == null)
            return;
        
        T tmp = parent.data;
        parent.data = child.data;
        child.data = tmp;
    }

    public T secondMin() {
        BSTNode<T> child = root;
        BSTNode<T> parent = root;

        while (child.left != null) {
            parent = child;
            child = child.left;
        }

        if (child.right == null)
            return parent.data;
        else {
            parent = child;
            child = child.right;
            while (child.left != null) {
                parent = child;
                child = child.left;
            }
            return child.data;
        }
    }

    public boolean isSearchPathLinear(int k) {

        BSTNode<T> tmp = root;

        if (k > tmp.key) {
            while (tmp != null && tmp.key != k)
                tmp = tmp.right;
        } else if (k < tmp.key) {
            while (tmp != null && tmp.key != k)
                tmp = tmp.left;
        }

        if (tmp == null)
            return false;
        
        return true;
    }

    public boolean isLeftRangeLarger() {
        return isLeftRangeLargerHelper(root);
    }

    private boolean isLeftRangeLargerHelper(BSTNode<T> node) {
        
        BSTNode<T> rightMostNode = root.right, leftMostNode = root.left;
        int rightNodeKey = 0, leftNodeKey = 0;
        while (rightMostNode != null) {
                rightNodeKey = rightMostNode.key;
                rightMostNode = rightMostNode.right;
        }

        while (leftMostNode != null) {
            leftNodeKey = leftMostNode.key;
            leftMostNode = leftMostNode.left;
        }

        return (root.key - leftNodeKey) >(rightNodeKey - root.key);
    }

    public LinkedQueue<T> intervalSearch(int k1, int k2) {
        LinkedQueue<T> queue = new LinkedQueue<>();
        intervalSearchHelper(root, k1, k2, queue);
        return queue;
    }

    private void intervalSearchHelper(BSTNode<T> node, int k1, int k2, LinkedQueue<T> q) {
        if (node == null)
            return;
        
        intervalSearchHelper(node.left, k1, k2, q);
        intervalSearchHelper(node.right, k1, k2, q);

        if (node.key >= k1 && node.key <= k2) {
            q.enqueue(node.data);
        }
    }

    public int nbLess(int k) {
        return nbLessHelper(k, root);
    }

    private int nbLessHelper(int k, BSTNode<T> node) {
        if (node == null)
            return 0;
    
        int counter = nbLessHelper(k, node.left) + nbLessHelper(k, node.right);
        if (node.key <= k) 
            return counter + 1;

        return counter;    
    }

    BSTNode<T> root, current;
	
	public BST() {
		root = current = null;
	}
	
	public boolean empty() {
		return root == null;
	}
	
	public boolean full() {
		return false;
	}
	
	public T retrieve () {
		return current.data;
	}

    public boolean findkey(int tkey) {
		BSTNode<T> p = root, q = root;
				
		if(empty())
			return false;
		
		while(p != null) {
			q = p;
			if(p.key == tkey) {
				current = p;
				return true;
			}
			else if(tkey < p.key)
				p = p.left;
			else
				p = p.right;
		}
		
		current = q;
		return false;
	}


    public boolean insert(int k, T val) {
		BSTNode<T> p, q = current;
		
		if(findkey(k)) {
			current = q;  // findkey() modifies current
			return false; // key already in the BST
		}
		
		p = new BSTNode<T>(k, val, null, null);
		if (empty()) {
			root = current = p;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (k < current.key)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

    public boolean remove_key(int tkey) {
		
        // Search for tkey
        int k = tkey;
        BSTNode<T> p = root;
        BSTNode<T> q = null; // Parent of p
        while (p != null) {
            if (k < p.key) {
                q = p;
                p = p.left;
            } else if (k > p.key) {
                q = p;
                p = p.right;
            } else { // Have found the key 
                if (p.left != null && p.right != null) { // Deleted node has 2 children
                    BSTNode<T> min = p.right;
                    q = p;
                    while (min.left != null) { // This loop finds min in right subtree
                        q = min;
                        min = min.left;
                    }
                    // After the loop:
                    // p is wanted for deletion
                    // q is parent of min
                    // min is the min in right subtree

                    // Swapping min & p
                    p.key = min.key;
                    p.data = min.data;
                    k = min.key;
                    p = min;
                }

                // Now wanted to be deleted is p
                if (p.left != null) // if one child on left
                    p = p.left;
                else // if one child on right or no children
                    p = p.right;

                if (q == null)  // No parent for p
                    root = p;
                else {
                    if (k < q.key)
                        q.left = p;
                    else
                        q.right = p;
                }
                
                current = root;
                return true;
            }
        }
        
        return false; // Key not found
	}

    public void removeLarger(int k) {
        removeLarger(k, root);
    }

    public void removeLarger(int k, BSTNode<T> node) {
        
        if (node == null)
            return;
        
        removeLarger(k, node.right);
        removeLarger(k, node.left);

        if (node.key > k)
            remove_key(node.key);
        
    }

    public void printLevelOrder() {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
    
        Queue<BSTNode<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);  // Level marker
    
        while (!queue.isEmpty()) {
            BSTNode<T> temp = queue.poll();
            
            if (temp == null) {
                System.out.println();  // New level
                if (!queue.isEmpty()) {
                    queue.add(null);  // Add marker for next level
                }
            } else {
                // Print node key and value
                System.out.print("[" + temp.key + ":" + temp.data + "] ");
                
                // Add children to queue
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
    }

}
