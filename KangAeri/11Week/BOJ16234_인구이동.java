package probs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ16234 {
	private static int[][] dir = {{0,-1},{0,1},{-1,0},{1,0}};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=0,l=0,r=0;
		int i=0, j=0, k=0,ni=0,nj=0,cur=0, diff=0,g=0;
		int tsum=0, tnum=0,mov=0, day=0;	
		Queue<Integer> que = new ArrayDeque<>();
		Queue<Integer> chkque = new ArrayDeque<>();
		
		String[] in = br.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		l = Integer.parseInt(in[1]);
		r = Integer.parseInt(in[2]);
		int[][] map = new int[n][n];
		int[][] state = new int[n][n];
		
		for(i=0; i<n; i++) {
			in = br.readLine().split(" ");
			for(j=0; j<n; j++) {
				map[i][j]=Integer.parseInt(in[j]);
			}
		}
		
		while(day<=2000) {
			g=1;	
			mov=0;
			state=new int[n][n];		
			
			for(i=0; i<n; i++) {
				for(j=0; j<n; j++) {
					if(state[i][j]>0) {
						continue;
					}
					tnum=1;	
					tsum=map[i][j];
					state[i][j]=g;	
					//bfs start//
					que.offer(i*n+j);
					chkque.offer(i*n+j);
					while(!que.isEmpty()) {
						cur = que.poll();	
						for(k=0; k<4; k++) {
							ni=cur/n +dir[k][0];
							nj=cur%n +dir[k][1];
							if(ni>=0 &&nj>=0 && ni<n && nj<n && state[ni][nj]==0) {
								diff = Math.abs(map[ni][nj]-map[cur/n][cur%n]);
								if(diff>=l && diff<=r) {
									state[ni][nj]=g;
									que.offer(ni*n+nj);
									chkque.offer(ni*n+nj);
									tsum+=map[ni][nj];
									tnum++;
								}
							}
						}
					}
					//bfs end//
			
					
					while(!chkque.isEmpty()) {
						cur=chkque.poll();		
						if(map[cur/n][cur%n]!=tsum/tnum) {
							mov++;
							map[cur/n][cur%n]=tsum/tnum;
						}
					}//end while : chkque isempty
					g++;
					
				}//end for :j
			}//end for:i
			
			if(mov==0) {
				break;
			}	
			
			day++;
		}
	
		System.out.println(day);
		
	}
}
