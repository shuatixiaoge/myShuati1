import java.util.*;

public class GuessNumber {
    /*
        Guess Number
        AirBnB Interview Question
     */
        String target;

        private int guessServer(String guess) {
            int c = 0;
            for (int i = 0; i < target.length(); i++) {
                if (target.charAt(i) == guess.charAt(i)) c++;
            }
            return c;
        }

        private String genNumber(int c, int idx) {
            String t = "8888";
            return t.substring(0, idx) + c + t.substring(idx + 1, 4);
        }

        //maximum guess is 20
        public String guess(String t) {
            target = t;
            int i = 0;
            String res = "";
            while (i < 4) {
                for (int cand = 0; cand < 7; cand++) {
                    // last time don't need to guess
                    int guessRes = 0;
                    if (cand != 6){
                        String guessCand = genNumber(cand, i);
                        guessRes = guessServer(guessCand);
                    }
                    if (cand == 6 || guessRes > 0) {
                        res += cand;
                        i++;
                        break;
                    }
                }
            }

            return res;
        }

    public static void main(String[] args) {
        GuessNumber t = new GuessNumber();
        String guess = "6523";
        System.out.println(t.guess(guess));
    }
}
