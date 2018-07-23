public class Solution {
    	public List<List<Integer>> threeSum(int[] num) {

        List<List<Integer>> res=new ArrayList<List<Integer>>();
        if(num.length==0) return res;
        int len=num.length;
        Arrays.sort(num);
        for(int i=0;i<len-2;i++){
        	if(i!=0 && num[i]==num[i-1]) continue;//i-1 compare with i is better
        	int j=i+1;
        	int k=len-1;
        	while(k>j){
        		if(num[i]+num[k]+num[j]==0){
    				ArrayList<Integer> temp=new ArrayList<Integer>();
    				temp.add(num[i]);
    				temp.add(num[j]);
    				temp.add(num[k]);
                    res.add(temp);
    				j++;
                    k--;
        			while(j<k && num[j]==num[j-1]) j++;
        			while(j<k && num[k]==num[k+1]) k--;
        		}
        		else if(num[i]+num[k]+num[j]<0){
        			j++;
        		}
        		else {
        			k--;
        		}
        	}
        }
        return res;

    }
    //Follow Up if you one of the number could use 2 times.

    // assuming no duplicate in nums
public List<int[]> threeSum(int[] nums, int target) {
    Arrays.sort(nums);

    List<int[]> ret = new LinkedList<>();
    for (int i = 0; i < nums.length; i++) {
        int left = i, right = nums.length - 1;
        while (left <= right) {
            int sum = nums[i] + nums[left] + nums[right] - target;
            if (sum == 0) {
                if (i == left && left == right) break;
                ret.add(new int[]{nums[i], nums[left], nums[right]});
                left++;
                right--;
            } else if (sum > 0)
                right--;
            else
                left++;
        }
    }
    return ret;
}
}
