public class Solution {
    public int trap(int[] A){
        if(A.length==0) return 0;
        int right=A.length-1;
        int left=0;
        int sum=0;
        int leftmax=A[0];
        int rightmax=A[right];
        //while(left<right){
        //    if(A[left]<A[right]){
        //        leftmax=Math.max(leftmax,A[left]);
        //        int t= Math.min(leftmax,rightmax)-A[left++];
        //        sum += (t>0) ? t:0;
        //    }
        //    else{
        //        rightmax=Math.max(rightmax,A[right]);
        //        int tmp=Math.min(leftmax,rightmax)-A[right--];
        //        sum += (tmp>0)? tmp:0;
        //    }
        //}
        //This is not right,since its not comparing the left or right, but by comparing the leftmax and rightmax
        while(left<right){
            leftmax=Math.max(A[left],leftmax);
            rightmax=Math.max(A[right],rightmax);
            if(leftmax<rightmax){
                sum += leftmax- A[left++];
            }
            else{
                sum += rightmax-A[right--];
            }
        }
        return sum;
	}
}


//3D
class Cell{
  public int x,y, h;
  Cell(){}
  Cell(int xx,int yy, int hh){
    x = xx;
    y = yy;
    h = hh;
  }
}
class CellComparator implements Comparator<Cell> {
  @Override
  public int compare(Cell x, Cell y)
  {
    if(x.h > y.h)
      return 1;
    else if(x.h == y.h){
     return 0;
    }
    else {
      return -1;
    }
  }
}


public class Solution {
  int []dx = {1,-1,0,0};
  int []dy = {0,0,1,-1};
  public  int trapRainWater(int[][] heights) {
       // write your code here
      if(heights.length == 0)
        return 0;
      PriorityQueue<Cell> q =  new PriorityQueue<Cell>(new CellComparator());
      int n = heights.length;
      int m = heights[0].length;
      int [][]visit = new int[n][m];

      for(int i = 0; i < n; i++) {
        q.offer(new Cell(i,0,heights[i][0]));

        q.offer(new Cell(i,m-1,heights[i][m-1]));
        visit[i][0] = 1;
        visit[i][m-1] = 1;
      }
      for(int i = 0; i < m; i++) {
        q.offer(new Cell(0,i,heights[0][i]));

        q.offer(new Cell(n-1,i,heights[n-1][i]));
        visit[0][i] = 1;
        visit[n-1][i] = 1;

      }
      int ans = 0 ;
      while(!q.isEmpty()) {

        Cell now = q.poll();

        for(int i = 0; i < 4; i++) {

          int nx = now.x + dx[i];
          int ny = now.y + dy[i];
          if(0<=nx && nx < n && 0 <= ny && ny < m && visit[nx][ny] == 0) {
            visit[nx][ny] = 1;
            q.offer(new Cell(nx,ny,Math.max(now.h,heights[nx][ny])));
            ans = ans + Math.max(0,now.h - heights[nx][ny]);
          }

        }
      }
      return ans;
    }
}
