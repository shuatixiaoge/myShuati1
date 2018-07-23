public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int i = 1;
        int j = n;
        while(i < j) {
            int mid = i + (j - i)/2;
            if(isBadVersion(mid)) {
                j = mid;// since this could be the equal case so you better not do mid - 1 here;
            } else {
                i = mid + 1;
            }
        }
        return i;// return j is okay too
    }
}
