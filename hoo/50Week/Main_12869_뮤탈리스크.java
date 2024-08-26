package SSAFY.study.algo.week50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12869_뮤탈리스크 {

    static class SCVs { // bfs를 통해 경우의 수를 탐색하기 위한 scv들의 체력 객체
        int[] scvHPs;
        int count;

        public SCVs(int[] scvHPs, int count) {
            this.scvHPs = scvHPs;
            this.count = count;
        }

        @Override
        public String toString() { return Arrays.toString(this.scvHPs); }
    }
    static int N;

    public static void main(String[] args) throws IOException {
        SCVs initSCVs = init();
        System.out.println(bfs(initSCVs));
    }

    static SCVs init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] scvHPs = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int scvIndex = 0;   // 로직 처리 상 몇 대의 scv가 입력으로 주어지든 3대로 고정되게 처리하기 위함
        while (st.hasMoreTokens()) scvHPs[scvIndex++] = Integer.parseInt(st.nextToken());   // 입력받지 않은 scv는 체력이 0인 scv로 처리

        return new SCVs(scvHPs, 0);
    }

    static int bfs(SCVs initSCVs) {
        int[][] attacks = new int[][] {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}}; // bfs 탐색을 위한 공격의 6가지 경우
        int[][][] isChecked = new int[initSCVs.scvHPs[0]+1][initSCVs.scvHPs[1]+1][initSCVs.scvHPs[2]+1];    // scv들의 체력을 방문 배열로 사용
        Queue<SCVs> q = new ArrayDeque<>();
        q.offer(initSCVs);

        SCVs now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (now.scvHPs[0] <= 0 && now.scvHPs[1] <= 0 && now.scvHPs[2] <= 0) return now.count;   // 기저, 모든 scv를 파괴한 경우. 이 중에서도 제일 먼저 도달하는 건 최소 횟수로 간주.

            int[] attack;
            int[] nextHPs;
            for (int c = 0; c < 6; c++) {
                nextHPs = now.scvHPs.clone();
                attack = attacks[c];
                for (int i = 0; i < 3; i++) {   // 공격 패턴에 따라 공격 처리
                    nextHPs[attack[i]] = (nextHPs[attack[i]] - (9 / (int) Math.pow(3, i)) < 0)? 0:nextHPs[attack[i]] - (9 / (int) Math.pow(3, i));
                }
                if (isChecked[nextHPs[0]][nextHPs[1]][nextHPs[2]] != 0) continue;   // 이미 만들어봤던 체력들이면 건너뜀
                q.offer(new SCVs(nextHPs, now.count+1));
                isChecked[nextHPs[0]][nextHPs[1]][nextHPs[2]]++;
            }
        }

        return 0;
    }

}
