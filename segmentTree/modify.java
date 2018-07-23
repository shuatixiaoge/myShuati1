public class Solution {
    /**
    *@param root, index, value: The root of segment tree and
    *@ change the node's value with [index, index] to the new g
    iven value
    *@return: void
    */
    public void modify(SegmentTreeNode root, int index, int value) {
        // write your code here
        if(root == null) return;
        if(index < root.start || index > root.end) return;
        // Segment Tree 不会出现单独分叉的节点，所以到叶节点可以直接返回。
        if(index == root.start && index == root.end){
            root.max = value;
            return;
        }
        modify(root.left, index, value);
        modify(root.right, index, value);
        root.max = Math.max(root.left.max, root.right.max);
    }
}
