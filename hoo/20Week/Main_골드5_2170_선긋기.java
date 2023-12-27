package SSAFY.study.algo.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_골드5_2170_선긋기 {

    static int N;
    static int[][] lines;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(drawLine());
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lines = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(lines, new Comparator<int[]>() {    // 선의 시작점 기준 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];   // 시작점이 같은 선의 경우 끝점을 기준으로 정렬
                return o1[0] - o2[0];   // 그 외에는 시작점을 기준으로 정렬
            }
        });
    }

    static int drawLine() {
        int lineLength = 0;
        int start = -1_000_000_000;
        int end = -1_000_000_000;
        for (int i = 0; i < N; i++) {
            if (lines[i][0] > end) {    // 저장돼있는 선의 마지막 좌표보다 큰 값(이어진 선이 아닌 경우) -> 이전 저장돼있던 end - start만큼 선 길이 더해주고 start, end 값 새로 갱신
                lineLength += end - start;
                start = lines[i][0];
                end = lines[i][1];
            } else {    // lines[i][0] <= end, 선의 시작이 저장돼있는 선의 끝보다 낮은 값 -> 끝 값 업데이트
                end = Math.max(end, lines[i][1]);
            }
        }
        lineLength += end - start;  // 마지막 갱신돼있던 선은 길이에 추가하지 않았으므로 더해줌

        return lineLength;
    }

}
