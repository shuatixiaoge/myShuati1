// boggle game，给一个board和字典，找出board上出现最多的单词集合，单词不能重叠在同一个位置
//
// 例如
//
// board:
// {'a', 'b', 'c'},
// {'d', 'e', 'f'},
// {'g', 'h', 'i'}
//
// dict:
// ["abc", "cfi", "beh", "defi", "gh"]
// 答案应该是 ["abc", "defi", "gh"]
import java.util.*;
class BoggleGame {
    // 从每个点开始，找从这个点出发的所有单词组合
    public List<String> getAllWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (words.length == 0 || board.length == 0 || board[0].length == 0) return res;;
        Trie t = new Trie();
        for (String w : words) {
            t.add(w);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        findWords(board, t.root, visited, 0, 0, res, new ArrayList<>());
        return res;
    }

    private void findWords(char[][] board, TrieNode root, boolean[][] visited, int x, int y, List<String> res, List<String> words) {
        for (int i = x; i < board.length; i++) {
            for (int j = y; j < board[0].length; j++) {
                List<List<Integer>> pathList = new ArrayList<>();
                // 获得从当前点开始的所有可能单词的path
                findPath(board, root, visited, i, j, pathList, new ArrayList<>());
                for (List<Integer> path : pathList) {
                    String w = "";
                    // 设置visited为当前使用单词
                    for (int t : path) {
                        int x1 = t / board[0].length;
                        int y1 = t % board[0].length;
                        visited[x1][y1] = true;
                        w += board[x1][y1];
                    }
                    words.add(w);
                    // 只要更新了words，就保存一次words
                    if (words.size() > res.size()) {
                        // can't do res = new ArrayList<>(words), since this would be mutable for words. if you get a new one, it won't be muttable anymore
                        res.clear();
                        res.addAll(words);
                    }
                    findWords(board, root, visited, i, j, res, words);

                    words.remove(words.size() - 1);
                    // set back visited
                    for (int t : path) {
                        int x1 = t / board[0].length;
                        int y1 = t % board[0].length;
                        visited[x1][y1] = false;
                    }
                }
            }
            //// 只有第x行是从y开始，后面都从0开始
            y = 0;
        }
    }

    private void findPath(char[][] board, TrieNode root, boolean[][] visited, int x, int y, List<List<Integer>> pathList, List<Integer> path) {
        if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1 || visited[x][y] || !root.map.containsKey(board[x][y])) return;
        root = root.map.get(board[x][y]);
        if (root.isLeaf) {
            List<Integer> tmp = new ArrayList<>(path);
            tmp.add(x * board[0].length + y);
            pathList.add(tmp);
            return;
        }
        visited[x][y] = true;
        path.add(x * board[0].length + y);
        findPath(board, root, visited, x + 1, y, pathList, path);
        findPath(board, root, visited, x, y + 1, pathList, path);
        findPath(board, root, visited, x - 1, y, pathList, path);
        findPath(board, root, visited, x, y - 1, pathList, path);
        path.remove(path.size() - 1);
        visited[x][y] = false;
    }

    class TrieNode {
        HashMap<Character, TrieNode> map;
        boolean isLeaf;
        public TrieNode() {
            this.map = new HashMap<>();
            this.isLeaf = false;
        }
    }

    class Trie {
        TrieNode root;
        public Trie() {
            this.root = new TrieNode();
        }

        public void add(String word) {
            TrieNode node = this.root;
            for (char c : word.toCharArray()) {
                if (node.map.containsKey(c)) {
                    node = node.map.get(c);
                } else {
                    TrieNode tmp = new TrieNode();
                    node.map.put(c, tmp);
                    node = tmp;
                }
            }
            node.isLeaf = true;
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'}
        };
        String[] words = new String[] {
            "abc", "cfi", "beh", "defi", "gh", "a", "b"
        };

        BoggleGame s = new BoggleGame();
        for (String str : s.getAllWords(board, words)) {
            System.out.println(str);
        }
    }
}
