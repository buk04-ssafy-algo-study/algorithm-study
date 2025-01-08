import java.util.*;
import java.io.*;

class Main {

    private static int N, K;
    private static String num, answer;

    private static int bfs() {

        Queue<Str> q = new ArrayDeque<>();
        Set<String> set = new HashSet<>();  // 중복 검사

        q.add(new Str(num, 0));

        while (!q.isEmpty()) {

            Str now = q.poll();

            if (now.str.equals(answer)) return now.count;

            if (set.contains(now.str)) continue;
            set.add(now.str);   // 중복이 아니면 넣음

            for (int i = 0; i <= N - K; i++) {
                // 제일 처음부터, K개를 뒤집을 수 있는 마지막까지만 뒤집기 가능
                // 매개변수: 현재 문자열, 뒤집기 시작하는 곳, 뒤집히지 않는 곳
                q.add(new Str(reverse(now.str, i, i + K), now.count + 1));
            }
        }

        return -1;
    }

    private static String reverse(String str, int i, int j) {

        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, i));

        String reverse = str.substring(i, j);  // 뒤집히는 부분
        for (int k = K - 1; k >= 0; k--) {
            sb.append(reverse.charAt(k));
        }

        sb.append(str.substring(j, N));

        return sb.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        num = "";
        for (int i = 0; i < N; i++) {
            num += sc.next();
        }

        answer = "";
        for (int i = 1; i <= N; i++) {
            answer += i;
        }

        System.out.println(bfs());
    }

    public static class Str {
        String str;
        int count;

        public Str(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
}
