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
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        int max = 0;
        for (int i = 0; i < intervals.length; i++) {
            Interval now = intervals[i];
            if (now.start < max) {
                return false;
            }
            max = Math.max(now.end, max);
        }
        return true;
    }

    public int minMeetingRooms(Interval[] intervals) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Interval i : intervals) {
            if (!map.containsKey(i.start)) {
                map.put(i.start, 1);
            }
            else {
                map.put(i.start, map.get(i.start) + 1);
            }
            if (!map.containsKey(i.end)) {
                map.put(i.end, -1);
            }
            else {
                map.put(i.end, map.get(i.end) - 1);
            }
        }
        Object[] arr = map.keySet().toArray();
        Arrays.sort(arr);
        int room = 0, res = 0;
        for (Object c : arr) {
            room += map.get(c);// += not =
            if (res < room) res = room;
        }
        return res;
    }


    //Follow-up 
}
