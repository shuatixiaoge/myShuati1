public boolean isStrobogrammatic(String num) {
    Map<Character, Character> map = new HashMap<Character, Character>();
    map.put('6', '9');
    map.put('9', '6');
    map.put('0', '0');
    map.put('1', '1');
    map.put('8', '8');

    int l = 0, r = num.length() - 1;
    while (l <= r) {
        if (!map.containsKey(num.charAt(l))) return false;
        if (map.get(num.charAt(l)) != num.charAt(r))
            return false;
        l++;
        r--;
    }

    return true;
}


public class Solution {
    public List<String> findStrobogrammatic(int n) {
        Map<Character, Character> map = buildMap();
        List<String> ret = n % 2 == 0 ? Arrays.asList("") : Arrays.asList("1", "8", "0");

        for (int i = n % 2 == 0 ? 1 : 2; i < n; i += 2) {
            List<String> cur = new ArrayList<>();
            for (char c : map.keySet()) {
                for (String s : ret) {
                    // don't add leading 0s!
                    if (!(i == n - 1 && c == '0'))
                        cur.add(c + s + map.get(c));
                }
            }
            ret = cur;
        }

        return ret;
    }

    private Map<Character, Character> buildMap() {
        Map<Character, Character> map = new HashMap<>();
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        map.put('0', '0');
        return map;
    }
}
