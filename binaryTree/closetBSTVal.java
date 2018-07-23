public class Solution {
    public int val;
    public int closestValue(TreeNode root, double target) {
        val = root.val;
        helper(root, target);
        return val;
    }
    public void helper(TreeNode root, double target) {
        if (root == null) return;
        // 最好不要用一个min值来存最小值，这样很容易会越界出问题。
        if (Math.abs(root.val - target) <= Math.abs(val - target)) {
            val = root.val;
        }
        if (root.val == target) return;
        else if (root.val > target)
            helper(root.left, target);
        else
            helper(root.right, target);
    }
}

//What if you want to get K Elements
//In order tranverse make sure the later element is always larger than the current element

public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        closestKValuesHelper(list, root, target, k);
        return list;
    }

    /**
     * @return <code>true</code> if result is already found.
     */
    private boolean closestKValuesHelper(LinkedList<Integer> list, TreeNode root, double target, int k) {
        if (root == null) {
            return false;
        }

        if (closestKValuesHelper(list, root.left, target, k)) {
            return true;
        }

        if (list.size() == k) {
            if (Math.abs(list.getFirst() - target) < Math.abs(root.val - target)) {
                return true;
            } else {
                list.removeFirst();
            }
        }

        list.addLast(root.val);
        return closestKValuesHelper(list, root.right, target, k);
    }
}
