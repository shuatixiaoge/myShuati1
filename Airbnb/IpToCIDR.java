//
// "255.0.0.7"
// 10
// ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
// 7 is 0111 so step is 1 first, x = 1, then step = 1000 which is 8, range is 9 and mask is 29, x = 15, then x is 16,range is 1 step is 1 in the last loop
class Solution {
	public List<String> ipToCIDR(String ip, int range) {
		long x = 0;
		// cannot be "."
		String[] ips = ip.split("\\.");
		for (int i = 0; i < ips.length; ++i) {
			x = Integer.parseInt(ips[i]) + x * 256;
		}

		List<String> ans = new ArrayList<>();
		while (range > 0) {
			long step = x & -x;
			while (step > range) step /= 2;
			ans.add(longToIP(x, (int)step));
			x += step;
			range -= step;
		}

		return ans;
	}

	String longToIP(long x, int step) {
		int[] ans = new int[4];
		ans[0] = (int) (x & 255); x >>= 8;
		ans[1] = (int) (x & 255); x >>= 8;
		ans[2] = (int) (x & 255); x >>= 8;
		ans[3] = (int) x;
		int len = 33;
		while (step > 0) {
			len --;
			step /= 2;
		}
		return ans[3] + "." + ans[2] + "." + ans[1] + "." + ans[0] + "/" + len;
	}
}
