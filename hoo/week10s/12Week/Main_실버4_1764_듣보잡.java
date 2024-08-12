package SSAFY.study.algo.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_실버4_1764_듣보잡 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
//        TreeSet<String> people = new TreeSet<>();
        HashMap<String, Integer> people = new HashMap<>();  // 이 문제에서 속도 -> treeSet보다 HashMap이 빨랐음
        PriorityQueue<String> answer = new PriorityQueue<>((o1, o2) -> o1.compareTo(o2));   // 사전순 정렬하기 위한 pq

        for (int i = 0; i < N; i++) {   // 듣도 못한 사람
//            people.add(br.readLine());
            people.put(br.readLine(), 1);
        }
        for (int i = 0; i < M; i++) {   // 보도 못한 사람
            String noSeen = br.readLine();
            if (people.containsKey(noSeen)) {
                answer.offer(noSeen);
            }
        }

        System.out.println(answer.size());
        StringBuilder sb = new StringBuilder();
        while (!answer.isEmpty()) {
            sb.append(answer.poll()).append("\n");
        }
        System.out.println(sb);
    }

}
