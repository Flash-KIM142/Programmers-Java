class BracketTransform{
    static String solution(String p){
        return recursion(p);
    }

    static String recursion(String p){
        if(p.equals(""))    return "";
        StringBuilder sb = new StringBuilder();
        String v = "";

        int balance = 0;
        if(p.charAt(0)=='(')    balance++;
        else    balance--;
        sb.append(p.charAt(0));

        for(int i=1; i<p.length(); i++){
            if(p.charAt(i)=='(')    balance++;
            else    balance--;
            sb.append(p.charAt(i));

            if(balance==0) {
                if(i+1<p.length())
                    v = p.substring(i + 1);
                break;
            }
        }

        if(sb.toString().charAt(0)=='(')    return sb +recursion(v);
        else    return reverse(sb.toString(), v);
    }

    static String reverse(String u, String v){
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(recursion(v));
        sb.append(')');

        u = u.substring(1, u.length()-1);
        for(int i=0; i<u.length(); i++){
            if(u.charAt(i)=='(')    sb.append(')');
            else    sb.append('(');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String p = ")(";
        System.out.println(solution(p));
    }
}