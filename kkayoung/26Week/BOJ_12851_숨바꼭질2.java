// https://www.acmicpc.net/problem/12851
import java.io.*;
import java.util.*;

public class Main {

    static int MAX = 100_000;
    static int[] visited = new int[100_001]; // 좌표에 도착하기까지 소요하는 최소 시간을 저장

    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Arrays.fill(visited, 100_001);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int caseCnt = 0;
        int minTime = Integer.MAX_VALUE;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{N,0});
        visited[N] = 0;

        while(!q.isEmpty()) {
            int[] arr = q.poll();
            int now = arr[0];
            int time = arr[1];
            
            if(now==K) { // 동생과 만남
                if(time<minTime) { // time 최솟값 갱신
                    minTime = time;
                    caseCnt=1; // caseCnt 1로 초기화
                } else if(time==minTime) {
                    caseCnt++; // caseCnt 1 증가
                }
                continue;
            }
            // X-1
            if(now-1>=0 && time+1 <= visited[now-1]) {
                visited[now-1] = time+1;
                q.offer(new int[]{now-1, time+1});
            }
            // X+1
            if(now+1<=MAX && time+1 <= visited[now+1]) {
                visited[now+1] = time+1;
                q.offer(new int[]{now+1, time+1});
            }

            // 2*X
            if(2*now<=MAX && time+1 <= visited[2*now]) {
                visited[now*2] = time+1;
                q.offer(new int[]{now*2, time+1});
            }
        }

        bw.write(String.format("%d\n%d",minTime, caseCnt));
        bw.flush();
        bw.close();
    }

}
