// https://www.acmicpc.net/problem/15663
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] num;
	static boolean[] selected;
	static Set<String> set = new HashSet<>();
	static StringBuilder sb = new StringBuilder();
	static StringBuilder output = new StringBuilder();
	static int[] selectedNum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[N];
		selected = new boolean[N];
		selectedNum = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);

		// permutation
		perm(0, 0);

		// output
		output.deleteCharAt(output.length()-1);
		bw.write(output.toString());
		bw.flush();
		bw.close();
	}

	static void perm(int idx, int limit) {
		if(limit==M) {
			for(int n:selectedNum){
				sb.append(n).append(" ");
			}
			if(!set.contains(sb.toString())) { // 중복검사
				output.append(sb.toString()).append("\n");
				set.add(sb.toString());
			}
			sb.setLength(0);
			return;
		}
		
		for(int i=0;i<N;i++){
			if(selected[i]) continue;
			selected[i] = true;
			selectedNum[limit] = num[i];
			perm(i+1, limit+1);
			selected[i] = false;
		}	
	}
}
