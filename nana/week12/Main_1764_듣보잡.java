package study.week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1764_듣보잡 {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 들어본 적 없는 사람의 이름을 저장
        HashMap<String, Integer> map = new HashMap<>();
        // 듣도보도 못한 사람을 저장할 리스트
        // Priory Queue를 선언해도 됨
        List<String> result = new ArrayList<>();

        for(int i =0; i<N; i++){
            map.put(br.readLine(), 1);
        }


        for(int i =0; i<M; i++){
            String name = br.readLine();
            // 보지 못한 사람이 들어본 적도 없는 사람이면 추가
            if(map.containsKey(name)) {
                result.add(name);
            }
        }

        // 사전순 출력을 위한 리스트 정렬
        Collections.sort(result);

        sb.append(result.size()).append("\n");
        for(String s:result){
            sb.append(s).append("\n");
        }

        System.out.println(sb);
    }
}
