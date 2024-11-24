
public class User {
    public static void main(String[] args) {
        // Balanced BST
        BST<String> bst1 = new BST<String>();
        bst1.insert(50, "A");
        bst1.insert(25, "B");
        bst1.insert(75, "C");
        bst1.insert(12, "D");
        bst1.insert(37, "E");
        bst1.insert(62, "F");
        bst1.insert(87, "G");
        
        // Right-heavy BST
        BST<String> bst2 = new BST<String>();
        for(int i = 10; i <= 50; i += 10) {
            bst2.insert(i, "Val" + i);
        }
        
        // Left-heavy BST
        BST<String> bst3 = new BST<String>();
        for(int i = 50; i >= 10; i -= 10) {
            bst3.insert(i, "Val" + i);
        }

        
        BST<String> ex_bst = new BST<>();
        ex_bst.insert(8, "A");
        ex_bst.insert(12, "C");
        ex_bst.insert(14, "G");
        ex_bst.insert(10, "F");
        ex_bst.insert(4, "B");
        ex_bst.insert(5, "E");
        ex_bst.insert(2, "D");
        ex_bst.insert(1, "H");

        ex_bst.printLevelOrder();
        System.out.println("----------------------");
        int i = ex_bst.countNodesInLevel(4);
        System.out.println(i);
    }  


    public static <T> void removeFirstHalf(Queue<T> q, T x) {

        int qLength = q.length();
        for (int i = 1; i <= qLength; i++) {
            T tmp = q.serve();
            if (!tmp.equals(x))
                q.enqueue(tmp);
            else if (tmp.equals(x) && i > qLength / 2)
                q.enqueue(tmp);
            
            
                
        }
    }

    public static <T> T midElement(Stack<T> s) {

        if (s.empty())
            return null;
        
        Stack<T> tempStack = new LinkedStack<>();
        int sCount = 0;
        while (!s.empty()) {
            tempStack.push(s.pop());
            sCount++;
        }

        T tmp = null, mid = null;
        for (int i = 0; i < sCount; i++) {
            tmp = tempStack.pop();
            s.push(tmp);
            if (sCount / 2 == i)
                mid = tmp;
        }

        return mid;

    }

    public static <T> void removeElement(List<T> l, T e) {

        if (l.empty())
            return;

        l.findFirst();
        while (!l.retrieve().equals(e)) {
            l.findNext();
        }

        T tmp = null;
        if (l.retrieve().equals(e))
            tmp = l.retrieve();
        else 
            return;

        l.remove();
        while (!l.last())
            l.findNext();
        l.insert(tmp);
    }

    public static boolean checkTotalTop(Stack<Integer> st) {

        if (st.empty())
            return false;

        boolean flag = false;

        DoubleLinkedList<Integer> tmpList = new DoubleLinkedList<>();

        while (!st.empty()) 
            tmpList.insert(st.pop());

        tmpList.findFirst();
        int top = tmpList.retrieve();
        tmpList.findNext();

        int total = 0;
        while (!tmpList.last()) {
            total += tmpList.retrieve();
            tmpList.findNext();
        } total += tmpList.retrieve();

        while (!tmpList.first()) {
            st.push(tmpList.retrieve());
            tmpList.findPrevious();
        } st.push(tmpList.retrieve());
        
        if (total == top)
            flag = true;

        return flag;
    }   

    public static <T> Stack<T> concat(Stack<T> s1, Stack<T> s2) {
        DoubleLinkedList<T> tempList = new DoubleLinkedList<>();
        int s1Length = 0, s2Length = 0;
        while (!s1.empty()) {
            tempList.insert(s1.pop());
            s1Length++;
        }
        while (!s2.empty()) {
            tempList.insert(s2.pop());
            s2Length++;
        }

        tempList.print();

        LinkedStack<T> concatStack = new LinkedStack<>();

        for (int i = 0; i < s2Length; i++) {
            s2.push(tempList.retrieve());
            tempList.findPrevious();
        }
        for (int i = 0; i < s1Length; i++) {
            s1.push(tempList.retrieve());
            tempList.findPrevious();
        }

        
        while (!tempList.first()) {
            concatStack.push(tempList.retrieve());
            tempList.findPrevious();
        }
    
        return concatStack;

    }

    public static <T> boolean search(Stack<T> s, T e) {
        
        if (s.empty())
            return false;
        T temp = s.pop();
        boolean found;
        if (e.equals(temp))
            found = true;
        else
            found = search(s, e);
        s.push(temp);
        return found;
    }

    public static <T> void remove(LinkedPQ<T> pq, int p) {
        LinkedPQ<T> tempPQ = new LinkedPQ<>();

        while (pq.length() != 0) {
            PQElement<T> tmp = pq.serve();
            if (tmp.priority >= p)
                tempPQ.enqueue(tmp.data, tmp.priority);
        }

        while (tempPQ.length() != 0) {
            PQElement<T> tmp = tempPQ.serve();
            pq.enqueue(tmp.data, tmp.priority);
        }
    }

    public static <T> void split(Queue<T> q, Queue<T> oq, Queue<T> eq) {
        if (q.length() == 0)
            return;
        
        T tmp = q.serve();
        
        if (q.length() % 2 == 0)
            eq.enqueue(tmp);
        else
            oq.enqueue(tmp);

        split(q, oq, eq);

        q.enqueue(tmp);

    }

    public static <T> void circularLeftShift(List<T> l, int k) {

        l.findFirst();
        for (int i = 0; i < k; i++) {
            T tmp = l.retrieve();
            l.remove();
            while (!l.last())
                l.findNext();
            l.insert(tmp);
            l.findFirst();
        }

    }

    public static <T> void reverseCopy(DoubleLinkedList<T> l1, DoubleLinkedList<T> l2) {

        while (!l1.last()) 
            l1.findNext();
        
        while (!l1.first()) {
            l2.insert(l1.retrieve());
            l1.findPrevious();
        } l2.insert(l1.retrieve());

    }

    public static <T> void removeItem(Queue<T> q, T e, int k) {
        int eOccurrences = 0;
        boolean hasOccurredkTimes = false;
        int lastIndex = 1;
        for (int i = 0; i < q.length(); i++) {
            T temp = q.serve();
            q.enqueue(temp);
            if (temp.equals(e)) {
                eOccurrences++;
                lastIndex++;
            }
            if (eOccurrences == k)
                hasOccurredkTimes = true;
        }

        if (hasOccurredkTimes)
            for (int i = 0; i < q.length(); i++) {
                T temp = q.serve();
                q.enqueue(e);
            }
    }

    public static <T> Queue<T> concatQueues(Queue<Queue<T>> q) {

        Queue<T> concatQueue = new LinkedQueue<>();
        for (int i = 0; i < q.length(); i++) {
            Queue<T> tempQueue = q.serve();
            q.enqueue(tempQueue);
            for (int j = 0; j < tempQueue.length(); j++) {
                T temp = tempQueue.serve();
                concatQueue.enqueue(temp);
                tempQueue.enqueue(temp);
            }
            }
            return concatQueue;
    }

    public static <T> List<T> concatLists(Queue<List<T>> q) {
        List<T> concatList = new LinkedList<T>();
        for (int i = 0; i < q.length(); i++) {
            
            List<T> tempList = q.serve();
            q.enqueue(tempList);
            tempList.findFirst();

            while (!tempList.last()) {
                concatList.insert(tempList.retrieve());
                tempList.findNext();
            } concatList.insert(tempList.retrieve());
        }
        return concatList;
    }

    public static <T> boolean isReverse(DoubleLinkedList<T> dl, Queue<T> q) {

        boolean areEqual = true;

        int dlCount = 0;
        dl.findFirst();
        while (!dl.last()) {
            dl.findNext();
            dlCount++;
        } dlCount++;

        if (dlCount != q.length())
            return false;
        
        for (int i = 0; i < dlCount; i++) {
            T tmp = q.serve();
            q.enqueue(tmp);
            if (!dl.retrieve().equals(tmp))
                areEqual = false;
            dl.findPrevious();
        }

        return areEqual;

    }

    public static boolean isOrdered(Queue<Integer> q) {

        boolean flag = true;
        int e1 = q.serve();
        q.enqueue(e1);
        
        for (int i = 1; i < q.length(); i++) {
            int e2 = q.serve();
            q.enqueue(e2);
            if (e1 > e2)
                flag = false;
            e1 = e2;
        }

        return flag;
    }

    public static int stackSum(Stack<Integer> st) {
        if (st.empty())
            return 0;
        
        int x = st.pop();

        int y = stackSum(st);

        st.push(x);

        return x + y;
    }

    public static <T> void m(Stack <T> s) {
        Queue <T> q = new LinkedQueue <T>();

        while (!s.empty())
            q.enqueue (s.pop());

        int n = q.length();
        for (int i = 0; i < n; i++) {
            T e = q.serve();
            if (i % 2 == 0)
                s.push(e);
        }
    }

    public static <T> void exchange(Queue<T> q1, Queue<T> q2) {
        
        int q1Length = q1.length();
        while (q2.length() != 0) 
            q1.enqueue(q2.serve());
        
        for (int i = 0; i < q1Length; i++)
            q2.enqueue(q1.serve());
        
    }
    


    public static <T> void printQueueOfLists(Queue<List<T>> q) {
        List<T> l;
        System.out.print("->> ");
        for (int i = 0; i < q.length() - 1; i++) {
            l = q.serve();
            q.enqueue(l);
            l.print();
            System.out.print("->> ");
        }
        l = q.serve();
        q.enqueue(l);
        l.print();

    }
}
