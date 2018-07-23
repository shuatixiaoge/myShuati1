//有点像LIS
// put envelopes into each other
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
           || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
            //Sort the width of the envelopes with decreasing order so that it would not choose itself.
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
           }
        });
        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = binarySearch(dp, 0, len, envelope[1]);
            if(index == len) dp[len++] =  envelope[1];
            else dp[index] = envelope[1];
        }
        return len;
    }
    public int binarySearch(int[] nums, int i, int j, int target) {
        while (i < j) {
            int mid = i + (j - i)/2;
            if (target <= nums[mid]) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

}
