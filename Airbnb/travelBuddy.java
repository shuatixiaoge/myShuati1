import java.util.*;
public class travelBuddy {
    private List<Buddy> buddies;
    private Set<String> myWishList;

    public travelBuddy(Set<String> myWishList, Map<String, Set<String>> friendsWishList) {
        this.myWishList = myWishList;
        this.buddies = new ArrayList<>();
        for (String k : friendsWishList.keySet()) {
            //一定要新建一个List 不然因为muttable 所以存进去的东西会变
            Set<String> fList = new HashSet<>(friendsWishList.get(k));
            fList.retainAll(myWishList);
            if (fList.size() >= myWishList.size()/2) {
                buddies.add(new Buddy(k, friendsWishList.get(k), fList.size()));
            }
        }
    }
    public List<Buddy> getSortedBuddies() {
        Collections.sort(buddies);
        List<Buddy> res = new ArrayList<>(buddies);
        return res;
    }

    public List<String> recommendCities(int k) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while(i < buddies.size() && res.size() < k) {
            Set<String> tmp = buddies.get(i).list;
            tmp.removeAll(this.myWishList);
            if (k - res.size() >= tmp.size()) {
                res.addAll(tmp);
            } else {
                Iterator<String> iter = tmp.iterator();
                while(k - res.size() > 0) {
                    res.add(iter.next());
                }
            }
            i++;
        }
        return res;
    }

    public class Buddy implements Comparable<Buddy>{
        String name;
        Set<String> list;
        int similarity;
        public Buddy(String n, Set<String> l, int s) {
            this.name = n;
            this.list = l;
            this.similarity = s;
        }
        @Override
        public int compareTo(Buddy b) {
            return b.similarity - this.similarity;
        }
    }

    public static void main(String args[]) {
        String[] a = {"a", "b", "c","d"};
        Set<String> l1 = new HashSet<>(Arrays.asList(a));
        String[] b1 = {"a", "b", "e","f"};
        Set<String> l2 = new HashSet<>(Arrays.asList(b1));
        String[] b2 = {"a", "c", "d","g"};
        Set<String> l3 = new HashSet<>(Arrays.asList(b2));
        Map<String, Set<String>> m = new HashMap<>();
        m.put("f1", l2);
        m.put("f2", l3);

        travelBuddy test = new travelBuddy(l1, m);
        for (Buddy h: test.getSortedBuddies()) {
            System.out.println(h.name);
        }
        for (String s: test.recommendCities(3)) {
            System.out.println(s);
        }
    }
}
