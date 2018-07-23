// 5
// 13
// 1,28,310.6,SF
// 4,5,204.1,SF
// 20,7,203.2,Oakland
// 6,8,202.2,SF
// 6,10,199.1,SF
// 1,16,190.4,SF
// 6,29,185.2,SF
// 7,20,180.1,SF
// 6,21,162.1,SF
// 2,18,161.2,SF
// 2,30,149.1,SF
// 3,76,146.2,SF
// 2,14,141.1,San	Jose
//
//
// 以上是一个 Sample	输入，和希望的输出，1,28,100.3,Paris	代表 Host	ID,	List	ID,	Points,	City.	这是
// Airbnb 根据用户搜索条件得出的一些 list，然后我们要分页，第一行的 5 代表每一页最多展示 5 个
// list，13 应该是代表有 13 个 List.所以我们是要分成 3 页。规则是：每一页最多展示一个 host 的
// list，但是如果再没有其他 host 的 list 可以展示了，就按照原有的顺序填补就可（根据 Points，也
// 就是排名）。应得到的输出：
// 希望输出：
// 1,28,310.6,SF
// 4,5,204.1,SF
// 20,7,203.2,Oakland
// 6,8,202.2,SF
// 7,20,180.1,SF
// 6,10,199.1,SF
// 1,16,190.4,SF
// 2,18,161.2,SF
// 3,76,146.2,SF
// 6,29,185.2,SF -- 这时不得不重复了，从原有队列拉出第一个
// 6,21,162.1,SF
// 2,30,149.1,SF
// 2,14,141.1,San	Jose
import java.util.*;
public class displayPages {
    public List<String> displayPages(List<String> input, int pageSize) {
        List<String> res = new ArrayList<>();
        if (input == null || pageSize == 0) return res;
        Iterator<String> iter = input.iterator();
        HashSet<String> set = new HashSet<>();
        //pageEnd means scan the first loop finished or not
        boolean pageEnd = false;
        int count = 0;
        while(iter.hasNext()) {
            String str = iter.next().split(",")[0];
            if (!set.contains(str) || pageEnd) {
                res.add(str);
                set.add(str);
                count++;
                iter.remove();
            }
            if (count == pageSize) {
                if (!input.isEmpty()) res.add(" "); //Don't forget input cannot be empty here
                count = 0;
                set.clear();
                iter = input.iterator();// don't forget to set the iterator here as well
                pageEnd = false;
            }
            //没办法了 剩下的都要填满到当前页, 不一定是最后一页
            if (!iter.hasNext()) {
                pageEnd = true;
                iter = input.iterator();
            }
        }
        // it would fill in first page first, and then second page.
        return res;
    }
    public static void main(String args[]) {
        List<String> l = new ArrayList<>();
        l.add("1");
        l.add("2");
        Iterator<String> iter = l.iterator();
        iter.next();
        iter.remove();
        for (String s : l) System.out.println(s);
    }
}
