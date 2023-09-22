import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16926_배열돌리기1{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		 String[] split = br.readLine().split(" ");
		 int n = Integer.parseInt(split[0]);
		 int m = Integer.parseInt(split[1]);
		 int r = Integer.parseInt(split[2]);
		 
		 int[][] arr = new int[n][m];
		 
		 for (int i = 0; i < n; i++) {
			 split = br.readLine().split(" ");
			 for (int j = 0; j < m; j++) {
				 arr[i][j] = Integer.parseInt(split[j]);
			}
		}
		 int[] dx = {0, 1, 0, -1};
		 int[] dy = {1, 0, -1, 0};
		 for (int i = 0; i < r; i++) {
			 for (int j = 0; j < Math.min(m, n)/2; j++) {
				int cx = j;
				int cy = j;
				int temp = arr[cx][cy];
				
				int dir = 0;
				while(dir < 4) {
					int nx = cx+dx[dir];
					int ny = cy+dy[dir];
					
					if(nx < n-j && ny < m-j && nx >= j && ny >= j) {
	    				arr[cx][cy] = arr[nx][ny];
	    				cx = nx;
	    				cy = ny;
	    			} 
	    			else {
	    				dir++;
	    			}
				}
				arr[j+1][j] = temp;
			}
			 
			 
		}
		 
		 for (int i = 0; i < n; i++) {
			 for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j] + " ");
			}
			 System.out.println();
			
		}
		 
	}
}