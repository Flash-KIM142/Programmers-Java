public class BracketTransform2 {

    static String solution(String p) {
        if(isAlright(p))    return p;
        return recursion(p);
    }

    static String recursion(String s){
        if(s.length()==0)   return "";

        String u = "";
        String v = "";

        int sum = 0;
        for(int i=0; i<s.length(); i++){
            switch(s.charAt(i)){
                case '(':
                    sum += 1;
                    break;
                case ')':
                    sum -= 1;
                    break;
            }
            if(sum==0){
                if(i==s.length()-1){
                    u = s;
                }
                else{
                    u = s.substring(0, i+1);
                    v = s.substring(i+1);
                }
                break;
            }
        }

        if(isAlright(u)){
            return u + recursion(v);
        }
        else{
            String tmp = "(";
            tmp += recursion(v);
            tmp += ")";
            u = u.substring(1, u.length()-1);
            u = reverse(u);

            return tmp + u;
        }
    }

    static String reverse(String u){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<u.length(); i++){
            switch(u.charAt(i)){
                case '(':
                    sb.append(')');
                    break;

                case ')':
                    sb.append('(');
                    break;
            }
        }

        return sb.toString();
    }

    static Boolean isAlright(String s){
        int sum = 0;
        for(int i=0; i<s.length(); i++){
            char cur = s.charAt(i);
            switch(cur){
                case '(':
                    sum += 1;
                    break;

                case ')':
                    sum -= 1;
                    break;
            }

            if(sum<0)   return false;
        }

        return true;
    }
}
