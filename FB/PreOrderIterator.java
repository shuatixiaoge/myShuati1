class PreOderIterator {
    Stack<Node> stack;
    Node preNode;
    Node root;
    Map<Node, Node> map;

    public PreOderIterator(Node root) {
        stack = new Stack<Node>();
        if (root != null)
            stack.push(root);

        preNode = null;
        map = HashMap<Node, Node>();
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public Value next() {
        if (!hasNext())
            return null;

        Node n = stack.pop();
        if (n.right != null) {
            stack.push(n.right);
            map.put(n.right, n);
        }
        if (n.left != null) {
            stack.push(n.left);
            map.put(n.left, n);
        }

        if (preNode != null)  // 这两行事后才想起 来源一亩.三分地论坛.
            map.remove(preNode);

        preNode = n;

        return n.val;
    }

    public void remove() {
        if (preNode == null)
            return;

        if (preNode.left != null) {
            stack.pop();
            map.remove(preNode.left); // 事后才想起
        }
        if (preNode.right != null) {
            stack.pop();
            map.remove(preNode.right); // 事后才想起
        }
        if（map.containsKey(preNode) {
            Node parent = map.get(preNode);
            if (preNode == parent.left)
                parent.left = null;
            else if (preNode == parent.right)
                parent.right = null;
        } else if (root == preNode) {
            root = null;
            map.clear(); // 事后才想起
        }

        map.remove(preNode); // 事后才想起
        preNode = null;
    }
}. more info on
