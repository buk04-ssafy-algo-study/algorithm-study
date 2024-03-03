import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Map<String, Integer> map = new HashMap<>(); // 이름, 등장횟수
            List<String> nameList= new ArrayList<>();   // 듣도 보도 못한 사람 리스트
            int answer = 0;
            for (int i = 0; i < N; i++) {
                map.put(br.readLine(), 1); // 듣도 못한 사람의 등장 횟수: 1
            }
            for (int i = 0; i < M; i++) {
                String name = br.readLine(); // 보도 못한 사람 이름
                int cnt = map.getOrDefault(name, 0);
                if (cnt>0){ // map에 있던 사람이라면
                    answer++;
                    nameList.add(name); // 듣도 보도 못한 사람을 리스트에 추가
                }
            }
            Collections.sort(nameList); // 정렬
            sb.append(String.valueOf(answer)+"\n");
            for (String name : nameList) {
                sb.append(name + "\n");
            }
            out.write(sb.toString());
            out.flush();
            out.close();
        }
}
