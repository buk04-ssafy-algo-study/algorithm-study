import java.io.*;
import java.util.*;

class Pos {
    int r;
    int c;
    
    public Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static int[][] roomNoMap;
    static int N, M;
    static boolean[][] visited;
    static int roomNum; // 방 갯수 
    static int maxRoomSize; // 단일로 가장 큰 방 크기
    static int roomNo; // 방 번호
    static Map<Integer, Integer> roomSizeMap; // 해당 방 번호의 방 크기 저장
    static int plusMaxRoomSize; // 방 2개를 합쳤을 때 가장 큰 방 크기
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 열
        M = Integer.parseInt(st.nextToken()); // 행
        
        visited = new boolean[M][N];
        map = new int[M][N];
        roomNoMap = new int[M][N];
        roomNum=0;
        roomNo=1;
        maxRoomSize=0;
        plusMaxRoomSize=0;
        roomSizeMap = new HashMap<>();
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
            
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                // 방문안한 칸이면 BFS 탐색 시작
                if(!visited[i][j]) bfs(i, j);
            }
        }
        
        brokenWall();
        System.out.println(roomNum);
        System.out.println(maxRoomSize);
        System.out.println(plusMaxRoomSize);
    } // main
    
    public static void bfs(int r, int c) {
        
        int curRoomSize = 1;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(r, c));
        visited[r][c] = true;
        roomNoMap[r][c] = roomNo;
        
        while(!q.isEmpty()) {
            
            Pos p = q.poll();
            
            for(int i=0; i<4; i++) {
                int nr = dr[i] + p.r;
                int nc = dc[i] + p.c;
                
                if(nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc]) continue;
                
                // 현재 진행 방향과 다음 칸의 숫자를 비교해서 갈 수 있는 지 없는 지 비교
                // i=0 상, 1 하, 2 좌, 3 우
                if(i==0) {
                    if(map[nr][nc] == 8 || map[nr][nc] == 9 || map[nr][nc] == 10 ||
                    map[nr][nc] == 12 || map[nr][nc] == 11 || map[nr][nc] == 13 ||
                    map[nr][nc] == 14 || map[nr][nc] == 15) {
                         
                        continue;
                    }
                }
                else if(i==1) {
                    if(map[nr][nc] == 2 || map[nr][nc] == 3 || map[nr][nc] == 6 ||
                    map[nr][nc] == 10 || map[nr][nc] == 7 || map[nr][nc] == 11 ||
                    map[nr][nc] == 14 || map[nr][nc] == 15) {
                        continue;
                    }
                }
                else if(i==2) {
                    if(map[nr][nc] == 4 || map[nr][nc] == 5 || map[nr][nc] == 6 ||
                    map[nr][nc] == 12 || map[nr][nc] == 7 || map[nr][nc] == 13 ||
                    map[nr][nc] == 14 || map[nr][nc] == 15) {
                        continue;
                    }
                }
                else if(i==3) {
                    if(map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 9 ||
                    map[nr][nc] == 5 || map[nr][nc] == 7 || map[nr][nc] == 11 ||
                    map[nr][nc] == 13 || map[nr][nc] == 15) {
                        continue;
                    }
                }
                
                // 다음 칸으로 이동하면
                q.offer(new Pos(nr, nc));
                curRoomSize++; // 현재 탐색하는 방 크기 증가
                roomNoMap[nr][nc] = roomNo;
                
                visited[nr][nc] = true;
            }
        } // while
        
        // 1번 답
        roomNum++; // 탐색 끝나면 방 갯수+1
        
        // 2번 답
        // 최대 방 크기 초기화
        if(maxRoomSize < curRoomSize) {
            maxRoomSize = curRoomSize;
        }
        
        // 현재 방의 크기를 맵에 저장 -> 벽 하나 뚫어서 크기 구하는 3번 답 구하기 위함
        roomSizeMap.put(roomNo, curRoomSize);
        
        roomNo++; // 방 번호 증가
    }
    
    static void brokenWall() {
        
     
        
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                
                // 현재 방 번호
                int curRoomNum = roomNoMap[i][j];
    
                for(int k = 0; k < 4; k++){
                    int nr = i + dr[k];
                    int nc = j + dc[k];

                    // 범위 초과
                    if(nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
                    
                    // 다음 이동 칸 번호가 현재 방 번호와 다르다면
                    if(roomNoMap[nr][nc] != curRoomNum){
                        // 현재 방, 다음 칸 방 크기 합쳐서 최대값 갱신
                       plusMaxRoomSize = Math.max(plusMaxRoomSize, (roomSizeMap.get(curRoomNum) + roomSizeMap.get(roomNoMap[nr][nc])));
                    }
                }
            }
        }
    } // brokenWall
}