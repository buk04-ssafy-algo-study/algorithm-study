import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15663_N과M9 {
	static int N,M,input[], output[];
	static boolean selected[];
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		output = new int[M];
		selected = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		permutation(0);
	}

	private static void permutation(int cnt) {
		if(cnt == M) {
			for(int i=0;i<M;i++)
				sb.append(output[i]+" ");
			sb.append("\n");
			return;
		}
		
		int past = -1;
		for(int i=0;i<N;i++) {
			int now = input[i]; //현재 뽑으려는 수
			if(!selected[i] && past != now) { //현재 뽑으려는 수와 전에 뽑은 수가 달라야 뽑음, 중복 제거 됨 
				past=now; //직전에 뽑은 수로 업데이트
				selected[i] = true;
				output[cnt] = input[i];
				permutation(cnt+1);
				selected[i] = false;
			}
		}
	}
}