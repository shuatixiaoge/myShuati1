public class Solution {
    public List<String> findItinerary(String[][] tickets) {
        LinkedList<String> list = new LinkedList<>();
        HashMap<String, PriorityQueue<String>> map = new HashMap
        <>();
        for(String[] ticket : tickets){
            if(!map.containsKey(ticket[0])) map.put(ticket[0], n
            ew PriorityQueue<String>());
            map.get(ticket[0]).add(ticket[1]);
        }
        dfs(list, "JFK", map);
        return new ArrayList<String>(list);
    }
    private void dfs(LinkedList<String> list, String airport, Ha
    shMap<String, PriorityQueue<String>> map){
        while(map.containsKey(airport) && !map.get(airport).isEm
        pty()){
            dfs(list, map.get(airport).poll(), map);
        }
        list.offerFirst(airport);
    }
}
