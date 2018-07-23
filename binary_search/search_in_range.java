// Given [5, 7, 7, 8, 8, 10] and target value 8,
// return [3, 4].
public class Solution {
    public ArrayList<Integer> searchRange(ArrayList<Integer> A, int target) {
        int left = 0;
        ArrayList<Integer> res = new ArrayList<>();
        int right = A.size() - 1;
        if (A.size() == 0) {
            res.add(-1);
            res.add(-1);
            return res;
        }
        while (left + 1 < right) {
            int mid = left + (right - left)/2;
            int midval = A.get(mid);
            if (midval < target) { //!!!WITHOUT EQUAL SIGN HERE IS IMPORTANT
                left  = mid;
            }
            else {
                right = mid;
            }
        }
        int rightbound = -1;
        if (A.get(left) == target) {
            rightbound = left;
        }
        else if (A.get(right) == target) {
            rightbound = right;
        }// this is actually leftbound

        left = 0;
        right = A.size() -1;
        while (left + 1 < right) {
            int mid = left + (right - left)/2;
            int midval = A.get(mid);
            if (midval <= target) {
                left  = mid;
            }
            else {
                right = mid;
            }
        }
        int leftbound = -1;
        if (A.get(right) == target) {
            leftbound = right;
        }
        else if (A.get(left) == target) {
            leftbound = left;
        }//rightbound


        res.add(rightbound);
        res.add(leftbound);
        return res;
    }

    public int[] searchRange(int[] A, int target) {
      int start = Solution.firstGreaterEqual(A, target);
      if (start == A.length || A[start] != target) {
          return new int[]{-1, -1};
      }
      return new int[]{start, Solution.firstGreaterEqual(A, target + 1) - 1};
  }

  //find the first number that is greater than or equal to target.
  //could return A.length if target is greater than A[A.length-1].
  //actually this is the same as lower_bound in C++ STL.
  private static int firstGreaterEqual(int[] A, int target) {
      int low = 0, high = A.length;
      while (low < high) {
          int mid = low + ((high - low) >> 1);
          //low <= mid < high
          if (A[mid] < target) {// if this is equal, then this could lastGreaterEqual
            // if no target exist, low = mid + 1 would give you first greater than target, high = mid - 1
            // with low = mid would give you first less than target
              low = mid + 1;
          } else {
              //should not be mid-1 when A[mid]==target.
              //could be mid even if A[mid]>target because mid<high.
              high = mid;
          }
      }
      return low;
  }

  private static int firstGreater(int[] A, int target) {
      int low = 0, high = A.length;
      while (low < high) {
          int mid = low + ((high - low) >> 1);
          //low <= mid < high
          if (A[mid] <= target) {
              low = mid + 1;
          } else {
              high = mid;
          }
      }
      return low;
  }
}
