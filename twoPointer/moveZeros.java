class moveZeroes {
    public void moveZeroes(int[] nums) {
        int j = 0;
        int i = 0;//idea if to throw any non-zero nums to the front
        //in this case the for loop index is the right boundary

        for (;j < nums.length; j++) {
            if (nums[j] != 0) {
                swap(nums, i++, j);
            }
        }

    }
    private void swap(int[] a, int i, int j) {
        System.out.println("HIH");
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    //Follow up don't need to keep the order, but need to do min swaps

    public int moveZeroesWithMinWrites(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] != 0)		left++;
            while (left < right && nums[right] == 0)	right--;
            
            if (left < right)	swap(nums, left++, right--);
        }
        return left;
    }

    public static void main(String[] args) {
        moveZeroes s = new moveZeroes();
        int[] a = {1,0,2,4,0,5,0,2};
        s.moveZeroesWithMinWrites(a);
        for (int i : a) {
            System.out.println(i);
        }
    }


}
