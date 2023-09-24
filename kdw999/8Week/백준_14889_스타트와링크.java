// 백준

import java.util.*;
import java.io.*;

public class 백준_14889_스타트와링크 {

	static int[][] synergy;
	static int N;
	static int[] startTeamPick;
	static int min = Integer.MAX_VALUE;
	
public static void main(String[] args) throws IOException{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	N = Integer.parseInt(br.readLine());
	
	synergy= new int[N][N];
	startTeamPick = new int[N/2];
	
	for(int i=0; i<N; i++) {
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int j=0; j<N; j++) {
			synergy[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
	combi(0, 0);
	System.out.println(min);
   }

   public static void combi(int cnt, int start) {
	   
	   // 스타트팀 다 뽑았다면
	   if(cnt == N/2) {
		   
		   // 미리 뽑은 스타트팀
		   int[] startTeam = new int[N/2];
		   // 남은 인원 링크팀
		   int[] linkTeam = new int[N/2];
		   // 남은 인원 파악위한 체크
		   boolean[] used = new boolean[N];
		   
		// 미리 뽑은 스타트팀 배정
		   for(int i=0; i<N/2; i++) {
			   startTeam[i] = startTeamPick[i]; // 미리 뽑은 N/2명을 스타트팀에 배정
			   used[startTeamPick[i]] = true; // 뽑은 인원 표시
		   }
		   
		// 남은 인원 링크팀 배정
		   int linkIdx = 0;
		   for(int i=0; i<N; i++) {
			   if(!used[i]) linkTeam[linkIdx++] = i;
		   }
		   
		   int startSum=0;
		   int linkSum=0;
		   for(int i=0; i<(N/2)-1; i++) {
			   for(int j=i; j<N/2; j++) {
				   if(i==j) continue;
				   startSum += synergy[startTeam[i]][startTeam[j]] + synergy[startTeam[j]][startTeam[i]];
				   linkSum += synergy[linkTeam[i]][linkTeam[j]] + synergy[linkTeam[j]][linkTeam[i]];
			   }
		   }
		   int diff = Math.abs(startSum-linkSum);
		   min = Math.min(min, diff);
		   
		   return;
	   }
	   
	   // 스타트팀 먼저 뽑아놓고 남은 인원을 링크팀에 배정
	   for(int i=start; i<N; i++) {
		   startTeamPick[cnt] = i;
		   combi(cnt+1, i+1);
	   }
   }
}