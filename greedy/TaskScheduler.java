class Solution {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Chracter, Integer> map = new HashMap<>();
        for (char task : tasks) {
            if (map.containsKey(task)) {
                map.put(task, map.get(task) + 1);
            } else {
                map.put(task, 1);
            }
        }

        Map<Integer, Character> reverseMap = new TreeMap<>();
        for (Map.Entry<Integer, Character> entry : map.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }

        int sum = 0;
        for (Map.Entry<Integer, Character> entry : reverseMap.entrySet()) {
            if (n < reverseMap.size()) {
                sum += reverseMap.size();
            }
            else {
                sum += n;
            }
            reverseMap.remove(entry.getKey());
        }
        return sum;
    }
}
