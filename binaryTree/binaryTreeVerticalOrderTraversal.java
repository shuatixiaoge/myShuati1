/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null) return list;
        Queue<Integer> lvl = new LinkedList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        lvl.offer(0);
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int currentLvl = lvl.poll();
            if (map.containsKey(currentLvl)) {
                List<Integer> tmpList = map.get(currentLvl);
                tmpList.add(node.val);
                map.put(currentLvl, tmpList);
            } else {
                List<Integer> newList = new ArrayList<Integer>();
                newList.add(node.val);
                map.put(currentLvl, newList);
            }

            if (node.left != null) {
                queue.offer(node.left);
                lvl.offer(currentLvl - 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                lvl.offer(currentLvl + 1);
            }

        }

        for (int i = Collections.min(map.KeySet()); i <= Collections.max(map.KeySet()); i++) {
            list.add(map.get(i));
        }
        return list;
    }
}
