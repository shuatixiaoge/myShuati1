class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> list = new ArrayList<>();
        if (num.length() == 0)  return list;
        helper(num, target, list, "", 0, 0, 0);
        return list;
    }

    private void helper(String num, int target, List<String> res, String path, int idx, long val, long carry) {
        if (idx == num.length()) {
            if (val == target) res.add(path);
            return;
        }
        for (int i = idx; i < num.length(); i++) {
            // Dont forget to remove the leading zero
            if (i != idx && num.charAt(idx) == '0') break;
            //for excluding the leading zeros
            //"105", 5 -> ["1*0+5","10-5"]
            // "00", 0 -> ["0+0", "0-0", "0*0"]
            // but with leading zeros we will have
            //
            // "105", 5 -> ["1*0+5","10-5", "1*05"]
            // "00", 0 -> ["0+0", "0-0", "0*0", "00"]

            long current_val = Long.parseLong(num.substring(idx, i + 1));
            // the initial case
            if (idx == 0) {
                helper(num, target, res, path + current_val, i + 1, current_val, current_val);
            } else {
                helper(num, target, res, path + '+' + current_val, i + 1, val + current_val, current_val);
                helper(num, target, res, path + '-' + current_val, i + 1, val - current_val, -current_val);
                helper(num, target, res, path + '*' + current_val, i + 1, val - carry + carry * current_val, current_val * carry);
                //for example, if you have a sequence of 12345 and you have proceeded to 1 + 2 + 3,
                // now your eval is 6 right? If you want to add a * between 3 and 4,
                // you would take 3 as the digit to be multiplied, so you want to take
                // it out from the existing eval.
                // You have 1 + 2 + 3 * 4 and the eval now is (1 + 2 + 3) - 3 + (3 * 4).
            }
        }
    }
}
