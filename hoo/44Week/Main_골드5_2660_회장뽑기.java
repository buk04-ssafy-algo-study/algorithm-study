package SSAFY.study.algo.week44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드5_2660_회장뽑기 {

    static int N;
    static int[][] dist;    // 플로이드 워셜 알고리즘에 사용 될 배열

    public static void main(String[] args) throws IOException {
        init();
        findLeader();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dist = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if (i == j) continue;   // 자기 자신으로의 거리는 0으로 두고 나머지만 초기화
                dist[i][j] = 10_000_000;    // 플로이드 워셜 알고리즘 적용을 위해 일단 각 노드에서의 최소 거리는 1000만으로 초기화(회원 수가 어차피 50이긴 한데 안전하게 1000만으로)
            }
        }

        StringTokenizer st;
        int from, to;
        while (true) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            if (from == -1 && to == -1) break;  // 입력 종료 조건
            dist[from][to] = 1;
            dist[to][from] = 1;
        }
    }

    static void findLeader() {
        for (int k = 1; k < N+1; k++) { // 경유지
            for (int i = 1; i < N+1; i++) { // 출발지
                for (int j = 1; j < N+1; j++) { // 도착지
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        List<Integer> cadidateList = new ArrayList<>(); // 회장 후보를 담을 리스트
        int minScore = Integer.MAX_VALUE;   // 회장 후보의 점수
        int score;
        for (int i = 1; i < N+1; i++) {
            score = Arrays.stream(dist[i]).max().getAsInt();    // 한 사람의 점수는 각 사람들과의 거리 중 최대값
            if (score < minScore) { // 점수 최솟값이 갱신되면 저장돼있던 최소 점수 갱신, 회장 후보 리스트도 갱신
                minScore = score;
                cadidateList = new ArrayList<>();
                cadidateList.add(i);
            } else if (score == minScore) cadidateList.add(i);  // 최소 점수와 같으면 후보 리스트에 추가만
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minScore).append(" ").append(cadidateList.size()).append("\n");
        for (int candidate : cadidateList) sb.append(candidate).append(" ");
        System.out.println(sb);
    }

}
