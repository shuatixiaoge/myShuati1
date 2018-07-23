// Input:	csvformat
// John,Smith,john.smith@gmail.com,Los	Angeles,1
// Jane,Roberts,janer@msn.com,"San	Francisco,	CA",0
// "Alexandra	""Alex""",Menendez,alex.menendez@gmail.com,Miami,1	"""Alexandra	Alex"""
// Output:	escaped	string
// John|Smith|john.smith@gmail.com|Los	Angeles|1
// Jane|Roberts|janer@msn.com|San	Francisco,	CA|0
// Alexandra	"Alex"|Menendez|alex.menendez@gmail.com|Miami|1	"Alexandra	Alex"

// basically need to deal with empty string case. NO!!
// just deal with two quotes together, this is the escaping in CSV, and it that string has to be inQuote too.
public String parseCSV(String str) {
    List<String> res = new ArrayList<>();
    boolean inQuote = false;
    StringBuilder sb = new StringBuilder();
    // No need to split
    for (int i = 0; i < str.length(); i++) {
        if (inQuote) {
            if (str.charAt(i) == '\"') {
                if (i < str.length() - 1 && str.charAt(i + 1) == '\"') {
                    sb.append("\"");
                    // for too many duplicate " probably need a while loop to deal with it
                    i++;
                } else {
                    inQuote = false;
                }
            } else {
                // don't forget this line
                sb.append(str.charAt(i));
            }
        } else {
            if (str.charAt(i) == '\"') {
                inQuote = true;
            } else if (str.charAt(i) == ',') {
                res.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(str.charAt(i));
            }
        }
    }
    // don't forget to deal with the rest
    if (sb.length() > 0) {
        res.add(sb.toString());
    }
    return String.join("|", res);
}
