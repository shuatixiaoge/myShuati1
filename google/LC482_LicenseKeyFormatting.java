public class LC482_LicenseKeyFormatting {
  /**
   * Input: S = "5F3Z-2e-9-w", K = 4

   Output: "5F3Z-2E9W"

   Explanation: The string S has been split into two parts, each part has 4 characters.
   Note that the two extra dashes are not needed and can be removed.
   * @param S
   * @param K
   * @return
   */
  public String licenseKeyFormatting(String S, int K) {
    String s = S.toUpperCase();
    StringBuilder sb = new StringBuilder();
    int k = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      char c = s.charAt(i);
      if (c == '-') continue;
      if (k < K) {
        sb.append(c);
        k++;
      } else {
        sb.append('-');
        sb.append(c);
        k = 1;
      }
    }
    return sb.reverse().toString();
  }

  public static void main(String[] args) {
    LC482_LicenseKeyFormatting solution = new LC482_LicenseKeyFormatting();
    System.out.println(solution.licenseKeyFormatting("5F3Z-2e-9-w", 4));
    System.out.println(solution.licenseKeyFormatting("2-5g-3-J", 2));
    System.out.println(solution.licenseKeyFormatting("2-4A0r7-4k", 3));
  }
}
