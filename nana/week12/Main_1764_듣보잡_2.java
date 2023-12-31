package study.week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1764_듣보잡_2 {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 들어본 적 없는 사람의 이름을 저장할 set. 중복을 허용하지 않음.
        HashSet<String> set = new HashSet<>();
        List<String> result = new ArrayList<>();

        for(int i =0; i<N; i++){
            set.add(br.readLine());
        }


        for(int i =0; i<M; i++){
            String name = br.readLine();
            if(set.contains(name)) {
                result.add(name);
            }
        }

        Collections.sort(result);

        sb.append(result.size()).append("\n");
        for(String s:result){
            sb.append(s).append("\n");
        }

        System.out.println(sb);
    }
}
