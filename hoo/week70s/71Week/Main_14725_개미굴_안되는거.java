package SSAFY.study.algo.week70s.week71;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14725_개미굴_안되는거 {

    static class Room {
        String roomName;    // 현재 방의 정보
        Room parent; // 부모 방의 정보
        int depth;

        public Room(String roomName, Room parent, int depth) {
            this.roomName = roomName;
            this.parent = parent;
            this.depth = depth;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // 같은 객체 참조인지 확인
            if (o == null || getClass() != o.getClass()) return false; // 타입 확인
            Room r = (Room) o;
            return depth == r.depth &&
                    Objects.equals(roomName, r.roomName) && // 문자열 비교는 equals 사용
                    Objects.equals(parent, r.parent); // 부모 비교도 equals 사용
        }

        @Override
        public int hashCode() {
            return Objects.hash(roomName, parent, depth); // 해시 코드 생성
        }

        @Override
        public String toString() { return "방 정보 : " + this.roomName + " 깊이 : " + this.depth; }
    }

    static int N;
    static List<List<Room>> roomList;
    static Map<Room, Integer> roomMap;  // 각 방에 번호를 부여해서 저장한 맵
    static Map<Integer, Room> roomNumberMap;    // 반대로 방의 번호를 키로 가지고 방 정보를 value로 가지는 맵
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        init();
        boolean[] isPrinted = new boolean[roomMap.size()];
        for (int i = 0; i < roomMap.size(); i++) if (roomNumberMap.get(i).depth == 1) printRooms(roomNumberMap.get(i), isPrinted, "");  // 깊이 1인 곳에서만 재귀 출발하도록 함
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        roomList = new ArrayList<>();
        roomMap = new HashMap<>();
        roomNumberMap = new HashMap<>();

        String[] inputLines = new String[N];
        String inputLine;
        for (int i = 0; i < N; i++) {   // 우선 방 정보들 다 입력받기
            inputLine = br.readLine();
            inputLine = inputLine.substring(2, inputLine.length());
            inputLines[i] = inputLine;
        }
        Arrays.sort(inputLines);    // 먼저 사전 순 정렬 수행

        int roomNumber = 0; // 입력받고 사전 순 정렬한 N개의 정보를 토대로 1번 방부터 카운트해서 각 방에 번호를 매겨줄 때 사용하는 변수
        for (int i = 0; i < N; i++) {
            Room parentRoomInfo = new Room("", null, 0);

            String[] rooms = inputLines[i].split(" ");
            String roomInfo;
            for (int j = 1; j <= rooms.length; j++) {
                roomInfo = rooms[j-1];
                Room r = new Room(roomInfo, parentRoomInfo, j);
                if (!roomMap.containsKey(r)) {  // 처음 입력받는 방이라면 맵에 저장하고 방 개수도 +1해줌
                    roomMap.put(r, roomNumber);
                    roomNumberMap.put(roomNumber, r);
                    roomList.add(new ArrayList<>());
                    roomNumber++;
                }
                parentRoomInfo = r; // 부모 방 현재 방으로 갱신
                if (j != 1) roomList.get(roomMap.get(r.parent)).add(r);   // 깊이 1인 방 제외하고 부모 방의 리스트에 저장
            }
        }
        sb = new StringBuilder();
    }

    static void printRooms(Room now, boolean[] isPrinted, String offset) {
        if (isPrinted[roomMap.get(now)]) return;    // 이미 출력했던 방은 건너 뜀

        sb.append(offset + "" + now.roomName).append("\n");
        isPrinted[roomMap.get(now)] = true;

        int nowRoomNumber = roomMap.get(now);
        List<Room> childRoomList = roomList.get(nowRoomNumber);
        for (Room c : childRoomList) printRooms(c, isPrinted, offset+"--");
    }

}
