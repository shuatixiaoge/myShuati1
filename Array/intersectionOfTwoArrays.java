public class Solution {
    // the array is not sorted, O(N), time complexity, and result should have no duplicate
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            result[i++] = num;
        }
        return result;
    }

    // II what if the result should be as many as it intersects
    public class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            ArrayList<Integer> result = new ArrayList<Integer>();
            for(int i = 0; i < nums1.length; i++)
            {
                if(map.containsKey(nums1[i])) map.put(nums1[i], map.get(nums1[i])+1);
                else map.put(nums1[i], 1);
            }

            for(int i = 0; i < nums2.length; i++)
            {
                if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0)
                {
                    result.add(nums2[i]);
                    map.put(nums2[i], map.get(nums2[i])-1);
                }
            }

           int[] r = new int[result.size()];
           for(int i = 0; i < result.size(); i++)
           {
               r[i] = result.get(i);
           }

           return r;
        }
    }
}
