package SSAFY.study.algo.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_16234_인구이동 {

    // https://www.acmicpc.net/board/view/35819 여기 반례 참고해서 다시 풀기(무작정 bfs가 아닌, 연합끼리만 인구이동을 해야함)

    static int[] dirRow = {-1, 1, 0, 0};    // 상 하 좌 우
    static int[] dirCol = {0, 0, -1, 1};
    static int N, L, R; // N: 땅 크기, L, R: 인구 이동 조건(L <= 인구 차이 <= R)
    static int[][] land;
    static boolean[][] isSelected;
    static int unionNumber;
    static int[][] unionNumbers;    // 몇 번 연합인지 나누어 저장할 배열
    static boolean noMore;  // 더 이상 인구이동이 일어나지 않을지 체크

    public static void main(String[] args) throws IOException {
        init();
        int day = 0;
        while (true) {
            populationMovement();
            if (noMore) break;
            day += 1;
        }
        System.out.println(day);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        land = new int[N][N];
        isSelected = new boolean[N][N];
        unionNumber = 1;    // 같은 연합인지 체크할 번호
        unionNumbers = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < N)) return false;

        return true;
    }

    static void checkCountries(int nowR, int nowC) {  // 인구 이동이 일어날 국가인지 체크, 이후 boolean 배열의 bfs를 통해 인접 국가 판단, 인구 이동 실시
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {nowR, nowC});

        boolean isUnioned = false;
        while (!queue.isEmpty()) {
            int[] nowCountry = queue.poll();
            int population = land[nowCountry[0]][nowCountry[1]];
            for (int i = 0; i < 4; i++) {
                int nextR = nowCountry[0] + dirRow[i];
                int nextC = nowCountry[1] + dirCol[i];
                if (!isOuted(nextR, nextC) && !isSelected[nextR][nextC]) {
                    int nextPopulation = land[nextR][nextC];
                    int diffPopulation = Math.abs(nextPopulation - population);
                    if (L <= diffPopulation && diffPopulation <= R) {
                        isSelected[nextR][nextC] = true;
                        unionNumbers[nextR][nextC] = unionNumber;
                        queue.offer(new int[] {nextR, nextC});
                        isUnioned = true;
                    }
                }
            }
        }

        if (!isUnioned) return;
        isSelected[nowR][nowC] = true;
        unionNumbers[nowR][nowC] = unionNumber;
        unionNumber += 1;
    }

    static int calcAvgPopulation(List<int[]> union) {
        int avg = 0;
        for (int i = 0; i < union.size(); i++) {
            int[] country = union.get(i);
            avg += land[country[0]][country[1]];
        }

        return avg / union.size();
    }

    static void doMove(int row, int col) {  // 인구 이동하기
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {row, col});
        isSelected[row][col] = false;
        int checkUnionNumber = unionNumbers[row][col];

        List<int[]> union = new ArrayList<>();
        union.add(new int[] {row, col});
        while (!queue.isEmpty()) {
            int[] nowCountry = queue.poll();    // 현재 국가
            for (int i = 0; i < 4; i++) {
                int nextR = nowCountry[0] + dirRow[i];
                int nextC = nowCountry[1] + dirCol[i];
                if (!isOuted(nextR, nextC)) {
                    if (isSelected[nextR][nextC] && unionNumbers[nextR][nextC] == checkUnionNumber) {    // 같은 연합이면 인구이동해줌
                        queue.offer(new int[] {nextR, nextC});
                        isSelected[nextR][nextC] = false;
                        union.add(new int[] {nextR, nextC});
                    }
                }
            }
        }

        int avgPopulation = calcAvgPopulation(union);
        for (int i = 0; i < union.size(); i++) {
            int[] country = union.get(i);
            land[country[0]][country[1]] = avgPopulation;
        }
    }

    static void populationMovement() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
//                if (isSelected[i][j]) continue;
                checkCountries(i, j);
            }
        }

        boolean unionMaked = false; // 연합이 만들어졌는지 체크
        for (int i = 0; i < N; i++) {   // boolean 배열을 돌며 bfs, 연합 국가들 인구 이동 실시
            for (int j = 0; j < N; j++) {
                if (!isSelected[i][j]) continue;    // 연합이 안됐거나 이미 처리한 국가면 지나치기
                doMove(i, j);
                unionMaked = true;
            }
        }
        if (!unionMaked) noMore = true; // 연합이 안만들어졌다면 더 이상 진행하지 않음
        isSelected = new boolean[N][N];    // 인구 이동 완료 후 연합여부 초기화
        unionNumber = 1;
        unionNumbers = new int[N][N];
    }

}
