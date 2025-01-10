package SSAFY.study.algo.week60s.week68;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2146_다리만들기 {

    static int[] dirRow = new int[] {-1, 0, 1, 0};  // 상 우 하 좌
    static int[] dirCol = new int[] {0, 1, 0, -1};

    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        findShortestBridge();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void findShortestBridge() {  // 각 섬의 가장자리 땅에서 다른 섬의 가장자리 땅에 다리를 놓으며 최소 길이를 찾는 함수
        List<int[]> edgeList = makeEdgeList();
//        for (int i = 0; i < N; i++) System.out.println(Arrays.toString(map[i]));
//        System.out.println(edgeList.size());
//        for (int i = 0; i < edgeList.size(); i++) System.out.println(Arrays.toString(edgeList.get(i)));
        int shortestLength = Integer.MAX_VALUE; // 연결한 다리 길이 중 최소 길이 저장하는 변수
        for (int i = 0; i < edgeList.size(); i++) {
            shortestLength = Math.min(shortestLength, connectBridge(edgeList.get(i)));
//            System.out.println(Arrays.toString(edgeList.get(i)) + " " + shortestLength);
        }
        System.out.println(shortestLength);
    }

    static List<int[]> makeEdgeList() { // 각 섬의 가장자리 좌표를 저장하는 리스트를 만드는 함수, 동시에 각 섬에 번호를 부여함
        List<int[]> edgeList = new ArrayList<>();
        boolean[][] isChecked = new boolean[N][N];  // 이미 체크해본 곳인 지 여부 저장
        int islandNumber = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || isChecked[i][j]) continue;    // 바다이거나 이미 체크한 곳은 건너 뜀
                markIslandNumberAndFindEdge(i, j, islandNumber++, isChecked, edgeList);
            }
        }

        return edgeList;
    }

    static List<int[]> markIslandNumberAndFindEdge(int row, int col, int islandNumber, boolean[][] isChecked, List<int[]> edgeList) { // 너비우선탐색을 기반으로 각 섬에 번호를 표시하는 함수, 동시에 가장자리 좌표도 구해줌
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {row, col});
        map[row][col] = islandNumber;
        isChecked[row][col] = true;
        if (isEdge(row, col)) edgeList.add(new int[] {row, col, islandNumber}); // 가장자리라면 가장자리 리스트에 좌표와 섬 번호 추가

        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if (isOuted(nextRow, nextCol) || isChecked[nextRow][nextCol] || map[nextRow][nextCol] == 0) continue;

                q.offer(new int[] {nextRow, nextCol});
                map[nextRow][nextCol] = islandNumber;   // 지도에 섬의 번호를 표시
                isChecked[nextRow][nextCol] = true;
                if (isEdge(nextRow, nextCol)) edgeList.add(new int[] {nextRow, nextCol, islandNumber}); // 가장자리라면 가장자리 리스트에 좌표와 섬 번호 추가
            }
        }

        return edgeList;
    }

    static boolean isEdge(int row, int col) {
        int nextRow, nextCol;
        for (int d = 0; d < 4; d++) {
            nextRow = row + dirRow[d];
            nextCol = col + dirCol[d];
            if (isOuted(nextRow, nextCol)) continue;

            if (map[nextRow][nextCol] == 0) return true;    // 옆이 바다면 가장자리임을 반환
        }

        return false;
    }

    static int connectBridge(int[] startAxis) {   // 각 섬의 가장자리에서 다른 섬까지 너비 우선 탐색을 통해 다리를 놓는 함수
        Queue<int[]> q = new ArrayDeque<>();    // startAxis 0 : 행, 1 : 열, 3 : 길이
        boolean[][] isChecked = new boolean[N][N];
        q.offer(new int[] {startAxis[0], startAxis[1], 0});
        isChecked[startAxis[0]][startAxis[1]] = true;

        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (map[now[0]][now[1]] != 0 && map[now[0]][now[1]] != startAxis[2]) return now[2]-1;   // 다른 섬에 연결됐다면 길이 반환

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if (isOuted(nextRow, nextCol) || isChecked[nextRow][nextCol] || map[nextRow][nextCol] == startAxis[2]) continue;

                q.offer(new int[] {nextRow, nextCol, now[2]+1});
                isChecked[nextRow][nextCol] = true;
            }
        }

        return Integer.MAX_VALUE;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < N)) return false;
        return true;
    }

}
