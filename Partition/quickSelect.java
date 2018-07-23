class QuickSelect {
    public int quickSelect(int[] array, int k) {
        return helper(array, 0, array.length - 1, k);
    }

    private int helper(int[] array, int start, int end, int k) {
        if (start >= end) {
            return array[start];
        }
        int pivot = array[end];
        int i = start;
        int j = end;
        while(true) {
            while(i < j && array[i] < pivot) i++;
            while(i < j && array[j] >= pivot) j--;
            if (i == j) break;
            swap(array, i, j);
        }
        swap(array, i, end);
        if (i < k) return helper(array, i + 1, end, k);
        else if (i > k) return helper(array, start, i - 1, k);// have to use i -1 instead of i
        else return array[k];
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String args[]) {
        int[] A = {6,4,7,9,1,2,0,5,3,8, 8, 8, 8};
        int test = new QuickSelect().quickSelect(A, 5);
        for (int i = 0; i < 13; i++) {
            System.out.println(A[i]);
        }
    }
}
