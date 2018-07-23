public class Solution {
    public void sortColors(int[] A) {
        int red,white,blue;
        red=0;white=0;blue=0;
        for(int i=0;i<A.length;i++){
            int current=A[i];
            if (current<=2)
                A[blue++]=2;
            if (current<=1)
                A[white++]=1;
            if (current<=0)
                A[red++]=0;
        }
    }

    public void sortColors2(int[] a) {
        if(a == null || a.length <= 1)
            return;

        int pl = 0;
        int pr = a.length - 1;
        int i = 0;
        while(i <= pr){
            if(a[i] == 0){
                swap(a, pl, i);
                pl++;
                i++;
            }else if(a[i] == 1){
                i++;
            }else{
                swap(a, pr, i);
                pr--;
            }
        }
    }

    private void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
//K - partition
class Solution {
    /**
    * @param colors: A list of integer
    * @param k: An integer
    * @return: nothing
    */
    public void sortColors2(int[] colors, int k) {
        // write your code here
        int left = 0;
        int right = colors.length - 1;
        int cur;
        int lowColor = 1;
        int highColor = k;
        while(lowColor < highColor){
            // what's in the middle is the color with out of range of lowColor and highColor so left should be renewed to curr each time of color change
            cur = left;
            while(cur <= right){
                // no need to stay on left since everything on the left cannot be the high color
                if(colors[cur] == lowColor){
                    swap(colors, cur ++, left ++);
                } else if(colors[cur] == highColor){
                    swap(colors, cur, right --);
                } else {
                    cur ++;
                }
            }
            lowColor ++;
            highColor --;
        }
    }
    private void swap(int[] colors, int a, int b){
        int temp = colors[a];
        colors[a] = colors[b];
        colors[b] = temp;
    }


    //If Color range is not from 1 to k
// sort k colors
// naive:counting sort(O(n) time, need O(k) space, but can be stable if use same idea above)
// below:each time sort min&max, then sort middle part's min&max, until we sort all min&max, O(n) time, O(1) space
public void sortColors2(int[] colors, int k) {
    //if (colors == null || colors.length <= 1 || k == 1)     return;
    int left = 0, right = colors.length - 1;
    while (left < right) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            max = Math.max(max, colors[i]);
            min = Math.min(min, colors[i]);
        }
        int i = left;
        while (i <= right)
            if (colors[i] == min)    swap(colors, i++, left++);
            else if (colors[i] > min && colors[i] < max)    i++;
            else     swap(colors, i, right--);
    }
}
}
