import java.util.*;
public class ListOfListIterator {
    // private Iterator<List<Integer>> rowIter;
    // private Iterator<Integer> colIter;
    //
    // public ListOfListIterator(List<List<Integer>> list) {
    //     rowIter = list.iterator();
    //     colIter = null;
    // }
    //
    // public Integer next() {
    //     return colIter.next();
    // }
    //
    // public boolean hasNext() {
    //     // if has to be while loop since it would deal with the case of empty arraylist
    //     while(rowIter.hasNext() && (colIter == null || !colIter.hasNext()))
    //         colIter = rowIter.next().iterator();
    //     return colIter != null && colIter.hasNext();
    // }
    //
    // public void remove() {
    //     // for the first time call remove
    //     // remove don't care about hasNext or not, it cares about current
    //     //don't need  !colIter.hasNext() here since if you are removing the last element of col, then it needs to use the old colIter
    //
    //     // if(rowIter.hasNext() && (colIter == null))
    //     //     colIter = rowIter.next().iterator();
    //     if (colIter != null) colIter.remove();
    // }


    //follow Up 小哥二号出了道flattern 2D vector with remove的题，我虽然当时没有做过但也知道解法啊，秒了+thorough testcases。
    // 小哥二号说奥森！你能来几个testcases比如只print出奇数位的value吗？你能在某些情况下remove的同时print out一些msg吗，比如说这个list空了我要去下一层list之类的，一一秒了。

    public static void main(String[] args) {
      List<List<Integer>> array = new ArrayList<>();
      List<Integer> row1 = new ArrayList<>();
      row1.add(1);
      row1.add(2);
      row1.add(3);

      array.add(row1);

      List<Integer> row3 = new ArrayList<>();
      array.add(row3);

      List<Integer> row2 = new ArrayList<>();
      row2.add(4);
      array.add(row2);
      List<Integer> row4 = new ArrayList<>();
      array.add(row4);

      List<Integer> row5 = new ArrayList<>();
      row5.add(5);
      array.add(row5);

      ListOfListIterator sol = new ListOfListIterator(array);
      while (sol.hasNext()) {
        int result = sol.next();
        System.out.println(result);
        if (result == 5)
            System.out.println(sol.hasNext());
        if (result != 4) sol.remove();
      }

      System.out.println();

      for (List<Integer> row : array) {
        for (Integer elem : row) {
          System.out.println(elem);
        }
      }
    }
    int col;
    int row;
    List<List<Integer>> list;

    public ListOfListIterator(List<List<Integer>> list) {
        this.col = 0;
        this.row = 0;
        this.list = list;
    }

    public Integer next() {
        if (hasNext()) {
            return list.get(row).get(col++);
        } else {
            return null;
        }
    }

    public boolean hasNext() {
        // have to check the row first, since that row could be removed to be empty
        // while (row < list.size() && list.get(row).size() == 0 ) {
        //     row++;
        // }
        // if (row >= list.size()) return false;
        if (col == list.get(row).size()) {
            row++;
            while (row < list.size() && list.get(row).size() == 0 ) {
                row++;
            }
            col = 0;
        }
        return row < list.size() && row >= 0 && col < list.get(row).size() && col >= 0;
    }

    public void remove() {
        if(col == 0) {
            if (row == 0) return; // dont need this line
            else {
                row--;
                while(row >= 0 && list.get(row).size() == 0) {
                    row--;
                }
                if (row == -1) return;
                col = list.get(row).size() - 1;
            }
        } else {
            col--;
        }
        list.get(row).remove(col);
    }
}
