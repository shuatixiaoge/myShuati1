import java.util.*;
public class NumberOfIntersectedRectangles {
    int[] parent;
    public int countIntersection(int[][][] rectangles) {
        int n = rectangles.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            // don't forget parent initialization
            parent[i] = i;
        }
        // count is starting with n + 1
        int res = n + 1;

        for (int i = 0; i < n - 1; i++) {
            int[][] rect1 = rectangles[i];
            for (int j = i + 1; j < n; j++) {
                int[][] rect2 = rectangles[j];
                if (intersect(rect1, rect2)) {
                    int r1 = find(i);
                    int r2 = find(j);
                    //dont't forget r1 != r2
                    if (r1 != r2) {
                        parent[r1] = r2;
                    }
                    res--;
                }
            }
        }
        return res;
    }

    private int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private boolean intersect(int[][] r1, int[][] r2) {
        if (r1[0][0] > r2[1][0] || r1[1][0] < r2[0][0]) return false;
        if (r1[1][1] < r2[0][1] || r1[0][1] > r2[1][1]) return false;
        return true;
    }

    public static void main(String[] args) {
        NumberOfIntersectedRectangles t = new NumberOfIntersectedRectangles();
        int[][][] rectangles = {
            {{-3, -2}, {2, 1}},
            {{10, 8}, {15, 10}},
            {{1, 0}, {7, 4}},
            {{12, 9}, {16, 12}},
            {{-2, -1}, {5, 3}}
        };
        System.out.println(t.countIntersection(rectangles));
    }
}
