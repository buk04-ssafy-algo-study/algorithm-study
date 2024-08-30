import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            List<String> list = new ArrayList<>();
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                list.add(st.nextToken());
            }

            Collections.sort(list); // String 오름차순 정렬
            boolean problem = false;
            for(int i=0;i<N-1;i++){
                String cur = list.get(i); // 현재 전화번호
                String next = list.get(i+1); // 다음 전화번호
                if(cur.length()>next.length()) continue; // 다음 전화번호가 더 짧으면 겹칠 수 x
                if(cur.length()<= next.length() &&
                        next.substring(0,cur.length()).equals(cur)){ // 현재 전화번호 길이만큼 일치하는지 확인
                    problem = true;
                    sb.append("NO\n");
                    break;
                }
            }
            if(!problem)
                sb.append("YES\n");
        }
        System.out.print(sb);
    }
}
