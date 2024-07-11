// https://www.acmicpc.net/problem/2812
import java.io.*;
import java.util.*;

class Main {

    static Deque<Integer> d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        d = new ArrayDeque<>();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String num = br.readLine();

        for(int i=0;i<N;i++) {
            int n = Integer.parseInt(num.substring(i, i+1));
            if(d.size()==0) {
                d.push(n);
                continue;
            } else {
                while(K>0 && d.size()>0 && d.peek()<n) {
                    d.pop();
                    K--;
                }
                d.push(n);
            }
        }

        while(K>0) {
            d.pop();
            K--;
        }

        StringBuilder sb = new StringBuilder();
        while(!d.isEmpty()) {
            sb.append(d.pollLast());
        }

        System.out.println(sb.toString());
    }

}
