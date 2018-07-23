//这个是属于拿或者不拿的问题， 因此在dfs内部不需要额外的for loop

class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        helper("", 0, 0, res, word);
        return res;
    }

    private void helper(String curr, int count, int index, List<String> res, String word) {
        if (word.length() == index) {
           if(count != 0) {
               res.add(curr + String.valueOf(count));
           } else {
               res.add(curr);
           }
           return;
        }
        // 这个地方不再需要for loop了

        helper(curr, count + 1, index + 1, res, word);
        helper(curr + (count == 0 ? "" : String.valueOf(count)) + word.charAt(index), 0, index + 1, res, word);
    }
}
