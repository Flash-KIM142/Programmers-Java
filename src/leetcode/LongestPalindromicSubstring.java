package leetcode;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        int res_len = 0;
        String res = "";
        int len = s.length();
        for(int i=0; i<len; i++) {
            for(int j=i; j<len; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if(check_palindrome(s.substring(i,j+1))) {
                        if(j-i+1 > res_len) {
                            res_len = j-i+1;
                            res = s.substring(i,j+1);
                        }
                    }
                }
            }
        }
        return res;
    }

    public boolean check_palindrome(String sub) {
        int len = sub.length();
        if(len==1) {
            return true;
        }
        int mid = sub.length() / 2;
        for(int i=0; i<mid; i++) {
            if(sub.charAt(i) != sub.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
