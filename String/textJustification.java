// For example,
// words: ["This", "is", "an", "example", "of", "text", "justification."]
// L: 16.
//
// Return the formatted lines as:
// [
//    "This    is    an",
//    "example  of text",
//    "justification.  "
// ]
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> result = new ArrayList<>();
        //special case for [""], 0
        if (words.length == 1 && words[0].equals("") && maxWidth == 0)  {
            result.add("");
            return result;
        }
        if (words.length == 0 || maxWidth == 0)  return result;
        StringBuilder sb = new StringBuilder();
        int currLen = 0;
        //don't do for loop, since it would skip the first word like `example`
        int i = 0;
        while (i < words.length) {
            String word = words[i];
            currLen += word.length();

            if(currLen > maxWidth) {
               result.add(alignLine(sb.toString(), maxWidth));
               sb.setLength(0);
               currLen = 0;
            } else {
               sb.append(' ');
               currLen++;
               sb.append(word);
               i++;
            }
        }
        // last row needs to left align
        result.add(leftAlign(sb.toString(), maxWidth));

        return result;
    }
    private String leftAlign(String sentence, int maxWidth) {
        String[] words = sentence.trim().split("\\s+");
        int len = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            len += words[i].length();
            if (i != words.length - 1) {
                sb.append(' ');
                len++;
            }
        }
        for (int i = 0; i < maxWidth - len; i++) sb.append(' ');
        return sb.toString();
    }

    private String alignLine(String sentence, int maxWidth) {
        String[] words = sentence.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        // when word length is 1
        if (words.length == 1) {
            return leftAlign(sentence, maxWidth);
        }
        int len = 0;
        for (String word : words) {
            len += word.length();
        }
        int space = (maxWidth - len)/(words.length - 1);
        int extra = (maxWidth - len)%(words.length - 1);
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if (i == words.length - 1) break;
            for (int j = 0; j < space; j++) {
                sb.append(' ');
            }
            if (extra-- > 0) sb.append(' ');
        }
        return sb.toString();
    }
}
