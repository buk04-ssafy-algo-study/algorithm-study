package SSAFY.study.algo.week54;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17352_여러분의다리가되어드리겠습니다 {

    static class Bridge {
        int from;
        int to;

        public Bridge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static int N;
//    static List<List<Bridge>> nonBrokenBridgeList;
    static List<int[]> nonBrokenBridges;
    static int[] parentIslands; // 각 섬들의 부모(?) 섬 저장할 배열

    public static void main(String[] args) throws IOException {
        init();
        findDisconnectedIslands();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
//        nonBrokenBridgeList = new ArrayList<>();
//        for (int i = 0; i <= N; i++) nonBrokenBridgeList.add(new ArrayList<>());
//        StringTokenizer st;
//        int from, to;
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            from = Integer.parseInt(st.nextToken());
//            to = Integer.parseInt(st.nextToken());
//            nonBrokenBridgeList.get(from).add(new Bridge(from, to));
//            nonBrokenBridgeList.get(to).add(new Bridge(to, from));
//        }
        nonBrokenBridges = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N -2; i++) {
            st = new StringTokenizer(br.readLine());
            nonBrokenBridges.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        parentIslands = new int[N+1];
        for (int i = 1; i <= N; i++) parentIslands[i] = i;  // 우선 자신의 부모는 자기 자신으로
    }

    static void findDisconnectedIslands() {
        int[] nonBrokenBridge;
        int from, to;
        for (int i = 0; i < nonBrokenBridges.size(); i++) {   // 부서지지 않은 다리들에 대해 순회
            nonBrokenBridge = nonBrokenBridges.get(i);
            from = nonBrokenBridge[0];
            to = nonBrokenBridge[1];
            union(from, to);
            union(to, from);
        }

        int islandA = parentIslands[1], islandB = -1;   // 일단 1번 섬의 부모를 기준으로 조사
        for (int i = 2; i < parentIslands.length; i++) {    // 모든 섬들의 부모 섬을 조사함
            parentIslands[i] = find(i);
            if (parentIslands[i] != islandA) {  // 1번 섬의 부모 섬과 다른 섬이 나오면 그 섬이 연결 안된 섬임
                islandB = i;    // 갱신해주고 반복문 바로 종료
                break;
            }
        }
        System.out.println(islandA + " " + islandB);
    }

    static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x != y) {   // 각 섬의 부모 섬이 다르면
            if  (x < y) parentIslands[y] = x;   // 번호가 낮은 섬을 부모로 여김
            else parentIslands[x] = y;
        }
    }

    static int find(int a) {
        if (parentIslands[a] == a) return a;

        return parentIslands[a] = find(parentIslands[a]);
    }


}
