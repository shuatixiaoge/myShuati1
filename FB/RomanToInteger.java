public int romanToInt(String s) {
    if (s.length() == 0) return 0;
    Map<Character, Integer> m = new HashMap<Character, Integer>();
    m.put('I', 1);
    m.put('V', 5);
    m.put('X', 10);
    m.put('L', 50);
    m.put('C', 100);
    m.put('D', 500);
    m.put('M', 1000);
    char[] ch = s.toCharArray();
    int res = 0;
    for (int i = 0; i < ch.length; i++) {
        res += m.get(ch[i]);
        if (i != 0 && m.get(ch[i]) > m.get(ch[i - 1])) {
            res -= 2 * m.get(ch[i - 1]);
        }
    }
    return res;
}

public class Solution {
    public String intToRoman(int num) {
        int val[]={ 1000,900,500,400,100,90,50,40,10,9,5,4,1};//from high to low
        String s[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        String res="";
        int i=0;
        while(num!=0){
            if(num>=val[i]){
                res+=s[i];
                num-=val[i];
            }
            else i++;
        }
        return res;
    }
}
