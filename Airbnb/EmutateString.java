import java.util.*;
public class EmutateString {
    private boolean isBitSet(int n, int offset) {
        return (n >> offset & 1) != 0;
    }
    public List<String> ermutateString(String text) {
        List<String> res = new ArrayList<>();
        if (text == null || text.length() == 0) {
            return res;
        }
        char[] chars = text.toCharArray();
        for (int i = 0, n = (int) Math.pow(2, chars.length); i < n; i++) {
            char[] curr = new char[chars.length];
            for (int j = 0; j < chars.length; j++) {
                curr[j] = (isBitSet(i, j)) ? Character.toUpperCase(chars[j]) : chars[j];
            }
            res.add(new String(curr));
        }
        return res;
    }

    public List<String> ermutateString2(String text) {
        List<String> res = new ArrayList<>();
        helper(text, res, 0, new StringBuilder());
        return res;
    }
    private void helper(String text, List<String> res, int idx, StringBuilder sb) {
        if (idx == text.length()) {
            res.add(sb.toString());
            return;
        }

        //shouldn't be a if condition here
        sb.append(text.charAt(idx));
        helper(text, res, idx + 1, sb);
        sb.setLength(sb.length() - 1);
        sb.append(Character.toUpperCase(text.charAt(idx)));
        helper(text, res, idx + 1, sb);
        sb.setLength(sb.length() - 1);
    }
    public static void main(String[] args) {
        EmutateString t = new EmutateString();
        for (String s : t.ermutateString2("abc")) {
            System.out.println(s);
        }
    }
}
