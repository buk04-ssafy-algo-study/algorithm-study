package Week18;

import java.util.*;
import java.io.*;

public class N과M9 {
	
	static int[] number;
	static int[] picks;
	static boolean[] visit;
	static int N,M;
	static LinkedHashSet<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		number = new int[N];
		picks = new int[M];
		visit = new boolean[N];
		set = new LinkedHashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(number);
		permu(0);
		for(String s : set) System.out.println(s);
	}
	public static void permu(int cnt) {
		
		if(cnt==M) {
			StringBuilder sb = new StringBuilder();
		  	for(int i=0; i<picks.length; i++) {
		  		sb.append(picks[i]).append(" ");
		  	}
		  	set.add(sb.toString());
		  	return;
		}
		
		for(int i=0; i<N; i++) {
			if(visit[i]) continue;
			
			visit[i]=true;
			picks[cnt] = number[i];
			permu(cnt+1);
			visit[i]=false;
		}
	}
}
