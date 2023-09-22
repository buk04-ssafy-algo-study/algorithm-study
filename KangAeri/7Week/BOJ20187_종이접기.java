package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ20187 {
	private static StringBuilder sb = new StringBuilder();
	private static int x,y,lenr,lenc;
	private static int[][] paper;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k=0,n=0,i=0,j=0,h=0;
		String[] in;
		char[] seq;

		k = Integer.parseInt(br.readLine());
		n = 1<<k;
		in = br.readLine().split(" ");
		seq = new char[2*k];
		paper = new int[n][n];
		lenr=n;
		lenc=n;
		x=0;
		y=0;
		
		for(i=0; i<2*k ;i++) {
			seq[i] = in[i].charAt(0);
			fold(seq[i]);	
		}
		h=Integer.parseInt(br.readLine());
		paper[x][y]=h;
		
		for(i=2*k-1; i>=0 ;i--) {
			open(seq[i]);
		}
		
		for(i=0; i<lenr; i++) {
			for(j=0; j<lenc; j++) {
				sb.append(paper[i][j]+" ");
			}
			sb.append("\n");
		}	

		System.out.println(sb);
		
		
	}
	
	private static void fold(char cmd) {
		switch (cmd) {
			case 'D':
				lenr/=2;
				x+=lenr;			
				break;
			case 'U':
				lenr/=2;
				break;
			case 'R':
				lenc/=2;
				y+=lenc;
				break;
			case 'L':
				lenc/=2;
				break;
		}
	}
	
	
	
	private static void open(char cmd) {
		int ni=x,nj=y;
		
		switch (cmd) {
			case 'D':
				ni=x-1;
				break;
			case 'U':
				ni=x+lenr*2-1;
				break;
			case 'R':
				nj=y-1;
				break;
			case 'L':
				nj=y+2*lenc-1;
				break;
		}
		
		if(cmd=='D'||cmd=='U') {
			for(int i=0; i<lenr; i++) {
				for(int j=0; j<lenc; j++) {
					paper[ni-i][nj+j] =chk(paper[x+i][y+j],cmd);
				}
			}	
		} else if(cmd=='R'||cmd=='L') {
			for(int i=0; i<lenr; i++) {
				for(int j=0; j<lenc; j++) {
					paper[ni+i][nj-j] =chk(paper[x+i][y+j],cmd);
				}
			}
		}
		

		switch (cmd) {
		case 'D':
			x-=lenr;	
			lenr*=2;	
			break;
		case 'U':
			lenr*=2;	
			break;
		case 'R':
			y-=lenc;
			lenc*=2;
			break;
		case 'L':
			lenc*=2;
			break;
		}
		
	}
	
	private static int chk (int dir, char cmd) {
		if(cmd=='D'||cmd=='U') {
			return (dir+2)%4;		
		} else{
			if(dir==0|| dir==2) {
				return dir+1;
			} else {
				return dir-1;
			}
		}
	}
	

}
