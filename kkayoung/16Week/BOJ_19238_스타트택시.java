// https://www.acmicpc.net/problem/19238
import java.io.*;
import java.util.*;

public class Main {

	static class Passenger implements Comparable<Passenger>{
		int id, srcR, srcC, dstR, dstC, dist; // 승객 번호, 출발 행, 출발 열, 도착 행, 도착 열, 거리
		Passenger(int id, int srcR, int srcC, int dstR, int dstC, int dist){
			this.id = id;
			this.srcR = srcR;
			this.srcC = srcC;
			this.dstR = dstR;
			this.dstC = dstC;
			this.dist = dist;
		}
		Passenger(int id, int srcR, int srcC, int dstR, int dstC){
			this(id, srcR, srcC, dstR, dstC, 0);
		}
		@Override
		public int compareTo(Passenger o){ // 거리, 행, 열 순 정렬
			if(this.dist!=o.dist) return Integer.compare(this.dist, o.dist);
			if(this.srcR!=o.srcR) return Integer.compare(this.srcR, o.srcR);
			else return Integer.compare(this.srcC, o.srcC);
		}
		@Override
		public String toString(){
			return String.format("ID: %d/ srcR=%d, srcC=%d, dstR=%d, dstC=%d, dist=%d", id, srcR, srcC, dstR, dstC, dist);
		}
	}
	
	static final int BLANK = 0;
	static final int WALL = Integer.MIN_VALUE;
	static int N, M, fuel, taxiR, taxiC;
	static int INF = Integer.MAX_VALUE;
	static int[][] map, distance;
	static Passenger[] passengers;
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // u d l r
	static boolean[] completed;
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		init();
		solution();
		boolean SUCCESS = true;
		for(int i=1;i<=M;i++){ // 도착지까지 도착하지 못한 손님이 있다면 SUCCESS에 false 저장
			if(completed[i]==false){
				SUCCESS = false;
				break;
			}
		}
		// output
		int answer = SUCCESS ? fuel : -1;
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static void solution(){
		// 택시 초기 위치에서 출발지가 가장 가까운 승객 찾기
		Passenger first = findFirst(taxiR, taxiC);
		if(first==null) return;

		fuel -= first.dist; // 첫 번째 승객 출발지로 이동, 연료 소비
		if(fuel<0) return;  // 첫 번째 승객까지 이동 불가능할 경우

		for(int pId=1;pId<=M;pId++){
			// 도착지점에서 모든 출발지점까지의 거리 계산
			calDist(passengers[pId].dstR, passengers[pId].dstC, pId);
		}

		int nowPassengerId = first.id;
		for(int repeatCnt=1;repeatCnt<=M;repeatCnt++){
			// nowPassengerId번 승객의 출발지에서 도착지까지 이동
			int dist = distance[nowPassengerId][nowPassengerId];
			if(dist==INF) return;
			fuel -= dist; 
			if(fuel<0) return; 
			completed[nowPassengerId] = true; // 처리 완료
			fuel += dist*2; // 도착지 도착 -> 이동거리*2만큼 연료 충전
			// nowPassengerId번 승객의 도착지와 가장 가까운 승객 찾기
			List<Passenger> candidate = new ArrayList<>(); 
			for(int id=1;id<=M;id++){
				if(completed[id]==true || distance[id][nowPassengerId]==INF) continue; 
				passengers[id].dist = distance[id][nowPassengerId];
				candidate.add(passengers[id]);
			}
			if(candidate.size()==0) return;
			Collections.sort(candidate);
			// nowPassengerId번 도착지에서 다음 승객 출발지로 이동
			Passenger next = candidate.get(0);
			fuel -= distance[next.id][nowPassengerId]; // nowPassengerId번 승객의 도착지와 다음 승객의 출발지 사이 거리만큼 연료 감소
			if(fuel<0) return;
			nowPassengerId = next.id; // nowPassengerId 갱신
		}
		return;
	}

	static void calDist(int r, int c, int dstId){
		q.offer(new int[]{r,c,0});
		boolean[][] visited = new boolean[N][N];
		visited[r][c] = true;
		if(map[r][c]>0){
			distance[map[r][c]][dstId] = 0;
		}

		while(!q.isEmpty()){
			int[] now = q.poll();
			int nowR = now[0];
			int nowC = now[1];
			int dist = now[2];

			for(int d=0;d<4;d++){
				int nr = nowR+dir[d][0];
				int nc = nowC+dir[d][1];

				if(canReach(nr,nc,visited)){
					visited[nr][nc] = true;
					if(map[nr][nc]>0){ // src
						int pId = map[nr][nc];
						distance[pId][dstId] = dist+1;
					}
					q.offer(new int[]{nr, nc, dist+1});
				}
			}
		}
	}


	static Passenger findFirst(int r, int c){
		if(map[r][c]>0) return passengers[map[r][c]];

		List<Passenger> p = new ArrayList<>();		
		boolean[][] visited = new boolean[N][N];
		visited[r][c] = true;
		q.offer(new int[]{r,c,0});

		while(!q.isEmpty()){
			int[] now = q.poll();
			int nowR = now[0];
			int nowC = now[1];
			int dist = now[2];

			for(int d=0;d<4;d++){
				int nr = nowR+dir[d][0];
				int nc = nowC+dir[d][1];

				if(canReach(nr,nc,visited)){
					visited[nr][nc] = true;
					if(map[nr][nc]>0) { // src
						int pId = map[nr][nc];
						passengers[pId].dist = dist+1;
						p.add(passengers[pId]);
					}
					q.offer(new int[]{nr, nc, dist+1});
				}
			}
		}
    Collections.sort(p);
		if(p.size()>0) return p.get(0);
		else return null;
	}

	static boolean canReach(int r, int c, boolean[][] visited){
		if(r<0 || r>=N || c<0 || c>=N) return false;
		if(map[r][c]==WALL || visited[r][c]==true) return false;
		return true;
	}

	static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		// map
		map = new int[N][N];
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) map[i][j] = WALL;
			}
		}
		// taxi
		st = new StringTokenizer(br.readLine());
		taxiR = Integer.parseInt(st.nextToken())-1;
		taxiC = Integer.parseInt(st.nextToken())-1;

		// passengers
		distance = new int[M+1][M+1];
		for(int i=0;i<=M;i++){
			Arrays.fill(distance[i],INF);
		}
		completed = new boolean[M+1];
		passengers = new Passenger[M+1];
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int srcR = Integer.parseInt(st.nextToken())-1;
			int srcC = Integer.parseInt(st.nextToken())-1;
			int dstR = Integer.parseInt(st.nextToken())-1;
			int dstC = Integer.parseInt(st.nextToken())-1;
			passengers[i] = new Passenger(i, srcR, srcC, dstR, dstC);
			map[srcR][srcC] = i;
		}
	}
}
