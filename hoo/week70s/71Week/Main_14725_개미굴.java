package SSAFY.study.algo.week70s.week71;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main_14725_개미굴 {

    static class Room {
        String name;
        Map<String, Room> children = new TreeMap<>();

        Room(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Room root = new Room(""); // 루트 노드 생성

        for (int i = 0; i < N; i++) {   // 입력 처리 및 트리 구성
            String[] path = br.readLine().split(" ");
            int depth = Integer.parseInt(path[0]); // 깊이 정보
            Room current = root;

            for (int j = 1; j <= depth; j++) {
                String room = path[j];
                current.children.putIfAbsent(room, new Room(room)); // 현재 노드의 자식 중 room이 없다면 새로 추가
                current = current.children.get(room);   // 현재 노드를 자식 노드로 이동
            }
        }

        for (String childName : root.children.keySet()) {   // 루트 노드의 자식들 순회하며 출력
            printTree(root.children.get(childName), "");
        }
    }

    // 트리 출력 함수
    static void printTree(Room room, String offset) {
        if (!room.name.isEmpty()) { // 루트 노드는 출력하지 않음
            System.out.println(offset + room.name);
        }
        for (Room child : room.children.values()) {
            printTree(child, offset + "--");
        }
    }

}
