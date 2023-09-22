import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int[][] mov = {{0,1},{0,-1},{-1,0},{1,0}};
	private static int[][] board;
	private static int[][] put;
	private static int[][] info;
	private static int x=0,y=0, nx=0, ny=0, i=0, loc=0, nloc=0, n=0,k=0;
	private static boolean flag=false;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int j=0, turn=0;
		
		String[] in = br.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		k = Integer.parseInt(in[1]);
		board = new int[n][n];
		put = new int[n*n][5];
		info = new int[k+1][2];
		
		//board input
		for(i=0; i<n; i++) {
			in = br.readLine().split(" ");
			for(j=0; j<n; j++) {
				board[i][j] = Integer.parseInt(in[j]);
			}
		}
		
		//chess input : posi | dir
		for(i=1; i<=k; i++) {
			in = br.readLine().split(" ");
			loc =(Integer.parseInt(in[0])-1)*n+(Integer.parseInt(in[1])-1);
			info[i][0]=loc;
			info[i][1] = Integer.parseInt(in[2])-1;	
			put[loc][++put[loc][0]] = i; //save num
			if(put[loc][0]>=4) {
				flag=true;
				break;
			}
		}
		
		
		while(true) {
			turn++;
			if(turn>1000 ) {
				System.out.println(-1);
				break;
			}
			for(i=1; i<=k; i++) {
				x = info[i][0]/n;
				nx =x+ mov[info[i][1]][0];
				y = info[i][0]%n;
				ny =y+ mov[info[i][1]][1];
				loc = x*n+y;
				nloc = nx*n+ny;
				if(nx>=0 && nx<n && ny>=0 && ny<n) {
					moved();
				} else {
					moveblue(); //case blue
				}
				if(flag==true) {
					break;
				}
			}
			
			if(flag==true) {
				System.out.println(turn);
				break;
			}
		}
	}
	
	private static int dirconvert(int dir) {
		int chg=0;
		if(dir==0 || dir==2) {
			chg = dir+1;
		} else if(dir==1 || dir==3) {
			chg = dir-1;
		}
		return chg;
	}
	
	private static void moved() {
		int j=0,p=0,cnt=0;
		
		switch (board[nx][ny]) {
			case 0:
				for(j=1; j<=put[loc][0]; j++){
					//System.out.println(put[loc][j]+" "+i);
					if(put[loc][j]==i) {
						break;
					}
				}
				//System.out.println("/");
				cnt=put[loc][0];
				for(p=j; p<=cnt; p++){
					if((1+put[nloc][0])>=4) {
						flag=true;
						break;
					}
					put[nloc][++put[nloc][0]] = put[loc][p];
					put[loc][0]--;
					info[i][0] = nloc;
					
				}
				break;
				
			case 1:	
				for(j=1; j<=put[loc][0]; j++){
					//System.out.println(put[loc][j]+" "+i);
					if(put[loc][j]==i) {
						break;
					}
				}
				//System.out.println("/");
				cnt=put[loc][0];
				for(p=cnt; p>=j; p--){
					if(1+put[nloc][0]>=4) {
						flag=true;
						break;
					}
					put[nloc][++put[nloc][0]] = put[loc][p];
					put[loc][0]--;
					info[i][0] = nloc;
				}
				break;
				
			case 2:
				//System.out.println(2);
				moveblue();
				break;
			}
		
	}
	
	private static void moveblue() {
		//System.out.print(info[i][1]+",");
		info[i][1] = dirconvert(info[i][1]);
		//System.out.println(info[i][1]);
		nx = x + mov[info[i][1]][0];
		ny = y +mov[info[i][1]][1];
		loc = x*n+y;
		nloc = nx*n+ny;
		if(nx>=0 && nx<n && ny>=0 && ny<n && board[nx][ny]!=2) {
			moved();
		}
		
	}
	
	
}
