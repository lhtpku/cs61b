public class LinkedListDeque<T> {

    public class Node {
        T item;
        Node prev;
        Node next;

        public Node(T x) {
            item = x;
            prev = null;
            next = null;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        Node tmp = other.sentinel;
        for (int i = 0; i < other.size(); i++) {
            tmp = tmp.next;
            addLast((T) tmp.item);
        }
    }

    public void addFirst(T item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node first = new Node(item);
        first.prev = sentinel;
        first.next = sentinel.next;

        sentinel.next.prev = first;
        sentinel.next = first;

        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        Node tmp = sentinel.next;
        T res = tmp.item;
        sentinel.next = tmp.next;
        tmp.next.prev = sentinel;
        size--;
        tmp = null;
        return res;
    }


    public void addLast(T item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node last = new Node(item);
        last.next = sentinel;
        last.prev = sentinel.prev;

        sentinel.prev.next = last;
        sentinel.prev = last;

        size++;
    }

    public T removeLast() {
        if (size == 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        Node tmp = sentinel.prev;
        T res = tmp.item;
        sentinel.prev = tmp.prev;
        tmp.prev.next = sentinel;
        size--;
        tmp = null;
        return res;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node tmp = sentinel.next;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(tmp.next + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public T get(int index) {
        if (size == 0 || index > size) {
            return null;
        }
        Node tmp = sentinel.next;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node curr, int index) {
        if (index == 0) {
            return curr.item;
        } else {
            return getRecursiveHelper(curr.next, index - 1);
        }
    }
}
