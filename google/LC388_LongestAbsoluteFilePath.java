package google;

import java.util.ArrayDeque;
import java.util.Deque;

/*
   https://leetcode.com/problems/longest-absolute-file-path/description/
   The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

   dir
   subdir1
       file1.ext
       subsubdir1
   subdir2
       subsubdir2
           file2.ext

   The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

   We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

   Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
 */
public class LC388_LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {
        Deque<Integer> pathStack = new ArrayDeque<>();
        pathStack.push(0);
        String[] arr = input.split("\n");
        int maxLen = 0;

        for (String s : arr) {
            int numTabs = s.lastIndexOf("\t") + 1;
            int curLevel = numTabs + 1;
            // remove path in the current path that was previously pushed to the stasck
            while (curLevel < pathStack.size()) {
                pathStack.pop();
            }
            // parent dir's length + current path's length - tabs + '/'
            int curLen = pathStack.peek() + s.length() - numTabs + 1;
            if (s.contains(".")) {
                // remove the last '/' from length
                maxLen = Math.max(maxLen, curLen - 1);
            }
            pathStack.push(curLen);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LC388_LongestAbsoluteFilePath solution = new LC388_LongestAbsoluteFilePath();
        int result = solution.lengthLongestPath("dir\n\tsubdir1\n\t\\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext");
        System.out.println(result);
    }
}
