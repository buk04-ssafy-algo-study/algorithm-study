import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class PayAndDay implements Comparable<PayAndDay> {
    int p, d;

    public PayAndDay(int p, int d) {
        this.p = p;
        this.d = d;
    }

    @Override
    public int compareTo(PayAndDay o) { // 비용 내림 차순, 비용 같으면 날짜 내림 차순
        if (this.p == o.p)
            return o.d - this.d;
        return o.p - this.p;
    }
}

public class BOJ_2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        List<PayAndDay> payAndDayList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            payAndDayList.add(new PayAndDay(p, d));
        }
        Collections.sort(payAndDayList);

        int res = 0;
        boolean[] visited = new boolean[10001]; // 강연한 날짜인지 체크용
        for (int i = 0; i < n; i++) { // 비용 내림 차순으로 검사
            int d = payAndDayList.get(i).d;
            for (int j = d; j >= 1; j--) { // 금액이 높은 강연을 가능한 가장 늦은 날짜부터 배치하여 최대한 많은 강연 배치
                if (visited[j]) continue;
                visited[j] = true; // 강연 잡음
                res += payAndDayList.get(i).p;
                break;
            }
        }

        System.out.print(res);
    }
}