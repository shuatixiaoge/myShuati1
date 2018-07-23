public class Solution {
  ListNode cur=null;
	public TreeNode sortedListToBST(ListNode head) {
		int k=0;
		cur=head;
		ListNode temp=head;
		while(temp!=null){
			temp=temp.next;
			k++;
		}

		return helper(0,k-1);
    }
	public TreeNode helper(int left, int right){
		 if(left>right) return null;
		 int mid=left+(right-left)/2;

		TreeNode node=new TreeNode(-1);
		node.left=helper(left,mid-1);
		node.val=cur.val;
		cur=cur.next;
		node.right=helper(mid+1,right);
		return node;
	}


}
