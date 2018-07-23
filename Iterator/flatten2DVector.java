public class Vector2D {

    Queue<Iterator<Integer>> queue;
    Iterator<Integer> current = null;

    public Vector2D(List<List<Integer>> vec2d) {
        queue = new LinkedList<Iterator<Integer>>();
        for (int i = 0; i < vec2d.size(); i++){
            queue.add(vec2d.get(i).iterator());
        }
        current = queue.poll(); // first
    }

    public int next() {
        if (!current.hasNext()) return -1;

        return current.next();
    }

    public boolean hasNext() {
        if (current == null) return false;

        while (!current.hasNext()) {
            if (!queue.isEmpty()) {
                current = queue.poll();
            } else return false;
        }

        return true;
    }

    //Solution 2
    //Without Extra space
    public class Vector2D implements Iterator<Integer> {
    int x, y;
    List<List<Integer>> list;
    public Vector2D(List<List<Integer>> vec2d) {
        x = 0;
        y = 0;
        list = vec2d;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            Integer res = list.get(x).get(y++);
            if (y == list.get(x).size()) {
                x++;
                y = 0;
            }
            return res;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        // this is important for the case when some list is empty;
        while (x < list.size() && list.get(x).size() == 0) x++;
        return x < list.size() && x >= 0 && y < list.get(x).size() && y >= 0;
    }
}

}
