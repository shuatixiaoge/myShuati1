import java.util.*;

/*
  There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

  Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

  For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

  Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

  If there isn't such day, output -1.
 */
public class LC683_KEmptySlots {
  public int kEmptySlots(int[] flowers, int k) {
    // 1 2 3 4 5 6
    // 2 6 3 5 4 1 days

    // 1 2 3
    // 1 3 2 days
    int[] days = new int[flowers.length + 1]; // flower i blooms at days[i]
    for (int i = 1; i < days.length; i++) {
      days[flowers[i - 1]] = i;
    }
    System.out.println(Arrays.toString(days));
    int left = 1, right = left + k + 1, result = Integer.MAX_VALUE;
    for (int i = 2; right < days.length; i++) {
      if (days[i] > days[left] && days[i] > days[right]) {
        continue;
      }
      if (i == right) {
        result = Math.min(result, Math.max(days[left], days[right]));
      }
      left = i;
      right = left + k + 1;
    }
    return result == Integer.MAX_VALUE ? -1 : result;
  }

  public static void main(String[] args) {
    LC683_KEmptySlots solution = new LC683_KEmptySlots();
    System.out.println(solution.kEmptySlots(new int[]{6, 5, 8, 9, 7, 1, 10, 2, 3, 4}, 2)); // 8
    System.out.println(solution.kEmptySlots(new int[]{1, 3, 2}, 1)); // 2
    System.out.println(solution.kEmptySlots(new int[]{3, 9, 2, 8, 1, 6, 10, 5, 4, 7}, 1)); // 6
    System.out.println(solution.kEmptySlots(new int[]{9, 1, 4, 2, 8, 7, 5, 3, 6, 10}, 3)); // 5
    System.out.println(solution.kEmptySlots(new int[]{6,10,7,1,9,8,4,3,5,2}, 3)); // 2
    System.out.println(solution.kEmptySlots(new int[]{90,38,67,70,99,63,71,55,96,7,97,15,73,4,77,35,52,66,89,57,42,19,37,85,21,8,1,49,64,44,88,16,72,53,45,41,22,75,76,48,83,79,12,25,47,93,46,84,95,82,27,2,39,69,100,24,91,17,6,58,18,32,34,31,3,29,30,11,50,86,5,23,68,61,51,36,33,87,74,43,28,26,9,98,65,20,40,59,56,62,13,10,14,94,60,78,92,54,81,80}, 9));
  }
}
