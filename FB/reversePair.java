public class Solution {
    int[] helper;
    public int reversePairs(int[] nums) {
        this.helper = new int[nums.length];
        return mergeSort(nums, 0, nums.length-1);
    }
    private int mergeSort(int[] nums, int s, int e){
        if(s>=e) return 0;
        int mid = s + (e-s)/2;
        int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid+1, e);
        for(int i = s, j = mid+1; i<=mid; i++){
            while(j<=e && nums[i]/2.0 > nums[j]) j++;
            cnt += j-(mid+1);
        }
        //Arrays.sort(nums, s, e+1);
        myMerge(nums, s, mid, e);
        return cnt;
    }

    private void myMerge(int[] nums, int s, int mid, int e){
        for(int i = s; i<=e; i++) helper[i] = nums[i];
        int p1 = s;//pointer for left part
        int p2 = mid+1;//pointer for rigth part
        int i = s;//pointer for sorted array
        while(p1<=mid || p2<=e){
            if(p1>mid || (p2<=e && helper[p1] >= helper[p2])){
                nums[i++] = helper[p2++];
            }else{
                nums[i++] = helper[p1++];
            }
        }
    }
}

//BST worst case O(n2) due to inbalance
public class Solution {
    public int reversePairs(int[] nums) {
        Node root = null;
        int[] cnt = new int[1];
        for(int i = nums.length-1; i>=0; i--){
            search(cnt, root, nums[i]/2.0);//search and count the partially built tree
            root = build(nums[i], root);//add nums[i] to BST
        }
        return cnt[0];
    }

    private void search(int[] cnt, Node node, double target){
        if(node==null) return;
        else if(target == node.val) cnt[0] += node.less;
        else if(target < node.val) search(cnt, node.left, target);
        else{
            cnt[0]+=node.less + node.same;
            search(cnt, node.right, target);
        }
    }

    private Node build(int val, Node n){
        if(n==null) return new Node(val);
        else if(val == n.val) n.same+=1;
        else if(val > n.val) n.right = build(val, n.right);
        else{
            n.less += 1;
            n.left = build(val, n.left);
        }
        return n;
    }

    class Node{
        int val, less = 0, same = 1;//less: number of nodes that less than this node.val
        Node left, right;
        public Node(int v){
            this.val = v;
        }
    }
}
