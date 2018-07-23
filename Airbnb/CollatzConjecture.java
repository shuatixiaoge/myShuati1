import java.util.*;
class CollatzConjectrue {
    Map<Long, Integer> map = new HashMap<>();
    public int run() {
        long max = 0;
        int res = 0;
        for (int i = 1; i < 1000000; i++) {
            long tmp = findSteps(i);
            if (tmp > max) {
                max = tmp;
                res = i;
            }
        }
        return res;
    }

    public int findSteps(long num) {
        if (num == 1) return 1;
        int count = 0;
        long origin = num;
        while(num != 1) {
            if (map.containsKey(num)) {
                map.put(origin, map.get(num) + count);
                return map.get(num) + count;
            }
            if (num % 2 == 0) num = num / 2;
            else num = num * 3 + 1;
            count++;
        }
        map.put(origin, count);
        return count;
    }
    public static void main(String[] args) {
        CollatzConjectrue s = new CollatzConjectrue();
        System.out.println(s.run());
    }
}
