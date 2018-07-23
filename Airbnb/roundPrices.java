import java.util.*;
public class roundPrices {
     public class Tuple {
         double diff;
         int idx;
         public Tuple(double diff, int idx) {
             this.diff = diff;
             this.idx = idx;
         }
     }
     public int[] roundUp(double[] arr) {
         int[] res = new int[arr.length];
         if (arr.length == 0) return res;
         int sumFloor = 0;
         double sum = 0;
         Tuple[] diffArr = new Tuple[arr.length];
         for (int i = 0; i < arr.length; i++) {
             res[i] = (int)arr[i];
             double a = arr[i];
             sum += a;
             sumFloor += (int)a;
             diffArr[i] = new Tuple((int)a + 1 - a, i);
         }
         int diff = (int)Math.round(sum) - sumFloor;
         // Double compare here, cannot use return t1.diff - t2.diff
         Arrays.sort(diffArr, (Tuple t1, Tuple t2) -> Double.compare(t1.diff, t2.diff));
         for (int i = 0; i < diff; i++) {
             res[diffArr[i].idx] += 1;
         }
         return res;
     }

     public static void main(String args[]) {
         double[] a = {1.0, 2.0, 3.0};
         roundPrices t = new roundPrices();
         for (int b : t.roundUp(a)) {
             System.out.println(b);
         }
     }
}
