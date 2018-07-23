import java.util.*;
// Given:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log","cog"]
// Return
//   [
//     ["hit","hot","dot","dog","cog"],
//     ["hit","hot","lot","log","cog"]
//   ]
class WordLaddder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, Integer> distance = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < wordList.size(); i++) {
            set.add(wordList.get(i));
        }
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<String>();
        bfs(beginWord, endWord, distance, new HashSet<>(set));
        path.add(beginWord);
        dfs(beginWord, endWord, distance, new HashSet<>(set), path, res);
        return res;
    }

    private void bfs(String a, String b, HashMap<String, Integer> distance, HashSet<String> set) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(a);
        distance.put(a, 0);
        int count = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(b)) continue;

                for (int j = 0; j < 26; j++) {
                    for (int k = 0; k < a.length(); k++) {
                        String tmp = curr.substring(0, k) + (char)('a' + j) + curr.substring(k + 1, a.length());
                        if (set.contains(tmp)) {
                            distance.put(tmp, count + 1);
                            queue.offer(tmp);
                            set.remove(tmp);
                        }
                    }
                }
            }
            count++;
        }
    }

    private void dfs(String curr, String b, HashMap<String, Integer> distance, HashSet<String> set, List<String> path, List<List<String>> res) {
        if (curr.equals(b)) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int j = 0; j < 26; j++) {
            for (int k = 0; k < b.length(); k++) {
                String tmp = curr.substring(0, k) + (char)('a' + j) + curr.substring(k + 1, b.length());
                if (set.contains(tmp) && distance.get(curr) + 1 == distance.get(tmp)) {
                    path.add(tmp);
                    set.remove(tmp);
                    dfs(tmp, b, distance, set, path, res);
                    path.remove(path.size() - 1);
                    set.add(tmp);
                }
            }
        }
    }

    public static void main(String[] args) {
        WordLaddder2 test = new WordLaddder2();
        ArrayList<String> list = new ArrayList<>();
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        for (String st: wordList) {
            list.add(st);
        }
        List<List<String>> res = test.findLadders("hit", "cog", list);
        for (List<String> l : res) {
            for (String str : l) {
                System.out.print(str);
                System.out.print(',');
            }
            System.out.println();
        }
    }
}
