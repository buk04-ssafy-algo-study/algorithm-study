import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1263 {
    static class Work implements Comparable<Work> {
        int T;
        int S;

        public Work(int t, int s) {
            T = t;
            S = s;
        }

        @Override
        public int compareTo(Work o) {
            return o.S - this.S;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Work[] works = new Work[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            works[i] = new Work(T,S);
        }

        Arrays.sort(works); // 끝나는 시간이 늦은 순으로 정렬
        /*
        5 20 : 20-5=15
        1 16 : 15-1=14
        8 14 : 14-8=6
        3 5  : 5-3=2
        */

        int time = works[0].S; // 최대 시작 시간 : 가장 늦게 끝낼 수 있는 시각으로 초기화

        for (int i = 0; i < N; ++i) {
            time = Math.min(works[i].S, time) - works[i].T; // min(끝나야 되는 시각,최대 시작 시간) - 소요 시간
            if (time < 0) {
                time = -1;
                break;
            }
        }
        System.out.println(time);
    }
}