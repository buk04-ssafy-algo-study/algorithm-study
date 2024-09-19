import java.io.*;
import java.util.*;

public class Softeer_동계테스트시점예측 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int res = 0;
        int[][] arr = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            List<int[]> meltList = new ArrayList<>(); // 녹는 얼음 리스트
            int[][] air = new int[N][M]; // 외부, 내부 공기 정보
            outAirCheck(arr, N, M, air); // 외부 공기 체크

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(arr[i][j] == 1) // 얼음인 경우 녹는 얼음인지 판단
                        if(isMelted(arr,i,j,air)) meltList.add(new int[] {i, j});
                }
            }
            if(meltList.size() == 0) break; // 녹을 게 없다면 종료

            for(int i=0; i<meltList.size(); i++) {
                int[] tmp = meltList.get(i);
                arr[tmp[0]][tmp[1]] = 0; // 얼음 없앰
                air[tmp[0]][tmp[1]] = 1; // 외부 공기로 저장
            }

            res++; // 1초 추가
        }
        System.out.print(res);
    }
    public static boolean isMelted(int[][] arr, int i, int j, int[][] air) {
        int[] delr = new int[]{-1,1,0,0};
        int[] delc = new int[]{0,0,-1,1};

        int cnt = 0;
        for(int k=0;k<4;k++) { // 4방 탐색
            int nR = i+delr[k];
            int nC = j+delc[k];
            if(air[nR][nC] == 1 && arr[nR][nC] == 0) cnt++; // 외부 공기인 경우
        }

        if(cnt>=2) return true;
        else
            return false;
    }
    public static void outAirCheck(int[][] arr, int N, int M, int[][] air) {
        int[] delr = new int[]{-1,1,0,0};
        int[] delc = new int[]{0,0,-1,1};
        boolean[][] visited = new boolean[N][M];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0}); // 외부 공기 (0,0)부터 시작
        visited[0][0] = true;
        air[0][0] = 1;

        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            visited[tmp[0]][tmp[1]] = true;

            for(int k=0;k<4;k++) { // 4방 탐색
                int nR = tmp[0]+delr[k];
                int nC = tmp[1]+delc[k];

                if(nR<0 || nR>=N || nC<0 || nC>=M) continue;
                if(arr[nR][nC] == 1 || visited[nR][nC]) continue; // 방문한 곳이거나 얼음인 경우

                air[nR][nC] = 1; // 방문하지 않은 공기인 경우 = 외부 공기
                q.offer(new int[]{nR,nC});
                visited[nR][nC] = true;
            }
        }
    }
}