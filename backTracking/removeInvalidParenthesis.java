//BFS is definitely better than dfs because it only cares about the max result.

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Queue<String> queue = new LinkedList<String>();
        ArrayList<String> res = new ArrayList<>();
        HashSet<String> visited = new HashSet<String>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                if (isValid(str.toCharArray())) {
                    res.add(str);
                    found = true;// this is important, once you have found this level of result, you don't care about the lower level
                }

                if (!found) {
                    for (int j = 0; j < str.length(); j++) {
                        if (str.charAt(j) != '(' && str.charAt(j) != ')') continue;
                        String newStr = str.substring(0, j) + str.substring(j + 1);
                        if (!visited.contains(newStr)) {
                            queue.offer(newStr);
                            visited.add(newStr);
                        }
                    }
                }
            }
        }
        return res;
    }

    public boolean isValid(char[] arr) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : arr) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.empty()) return false;
                if (Math.abs(stack.pop() - c) > 2) return false;
            }
        }
        return stack.empty();
    }


    //dfs method, choose or not choose dfs
    public List<String> removeInvalidParentheses(String s) {
        int leftCount = 0;
        int rightCount = 0;
        int openCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ( c == '(') leftCount++;
            if ( c == ')') {
                if (leftCount > 0) leftCount--;
                else rightCount++;
            }
        }
        Set<String> res = new HashSet<>();
        dfs(s, leftCount, rightCount, 0, 0, new StringBuilder(), res);
        return new ArrayList<String>(res);
    }

    //openCount is for the case ')()', in this case rightCount = 1, so you can remove one rightCount, which could lead to ')('
    private void dfs(String s, int leftCount, int rightCount, int openCount, int index, StringBuilder sb, Set<String> res) {
        if (index == s.length()) {
            if (leftCount == 0 && rightCount == 0 && openCount == 0) res.add(sb.toString());
            return;
        }
        if (leftCount < 0 || rightCount < 0 || openCount < 0) return;
        int len = sb.length();
        if (s.charAt(index) == '(') {
            dfs(s, leftCount - 1, rightCount, openCount, index + 1, sb, res);
            dfs(s, leftCount, rightCount, openCount + 1, index + 1, sb.append('('), res);
        } else if (s.charAt(index) == ')') {
            dfs(s, leftCount, rightCount - 1, openCount, index + 1, sb, res);
            dfs(s, leftCount, rightCount, openCount - 1, index + 1, sb.append(')'), res);
        } else {
            dfs(s, leftCount, rightCount, openCount, index + 1, sb.append(s.charAt(index)), res);
        }
        sb.setLength(len);
    }
}


//If only need to return one result
private static String removeInvalid(String str){
    char[] chrArr = str.toCharArray();
    int val = 0;
    for(int i = 0; i < chrArr.length; i++){
        if(chrArr[i] == '(') val ++;
        else if(chrArr[i] == ')') val --;
        if(val < 0) {
            chrArr[i] = '#';
            val = 0;
        }
    }
    val = 0;
    for(int i = chrArr.length - 1; i >= 0; i--){
        if(chrArr[i] == ')') val ++;
        else if(chrArr[i] == '(') val --;
        if(val < 0){
            chrArr[i] = '#';
            val = 0;
        }
    }
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < chrArr.length; i++){
        if(chrArr[i] != '#') sb.append(chrArr[i]);
    }
    return sb.toString();
}
public static void main(String[] args) {
    String[] testcases = new String[]{"()()()()((()()())()(" ,
    "()AD(S)A()W)(S))()ASD()",
    "))ASDAS()()()Q(((())QWE)()",
    "S()((A)()D))W)Q())(EQ())()W(",
    "))ASD)QW(()S(Q)(WE)(Q()(AS)D("};
    for(int i = 0; i < testcases.length; i++){
        System.out.println("Input : " + testcases[i]);
        System.out.println("Output : " + removeInvalid(testcases[i]));
        System.out.println();
    }
}
