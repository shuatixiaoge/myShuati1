public class Solution {
    class TreeNode {
    TreeNode prev, next;
    int val, key;
    public TreeNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}
HashMap<Integer, TreeNode> map;
int size;
TreeNode head, tail;

public LRUCache(int capacity) {
    this.map = new HashMap<>();
    this.size = capacity;
    this.head = new TreeNode(-1, -1);
    this.tail = new TreeNode(-1, -1);
    this.head.next = this.tail;
    this.tail.prev = this.head;
}

public int get(int key) {
   if (map.containsKey(key)) {
       moveToTop(key);
       return map.get(key).val;
   } else {
       return -1;
   }
}

public void put(int key, int value) {
    if (!map.containsKey(key)) {
        map.put(key, new TreeNode(key, value));
    }
    map.get(key).val = value;
    moveToTop(key);
    if (map.size() > this.size) {
        map.remove(tail.prev.key);
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
    }
}

private void moveToTop(int key) {
    if (!map.containsKey(key)) return;
    TreeNode node = map.get(key);
    // for new node there is no prev
    if (node.prev != null)
        node.prev.next = node.next;
    if (node.next != null)
        node.next.prev = node.prev;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
    node.prev = head;
}
}
