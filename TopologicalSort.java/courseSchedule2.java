class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];  //since courses are integer so data structure is different than alien dic
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
            degree[prerequisites[i][1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int idx = numCourses; // the result array starts with the last to the begin
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            result[--idx] = curr;
            Iterator<Integer> iter =  graph[curr].iterator();
            while(iter.hasNext()) {
                int course = iter.next();
                if(--degree[course] == 0) queue.offer(course);
            }
        }
        if (idx != 0) return new int[0];

        return result;
    }
}
