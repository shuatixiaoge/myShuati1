public class Solution {

    // @param capacity, an integer
    class Node{
        int key, value;
        Node prev, next;
        Node(int k, int v){
            key = k;
            value = v;
            next = null;
            prev = null;
        }
    }
    HashMap<Integer,Node> map;
    private int capacity;
    private Node tail, head;
    public Solution(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    public void movetohead(int key){
        if (!map.containsKey(key)) return;
        Node node = map.get(key);
        if(node.prev != null && node.next != null){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }
    // @return an integer
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        int val = node.value;
        movetohead(key);
        return val;
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if (get(key) != -1){
            map.get(key).value = value;
            return;
        }
        else{
            map.put(key, new Node(key,value));
            if (map.size() > capacity) {
                tail = tail.prev;
                tail.next = null;
                map.remove(tail.key);
            }
            movetohead(key);
        }
    }
}
