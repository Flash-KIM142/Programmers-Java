package leetcode;

public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        if(s.length()==0) {
            return true;
        }
        int idx = 0;
        for(int i=0; i<t.length(); i++) {
            char t_cur = t.charAt(i);
            if(s.charAt(idx)==t_cur) {
                idx++;
            }
            if(idx==s.length()) {
                return true;
            }
        }
        return false;
    }
}
