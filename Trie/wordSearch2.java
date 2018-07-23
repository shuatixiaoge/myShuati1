public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */

     //用Trie而不用Hash的好处在于能够提前的处理前缀，从而能够节省时间复杂度
     //Pruning
     //15min 要写出来
    class TrieNode {
        String s;
         boolean isString;
         HashMap<Character, TrieNode> subtree;
         public TrieNode() {
            // TODO Auto-generated constructor stub
             isString = false;
             subtree = new HashMap<Character, TrieNode>();
             s = "";
         }
    };


    class TrieTree{
        TrieNode root ;
        public TrieTree(TrieNode TrieNode) {
            root = TrieNode;
        }
        public void insert(String s) {
            TrieNode now = root;
            for (int i = 0; i < s.length(); i++) {
                if (!now.subtree.containsKey(s.charAt(i))) {
                    now.subtree.put(s.charAt(i), new TrieNode());
                }
                now  =  now.subtree.get(s.charAt(i));
            }
            now.s = s;
            now.isString  = true;
        }
        public boolean find(String s){
            TrieNode now = root;
            for (int i = 0; i < s.length(); i++) {
                if (!now.subtree.containsKey(s.charAt(i))) {
                    return false;
                }
                now  =  now.subtree.get(s.charAt(i));
            }
            return now.isString ;
        }
    };

    public int []dx = {1, 0, -1, 0};
    public int []dy = {0, 1, 0, -1};

    public void search(char[][] board, int x, int y, TrieNode root, List<String> ans) {
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y]==0 || root == null) return ;
        if(root.isString == true)
        {
            if(!ans.contains(root.s)){
                ans.add(root.s);
            }
        }
        if(root.subtree.containsKey(board[x][y])){
            for(int i = 0; i < 4; i++){
                char now = board[x][y];
                board[x][y] = 0;
                search(board, x+dx[i], y+dy[i], root.subtree.get(now), ans);
                board[x][y] = now;
            }
        }

    }

    public List<String> wordSearchII(char[][] board, List<String> words) {
        List<String> ans = new ArrayList<String>();

        TrieTree tree = new TrieTree(new TrieNode());
        for(String word : words){
            tree.insert(word);
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                search(board, i, j, tree.root, ans);
            }
        }
        return ans;
        // write your code here

    }

}
