public class 괄호변환 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        String p = ")(";
        String res = solution(p);
        System.out.println(res);
    }

    public static String solution(String p) {
        if(p.isEmpty()) {
            return "";
        }

        recursion(p);
        return sb.toString();
    }

    static void recursion(String sub) {
        if(sub.isEmpty()) {
            return;
        }
        int cnt = sub.charAt(0) == '(' ? 1 : -1;
        int idx = 1;
        while(true) {
            cnt += sub.charAt(idx) == '(' ? 1 : -1;
            if(cnt==0) {
                if(sub.charAt(idx)==')') {
                    sb.append(sub.substring(0, idx+1));
                    recursion(sub.substring(idx+1));
                }
                else {
                    sb.append('(');
                    recursion(sub.substring(idx+1));
                    sb.append(')');
                    String tmp = sub.substring(0, idx+1);
                    tmp = tmp.substring(1, tmp.length()-1);
                    if(tmp.length()>0) {
                        for(int i=0; i<tmp.length(); i++) {
                            sb.append(tmp.charAt(i)=='(' ? ')' : '(');
                        }
                    }
                }

                break;
            }
            idx++;
        }
    }
}
