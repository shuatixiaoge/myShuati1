
TreeNode head = null, prev = null;
public TreeNode convertBSTtoCircularDL(TreeNode root) {
	convert(root);
	return head;
}
public void convert(TreeNode root) {
	if (root == null)	return;
	convert(root.left);
	root.left = prev;
	if (prev != null)	prev.right = root;
	else	head = root;
	// would make head <-> tail in the end
	TreeNode right = root.right;
	head.left = root;
	root.right = head;
	prev = root;
	convert(right);
}
