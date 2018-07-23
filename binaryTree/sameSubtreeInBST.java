public class Solution {
    private static class TreeNode{
        int val;
        TreeNode left, right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    private static class TreeTuple{
        String key;
        int id;
        int size;
        public TreeTuple(String key, int id, int size){
            this.key = key;
            this.id = id;
            this.size = size;
        }
    }
    public static int largestSubtree(TreeNode root){
        HashMap<String, Integer> keyMap = new HashMap<>();
        HashMap<Integer, List<TreeTuple>> groupMap = new HashMap<>();
        int[] id = new int[1];
        id[0] = 1;
        postOrder(root, keyMap, groupMap, id);
        Iterator<Integer> iter = groupMap.keySet().iterator();
        int maxSize = 0;
        while(iter.hasNext()){
            int groupId = iter.next();
            List<TreeTuple> list = groupMap.get(groupId);
            if(list.size() > 1) maxSize = Math.max(maxSize, list.get(0).size);
        }
        return maxSize;
    }
    // keyMap : Key - String - (val, leftId, rightId)
    // Val - Integer - groupId
    // groupMap : Key - Integer - groupId
    // Val - Integer - number of occurrances
    private static TreeTuple postOrder(TreeNode root, HashMap<String, Integer> keyMap, HashMap<Integer, List<TreeTuple>> groupMap, int[] id){
        if(root == null) return new TreeTuple("0,0,0", 0, 0);
        TreeTuple left = postOrder(root.left, keyMap, groupMap,id);
        TreeTuple right = postOrder(root.right, keyMap, groupMap, id);
        int curId;
        TreeTuple curTuple;
        String key = "" + root.val + "," + left.id + "," + right.id;
        if(!keyMap.containsKey(key)){
            curId = id[0]++;
            keyMap.put(key, curId);
            groupMap.put(curId, new ArrayList<>());
            curTuple = new TreeTuple(key, curId, left.size + right.size + 1);
            groupMap.get(curId).add(curTuple);
        } else {
            curId = keyMap.get(key);
            curTuple = new TreeTuple(key, curId, left.size + right.size + 1);
            groupMap.get(curId).add(curTuple);
        }
        return curTuple;
    }
