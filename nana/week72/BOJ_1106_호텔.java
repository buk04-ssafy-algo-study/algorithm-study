import java.util.*;
import java.io.*;

class Main {

    private static class City {
        int cost, customer;

        public City(int cost, int customer) {
            this.cost = cost;
            this.customer = customer;
        }
    }

    private static int C, N;
    private static int[] dp;
    private static List<City> promotions;
    private static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dp = new int[2001]; // C<=1000 이고, C명 이상의 인원을 홍보하니까 많이 잡음
        Arrays.fill(dp, INF);
        dp[0] = 0;

        promotions = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            promotions.add(new City(cost, customer));
        }

        for (City city : promotions) {
            for (int i = city.customer; i <= 2000; i++) {
                // dp[i] = i 명을 늘리는데 필요한 최소 홍보 비용
                dp[i] = Math.min(dp[i], dp[i - city.customer] + city.cost);
            }
        }

        int res = INF;
        for (int i = C; i <= 2000; i++) {   // C명 이상을 늘린다
            res = Math.min(res, dp[i]);
        }

        System.out.println(res);
    }
}
