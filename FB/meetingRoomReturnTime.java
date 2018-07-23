

    //Follow-up
    // return the max overlap meetingroom
    import java.util.*;
public class  meetingRoomReturnTime {
    public List<Integer> minMeetingRooms2(int[][] intervals) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] i : intervals) {
            if (!map.containsKey(i[0])) {
                map.put(i[0], 1);
            }
            else {
                map.put(i[0], map.get(i[0]) + 1);
            }
            if (!map.containsKey(i[1])) {
                map.put(i[1], -1);
            }
            else {
                map.put(i[1], map.get(i[1]) - 1);
            }
        }
        List<Integer> arr = new ArrayList<>(map.keySet());
        Collections.sort(arr);
        int room = 0, res = 0;
        List<Integer> list = new ArrayList<>();
        for (Integer c : arr) {
            room += map.get(c);// += not =
            if (res < room) {
                list.clear();
                list.add(c);
                res = room;
            } else if (res == room) {
                list.add(c);
            }
        }
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            resList.add(list.get(i));
            int start = list.get(i) + 1;
            for (; !map.containsKey(start); start++) {
                resList.add(start);
            }
        }
        return resList;
    }
    public static void main(String[] args) {
  meetingRoomReturnTime s = new meetingRoomReturnTime();
  System.out.println(s.minMeetingRooms(new int[][] {{1, 3}, {2, 7}, {4, 8}, {5, 9}}));
  System.out.println(s.minMeetingRooms(new int[][] {{1, 10}}));
  System.out.println(s.minMeetingRooms(new int[][] {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}}));
  System.out.println(s.minMeetingRooms(new int[][] {{1, 2}}));
  System.out.println(s.minMeetingRooms(new int[][] {{5, 9}, {5, 10}, {7, 13}}));
  System.out.println(s.minMeetingRooms(new int[][] {}));
  System.out.println(s.minMeetingRooms(new int[][] {{1, 4}, {1, 4}, {1, 4}}));
}

class timepoint {
  int t;
  int end;

  public timepoint(int tt, int se) {
    t = tt;
    end = se;
  }
}

public List<Integer> minMeetingRooms(int[][] intervals) {
  List<timepoint> timepoints = new ArrayList<>();
  for (int[] interval : intervals) {
    timepoints.add(new timepoint(interval[0], 0));
    timepoints.add(new timepoint(interval[1], 1));
  }
  timepoints.sort((x, y) -> {
    if (x.t != y.t)
      return x.t - y.t;
    return y.end - x.end;
  });
  // find
  List<timepoint> ret = new ArrayList<>();
  int max = 0;
  int count = 0;
  for (timepoint tp : timepoints) {
    if (tp.end == 0) {
      count++;
      if (count > max) {
        max = count;
        ret.clear();
        ret.add(tp);
      } else if (count == max) {
        ret.add(tp);
      }
    } else if (tp.end == 1) {
      if (count == max) {
        ret.add(tp);
      }
      count--;
    }
  }
  // build ret
  List<Integer> ret2 = new ArrayList<>();
  for (int i = 0; i < ret.size(); i += 2) {
    for (int j = ret.get(i).t; j < ret.get(i + 1).t; j++) {
      ret2.add(j);
    }
  }
  return ret2;
}
}
