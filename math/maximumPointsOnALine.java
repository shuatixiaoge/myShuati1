public class Solution {
    public int maxPoints(Point[] points) {
        if(points == null) return 0;
        if(points.length <= 2) return points.length;
        int max = 0;
        for(int i = 0; i < points.length; i++){
            Point p1 = points[i];
            int samePoint = 1;
            int localMax = 0;
            HashMap<Double, Integer> map = new HashMap<Double, Integer>()
            for(int j = i - 1; j >= 0; j-- ){
                Point p2 = points[j];
                double slope = 0.0;
                if(p1.y == p2.y && p1.x == p2.x) {
                    samePoint++;
                    continue;
                } else if(p1.x == p2.x) {
                    slope = Double.MAX_VALUE;
                } else {
                    slope = (double)(p1.y - p2.y) / (p1.x - p2.x);
                }
                if(map.containsKey(slope)){
                    int count = map.get(slope);
                    map.put(slope, count + 1);
                    localMax = Math.max(localMax, count + 1);
                } else {
                    map.put(slope, 1);
                    localMax = Math.max(localMax, 1);
                }
            }
            max = Math.max(max, localMax + samePoint);
        }
        return max;
    }
};
