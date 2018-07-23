public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res=new ArrayList<List<String>>();
        List<Integer> tmp=new ArrayList<Integer>();
        search(res,tmp,n);
        return res;
    }
    public List<String> print(ArrayList<Integer> cols){
        List<String> res=new ArrayList<String>();
        for(int i=0;i<cols.size();i++){
            String str="";
            for(int j=0;j<cols.size();j++){
                if(cols.get(i)==j) str+='Q';
                else str+='.';
            }
            res.add(str);
        }
        return res;
    }
    public void search(List<List<String>> res, List<Integer> tmp, int n){
        if(tmp.size()==n){

            res.add(print(new ArrayList<Integer>(tmp)));
        }
        for(int i=0;i<n;i++){
            if(!isValid(tmp,i)) continue;
            tmp.add(i);
            search(res,tmp,n);
            tmp.remove(tmp.size()-1);
        }
    }
    public boolean isValid(List<Integer> cols,int col){
        for(int i=0;i<cols.size();i++){
            int tmp=cols.get(i);
            if(tmp==col) return false;
            else if(col-tmp==cols.size()-i) return false;
            else if(cols.size()-i==tmp-col) return false;
        }
        return true;
    }

}
