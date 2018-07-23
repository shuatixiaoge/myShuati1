public class binarySearch {
    public int search(int[] A, int target) {
        int l = 0, r = A.length-1, ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (target <= A[mid]) {
                ans = mid;//不着急返回，不停地给ans赋值, find left bound.
                r = mid - 1;
            }  else
                l = mid + 1;
        }
        return ans;
    }

    //another way to find the firstGreaterEqual
    public int search2(int[] A, int target) {
        int l = 0, r = A.length-1, ans = 0;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target <= A[mid]) {
                r = mid;
            }  else
                l = mid + 1;
        }
        // return l;
        return l < A.length && A[l] >= target ? l : -1;
    }

    //the way to find firstGreater
    //这个方法有个bug就是当整个array 丢不存在满足要求的时候 会返回 最后一个元素, ，array size是1的时候，例如【4】 target 5， find firstGreater than 5 should return -1
    public int search3(int[] A, int target) {
        int l = 0, r = A.length-1, ans = 0;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target < A[mid]) {
                r = mid;
            }  else
                l = mid + 1;
        }
        // return l;
        //Without bug implementation:
        //Simply doing A[l] would lead to out of range if A is empty
        return l < A.length && A[l] > target ? l : -1;
    }
    //cannot do mid - 1 and mid, which gonna be infinite loop
    //

    public static void main(String[] args) {
        binarySearch bs = new binarySearch();
        int[] arr = new int[]{1, 2, 2, 2, 4, 5};
        System.out.println(bs.search3(arr, 2));
    }
}
