import java.util.HashMap;

public class LRUCache {
    private final static int dummyValue = -1;
    private final static Node dummyNode = new Node(dummyValue,dummyValue);

    private final int capacity;
    private final HashMap<Integer, Node> keyToNode;

    private Node head;
    private Node tail;

    public LRUCache(final int capacity) {
        this.capacity = capacity;
        this.keyToNode = new HashMap<>();
        this.head = dummyNode;
        this.tail = dummyNode;
        head.next = tail;
        tail.prev = head;
    }

    /**
     * @param key cache key
     * @return cache value
     */
    public int get(final int key) {
        moveToHead(key);
        return keyToNode.getOrDefault(key, dummyNode).value;
    }

    /**
     * @param key
     * @param value
     */
    public void put(final int key, final int value) {
        if (keyToNode.containsKey(key)) {
            keyToNode.get(key).value = value;
        } else {
            keyToNode.put(key, new Node(key, value));
            /* if eviction happens, kick the least recent used node*/
            if (keyToNode.size() > capacity) {
                Node readyToBeKickedNode = tail.prev;

                tail.prev = readyToBeKickedNode.prev;
                readyToBeKickedNode.prev.next = tail;

                keyToNode.remove(readyToBeKickedNode.key);

                readyToBeKickedNode = null; // for GC
            }
        }
        moveToHead(key);
    }

    /**
     * If cache hit, move current node to head
     */
    private void moveToHead(final int key) {
        if (keyToNode.containsKey(key)) {
            final Node currNode = keyToNode.get(key);
            final Node prevNode = currNode.prev;
            final Node nextNode = currNode.next;

            if (prevNode != null && nextNode != null) {
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }

            currNode.next = head.next;
            currNode.prev = head; // previous is dummyNode

            head.next.prev = currNode;
            head.next = currNode;
        }
    }

    /**
     * double linked list.
     * It contains previous and next Node reference. Initial values are dummyNode(-1,-1)
     */
    private static class Node {
        private Node prev;
        private Node next;
        private int value;
        private final int key;

        private Node(final int key, final int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private void print(Node node) {
        System.out.println("[" + node.key + "," + node.value + "]");
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2,1);
        lruCache.put(1,1);
        lruCache.put(2,3);
        lruCache.put(4,1);
        lruCache.print(lruCache.head.next);
        lruCache.print(lruCache.head.next.next);
        System.out.println(lruCache.get(1));
    }
}
