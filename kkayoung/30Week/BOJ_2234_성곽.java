// https://www.acmicpc.net/problem/2234
import java.io.*;
import java.util.*;

public class Main {

    static int[][] dir = {{0,-1},{-1,0},{0,1},{1,0}};
    static int N, M;
    static Queue<int[]> q = new ArrayDeque<>();
    static int[][] room;
    static int[][] visited;
    static Set<int[]> pair = new HashSet<>();
    static List<Integer> roomSize = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[M][N];
        visited = new int[M][N];
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int roomNum = 1;
        for(int i=0;i<M;i++) {
            for(int j=0;j<N;j++) {
                if(visited[i][j]==0) {
                    visited[i][j] = roomNum;
                    roomSize.add(explore(i, j, roomNum));
                    roomNum++;
                }
            }
        }

        for(int[] i:pair) {
            pq.add(roomSize.get(i[0]-1)+roomSize.get(i[1]-1));
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(roomSize.size()).append("\n");
        Collections.sort(roomSize);
        sb.append(roomSize.get(roomSize.size()-1)).append("\n");
        sb.append(pq.poll());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    static int explore(int r, int c, int roomNum) {
        q.offer(new int[]{r, c});
        int size = 0;
    
        while(!q.isEmpty()) {
            size++;
            int[] now = q.poll();
            r = now[0];
            c = now[1];
            
            for(int d=0;d<4;d++) {

                int nr = r+dir[d][0];
                int nc = c+dir[d][1];

                if((room[r][c] & (int)Math.pow(2,d))==(int)Math.pow(2,d)) {
                    if(0<=nr && nr<M && 0<=nc && nc<N && visited[nr][nc]>0 && visited[nr][nc]!=visited[r][c]) {
                        pair.add(new int[]{roomNum, visited[nr][nc]});
                    }
                    continue;
                }                

                if(0<=nr && nr<M && 0<=nc && nc<N && visited[nr][nc]==0) {
                    visited[nr][nc] = roomNum;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return size;
    }
}
