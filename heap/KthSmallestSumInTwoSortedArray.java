// 给定两个排好序的数组 A, B，定义集合 sum = a + b ，求 sum 中第k小的元素
class Pair {
    public int x, y, sum;
    public Pair(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.sum = val;
    }
}
class PairComparator implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        return a.sum - b.sum;
    }
}

public class Solution {
    /**
     * @param A an integer arrays sorted in ascending order
     * @param B an integer arrays sorted in ascending order
     * @param k an integer
     * @return an integer
     */
    public int kthSmallestSum(int[] A, int[] B, int k) {
        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};
        boolean[][] hash = new boolean[A.length][B.length];
        PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(k, new PairComparator());
        minHeap.add(new Pair(0, 0, A[0] + B[0]));

        for(int i = 0; i < k - 1; i ++){
            Pair cur = minHeap.poll();
            for(int j = 0; j < 2; j ++){
                int next_x = cur.x + dx[j];
                int next_y = cur.y + dy[j];
                Pair next_Pair = new Pair(next_x, next_y, 0);
                if(next_x < A.length && next_y < B.length &&  !hash[next_x][next_y]){
                    hash[next_x][next_y] = true;
                    next_Pair.sum = A[next_x] + B[next_y];
                    minHeap.add(next_Pair);
                }
            }
        }
        return minHeap.peek().sum;
    }
}
