import java.util.*;
public class GetCombos {
    public List<List<Double>> getCombos(double[] prices, double target) {
        List<List<Double>> res = new ArrayList<>();
        int len = prices.length;
        if (len == 0 || target <= 0) return res;
        int[] pricesCent = new int[len];
        int pricesTarget = (int)Math.round(target * 100);
        // no need to sort, if it doesn't require something output order
        // Arrays.sort(prices);
        for (int i = 0; i < len; i++) {
            pricesCent[i] = (int)Math.round(prices[i] * 100);
        }
        helper(res, 0, pricesCent, prices, new ArrayList<Double>(), pricesTarget);
        return res;
    }

    private void helper(List<List<Double>> res, int idx, int[] pricesCent, double[] prices, List<Double> l, int pricesTarget) {
        if (pricesTarget == 0) {
            res.add(new ArrayList<>(l));
            return;
        }
        for (int i = idx; i < pricesCent.length; i++) {
            // 不用去重， 因为每个菜都不一样
            // if (i > idx && pricesCent[i] == pricesCent[i - 1]) continue;
            if (pricesCent[i] > pricesTarget) continue;
            l.add(prices[i]);
            // the  index here is not idx + 1!!!!!!!!!!!!!!!!!!!!!
            helper(res, i + 1, pricesCent, prices, l, pricesTarget - pricesCent[i]);
            l.remove(l.size() - 1);
        }
    }
    public static void main(String[] args) {
        GetCombos t = new GetCombos();
        // would fail on some case like 0.444, 0.443 -> 0.887, since it's using round
        double[] a = {1.5, 1.1213, 0.6213, 0.5};
        for (List<Double> l : t.getCombos(a, 2.6213)) {
            for (double d : l) {
                System.out.print(d);
                System.out.print(",");
            }
                System.out.println();
        }
    }
}
