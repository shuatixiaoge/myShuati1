class Solution {
    public NestedInteger deserialize(String s) {
        char[] chars = s.toCharArray();
        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger root = new NestedInteger();
        NestedInteger curr = root; // root is like dummy node
        int num = 0;
        int sign = 1;
        boolean hasNumber = false;
        for (char c : chars) {
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                hasNumber = true;
            } else if (c == '-') {
                sign = -1;
            } else if (c == ',') {
                if (hasNumber) {
                    NestedInteger tmp = new NestedInteger(sign * num);
                    curr.add(tmp);
                }
                num = 0;
                sign = 1;
                hasNumber = false;
            } else if (c == '[') {
                stack.push(curr);
                curr = new NestedInteger();
            } else if (c == ']') {
                if (hasNumber) {
                    NestedInteger tmp = new NestedInteger(sign * num);
                    curr.add(tmp);
                }
                NestedInteger prev = stack.pop();
                prev.add(curr);
                curr = prev;
                num = 0;
                sign = 1;
                hasNumber = false;
            }
        }
        if (hasNumber) {
            root.setInteger(sign * num);
            return root;
        }
        return root.getList().get(0);
    }
}
