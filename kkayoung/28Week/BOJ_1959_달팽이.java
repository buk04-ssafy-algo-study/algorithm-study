// https://www.acmicpc.net/problem/1959
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        long M = Long.parseLong(st.nextToken());
        long N = Long.parseLong(st.nextToken());

        // 선이 꺾어지는 횟수 출력
        if(M<=N) sb.append(2*M-2).append("\n");
        else sb.append(2*N-1).append("\n");

        // 끝나는 점의 좌표 출력
        long r = Long.MIN_VALUE; // 끝나는 점의 좌표 행
        long c = Long.MIN_VALUE; // 끝나는 점의 좌표 열
        if(M==N) {
            if(M%2==0) {
                r = (M/2)+1;
                c = N/2;
            } else {
                r = M/2+1;
                c = r;
            }
        } else if(M>N) {
            if(N%2==0) {
                r = N/2+1;
                c = N/2;
            } else {
                r = M-N/2;
                c = N/2+1;
            }
        } else {
            if(M%2==0) {
                r = M/2+1;
                c = M/2;
            } else {
                r = M/2+1;
                c = N-M/2;
            }
        }
        sb.append(r).append(" ").append(c);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
