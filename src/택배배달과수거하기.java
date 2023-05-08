import java.util.*;

public class 택배배달과수거하기 {

    public static void main(String[] args) {
        int cap = 2;
        int n = 7;
        int[] deliveries = {1,0,2,0,1,0,2};
        int[] pickups = {0,2,0,1,0,2,0};

        System.out.println(solution(cap, n, deliveries, pickups));
    }

    static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delivery = 0, pickup = 0;

        for(int i=0; i<n; i++){
            delivery += deliveries[i];
            pickup += pickups[i];
        }

        int delTries = (delivery%cap==0) ? delivery/cap : delivery/cap+1;
        int pickTries = (pickup%cap==0) ? pickup/cap : pickup/cap+1;

        int journeys = Math.max(delTries, pickTries);
        int delEnd = n-1;
        int pickEnd = n-1;
        int end = n-1;

        int delRemain = delivery;
        int pickRemain = pickup;

        for(int i=0; i<journeys; i++){
            answer += 2L *(end+1);

            int delLoad = Math.min(delRemain, cap);
            delRemain -= delLoad;

            for(int j=delEnd; j>=0; j--){
                if(delLoad>=deliveries[j]){
                    delLoad -= deliveries[j];
                    deliveries[j] = 0;
                }
                else{
                    deliveries[j] -= delLoad;
                    delLoad = 0;
                }

                if(delLoad==0){
                    if(deliveries[j]==0 && j>0)    delEnd = j-1;
                    else    delEnd = j;
                    break;
                }
            }

            int pickLoad = Math.min(pickRemain, cap);
            pickRemain -= pickLoad;

            for(int j=pickEnd; j>=0; j--){
                if(pickLoad>=pickups[j]){
                    pickLoad -= pickups[j];
                    pickups[j] = 0;
                }
                else{
                    pickups[j] -= pickLoad;
                    pickLoad = 0;
                }

                if(pickLoad==0){
                    if(pickups[j]==0 && j>0)   pickEnd = j-1;
                    else    pickEnd = j;
                    break;
                }
            }

            if(delEnd!=0)
                while(deliveries[delEnd]==0)    delEnd--;

            if(pickEnd!=0)
                while(pickups[pickEnd]==0)  pickEnd--;

            end = Math.max(delEnd, pickEnd);
        }

        return answer;
    }
}
