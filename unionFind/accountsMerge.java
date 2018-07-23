class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, String> parents = new HashMap<>();
        HashMap<String, TreeSet<String>> unions = new HashMap<>();
        HashMap<String, String> owners = new HashMap<>();
        for(List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                parents.put(account.get(i), account.get(i));
                owners.put(account.get(i), account.get(0));
            }
        }
        // find to group
        for(List<String> account : accounts) {
            //use first one as default, if there are any duplicate the parent would change
            String p = find(account.get(1), parents);
            for (int i = 2; i < account.size(); i++) {
                //this p has to be the last one, since there is only one parent, not only severial parents
                // THIS HAS TO BE THE FIND!!!!! OTHERWISE IT WOULD OVERWRITE THE HASH OF CURR, and lose the connection between current and the 1-indx element
                parents.put(find(account.get(i), parents), p);
            }
        }

        for(List<String> account : accounts) {
            String p = find(account.get(1), parents);
            if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
            for (int i = 1; i < account.size(); i++) {
                unions.get(p).add(account.get(i));
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (String s : unions.keySet()) {
            List<String> list = new ArrayList<>(unions.get(s));
            list.add(0, owners.get(s));
            res.add(list);
        }
        return res;
    }

    public String find(String s, Map<String, String> parents) {
        if (s.equals(parents.get(s))) {
            return s;
        }
        String p = find(parents.get(s), parents);
        parents.put(s, p);
        return p;
    }
}
