import java.util.*;
//Assumption, the txn is coming with time increasing
class BankSystem {
    class Txn {
        int amount;
        int balance;
        int time;
        Txn(int a, int t, int b) {
            amount = a;
            time = t;
            balance = b;
        }
    }
    Map<Integer, List<Txn>> map = new HashMap<>();
    boolean deposit(int userId, int amount, int timestamp) {
        if (timestamp < 0) return false;
        List<Txn> l = map.getOrDefault(userId, new ArrayList<>());
        int balance = l.size() == 0 ? 0 : l.get(l.size() - 1).balance;
        if (balance + amount < 0) return false;
        l.add(new Txn(amount, timestamp, balance + amount));
        map.put(userId, l);
        return true;
    }

    boolean withdraw(int userId, int amount, int timestamp) {
        return deposit(userId, -amount, timestamp);
    }

    // not include start time
    int[] getStatement(int userId, int start, int end) {
        int[] res = new int[2];
        if (!map.containsKey(userId)) return res;
        List<Txn> l = map.get(userId);
        res[0] = bs(l, start);
        res[1] = bse(l, end);
        return res;
    }

    int bs(List<Txn> l, int time) {
        int i = 0;
        int j = l.size() - 1;
        while(i < j) {
            int mid = (j - i)/2 + i;
            if (l.get(mid).time > time) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return j > 0 ? l.get(j - 1).balance : 0;
    }

    int bse(List<Txn> l, int time) {
        int i = 0;
        int j = l.size() - 1;
        while(i < j) {
            int mid = (j - i)/2 + i;
            if (l.get(mid).time >= time) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return j > 0 ? l.get(j - 1).balance : 0;
    }

    public static void main(String[] args) {
        BankSystem s = new BankSystem();
        s.deposit(1, 10, 0);
        s.deposit(1, 30, 1);
        s.deposit(1, 20, 2);
        s.deposit(1, 40, 2);
        s.deposit(1, 50, 4);
        s.withdraw(1, 60, 4);
        s.deposit(1, 10, 7);
        int[] res = s.getStatement(1, 0, 5);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
