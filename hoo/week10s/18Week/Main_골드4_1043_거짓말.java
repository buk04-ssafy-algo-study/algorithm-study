package SSAFY.study.algo.week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_1043_거짓말 {

    static int N, M;
    static List<Integer> knowTruth; // 진실을 아는 사람들 리스트(bfs를 돌며 진실을 아는 사람에 의해 진실을 아는 사람들이 추가 될 것이므로 리스트로)
    static int[][] parties;    // 파티에 오는 사람들 번호를 저장할 배열

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        System.out.println(countNoTruth());
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int initKnowTruth = Integer.parseInt(st.nextToken());
        knowTruth = new ArrayList<>();
        for (int i = 0; i < initKnowTruth; i++) {
            knowTruth.add(Integer.parseInt(st.nextToken()));
        }

        parties = new int[M][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            parties[i] = new int[Integer.parseInt(st.nextToken())];
            for (int j = 0; j < parties[i].length; j++) {
                parties[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void bfs() {
        boolean[] isKnowingTruth = new boolean[N+1];  // 진실을 아는지 체크하는(방문) 배열, 1부터 시작하므로 크기 N+1로 초기화
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < knowTruth.size(); i++) {
            queue.offer(knowTruth.get(i));   // 최초에 진실을 아는 사람들 큐에 삽입
            isKnowingTruth[knowTruth.get(i)] = true;
        }

        while (!queue.isEmpty()) {
            int knowNum = queue.poll(); // 진실을 아는 사람
            for (int i = 0; i < M; i++) {   // 모든 파티를 순회
                int[] party = parties[i];
                boolean isInParty = false;  // 진실을 아는 사람이 파티에 포함돼있는지 확인하는 변수
                for (int j = 0; j < party.length; j++) {
                    if (party[j] == knowNum) {
                        isInParty = true;
                        break;
                    }
                }
                if (!isInParty) continue;   // 진실을 아는 사람이 포함 된 파티가 아니면 다음 파티로

                for (int j = 0; j < party.length; j++) {
                    if (!isKnowingTruth[party[j]]) {   // 진실을 모르는 사람이면 진실을 아는 사람으로 처리
                        knowTruth.add(party[j]);
                        isKnowingTruth[party[j]] = true;
                        queue.offer(party[j]);
                    }
                }
            }
        }
    }

    static int countNoTruth() {
        int count = 0;

        for (int i = 0; i < M; i++) {
            int[] party = parties[i];
            boolean isTruthInParty = false;
            for (int j = 0; j < party.length; j++) {
                if (knowTruth.contains(Integer.valueOf(party[j]))) {
                    isTruthInParty = true;
                    break;
                }
            }
            if (isTruthInParty) continue;
            count += 1;
        }

        return count;
    }

}
