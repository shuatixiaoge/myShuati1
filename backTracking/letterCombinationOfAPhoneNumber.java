class Solution {
    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) return res;
        StringBuilder sb = new StringBuilder();
        combination(res, digits, sb, 0);
        return res;
    }
    private void combination(List<String> res, String digits, StringBuilder sb, int pos) {
        if (pos == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String letters = KEYS[digits.charAt(pos) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            combination(res, digits, sb, pos + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
