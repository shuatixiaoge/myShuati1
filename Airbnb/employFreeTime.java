// public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
//     List<Interval> result = new ArrayList<>();
//     List<Interval> timeLine = new ArrayList<>();
//     avails.forEach(e -> timeLine.addAll(e));
//     Collections.sort(timeLine, ((a, b) -> a.start - b.start));
//
//     Interval temp = timeLine.get(0);
//     for(Interval each : timeLine) {
//         if(temp.end < each.start) {
//             result.add(new Interval(temp.end, each.start));
//             temp = each;
//         }else{
//             temp = temp.end < each.end ? each : temp;
//         }
//     }
//     return result;
// }
class Interval {
  int start;
  int end;
  Interval(int s, int e) {
    start = s;
    end = e;
  }
}

class Time {
  int flag;
  int time;
  Time(int f, int t) {
    flag = f;
    time = t;
  }
}

class Solution {

  public List<Interval> getAvailableIntervals(List<List<Interval>> intervals, int k) {
    List<Time> list = new ArrayList<>();
    List<Interval> res= new ArrayList<>();
    for (List<Interval> l : intervals) {
      for (Interval i : l) {
        list.add(new Time(1, i.start));
        list.add(new Time(-1, i.end));
      }
    }

    Collections.sort(list, (t1, t2)-> {
      if (t1.time == t2.time) return t1.flag - t2.flag;
      else return t1.time - t2.time;
    });
    int count = 0;
    int start = -1;
    for (int i = 0; i < list.size(); i++) {
      Time curr = list.get(i);
      if (curr.flag == 1)  {
        count++;
      } else {
        count--;
      }
      if (intervals.size() - count >= k) {
        if (start == -1) start = curr.time;
      } else {
        if (start != -1 && start != curr.time) {
          res.add(new Interval(start, curr.time));
          start = -1;
        }
      }
    }
    if (start != -1 && start != list.get(list.size() - 1).time) {
      res.add(new Interval(start, list.get(list.size() - 1).time));
    }
    return res;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    List<List<Interval>> intervals = new ArrayList<>();
    Interval i1 = new Interval(3, 4);
    Interval i2 = new Interval(6, 7);
    List<Interval> tmp = new ArrayList<>();
    tmp.add(i1);
    tmp.add(i2);
    intervals.add(tmp);

    Interval i3 = new Interval(2, 4);
    List<Interval> tmp1 = new ArrayList<>();
    tmp1.add(i3);
    intervals.add(tmp1);

    Interval i4 = new Interval(1, 4);
    Interval i5 = new Interval(9, 12);
    List<Interval> tmp2 = new ArrayList<>();
    tmp2.add(i4);
    tmp2.add(i5);
    intervals.add(tmp2);

    for (Interval i : s.getAvailableIntervals(intervals, 2)) {
      System.out.print(i.start);
      System.out.print(",");
      System.out.println(i.end);
    }
  }
}
