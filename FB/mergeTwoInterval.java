public class Solution {
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        List<Interval> res = new ArrayList<>();
        int idx1 = 0, idx2 = 0;
        while (idx1 != list1.size() && idx2 != list2.size()) {
            if (list1.get(idx1).start <= list2.get(idx2).start) {
                mergeInterval(res, list1.get(idx1++));
            } else {
                mergeInterval(res, list2.get(idx2++));
            }
        }

        while (idx1 != list1.size()) {
            mergeInterval(res, list1.get(idx1++));
        }
        while (idx2 != list2.size()) {
            mergeInterval(res, list2.get(idx2++));
        }
        return res;
    }

    private void mergeInterval(List<Interval> res, Interval t) {
        if (res.size() == 0) {
            res.add(t);
            return;
        }

        Interval cur = res.get(res.size() - 1);
        if (cur.end < t.start) {
            res.add(t);
        } else {
            cur.end = Math.max(cur.end, t.end);
        }
    }
}
