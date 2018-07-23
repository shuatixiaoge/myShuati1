public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        int len = preorder.length;
        Stack<Integer> stk = new Stack<>();
        //先走到最左下角，然后维护一个最小值，并每次更新，这个最小值就是比当前较小的值，如果发现最小值比当前值还大说明有问题
        for (int i = 0; i < len; i++) {
            if (low > preorder[i]) {
                return false;
            }
            while (!stk.isEmpty() && stk.peek() < preorder[i]) {
                low = stk.pop();
            }
            stk.push(preorder[i]);
        }
        return true;
    }
}

// 这样我们的判断条件变成了这样，给定 array 如 5(123)(678)，第一个元素一定为
// root，后面的比 root 小的连续序列为左子树，比 root 大的连续序列为右子树
public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1);
    }
    private boolean helper(int[] preorder, int start, int end){
        if(end - start <= 1) return true;
        int breakPoint = start + 1;
        int root = preorder[start];
        // breakPoint should stop at index of first element > root
        // if no left subtree, breakPoint stops at start;
        for(int i = start + 1; i <= end; i++){
            if(preorder[i] < root) breakPoint ++;
            else break;
        }
        for(int i = breakPoint; i <= end; i++){
            if(preorder[i] < root) return false;
        }
        return helper(preorder, start + 1, breakPoint - 1) && helper(preorder, breakPoint, end);
    }
}
