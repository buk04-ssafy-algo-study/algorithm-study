package SSAFY.study.algo.week34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_골드4_20056_마법사상어와파이어볼 {

    static int[] dirRow = new int[] {-1, -1, 0, 1, 1, 1, 0, -1}; // 상 우상 우 우하 / 하 좌하 좌 좌상
    static int[] dirCol = new int[] {0, 1, 1, 1, 0, -1, -1, -1};

    static class FireBall {
        int row;
        int col;
        int mass;
        int speed;
        int dir;

        public FireBall(int row, int col, int mass, int speed, int dir) {
            this.row = row;
            this.col = col;
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
        }

        @Override
        public String toString() {return this.row + " " + this.col + " " + this.mass + " " + this.speed + " " + this.dir;}
    }

    static int N, M, K;
    static List<FireBall>[][] map;  // 파이어볼의 위치?를 나타낸 리스트형 배열
    static List<FireBall> fireBallList;  // 파이어볼들 저장하는 리스트

    public static void main(String[] args) throws IOException {
        int answer = 0;
        init();
        for (int i = 0; i < K; i++) {
            doFireBall();
        }
        for (int i = 0; i < fireBallList.size(); i++) answer += fireBallList.get(i).mass;
        System.out.println(answer);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) map[i][j] = new ArrayList<>();
        }
        fireBallList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1; // 이 문제는 인덱스 초과에 대해서 회전체처럼 구현해야 하므로 리스트 사이즈 조절이 아니라 입력 받을 때 인덱스를 맞춰줘야 함.
            int col = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            fireBallList.add(new FireBall(row, col, mass, speed, dir));
        }
    }

    static void doFireBall() {
        for (FireBall fireBall : fireBallList) {
            fireBall.row = (fireBall.row + N + dirRow[fireBall.dir] * (fireBall.speed%N)) % N;    // 1과 N이 이어져 있다고 했으므로 이렇게 계산, speed를 N으로 모듈러 연산해준 건 여러 바퀴 도는 걸 간단히 계산하기 위함
            fireBall.col = (fireBall.col + N + dirCol[fireBall.dir] * (fireBall.speed%N)) % N;
            map[fireBall.row][fireBall.col].add(fireBall);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int fireBallCount = map[i][j].size();
                if (map[i][j].size() < 2) { // 2개 미만일 때 처리 제거 처리 해줘야 함
                    map[i][j].clear();
                    continue;
                }
                int sumMass= 0, sumSpeed = 0;
                boolean allEven= true, allOdd = true;    // 둘 중 하나라도 true로 남아있으면 0, 2, 4, 6으로 방향 퍼짐
                for (FireBall fireBall : map[i][j]) {
                    sumMass += fireBall.mass;
                    sumSpeed += fireBall.speed;
                    if (fireBall.dir % 2 == 0) allOdd = false;
                    else allEven = false;
                    fireBallList.remove(fireBall);  // 분열 처리 될 파이어볼은 파이어볼 전체 리스트에서 제거
                }
                map[i][j].clear();
                sumMass /= 5;
                if (sumMass == 0) continue;
                sumSpeed /= fireBallCount;
                if (allEven || allOdd) {
                    for (int d = 0; d < 8; d += 2) fireBallList.add(new FireBall(i, j, sumMass, sumSpeed, d));
                } else {
                    for (int d = 1; d < 8; d += 2) fireBallList.add(new FireBall(i, j, sumMass, sumSpeed, d));
                }
            }
        }
    }

}
