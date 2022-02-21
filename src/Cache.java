import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Cache {
    static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> q = new LinkedList<>();

        if (cacheSize == 0) return 5 * cities.length;

        for (String city : cities) {
            boolean flag = false;
            Iterator<String> itr = q.iterator();
            while (itr.hasNext()) {
                String cmp = itr.next();
                if (city.equalsIgnoreCase(cmp)) { // 캐시에 있는 도시면
                    flag = true;
                    itr.remove(); // 삭제해주고
                    q.offer(cmp); // 우선 순위를 높이기 위해 맨 뒤로 보내주자
                    break;
                }
            }
            if (flag) { // 캐시에 들어있는 도시면
                answer += 1;
            }
            else { // 캐시에 없는 도시면
                if (q.size() == cacheSize) { // 캐시가 꽉 차있으면
                    q.poll(); // 가장 먼저 넣어준 도시 빼고
                    q.offer(city); // 새로 도시 넣어주자
                    answer += 5;
                } else if (q.size() < cacheSize) { // 캐시에 자리 남았으면
                    q.offer(city);
                    answer += 5;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        int res = solution(cacheSize, cities);
        System.out.println(res);
    }
}