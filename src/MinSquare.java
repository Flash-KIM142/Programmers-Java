public class MinSquare {
    static int solution(int[][] sizes) {
        int[][] newSizes = new int[sizes.length][2];

        int idx =0;
        for(int[] size: sizes){
            int[] tmp = new int[2];
            tmp[0] = Math.max(size[0], size[1]);
            tmp[1] = Math.min(size[0], size[1]);

            newSizes[idx++] = tmp.clone();
        }

        int a = 0, b=0;
        for(int[] newSize: newSizes){
            a = Math.max(a, newSize[0]);
            b = Math.max(b, newSize[1]);
        }

        return a*b;
    }

    public static void main(String[] args) {
        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int res = solution(sizes);
        System.out.println(res);
    }
}