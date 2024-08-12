package SSAFY.study.algo.week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_2251_물통 {

    static Map<Integer, Integer> limitMap;
    static boolean[][][] isVisited;
    static Set<Integer> canLiterC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        limitMap = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            limitMap.put(i, Integer.parseInt(st.nextToken()));
        }
        isVisited = new boolean[limitMap.get(0)+1][limitMap.get(1)+1][limitMap.get(2)+1];
        isVisited[0][0][limitMap.get(2)] = true; // 처음 상태: 0, 0, C만큼 물이 담겨 있음
        canLiterC = new HashSet<>();
        bfs();
        List<Integer> answer = new ArrayList<>(canLiterC);
        Collections.sort(answer);
        for (Integer i : answer) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0, limitMap.get(2)});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == 0) canLiterC.add(now[2]);
            for (int i = 0; i < 3; i++) {   // A, B, C 컵에 대해
                if (now[i] == 0) continue;  // 빈 컵이면 따르지 못함

                for (int j = 0; j < 3; j++) {
                    int[] copied = Arrays.copyOf(now, now.length);
                    if (copied[i] == 0 || j == i || copied[j] >= limitMap.get(j)) continue;   // 자기 자신한테는 따르지 않음, 이미 가득찬 물컵에는 따르지 않음

                    int poorLiter = copied[i];  // 처음 따를 물의 양: i번째 컵에 있는 양만큼
                    if (copied[j] + poorLiter > limitMap.get(j)) poorLiter = limitMap.get(j) - copied[j];   // 그만큼 따랐을 때 물을 채울 컵이 넘치면, 넘치기 전까지만 따르게 따를 양 수정
                    copied[j] = copied[j] + poorLiter;  // 물을 채울 컵에 물을 따름
                    copied[i] -= poorLiter; // 따른 만큼 원래 컵에서 빼줌

                    if (!isVisited[copied[0]][copied[1]][copied[2]]) {  // 따른 적 없는 조합의 물 양들이면 큐에 넣음
                        queue.offer(copied);
                        isVisited[copied[0]][copied[1]][copied[2]] = true;
                    }
                }
            }
        }
    }
}
