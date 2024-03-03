package SSAFY.study.algo.week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_실버2_6236_용돈관리 {

    static int N, M;
    static int[] plan;
    static int maxPlan; // 계획 중 가장 큰 금액
    static long sum;    // N이 10만까지, plan[i]가 1만까지의 범위를 가지므로 최대가 10억까지임 -> long으로 선언

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(binarySearch());
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        plan = new int[N];
        maxPlan = Integer.MIN_VALUE;
        sum = 0;
        for (int i = 0; i < N; i++) {
            plan[i] = Integer.parseInt(br.readLine());
            maxPlan = Math.max(maxPlan, plan[i]);
            sum += plan[i];
        }
    }

    static long binarySearch() {
        long answer = 0;
        long high = sum;
        long low = maxPlan;   // 적어도 하루를 보낼 계획 중 제일 많은 금액과는 같게 시작해야함

        while (low <= high) {
            int tempCnt = 1;    // 인출 횟수
            long mid = (high+low) / 2;   // 인출할 돈(K)
            long having = mid;   // 수중에 있는 돈

            for (int i = 0; i < N; i++) {
                if (plan[i] > having) { // 수중에 있는 돈보다 많은 돈을 쓸 계획 -> (통장에 넣고 다시)K원을 인출
                    tempCnt += 1;
                    having = mid;
                }
                having -= plan[i];  // 그 날 계획한 돈을 씀
            }
            if (tempCnt > M) low = mid + 1; // 인출 횟수가 M보다 많다면 인출할 돈을 늘리기 위해 경계의 아랫값을 높임.
            else {  // 인출횟수가 적거나(인출할 돈이 많은 경우) 인출 횟수를 충족했더라도 인출할 돈의 최솟값을 찾아야하므로 경계의 윗값을 낮추며 이분탐색 계속 진행, 그때의 인출 금액을 정답에 계속 저장
                answer = mid;
                high = mid - 1;
            }
        }

        return answer;
    }

}
