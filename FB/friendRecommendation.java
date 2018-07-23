// Friend Recommendation
// return friends of friends that are not this persons friends
// // http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=210056&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D2%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311 
//
// 思路：第二层的friends排序，选出k个共同好友做多的friends
//
//
// Solution 1: Bucket Sort
// Time: O(m)  Space: O(n)
// m: # of person's friends' friends, n: # of legal recommend friends

public class Person {
    int id;
    HashSet<Integer> friends = new HashSet<>();
}
private List<Integer> friendsRecommend(Person person, int k) {
    List<Integer> res = new ArrayList<>();
    if (person == null)    return res;
    Map<Integer, Integer> map = new HashMap<>(); // recommend id -> count
    int b = 0;
    for (int friend : person.friends)
        for (int recommend : friend.friends) {
           int id = recommend.id;
           if (person.friends.contains(id) || id == person.id) // don't forget 'id == person.id'
               continue;
           map.put(id, map.getOrDefault(id, 0) + 1);
           b = Math.max(b, map.get(id));
       }
    // bucket sort 'recommend list'
    List<Integer>[] buckets = new List[b];
    for (int id : map.keySet()) {
        if (buckets[map.get(id)] == null)
            buckets[map.get(id)] = new ArrayList<Integer>();
        buckets[map.get(id)].add(id);
    }
    //this two loops are O(k) time, when res has k nums, return it
    for (int i = b; i >= 0; i--)
        for (int j = 0; j < buckets[i].size(); j++) {
            res.add(buckets[i].get(j));
            if (res.size() == k)    return res;
        }
    return res;
}
