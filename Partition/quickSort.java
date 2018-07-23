public class quickSort {

	public void sort(int[] arr, int left, int right){
		int i=left;
		int j=right;
		int pivot=i+(j-i)/2;
		int mid=arr[pivot];
		//partition
		while(i<=j){

			while(arr[i]<mid){
				i++;
			}
			while(arr[j]>mid){
				j--;
			}
			if(i<=j){
				int temp=arr[i];
				arr[i]=arr[j];
				arr[j]=temp;
				i++;
				j--;
			}
		}
		if(left<j) sort(arr,left,j);
		if(i<right) sort(arr,i,right);
	}


	//second method
	public void sort2(int[] nums) {
       helper(nums,0,nums.length-1);
    }
    public void helper(int[] nums,int i, int j){
		if (i >= j) return;
        int pivot=partition(nums,i,j);
        helper(nums,pivot+1,j);
        helper(nums,i,pivot-1);
    }
    public int partition(int[] nums,int start, int end){
        int pivot=nums[end];
        int i=start;
        int j=end;
        while(true){
            while(i<j && nums[i]<pivot){
                i++;
            }
            while(i<j && nums[j]>=pivot){// the equal sign here is important
                j--;
            }
            if(i==j) break;
            swap(nums,i,j);
        }
        swap(nums,i,end);
        return i;
    }

    public void swap(int[] nums,int i, int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
	public static void main(String args[]){

		quickSort test = new quickSort();
		int[] arr=new int[]{3,2,1,5,4,4,4,3,9,8,0};
		test.sort2(arr);
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}
}
