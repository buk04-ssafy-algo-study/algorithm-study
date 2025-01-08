import java.util.*;
import java.io.*;

public class Main_9019_DSLR {

    private static int A, B;
    private static int d, s, l, r;
    private static StringBuilder sb;

    private static void bfs() {

        Queue<Integer> q = new ArrayDeque<>();
        String[] ans = new String[10000];   // 결과 저장
        boolean[] isVisited = new boolean[10000];

        q.add(A);
        ans[A] = "";    // 시작 숫자의 경우 빈 문자열
        isVisited[A] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            d = (now * 2) % 10000;
            s = now == 0 ? 9999 : now - 1;
            l = (now % 1000) * 10 + (now / 1000);
            r = (now % 10) * 1000 + (now / 10);

            if (!isVisited[d]) {    // d는 항상 10000보다 작다
                q.add(d);
                ans[d] = ans[now] + "D";
                isVisited[d] = true;
            }

            if (s < 10000 && !isVisited[s]) {   // 수가 10000보다 작고 방문하지 않았다면
                q.add(s);
                ans[s] = ans[now] + "S";    // 현재 값의 답 + 해당 레지스터명
                isVisited[s] = true;
            }

            if (l < 10000 && !isVisited[l]) {
                q.add(l);
                ans[l] = ans[now] + "L";
                isVisited[l] = true;
            }

            if (r < 10000 && !isVisited[r]) {
                q.add(r);
                ans[r] = ans[now] + "R";
                isVisited[r] = true;
            }

            // 단순히 시간을 줄이기 위한 조건
            if (d == B || s == B || l == B || r == B) break;
        }

        sb.append(ans[B]);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {

            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            bfs();

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
