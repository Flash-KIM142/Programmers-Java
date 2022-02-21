import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class MusicJustPlayed {
    static String solution(String m, String[] musicinfos) {
        m = transformMusic(m);
        ArrayList<Info> list = new ArrayList<>();

        int idx = 1;
        for(String info: musicinfos){
            String[] split = info.split(",");
            Time begin = new Time(split[0]);
            Time end = new Time(split[1]);
            String title = split[2];
            String basicSheet = split[3];
            list.add(new Info(idx++, title, begin.getDiff(end), basicSheet));
        }

        Iterator<Info> itr = list.iterator();
        while(itr.hasNext()){
            Info cur = itr.next();
            if(!cur.playedSheet.contains(m))    itr.remove();
        }
        if(list.size()==0)  return "(None)";
        list.sort(Comparator.comparing(Info::getPlayTime).reversed().thenComparing(Info::getIdx));

        String answer = list.get(0).title;
        return answer;
    }

    static class Time{
        int hrs;
        int mins;

        Time(String time){
            String[] split = time.split(":");
            this.hrs = Integer.parseInt(split[0]);
            this.mins = Integer.parseInt(split[1]);
        }

        Integer getDiff(Time cmp){
            return ((60*cmp.hrs + cmp.mins) - (60*this.hrs + this.mins));
        }
    }

    static class Info{
        int idx;
        String title;
        int playTime;
        String playedSheet;

        Info(int idx, String title, int playTime, String basicSheet){
            this.idx = idx;
            this.title = title;
            this.playTime = playTime;
            this.playedSheet = createPlayedSheet(playTime, basicSheet);
        }

        Integer getPlayTime(){ return this.playTime; }
        Integer getIdx(){ return this.idx; }
    }

    static String transformMusic(String org){
        org = org.replaceAll("C#", "c");
        org = org.replaceAll("D#", "d");
        org = org.replaceAll("E#", "e");
        org = org.replaceAll("F#", "f");
        org = org.replaceAll("G#", "g");
        org = org.replaceAll("A#", "a");
        return org;
    }

    static String createPlayedSheet(int playTime, String basicSheet){
        basicSheet = transformMusic(basicSheet);

        String res = "";
        int idx = 0;
        while(playTime--!=0){
            res += ""+basicSheet.charAt(idx);
            idx++;
            if(idx==basicSheet.length())   idx = 0;
        }

        return res;
    }

    public static void main(String[] args) {
        String m = "ABC";
        String[] ary = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String res = solution(m, ary);
        System.out.println(res);
    }
}