// 给一个图中的n个节点, 记为 1 到 n . 在开始的时候图中没有边。
// 你需要完成下面两个方法:
//
// connect(a, b), 添加连接节点 a, b 的边.
// query(a, b), 检验两个节点是否联通
public class ConnectingGraph {

    private int[] father = null;

    private int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }

    public ConnectingGraph(int n) {
        // initialize your data structure here.
        father = new int[n + 1];
        for (int i = 1; i <= n; ++i)
            father[i] = i;
    }

    public void connect(int a, int b) {
        // Write your code here
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b)
            father[root_a] = root_b;
    }

    public boolean  query(int a, int b) {
        // Write your code here
        int root_a = find(a);
        int root_b = find(b);
        return root_a == root_b;
    }
}
