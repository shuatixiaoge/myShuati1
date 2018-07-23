public class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<String>();
        String[] list = path.split("/");
        for(String cur: list) {
            if (cur.equals("/") || cur.equals(".")
                || cur.equals("")) continue;
            if (cur.equals("..")) {
                if (! stack.isEmpty()) stack.pop();
            } else stack.push(cur);
        }
        String ans = "";
        if (stack.isEmpty()) return "/";
        while (! stack.isEmpty()) {
            ans = "/" + stack.pop() + ans;
        }
        return ans;
        }
}
