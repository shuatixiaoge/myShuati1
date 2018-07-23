// 给出一系列 不重复的单词，找出所有用这些单词能构成的 单词平方。
// 一系列的单词构成了一个有效的单词矩阵, 如果从第 k 行读出来的单词和第 k 列读出来的单词相同(0 <= k < max(numRows, numColumns))，那么就是一个单词平方.
// 例如，单词序列为 ["ball","area","lead","lady"] ,可以构成一个单词矩阵因为对于每一行和每一列读出来的单词都是相同的。
//
// b a l l
// a r e a
// l e a d
// l a d y
public class Solution {
     class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();
            for (String w : words) {
                TrieNode cur = root;
                for (char ch : w.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null)
                        cur.children[idx] = new TrieNode();
                    cur.children[idx].startWith.add(w);
                    cur = cur.children[idx];
                }
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null)
                    return ans;

                cur = cur.children[idx];
            }
            ans.addAll(cur.startWith);
            return ans;
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0)
            return ans;
        int len = words[0].length();
        Trie trie = new Trie(words);
        List<String> ansBuilder = new ArrayList<>();
        for (String w : words) {
            ansBuilder.add(w);
            search(len, trie, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }

        return ans;
    }

    private void search(int len, Trie tr, List<List<String>> ans,
            List<String> ansBuilder) {
        if (ansBuilder.size() == len) {
            ans.add(new ArrayList<>(ansBuilder));
            return;
        }

        int idx = ansBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : ansBuilder)
            prefixBuilder.append(s.charAt(idx));
        List<String> startWith = tr.findByPrefix(prefixBuilder.toString());
        for (String sw : startWith) {
            ansBuilder.add(sw);
            search(len, tr, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }
    }
}

// version: 高频题班
public class Solution {
    /**
     * @param words a set of words without duplicates
     * @return all word squares
     */
    int wordLen;
    Map<String, List<String>> hash = new HashMap<>();
    List<String> squares = new ArrayList<>();
    List<List<String>> ans = new ArrayList<>();

    void initPrefix(String[] words) {
        for (String item : words) {
            hash.putIfAbsent("", new ArrayList<>());
            hash.get("").add(item);

            String pre = "";
            for (char c : item.toCharArray()) {
                pre += c;
                hash.putIfAbsent(pre, new ArrayList<>());
                hash.get(pre).add(item);
            }
        }
    }

    boolean checkPrefix(int l, String nextWord) {
        for (int j = l + 1; j < wordLen; j++) {
            String pre = "";
            for (int k = 0; k < l; k++) {
                pre = pre + squares.get(k).charAt(j);
            }
            pre += nextWord.charAt(j);
            if (!hash.containsKey(pre)) {
                return false;
            }
        }
        return true;
    }

    void dfs(int l) {
        if (l == wordLen) {
            ans.add(new ArrayList<>(squares));
            return;
        }
        String pre = "";
        for (int i = 0; i < l; i++)
            pre += squares.get(i).charAt(l);
        List<String> w = hash.get(pre);

        for (String item : w) {
            if (!checkPrefix(l, item)) {
                continue;
            }
            squares.add(item);
            dfs(l + 1);
            squares.remove(squares.size() - 1);
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        // Write your code here
        if (words.length == 0) {
            return ans;
        }
        initPrefix(words);
        wordLen = words[0].length();
        dfs(0);
        return ans;
    }
}
