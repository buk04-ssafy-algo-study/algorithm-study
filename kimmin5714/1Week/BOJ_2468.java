import java.util.Scanner;

public class BOJ_2468 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int maxHeight = 0;
		int safeArea = 0;
		int maxSafeArea = 0;
		int[][] height = new int[N][N];		

		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				height[i][j]=sc.nextInt();
				if(height[i][j]>maxHeight) {
					maxHeight=height[i][j];
				}
			}
		}

		for(int h=0;h<=maxHeight;h++) {
			int[][] flooding = new int[N][N];
			safeArea = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					/*if(height[i][j]<=h) {
						flooding[i][j]=1;
					}*/
					if(flooding[i][j]==0 && height[i][j]>h) {
						safeArea += dfs(i,j,N,h,height,flooding);
					}
				}
			}
			if(safeArea > maxSafeArea) maxSafeArea = safeArea;
		}
		System.out.println(maxSafeArea);
	}

	public static int dfs(int i, int j, int N ,int h, int height[][] ,int flooding[][]) {
		int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
		flooding[i][j] = 2;
		for(int k=0;k<4;k++) {
			int ni = i+del[k][0];
			int nj = j+del[k][1];

			if(ni<0 || nj<0 || ni>=N || nj>=N || flooding[ni][nj] == 2) {
				continue;
			}
			if(height[ni][nj] > h) {
				dfs(ni, nj, N, h, height, flooding);
			}
		}	
		return 1;
	}
}
