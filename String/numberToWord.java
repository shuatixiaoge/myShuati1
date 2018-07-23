import java.util.HashMap;

public class numberToWord {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while(num != 0) {
            if (num % 1000 != 0 ) {
                sb.insert(0, unitMap(i));
            }
            sb.insert(0, translate(num % 1000));
            sb.insert(0, ' ');
            num /= 1000;
            i++;
        }
        return sb.toString().trim().replaceAll("\\s+", " ");
    }

    private String translate(int num) {
        StringBuilder sb = new StringBuilder();
        if (num / 100 > 0) {
            sb.append(firstMap(num/100));
            sb.append(" Hundred ");
            num = num%100;
        }
        if (num / 10 > 0) {
            if (num / 10 == 1) {
               sb.append(tenthMap(num % 10));
               sb.append(' ');
            }
            else {
               sb.append(secondMap(num / 10));
               sb.append(' ');
               sb.append(firstMap(num % 10));
               sb.append(' ');
            }
        }
        else {
            sb.append(firstMap(num % 10));
            sb.append(' ');
        }
        return sb.toString();
    }

    private String unitMap(int i) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(2, "Thousand");
        map.put(1, ""); // not to show null
        map.put(3, "Million");
        map.put(4, "Billion");
        return map.get(i);
    }

    private String secondMap(int i) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "");// not to show null
        map.put(2, "Twenty");
        map.put(3, "Thirty");
        map.put(4, "Forty");
        map.put(5, "Fifty");
        map.put(6, "Sixty");
        map.put(7, "Seventy");
        map.put(8, "Eighty");
        map.put(9, "Ninety");
        return map.get(i);
    }

    private String tenthMap(int i) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "Ten");
        map.put(1, "Eleven");
        map.put(2, "Twelve");
        map.put(3, "Thirteen");
        map.put(4, "Fourteen");
        map.put(5, "Fifteen");
        map.put(6, "Sixteen");
        map.put(7, "Seventeen");
        map.put(8, "Eighteen");
        map.put(9, "Nineteen");
        return map.get(i);
    }

    private String firstMap(int i) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "");// not to show null
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        return map.get(i);
    }

    public static void main(String[] args) {
        numberToWord test = new numberToWord();
        System.out.println(test.numberToWords(1000));
    }
}
