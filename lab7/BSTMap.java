import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;
    private int size;

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public BSTMap() {
        this.clear();
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    private V getHelper(K key, Node p) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        } else if (cmp > 0) {
            return getHelper(key, p.right);
        } else {
            return p.value;
        }
    }

    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.value = value;
        }
        return p;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> tmp = new HashSet<>();
        keySet(root, tmp);
        return tmp;
    }

    private void keySet(Node N, Set<K> tmp) {
        if (N == null) {
            return;
        }
        tmp.add(N.key);
        keySet(N.left, tmp);
        keySet(N.right, tmp);
    }

    private Node remove(Node N, K key) {
        if (N == null) {
            return null;
        }
        int cmp = N.key.compareTo(key);
        if (cmp > 0) {
            N.right = remove(N.right, key);
        } else if (cmp < 0) {
            N.left = remove(N.left, key);
        } else {
            if (N.left == null) {
                return N.right;
            } else if (N.right == null) {
                return N.left;
            } else {
                Node tmp = N;
                N = min(tmp.right);
                N.right = removeMin(tmp.right);
                N.left = tmp.left;
            }
        }
        size--;
        return N;
    }

    private Node removeMin(Node T) {
        if (T.left == null) {
            return T.right;
        }
        T.left = removeMin(T.left);
        size--;
        return T;
    }

    private Node min(Node T) {
        if (T.left == null) {
            return T;
        }
        return min(T.left);
    }

    @Override
    public V remove(K key) {
        V temp = getHelper(key, root);
        if (temp == null) {
            return null;
        } else {
            root = remove(root, key);
        }
        return temp;
    }

    @Override
    public V remove(K key, V value) {
        V temp = getHelper(key, root);
        if (!temp.equals(value)) {
            return null;
        } else {
            root = remove(root, key);
        }
        return temp;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
