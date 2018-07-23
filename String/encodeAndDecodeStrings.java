public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
       for (String str : strs)  {
           sb.append(str.length());
           sb.append(':');
           sb.append(str);
       }
       return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while(i < s.length()) {
            int deli = s.indexOf(':', i);
            int len = Integer.valueOf(s.substring(i, deli));
            res.add(s.substring(deli + 1, deli + 1 + len));
            i = deli + len + 1;
        }
        return res;
    }
}
