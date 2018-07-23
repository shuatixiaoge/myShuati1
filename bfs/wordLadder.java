// Given:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log","cog"]
// As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < wordList.size(); i++) {
            set.add(wordList.get(i));
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        //has to consider the loop problems, dfs bfs and graph, you have to do it!!!
        int result = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                for (int j = 0; j < curr.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String newStr = curr.substring(0, j) + c + curr.substring(j+1);
                        if (set.contains(newStr)) {
                            if (endWord.equals(newStr)) return result + 1;
                            queue.offer(newStr);
                            //instead of using visited which would lead to TLE, we modify the wordList set to mark as visited.
                            set.remove(newStr);
                        }
                    }
                }
            }
            result++;
        }
        return 0;
    }
}
