//Follow Up add Locks
class MyRunnableTask implements Runnable {
   public void run() {
     System.out.println("HIHI1");
   }
}
public class Solution {
    Map<String, Integer> pathMap;
    Map<String, Runnable> callbackMap;
    public Solution() {
        this.pathMap = new HashMap<>();
        this.callbackMap = new HashMap<>();
        //Inserting the initial is important since the create is using last index which could be empty substring
        this.pathMap.put("", 0);
    }
    public boolean create(String path, int value) {
        if (pathMap.containsKey(path)) {
            return false;
        }
        int lastSlashIndex = path.lastIndexOf("/");
        if (!pathMap.containsKey(path.substring(0, lastSlashIndex))) {
            return false;
        }
        pathMap.put(path, value);
        return true;
    }
    public boolean set(String path, int value) {
        if (!pathMap.containsKey(path)) {
            return false;
        }
        pathMap.put(path, value);
        // Trigger callbacks
        String curPath = path;
        while (curPath.length() > 0) {
            if (callbackMap.containsKey(curPath)) {
                callbackMap.get(curPath).run();
            }
            int lastSlashIndex = path.lastIndexOf("/");
            curPath = curPath.substring(0, lastSlashIndex);
        }
        return true;
    }
    public Integer get(String path) {
        return pathMap.get(path);
    }
    public boolean watch(String path, Runnable callback) {
        if (!pathMap.containsKey(path)) {
            return false;
        }
        callbackMap.put(path, callback);
        return true;
    }
    public static void main(String[] args) {
      Solution s = new Solution();
      s.create("/a", 1);
      s.create("/a/b", 2);
      s.create("/a/b/c", 3);
      System.out.println(s.get("/a/b/c"));
      System.out.println(s.set("/a/b", 4));
      System.out.println(s.watch("/a/b", new MyRunnableTask()));
      System.out.println(s.watch("/a", new MyRunnableTask()));
      System.out.println(s.set("/a/b/c", 5));
      System.out.println(s.get("/a/b/c"));
      System.out.println(s.get("/a/b"));
    }
}
