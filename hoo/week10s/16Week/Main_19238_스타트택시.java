package SSAFY.study.algo.week10s.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_19238_스타트택시 {

    static int[] dirRow = new int[] { -1, 1, 0, 0 };    // 상, 하, 좌, 우
    static int[] dirCol = new int[] { 0, 0, -1, 1 };

    static class Taxi {
        int row;
        int col;
        int move;

        public Taxi(int row, int col, int move) {
            this.row = row;
            this.col = col;
            this.move = move;
        }

        @Override
        public String toString() { return this.row + " " + this.col + " " + this.move; }
    }
    static Taxi mainTaxi;   // 메인으로 움직일 택시

    static class Passenger {
        int id;
        int startRow;
        int startCol;
        int endRow;
        int endCol;

        public Passenger(int id, int startRow, int startCol, int endRow, int endCol) {
            this.id = id;
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
        }
    }
    static Passenger takenPassenger;    // 현재 택시에 태운 승객
    static Map<Integer, Passenger> passengerMap; // 승객을 id로 관리하기 위한 맵

    static int N, M, fuel;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        Taxi initTaxi = init();
        doTaxi(initTaxi);
    }

    static Taxi init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int startRow = Integer.parseInt(st.nextToken()) - 1;
        int startCol = Integer.parseInt(st.nextToken()) - 1;
        Taxi initTaxi = new Taxi(startRow, startCol, 0);

        takenPassenger = null;
        passengerMap = new HashMap<>();
        int passengerStartRow, passengerStartCol, passengerEndRow, passengerEndCol;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            passengerStartRow = Integer.parseInt(st.nextToken()) - 1;
            passengerStartCol = Integer.parseInt(st.nextToken()) - 1;
            passengerEndRow = Integer.parseInt(st.nextToken()) - 1;
            passengerEndCol = Integer.parseInt(st.nextToken()) - 1;
            Passenger passenger = new Passenger(i + 2, passengerStartRow, passengerStartCol, passengerEndRow, passengerEndCol);
            passengerMap.put(i + 2, passenger);
            map[passengerStartRow][passengerStartCol] = i + 2; // 승객의 id를 맵에 저장
        }

        return initTaxi;
    }

    static void doTaxi(Taxi initTaxi) {
        int tookPassengerCount = 0;
        mainTaxi = initTaxi;
        while (tookPassengerCount < M) {
            int toStartFuel = bfs();
            fuel -= toStartFuel;

            if (fuel < 0) break;

            int toDestFuel = bfs();
            fuel -= toDestFuel;

            if (fuel < 0) break;

            fuel += toDestFuel * 2;
            tookPassengerCount++;
        }
        if (tookPassengerCount != M) System.out.println(-1);
        else System.out.println(fuel);
    }

    static int bfs() {
        Queue<Taxi> q = new LinkedList<>();
        Queue<Passenger> passengerQueue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int prevMove = mainTaxi.move;
        visited[mainTaxi.row][mainTaxi.col] = true;
        q.add(mainTaxi);

        while (!q.isEmpty()) {
            Taxi now = q.poll();

            if (fuel - now.move < 0) {
                return Integer.MAX_VALUE;
            }

            if (prevMove != now.move && !passengerQueue.isEmpty()) {
                break;
            }

            prevMove = now.move;

            if (takenPassenger == null) {
                int id = map[now.row][now.col];
                if (id > 1) {
                    Passenger p = passengerMap.get(id);
                    passengerQueue.add(p);
                }
            } else {
                if (now.row == takenPassenger.endRow && now.col == takenPassenger.endCol) {
                    passengerMap.remove(takenPassenger.id);
                    takenPassenger = null;
                    mainTaxi = new Taxi(now.row, now.col, 0);
                    return prevMove;
                }
            }

            for (int i = 0 ; i < 4; i++) {
                int nx = now.row + dx[i];
                int ny = now.col + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (map[nx][ny] != 1 && !visited[nx][ny]) {
                        q.add(new Taxi(nx, ny, now.move + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        if (passengerQueue.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        takenPassenger = findNearest(passengerQueue);
        mainTaxi = new Taxi(takenPassenger.startRow, takenPassenger.startCol, 0);
        map[takenPassenger.startRow][takenPassenger.startCol] = 0;
        return prevMove;
    }

    static Passenger findNearest(Queue<Passenger> q) {
        Passenger target = q.poll();

        while (!q.isEmpty()) {
            Passenger compare = q.poll();
            if (compare.startRow < target.startRow) {
                target = compare;
            } else if (compare.startRow == target.startRow && compare.startCol < target.startCol) {
                target = compare;
            }
        }

        return target;
    }

}
