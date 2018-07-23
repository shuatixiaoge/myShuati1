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
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() == 0) return intervals;
        Collections.sort(intervals, new intervalComparator());
        List<Interval> res = new ArrayList<Interval>();
        int right = -1;
        for (Interval interval : intervals) {
            if (interval.start <= right) {
                if (interval.end > right) {
                    Interval tmp = res.remove(res.size() - 1);
                    tmp.end = interval.end;
                    right = tmp.end;
                    res.add(tmp);
                }
            } else {
                res.add(interval);
                right = interval.end;//don't forget this line;
            }
        }

    }
    private class intervalComparator implements Comparator {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }
}
