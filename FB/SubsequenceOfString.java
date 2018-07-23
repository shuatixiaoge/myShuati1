public class Subsequence {

    public static void allSubSeq(char[] s)
    {
        int n = s.length;
        auxSubSeq(s, 0, n, "");
    }
    public static void auxSubSeq( char[] s, int d, int n, String pre)
    {
        if(d==n)
        {
           System.out.println(pre);
           return;
        }
       auxSubSeq(s, d+1, n, pre+s[d]);//pick
       auxSubSeq(s, d+1, n, pre);//not pick
    }
  	public static void main(String[] args)
	{
		char[] s = {'a','b','c'};
		allSubSeq(s);
	}
}
