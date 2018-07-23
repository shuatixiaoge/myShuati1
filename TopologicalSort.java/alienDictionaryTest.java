import java.util.*;
class alienDictionaryTest {
    public String alienOrder(String[] words) {
        int[] degree = new int[26];
        HashMap<Character, HashSet<Character>> map = new HashMap<>();
        HashSet<Character> set2 = new HashSet<>();
        for (int i = 0; i < 26; i++) map.put((char)('a' + i), new HashSet<Character>());

        for (int i = 0; i < words.length - 1; i++) {
            int len = Math.min(words[i].length(), words[i + 1].length());
            for (int j = 0; j < len; j++) {
                char a = words[i].charAt(j);
                char b = words[i + 1].charAt(j);
                set2.add(a);
                set2.add(b);
                HashSet set = map.get(a);
                if (a != b) {
                    if (set.contains(b)) continue;
                    set.add(b);
                    degree[b - 'a']++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (!set2.contains((char)('a' + i))) continue;
            if (degree[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()) {
            int i = q.poll();
            char curr = (char)('a' + i);
            sb.append(curr);
            for(char next : map.get(curr)) {
                if (--degree[next - 'a'] == 0) {
                   q.offer(next - 'a');
                }
            }
        }

        return sb.toString();
    }
    public static void main (String[] args) {
        alienDictionaryTest test = new alienDictionaryTest();
        String[] w = {  "wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(test.alienOrder(w));
    }
}
