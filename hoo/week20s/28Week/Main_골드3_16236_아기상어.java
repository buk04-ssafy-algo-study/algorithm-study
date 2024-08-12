package SSAFY.study.algo.week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드3_16236_아기상어 {

    static int[] dirRow = new int[]{-1, 1, 0, 0};  // 상 하 좌 우
    static int[] dirCol = new int[]{0, 0, -1, 1};

    static class Shark {
        int row;
        int col;
        int size;
        int eatCount;   // 자기 크기만큼 물고기를 먹어야 사이즈 1 증가
        int time;   // 먹이 먹은 시간

        public Shark(int row, int col, int size, int eatCount, int time) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.eatCount = eatCount;
            this.time = time;
        }

        @Override
        public String toString() {
            return "{" + this.row + ", " + this.col + "} " + this.size + " " + this.eatCount + " " + this.time;
        }
    }

    static class Fish {
        int row;
        int col;
        int size;
        int time;  // 상어가 이 물고기를 먹으러 가기 위해 움직인 시간

        public Fish(int row, int col, int size, int time) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.time = time;
        }

        @Override
        public String toString() {
            return this.row + " " + this.col + " " + this.size;
        }
    }

    static int N;
    static int[][] map; // 물고기 크기 정보 저장용
    static Shark shark;


    public static void main(String[] args) throws IOException {
        init();
        doEat();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int mapInfo = Integer.parseInt(st.nextToken());
                if (mapInfo == 9) {
                    shark = new Shark(i, j, 2, 0, 0);   // 최초 상어 크기는 2
                    map[i][j] = 0;  // 상어 자신은 맵에 표시하지 않음
                    continue;
                }
                map[i][j] = mapInfo;
            }
        }
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < N)) return false;
        return true;
    }

    static void eatFish(Fish feedFish) {
        shark.row = feedFish.row;
        shark.col = feedFish.col;
        shark.eatCount += 1;
        if (shark.eatCount == shark.size) {
            shark.size += 1;
            shark.eatCount = 0;
        }
        shark.time = feedFish.time;
    }

    static void doEat() {
        PriorityQueue<Fish> pq = new PriorityQueue<>(new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                // compare 함수 다시 정의
                if (o1.time == o2.time) {   // 걸리는 이동 시간이 같으면
                    if (o1.row == o2.row) return o1.col - o2.col;   // 높이 비교 후 같은 높이면 왼쪽에 있는 먹이 먹으러
                    else return o1.row - o2.row;    // 높이가 다르면 높이 있는 먹이를 먹으러
                }
                return o1.time - o2.time; // 상어가 이 물고기를 먹으러 이동한 시간을 기준으로 우선순위 따짐
            }
        });
        Queue<Shark> queue = new ArrayDeque<>();
        queue.offer(shark); // 먹이를 먹으러 출발하는 시점의 상어

        while (true) {  // 먹을 수 있는 먹이가 있는 동안 반복
            // 1. bfs이용, 먹을 수 있는 먹이가 나오면 pq에 추가
            boolean[][] isVisited = new boolean[N][N];  // 상어가 먹이를 먹은 뒤마다 방문 배열 초기화 해줘야 함
            isVisited[shark.row][shark.col] = true; // 지금 상어 위치 방문 처리

            while (!queue.isEmpty()) {
                Shark nowShark = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nextRow = nowShark.row + dirRow[d];
                    int nextCol = nowShark.col + dirCol[d];
                    if (isOuted(nextRow, nextCol) || isVisited[nextRow][nextCol] || map[nextRow][nextCol] > shark.size)
                        continue;  // 범위 밖이거나 방문한 곳, 상어보다 큰 물고기가 있는 곳이라면 continue
                    if (map[nextRow][nextCol] != 0 && map[nextRow][nextCol] < shark.size) pq.offer(new  Fish(nextRow, nextCol, map[nextRow][nextCol], nowShark.time+1));   // 물고기가 있는 곳이라면 그곳의 물고기를 pq에 삽입
                    queue.offer(new Shark(nextRow, nextCol, nowShark.size, nowShark.eatCount, nowShark.time+1));
                    isVisited[nextRow][nextCol] = true;
                }
            }
            // 2. 먹이 먹는 로직, pq 맨 앞의 먹이만 먹음
            if (pq.isEmpty()) break;   // 먹을 수 있는 먹이가 없으면 그대로 종료
            Fish feedFish = pq.poll();
            eatFish(feedFish);
            map[feedFish.row][feedFish.col] = 0;    // 먹은 물고기 0으로 수정
            while (!pq.isEmpty()) pq.poll();    // 맨 앞의 먹이만 먹고 그 자리에서 다시 탐색 시작해야 하므로 먹이 저장한 pq 다 비움
            queue.offer(shark); // 탐색 시작을 위해 먹이를 먹은 위치의 상어 다시 큐에 삽입
        }

        System.out.println(shark.time);
    }
}
