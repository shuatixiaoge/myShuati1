// Input: tasks = ["A","A","A","B","B","B"], n = 2
// Output: 8
// Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int max_val = map[25] - 1, idle_slots = max_val * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idle_slots -= Math.min(map[i], max_val);
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }
}
// better
// 3，无序的，频率统计的做法，算最后时间
//if tasks can be reordered, output the minimum time needed: O(n) time, O(n) space
private static int taskSchedule3(int[] tasks, int cooldown) {
   HashMap<Integer, Integer> map = new HashMap<>();
   for (int task : tasks)
       map.put(task, map.getOrDefault(task, 0) + 1);
   int maxFrequency = 0, countOfMax = 0;
   for (int frequency : map.values())
       if (frequency > maxFrequency) {
           maxFrequency = frequency;
           countOfMax = 1;
       } else if (frequency == maxFrequency) {
           countOfMax++;
       }
   int minimumTime = (maxFrequency - 1) * (cooldown + 1) + countOfMax;
   return Math.max(minimumTime, tasks.length);
}

// Get the Path for random order
class Solution {
    class Point {
      int idx, count;
      Point(int idx, int count) {
        this.idx = idx;
        this.count = count;
      }
    }
    public String leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        StringBuilder sb = new StringBuilder();
        for (char c: tasks)
            map[c - 'A']++;
        PriorityQueue < Point > queue = new PriorityQueue <> (26, (a, b)-> b.count - a.count);
        for (int i = 0; i < 26; i++) {
            int f = map[i];
            if (f > 0)
                queue.add(new Point(i, f));
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List < Point > temp = new ArrayList < > ();
            while (i <= n) {
                if (!queue.isEmpty()) {

                    sb.append((char)(queue.peek().idx + 'A'));
                    if (queue.peek().count > 1) {
                      Point p = queue.poll();
                      p.count -= 1;
                      temp.add(p);
                    }
                    else
                        queue.poll();
                } else {
                    sb.append('_');
                }
                time++; // has to be in the front if condition to be able to handle 'idle' case
                if (queue.isEmpty() && temp.size() == 0)
                    break;
                i++;
            }
            for (Point l: temp)
                queue.add(l);
        }
        return sb.toString();
    }


  public static void main(String[] args) {
    char[] arr = new char[]{'A', 'B', 'A', 'A', 'C', 'D'};
    Solution s= new Solution();
    System.out.println(s.leastInterval(arr, 3));
  }
}



    // reserve the order
class Solution {
  public int interval(char[] tasks, int n) {
      Map<Character, Integer> map = new HashMap<>();
      int times = 0;
      for (int i = 0; i < tasks.length; i++) {
          char c = tasks[i];
          if (map.containsKey(c)) {
              if (i - map.get(c) < n) {
                times += n - i + map.get(c);
              }
          }
          map.put(c, i);
          //don't forget this
        times++;
      }
    return times;
  }

  public static void main(String[] args) {
    char[] arr = new char[]{'1', '2', '1', '1', '3', '4'};
    Solution s= new Solution();
    System.out.println(s.interval(arr, 3));
  }
}
}
