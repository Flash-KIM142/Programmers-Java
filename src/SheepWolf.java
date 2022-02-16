import java.util.ArrayList;

public class SheepWolf {
    static ArrayList<Integer>[] childs;
    static int max = 0;

    static int solution(int[] info, int[][] edges) {
        childs = new ArrayList[info.length];
        for(int[] edge: edges){
            int parent = edge[0];
            int child = edge[1];
            if(childs[parent]==null)    childs[parent] = new ArrayList<>();

            childs[parent].add(child);
        }

        ArrayList<Integer> available = new ArrayList<>();
        available.add(0);
        dfs(0,0,0,available,info);

        return max;
    }

    static void dfs(int sheepNum, int wolfNum, int cur, ArrayList<Integer> available, int[] info){
        sheepNum += info[cur] ^ 1;
        wolfNum += info[cur];
        max = Math.max(sheepNum,max);

        if(sheepNum<=wolfNum)   return;

        ArrayList<Integer> copyAvailable = new ArrayList<>();
        copyAvailable.addAll(available);

        if(childs[cur]!=null)   copyAvailable.addAll(childs[cur]);
        copyAvailable.remove(Integer.valueOf(cur));

        for(int next: copyAvailable)
            dfs(sheepNum,wolfNum,next, copyAvailable, info);
    }

    public static void main(String[] args) {
        int[] info = { 0,0,1,1,1,0,1,0,1,0,1,1 };
        int[][] edges = { {0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9} };
        System.out.println(solution(info, edges));
    }
}