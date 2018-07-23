// 投两个骰子，从1-9里面选出几个数字，加和为这两个骰子的和。
// 比如第一次，两个骰子为3，5，那么我们可以选出几个数字，加和为8. 假设我们选1和7.
// 第二次，两个骰子为4，6，那么第一次1和7选了，这次不能再选了。我们可以选4和6.
// 第三次，做同样的事情
// 。。。。
// 怎样找到一个最优解，使得我们胜率最大？胜利的定义是所有1-9的数都取了
// One player

import java.util.*;

class RollingDice {
    int count = 0;
    int total = 0;
    int[] getBestPair(int dice, Set<Integer> set) {
        int[] res = new int[2];
        double max = 0;
        for (int i = 1; i < 10; i++) {
            count = 0;
            total = 1;
            helper(i, dice, set);
            if ((double)count/total > max) {
                res[0] = i;
                res[1] = dice - i;
                max = (double)count/total;
            }
        }
        return res;
    }

    void helper(int x, int dice, Set<Integer> set) {
        if (x >= dice || set.contains(x) || set.contains(dice - x) || dice - x >= 10) return;
        if (set.size() == 7 && x != dice - x) {
            total++;
        }
        else if (set.size() == 8 && x == dice - x) {
            total++;
        }
        set.add(x);
        set.add(dice - x);
        if (set.size() == 9) {
            count++;
            set.remove(x);
            set.remove(dice - x);
            return;
        }
        for (int i = 1; i < 10; i++) {
            if (set.contains(i)) continue;
            for (int j = 2; j <= 12; j++) {
                if (j <= i || set.contains(j - i)) continue;
                helper(i, j, set);
            }
        }

        set.remove(x);
        set.remove(dice - x);
    }
    public static void main(String[] args) {
        RollingDice s = new RollingDice();
        Set<Integer> set = new HashSet<>();
        int dice = 4;
        // set.add(1);
        // set.add(dice - 1);
        int[] res = s.getBestPair(dice, set);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
