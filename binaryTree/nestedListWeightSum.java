/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
public int depthSum(List<NestedInteger> nestedList) {
    return helper(nestedList, 1);
}

private int helper(List<NestedInteger> list, int depth)
{
    int ret = 0;
    for (NestedInteger e: list)
    {
        ret += e.isInteger()? e.getInteger() * depth: helper(e.getList(), depth + 1);
    }
    return ret;
}
}


// Problem 2 what if the level weight is reverse

public class Solution {
    private class Tuple{
        int sum;
        int depth;
        public Tuple(int val, int dep){
            sum = val;
            depth = dep;
        }
    }
    public int depthSumInverse(List<NestedInteger> nestedList) {
        return dfs(nestedList).sum;
    }
    private Tuple dfs(List<NestedInteger> list){
        int sum = 0;
        int maxDepth = 1;
        for(NestedInteger node : list){
            if(!node.isInteger()){
                Tuple tuple = dfs(node.getList());
                sum += tuple.sum;
                maxDepth = Math.max(maxDepth, tuple.depth + 1);
            }
        }
        for(NestedInteger node : list){
            if(node.isInteger()) sum += maxDepth * node.getInteger();
        }
        return new Tuple(sum, maxDepth);
    }
}

//instead of using the depth, just recalculate it everytime you have it.

class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
    int unweighted = 0, weighted = 0;
    while (!nestedList.isEmpty()) {
        List<NestedInteger> nextLevel = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger())
                unweighted += ni.getInteger();
            else
                nextLevel.addAll(ni.getList());
        }
        weighted += unweighted;
        nestedList = nextLevel;
    }
    return weighted;
}
}
