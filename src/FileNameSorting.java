import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class FileNameSorting {
    static ArrayList<Info> list = new ArrayList<>();

    static String[] solution(String[] files) {
        for(String s: files)    list.add(new Info(s));
        list.sort(Comparator.comparing(Info::getLowerCaseHead).thenComparing(Info::getNumber));

        String[] answer = new String[files.length];
        int i = 0;
        for(Info info: list){
            answer[i] = info.fileName;
            i++;
        }
        return answer;
    }

    static class Info{
        String fileName;
        String lowerCaseHead;
        int number;

        Info(String s){
            StringBuilder sb = new StringBuilder();
            String lowerCaseHead = "";
            int number;

            int i;
            for(i=0; i<s.length(); i++){
                if(!Character.isDigit(s.charAt(i))) sb.append(Character.toLowerCase(s.charAt(i)));
                else    break;
            }
            lowerCaseHead = sb.toString();
            sb = new StringBuilder();

            for(i=i; i<s.length(); i++){
                if(Character.isDigit(s.charAt(i)))  sb.append(s.charAt(i));
                else    break;
            }
            number = Integer.parseInt(sb.toString());

            this.fileName = s;
            this.lowerCaseHead = lowerCaseHead;
            this.number = number;
        }

        String getLowerCaseHead()    { return lowerCaseHead; }
        Integer getNumber() { return number; }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] result = solution(files);
        for(String res: result) bfw.write(res + "\n");
        bfw.close();
    }
}