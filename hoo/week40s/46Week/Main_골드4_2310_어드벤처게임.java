package SSAFY.study.algo.week46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_2310_어드벤처게임 {

    static class Player {   // 어드벤처 게임을 진행하는 플레이어
        int room;   // 현재 위치한 방의 번호
        int gold;   // 현재 소지한 돈의 양

        public Player(int room, int gold) {
            this.room = room;
            this.gold = gold;
        }

        @Override
        public String toString() { return this.room + " " + this.gold; }
    }

    static class Room {
        int roomNo; // 방의 번호
        String type;  // E: 빈 방, L: 레프리콘, T: 트롤
        int gold;    // L일 경우 채워주는 돈의 양, T일 경우 방에 입장할 때 필요한 돈의 양
        List<Integer> to;   // 갈 수 있는 방의 번호

        public Room(int roomNo, String type, int gold, List<Integer> to) {
            this.roomNo = roomNo;
            this.type = type;
            this.gold = gold;
            this.to = to;
        }

        @Override
        public String toString() { return this.roomNo + " " + this.type + " " + this.gold + " " + this.to; }
    }

    static int N;   // 방 개수
    static List<Room> roomList;
    static boolean isPossible;  // N번 방에 도착했는 지 여부 판단하는 플래그

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            init(br);
            bfs();
            if (isPossible) sb.append("Yes").append("\n");
            else sb.append("No").append("\n");
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st;
        roomList = new ArrayList<>();
        roomList.add(new Room(0, "N", 0, new ArrayList<>()));   // 0번 방은 없으니까 인덱스 헷갈리지 않게 미리 하나 추가해둠
        String type;
        int gold;
        List<Integer> to;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            type = st.nextToken();
            gold = Integer.parseInt(st.nextToken());
            to = new ArrayList<>();
            int toRoomNo;
            while (true) {
                toRoomNo = Integer.parseInt(st.nextToken());
                if (toRoomNo == 0) break;
                if (toRoomNo != i) to.add(toRoomNo);    // 자기 자신으로 향하는 간선은 의미가 없으므로 중복 방지하기 위해 미리 빼버리기
            }
            roomList.add(new Room(i, type, gold, to));
        }
        isPossible = false;
    }

    static void bfs() {
        boolean[][] isVisited = new boolean[N+1][N+1];  // 싸이클 발생 여부를 체크하기 위한 배열
        Queue<Player> q = new ArrayDeque<>();
        q.offer(new Player(1, 0));
        isVisited[1][1] = true;

        List<Integer> nextRooms;
        while (!q.isEmpty()) {
            Player nowPlayer = q.poll();
            if (nowPlayer.room == N) {  // 목표 방 도착 시 반복문 종료
                isPossible = true;
                break;
            }
            Room nowRoom = roomList.get(nowPlayer.room);
            nextRooms = nowRoom.to;
            Room nextRoom;
            int nextRoomNo;
            String nextRoomType;
            int nextRoomGold;
            for (int i = 0; i < nextRooms.size(); i++) {
                nextRoom = roomList.get(nextRooms.get(i));  // 다음 방
                nextRoomNo = nextRoom.roomNo;
                nextRoomType = nextRoom.type;
                nextRoomGold = nextRoom.gold;
                if (isVisited[nowRoom.roomNo][nextRoomNo]) continue;   // 현재 방에서 다음 방으로 간 기록이 있으면 건너뜀
                switch (nextRoomType) {
                    case "E":
                        q.offer(new Player(nextRoomNo, nowPlayer.gold));
                        isVisited[nowRoom.roomNo][nextRoomNo] = true;
                        break;
                    case "L":
                        int nextPlayerGold = nowPlayer.gold;
                        if (nextRoomGold >= nowPlayer.gold) nextPlayerGold = nextRoomGold;
                        q.offer(new Player(nextRoomNo, nextPlayerGold));
                        isVisited[nowRoom.roomNo][nextRoomNo] = true;
                        break;
                    case "T":
                        if (nextRoomGold > nowPlayer.gold) break;
                        q.offer(new Player(nextRoomNo, nowPlayer.gold - nextRoomGold));
                        isVisited[nowRoom.roomNo][nextRoomNo] = true;
                        break;
                }
            }
        }
    }

}
