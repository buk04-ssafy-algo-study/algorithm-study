import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static int[][] exp;
	private static int[] chk;
	private static int mindiff;
	private static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int i=0, j=0;
		n = Integer.parseInt(br.readLine());
		String[] in;
		exp = new int[n][n];
		chk = new int[n];
		for(i=0;i<n;i++) {
			in = br.readLine().split(" ");
			for(j=0;j<n;j++) {
				exp[i][j] = Integer.parseInt(in[j]);
			}
		}
		mindiff = 100000;	
		combi(0,0);
		
		System.out.println(mindiff);
	}
	
	private static void combi(int cnt, int start) {
		
		if(cnt==n/2) {
			int t1Score=0;
			int t2Score=0;
			for(int i=0; i<n; i++) {
				if(chk[i]==0) {
					for(int j=0; j<n; j++) {
						if(chk[j]==0) {
							t1Score+= exp[i][j];
						}	
					}
				} else if(chk[i]==1) {
					for(int j=0; j<n; j++){
						if(chk[j]==1) {
							t2Score+= exp[i][j];
						}
					}
				}
			}	
			int diff = Math.abs(t1Score-t2Score);
			if(diff<mindiff) {
				mindiff = diff;
			}
			return;
		}
		for(int i=start; i<n; i++) {
			chk[i]=1;
			combi(cnt+1, i+1);
			chk[i]=0;
		}
	}
}
