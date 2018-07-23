/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<Integer, UndirectedGraphNode> visited = new HashMap<>();
        return helper(node, visited);
    }
    public UndirectedGraphNode helper(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> visited) {
        if (node == null) return null;
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        if (visited.containsKey(node.label)) return visited.get(node.label);// has to use hashmap to memorize the node that has been cloned
        //if using hashset it would create extra node
        visited.put(node.label, newNode);// doing insertion here so it would have the self link
        for (UndirectedGraphNode n : node.neighbors) {
            UndirectedGraphNode tmpNode = helper(n, visited);
            newNode.neighbors.add(tmpNode);
        }
        //no need to remove the map, it would fail on 4,5,5 case
        return newNode;
    }
}
