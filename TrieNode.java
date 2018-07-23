public class TrieNode {
    HashMap<Character, TrieNode> map;
    boolean isLeaf;
    public TrieNode() {
        this.map = new HashMap<>();
        this.isLeaf = false;
    }
}

public void insert(word) {
    char[] chars = word.toCharArray();
    TrieNode curr = root;
    for (char c : chars) {
        if (!curr.map.containsKey(c)) {
            TrieNode node = new TrieNode();
            curr.map.put(c, node);
        }
        curr = curr.map.get(c);
    }
    curr.isLeaf = true;
}
