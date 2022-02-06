import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SecretMap {
    static ArrayList<String> binArr1 = new ArrayList<>();
    static ArrayList<String> binArr2 = new ArrayList<>();
    static ArrayList<String> map = new ArrayList<>();

    static String[] solution(int n, int[] arr1, int[] arr2) {
        createBinString(n, 1, arr1);
        createBinString(n, 2, arr2);
        getMap(n);
        String[] answer = map.toArray(new String[n]);

        return answer;
    }

    static void createBinString(int n, int idx, int[] arr){
        ArrayList<String> binArr = new ArrayList<>();

        for(int a: arr) {
            String tmp = Integer.toBinaryString(a);
            while(tmp.length()<n)
                tmp = "0" + tmp;
            binArr.add(tmp);
        }

        if(idx==1){
            binArr1 = new ArrayList<>(binArr);
            Collections.copy(binArr1, binArr);
        }
        else{
            binArr2 = new ArrayList<>(binArr);
            Collections.copy(binArr2, binArr);
        }
    }

    static void getMap(int n){
        for(int i=0; i<n; i++){
            String s1 = binArr1.get(i);
            String s2 = binArr2.get(i);
            String res = "";

            for(int j=0; j<n; j++){
                char c1 = s1.charAt(j);
                char c2 = s2.charAt(j);
                if(c1=='0' && c2=='0') // 둘 다 공백이면
                    res += " ";
                else                   // 둘 중 하나라도 벽이면
                    res += "#";
            }
            map.add(res);
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        for(String s: solution(n,arr1,arr2))    bfw.write(s + "\n");
        bfw.close();
    }
}