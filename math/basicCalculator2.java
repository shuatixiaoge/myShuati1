import java.util.*;

class Solution {
    public int calculate(String s) {
      int result = 0;
      int sign = 1;
      int num = 0;
      Stack<Integer> stack = new Stack<>();

      for (char c : s.toCharArray()) {
        if (c == ' ') continue;
        else if (Character.isDigit(c)) num = num*10 + Character.getNumericValue(c);
        else {
          if (!stack.isEmpty()) {
            int a = stack.pop();
            int b = stack.pop();
            num = cal(b, num, a);
          }
          if (c == '+') {
            result = cal(result, num, sign);
            sign = 1;
          } else if (c == '-') {
            result = cal(result, num, sign);
            sign = -1;
          } else if (c == '*') {
            stack.push(num);
            stack.push(2);
          } else if (c == '/') {
            stack.push(num);
            stack.push(3);
          }
          num = 0;
        }
      }
      if (!stack.isEmpty()) {
        int a = stack.pop();
        int b = stack.pop();
        num = cal(b, num, a);
      }

      return cal(result, num, sign);
    }
    private int cal(int a, int b, int op) {
      if (op < 2) {
        return a + op * b;
      } else if (op == 2) {
        return a * b;
      } else {
        return a / b;
      }
    }
}


// Solution 2, store the opartion and do * / first.
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (c != ' ' && !Character.isDigit(c) || i + 1 == s.length()) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                sign = c;
                num = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}


//Solution with shunting-yard algorithm
import java.util.*;
class Solution {
    public int calculate(String s) {
    return evaluate(getRPN(s));
}

private String getRPN(String s) {
    Stack<Character> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        // get rid of empty space
        if (c == ' ') continue;
        if (Character.isDigit(c)) {
            sb.append(c);
        } else {
            sb.append(' '); // don't forget this, otherwise it would mess up with numbers
            if (c == '(') {
                stack.push('(');
            } else if (c == ')') {
                while(stack.peek() != '(') {
                    // this is append stack.pop() not c
                    sb.append(stack.pop());
                    sb.append(' ');
                }
                stack.pop();
            } else {
                // pop all larger or equal identifier
                while(!stack.isEmpty() && getValue(c) <= getValue(stack.peek())) {
                    sb.append(stack.pop());
                    sb.append(' ');
                }
                // store the current operator
                // don't forget
                stack.push(c);
            }
        }
    }
    // some other operators may be left
    while(!stack.isEmpty()) {
        sb.append(' ');
        sb.append(stack.pop());
    }
    return sb.toString();
}

private int evaluate(String s) {
    // have to split
    String[] a = s.split(" ");
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < a.length; i++) {
        String c = a[i];
        // for extra "   " leads to empty string
        if (c.equals("")) continue;
        if ("+-*/".indexOf(c) == -1) {
            stack.push(Integer.parseInt(c));
            continue;
        }
        int num1 = stack.pop();
        int num2 = stack.pop();
        if (c.equals("*")) stack.push(num1 * num2);
        if (c.equals("/")) stack.push(num2 / num1);
        if (c.equals("+")) stack.push(num2 + num1);
        if (c.equals("-")) stack.push(num2 - num1);
    }
    return stack.pop();
}

private int getValue(char c) {
    if (c == '*' || c == '/') return 3;
    if (c == '+' || c == '-') return 2;
    return 1;
}

}
