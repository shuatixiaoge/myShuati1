// 直接用edit distance挨个遍历一遍也能做，但是如果word list很大，那重复计算会非常多，这时候可以用trie来优化。 下面举个例，假设字典为["cs", "ct", "cby"]，target word为"cat"，k=1。 首先建Trie:
//      c
//    / | \
//   b  s  t
//  /
// y
// 从根节点开始搜索，遍历的单词分别为：c -> cb -> (cby) -> cs -> ct。 与普通的Edit distance动态规划算法一样，
// 我们对每一个“路过”的单词记录一个DP table。那么所遍历的单词的DP table应该为(假设当前遍历单词，也就是下面代码中的path为”c”)：
//           c a t
//       [ 0 1 2 3 ] <- prev_dist
// -> c  [ 1 0 1 2 ] <- cur_dist
//    cb [ 2 1 1 2 ]
//    cs [ 2 1 1 2 ]
//    ct [ 2 1 1 1 ]
// 每一行的最后一位数字即为当前单词与target word的edit distance。显然，如果该值小于等于k，则加入到结果中。最终返回的结果只有一个单词，即ct。 注意，遍历到单词cb时，edit distance已经为2，
// 且长度已经与cat相等，也就意味着该节点的子树中所包含的单词与target word的edit distance无论如何都不可能小于等于k，因此直接从该子树返回。
// 所以单词cby是没有被遍历到的。这也就是Trie做这题的便利之处，当字典较大时会明显提高效率。
import java.util.*;
public class KEditDistance {
    public List<String> getKEditDistance(String[] words, String target, int k) {
        List<String> res = new ArrayList<>();
        if (k <= 0 || words.length == 0 || target == null) return res;
        int[] prevDist = new int[target.length() + 1];
        Trie trie = new Trie();
        for (int i = 0; i <= target.length(); i++) {
            prevDist[i] = i;
        }
        for (String w : words) {
            trie.add(w);
        }
        helper("", res, target, k, trie.root, prevDist);
        return res;
    }

    private void helper(String s, List<String> res, String target, int k, TrieNode root, int[] prevDist) {
        // need target.lenth, not idx
        if (root.isLeaf) {
            if (prevDist[target.length()] <= k) {
                // would keep checking even match
                res.add(s);
            } else {
                return;
            }
        }

        for (char c : root.map.keySet()) {
            TrieNode node = root.map.get(c);
            int[] currDist = new int[target.length() + 1];
            // this is s.length() + 1 as the example showed above
            currDist[0] = s.length() + 1;
            for (int j = 0; j < target.length(); j++) {
                // don't out of bound
                if (c == target.charAt(j)) {
                    currDist[j + 1] = prevDist[j];
                } else {
                    currDist[j + 1] = Math.min(currDist[j], Math.min(prevDist[j + 1], prevDist[j])) + 1;
                }
            }
            // call outside of j loop inside of c loop
            helper(s + c, res, target, k, node, currDist);
        }
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

        KEditDistance solution = new KEditDistance();

        String[] input = new String[]{"abcd", "abc", "abd", "ad", "bd", "bdf"};

        String target = "ac";

        int k = 2;



        List<String> result = solution.getKEditDistance(input, target, k);



        for (String s : result) {

            System.out.println(s);

        }

    }
}
