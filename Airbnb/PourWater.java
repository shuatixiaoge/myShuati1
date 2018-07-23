//Some specail cases
// for 23333, pour at last, it should go to index 0;
// for 33333, pour at last, it should go to last;
// for 12345, pour at alst, it should go to first;
// public int[] pourWater(int[] heights, int V, int K) {
//         if (heights == null || heights.length == 0 || V == 0) {
//             return heights;
//         }
//         int index;
//         while (V > 0) {
//             index = K;
//             // don't use while loop for this,
//             for (int i = K - 1; i >= 0; i--) {
//                 if (heights[i] > heights[index]) {
//                     break;
//                 } else if (heights[i] < heights[index]) {
//                     // need too update index
//                     index = i;
//                 }
//             }
//             if (index == 0) {
//                 V--;
//                 continue;
//             }
//             if (index != K) {
//                 heights[index]++;
//                 V--;
//                 continue;
//             }
//             for (int i = K + 1; i < heights.length; i++) {
//                 if (heights[i] > heights[index]) {
//                     break;
//                 } else if (heights[i] < heights[index]) {
//                     index = i;
//                 }
//             }
//             if (index == 0) {
//                 V--;
//                 continue;
//             }
//             heights[index]++;
//             V--;
//         }
//         return heights;
//     }



    // Follow up, what if the boundary is small
class PourWater {
  public void pourWater(int[] heights, int water, int location) {
    int n = heights.length;
    int[] h = new int[n];
    for (int i = 0; i < n; i++) {
      h[i] = heights[i];
    }
    int[] waters = new int[n];
    while(water > 0) {
      water--;
      int idx = location;
      // For Loop 比较好
      for (int i = location - 1; i >= 0; i--) {
        //记住 是和中间比
        if (waters[i] + h[i] > waters[idx] + h[idx]) {
          break;
        } else if (waters[i] + h[i] <= waters[idx] + h[idx]) {
          idx = i;
        }
      }
      if (idx == 0) {
          continue;
      } else if (idx != location) {
        waters[idx]++;
        continue;
      }

      for (int i = location + 1; i < n; i++) {
        if (waters[i] + h[i] > waters[idx] + h[idx]) {
          break;
        } else if (waters[i] + h[i] <= waters[idx] + h[idx]) {
          idx = i;
        }
      }

      if (idx == n - 1) {
          continue;
      } else {
          waters[idx]++;
      }
    }

    print(h, waters);
  }

  private void print(int[] heights, int[] water) {
    int max = 0;
    for (int i = 0; i < heights.length; i++) {
      max = Math.max(heights[i] + water[i], max);
    }
    for (int h = max; h > 0; h--) {
      for (int i = 0; i < heights.length; i++) {
        if (heights[i] >= h) {
          System.out.print("+");
        } else if (heights[i] < h && h <= heights[i] + water[i]) {
          System.out.print("W");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }

  public static void main(String args[]) {
    int[] heights = new int[]{4,1,3,1,2};
    PourWater s = new PourWater();
    int[] waters = new int[heights.length];
    s.pourWater(heights, 5, 2);
  }

}
