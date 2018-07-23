

public class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> list = new ArrayList<>();
        char[] num1 = {'0','1','8','6','9'};
        char[] num2 = {'0','1','8','9','6'};
        char[] number = new char[n];
        dfs(list, number, num1, num2, 0);
        return list;
    }
    private void dfs(List<String> list, char[] number, char[] num1, char[] num2, int index){
        int left = index;
        int right = number.length - index - 1;
        if(left > right){
            list.add(new String(number));
            return;
        }
        // We can fill in 0,1,8 only
        if(left == right){
            for(int i = 0; i < 3; i++){
                number[left] = num1[i];
                dfs(list, number, num1, num2, index + 1);
            }
        } else {
            for(int i = 0; i < num1.length; i++){
                if(index == 0 && i == 0) continue;// can't fill in 0 at the beginning, very corner case
                number[left] = num1[i];
                number[right] = num2[i];
                dfs(list, number, num1, num2, index + 1);
            }
        }
    }
}
