import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//https://www.acmicpc.net/problem/25046
	//https://www.acmicpc.net/problem/25047
	
	static int N;
	static long[][] Board;
	static final long INF = Long.MAX_VALUE;
	
	public static long calScore(long minwoo) {
		//민우 점수
		long score = 0;
		//열 탐색
		for(int i=0;i<N;i++) {
			//칠한 점수
			long onScore = 0;
			//안 칠한 점수
			long offScore = 0;
			//행탐색
			for(int j=0;j<N;j++) {
				int idx = 1<<j;
				//색을 칠한 경우
				if((minwoo&idx)==idx){
					onScore+=Board[j][i];
				//안 칠한 경우
				}else {
					offScore+=Board[j][i];
				}
			}
			//종진이가 색을 칠하면 offScore를,
			//색을 칠하지 않으면 onScore를 얻음 
			//큰 값은 종진이가, 작은 값은 민우가 얻음
			score+=Math.min(onScore, offScore);
		}
		//민우 점수 리턴
		return score;
	}
	
	public static long getMaxScore() {
		//점수를 -INF로 초기화
		long score=-INF;
		//완전탐색, 비트마스크 사용
		long bitMask = 1<<N;
		//행 칠하는 경우의 수
		for(long i=0;i<bitMask;i++) {
			//최대 점수를 취함
			score=Math.max(score, calScore(i));
		}
		//출력
		return score;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //게임판 크기
		Board=new long[N][N];
		StringTokenizer st;
		//배열 입력
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				Board[i][j]=Long.parseLong(st.nextToken());
			}
		}
		//출력
		System.out.println(getMaxScore());
		br.close();
	}
}
