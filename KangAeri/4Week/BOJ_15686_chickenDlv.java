import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int n;
	private static int m;
	private static int[] open;
	private static int[][] city;
	private static int[] chicken; 
	private static int num;
	//private static int min;
	private static int ckndis;
	private static int min;
	
	public static void main(String[] args) throws Exception {
		int i=0,j=0, k=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		m = Integer.parseInt(in[1]);
		
		city = new int[n][n];
		//numCm
		chicken = new int[14];
		num=0;
		for(i=0;i<n;i++) {
			in = br.readLine().split(" ");
			for(j=0;j<n;j++) {
				city[i][j] = Integer.parseInt(in[j]);
				if(city[i][j]==2) {
					chicken[num++]=i*n+j;
				}
			}
		}
	
	
		open = new int[m];
		ckndis=1000000;
		min=1000000;
		combi(0,0);
		
		System.out.println(min);
		
		
	}
	
	private static void combi(int cnt, int start) {
		int i=0,j=0;
		if(cnt==m) {
			ckndis=0;
			for(i=0;i<n;i++) {
				for(j=0;j<n;j++) {
					if(city[i][j]==1) {	
						int dis=0;
						int mindis=1000000;
						for(int k=0; k<m; k++) {
							
							dis =Math.abs(i-chicken[open[k]]/n)+Math.abs(j-chicken[open[k]]%n);
							//System.out.print(dis);
							if(dis<mindis) {
								mindis=dis;
							}
						}
						ckndis += mindis; // chicken distance from city[i][j]
						//System.out.println();
					}
				}
				
			}//end for:i
			
			if(min>ckndis) {
				min=ckndis;
			}
			return;
		}
		for(i=start;i<num;i++) {
			open[cnt]= i;
			combi(cnt+1, i+1);
		}
	}
}
