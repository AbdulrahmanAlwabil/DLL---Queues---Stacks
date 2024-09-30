
public class User {
    public static void main(String[] args) {
        ArrayQueue<Queue<Integer>> q = new ArrayQueue<>(15);
        LinkedQueue<Integer> q1 = new LinkedQueue<>();
        LinkedQueue<Integer> q2 = new LinkedQueue<>();
        LinkedQueue<Integer> q3 = new LinkedQueue<>();

        LinkedPQ<Integer> pq1 = new LinkedPQ<>();

        DoubleLinkedList<Integer> dl1 = new DoubleLinkedList<>();
        DoubleLinkedList<Integer> dl2 = new DoubleLinkedList<>();

        

        LinkedStack<Integer> ls = new LinkedStack<>();
        ArrayStack<Integer> as = new ArrayStack<>(10);
        
        for (int i = 1; i <= 5; i++) {
            dl1.insert(i);
        }

        for (int i = 5; i >= 1; i--) {
            q1.enqueue(i);
        }

        dl1.print();
        q1.print();
        System.out.println(isReverse(dl1, q1));

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
