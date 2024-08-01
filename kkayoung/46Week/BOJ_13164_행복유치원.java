// https://www.acmicpc.net/problem/13164
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        List<Integer> diff = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int[] height = new int[N];
        for(int i=0;i<N;i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1;i<N;i++) {
            diff.add(height[i]-height[i-1]);
        }
        Collections.sort(diff);        

        int answer = 0;
        for(int i=0;i<N-K;i++) {
            answer += diff.get(i);
        }
        
        System.out.println(answer);
    }

}
