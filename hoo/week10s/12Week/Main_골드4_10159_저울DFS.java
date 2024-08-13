package SSAFY.study.algo.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드4_10159_저울DFS {

    static int N, M;
    static List<Integer>[] compares;    // 비교 정보 저장
    static int[] comparedCount; // 비교한 물건 개수

    public static void main(String[] args) throws IOException {
        init();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int depth = dfs(i, new boolean[N+1], 0);    // 무거운 물건 -> 가벼운 물건 몇 개까지 비교 가능한지 개수
            comparedCount[i] += depth;  // dfs 내부적으로는 무거운 물건과 비교 가능한 가벼운 물건에 cnt +1 해줬으므로, 여기서는 무거운 물건이 가벼운 물건 몇 개와 비교 가능한지 개수 더해줌
        }

        for (int i = 1; i <= N; i++) {
            sb.append((N-1) - comparedCount[i]).append("\n");   // 자신을 제외한 N-1개의 물건과 비교가 불가능한 개수이므로, (N-1) - 비교 가능한 개수가 답임
        }
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        compares = new ArrayList[N+1];
        comparedCount = new int[N+1];
        for (int i = 0; i <= N; i++) {   // 물건과 비교했던 물건 저장할 list(Integer 형) 배열
            compares[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) {   //
            st = new StringTokenizer(br.readLine());
            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());
            compares[heavy].add(light); // 무거운 물건과 비교한 가벼운 물건 번호를, 무거운 물건의 인덱스에 추가
        }
    }

    static int dfs(int stuffNo, boolean visited[], int depth) { // stuffNo: 물건 번호, depth: 그 물건과 비교한 물건 개수
        visited[stuffNo] = true;
        List<Integer> comparedList = compares[stuffNo];
        for (int i = 0; i < comparedList.size(); i++) {
            int comparedStuffNo = comparedList.get(i);
            if (!visited[comparedStuffNo]) {
                visited[comparedStuffNo] = true;
                comparedCount[comparedStuffNo] += 1;    // 무거운 물건과 비교한 가벼운 물건은 비교 횟수 +1 해줌
                depth = dfs(comparedStuffNo, visited, depth+1);
            }
        }

        return depth;
    }

}
