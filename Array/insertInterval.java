public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int i=0;
        while(i<intervals.size() && intervals.get(i).end<newInterval.start) i++;// both are inclusive
        while(i<intervals.size() && intervals.get(i).start<=newInterval.end){//both are inclusive
            newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start),
                                       Math.max(intervals.get(i).end, newInterval.end));
            intervals.remove(i);
        }
        intervals.add(i,newInterval);
        return intervals;
}


//Binary search for finding the position
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    // Remove might not be better than just inserting, the complexity is still O(N) in this cases
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || newInterval == null) return intervals;
        int iStart = findStartPos(intervals, newInterval.start);

        //If not in the corner cases, this condition should apply.
        if (iStart == -1) {
            intervals.add(newInterval);
            return intervals;
        }
        int i = iStart;
        while(i<intervals.size() && intervals.get(i).start<=newInterval.end){
            newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start), Math.max(intervals.get(i).end, newInterval.end));
            intervals.remove(i);
        }
        intervals.add(i,newInterval);

        return intervals;
    }

    private int findStartPos(List<Interval> intervals, int value) {
        int l = 0, r = intervals.size() - 1;
        while (l < r) {
            int m = (l + r) >> 1;
            if (intervals.get(m).end < value) l = m + 1;
            else r = m;
        }
        return l < intervals.size() && intervals.get(l).end >= value ? l : -1;
    }
}


// Best solution O(N) guranteed
public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> result = new LinkedList<>();
    int i = 0;
    // add all the intervals ending before newInterval starts
    while (i < intervals.size() && intervals.get(i).end < newInterval.start)
        result.add(intervals.get(i++));
    // merge all overlapping intervals to one considering newInterval
    while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
        newInterval = new Interval( // we could mutate newInterval here also
                Math.min(newInterval.start, intervals.get(i).start),
                Math.max(newInterval.end, intervals.get(i).end));
        i++;
    }
    result.add(newInterval); // add the union of intervals we got
    // add all the rest
    while (i < intervals.size()) result.add(intervals.get(i++));
    return result;
}

//给出⼀一系列列区间，⽐比如{ [1,3], [3,5], [2,4], [4,7], [4,9], [7,12] }。问如何⽤用最少数 ⽬目的区间来覆盖⽬目标区间(⽐比如[2，9])。在这个例例⼦子中，答案应该是{[2,4], [4,9]}

class Solution {

  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
      int max = 0;
      int maxIdx = 0;
      int i = 0;
      List<Interval> result = new LinkedList<>();
      int start = newInterval.start;
      //Difference 1
      while(i < intervals.size() && start < newInterval.end) {
          while (i < intervals.size() && intervals.get(i).end < start)
              i++;
      //Difference 2 start and start
          while (i < intervals.size() && intervals.get(i).start <= start) {
              if (max < intervals.get(i).end) {
                  maxIdx = i;
                  max = intervals.get(i).end;
              }
              i++;
          }
          result.add(intervals.get(maxIdx));
          i = maxIdx + 1;
          start = max;
      }
      return result;
  }


  public static void main(String[] args) {
    Interval i1 = new Interval(1,3);
    Interval i2 = new Interval(2,4);
    Interval i3 = new Interval(3,5);
    Interval i4 = new Interval(4,7);
    Interval i5 = new Interval(4,9);
    Interval i6 = new Interval(7,12);
    List<Interval> l = new ArrayList<>();
    l.add(i1);
    l.add(i2);
    l.add(i3);
    l.add(i4);
    l.add(i5);
    l.add(i6);
    Solution s = new Solution();
    for (Interval i : s.insert(l, new Interval(2,4))) {
      System.out.print(i.start);
      System.out.print(",");
      System.out.println(i.end);
    }
  }
}
