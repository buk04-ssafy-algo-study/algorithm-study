import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9251 {
	static char[] str1, str2;
	static Integer[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();

		dp=new Integer[str1.length][str2.length];

		System.out.println(LCS(str1.length-1,str2.length-1));
	}

	private static int LCS(int x, int y) {
		if(x==-1 || y==-1)
			return 0; 

		if(dp[x][y] == null) { //Integer 배열은 null로 초기화 됨!
			if(str1[x]==str2[y]) 
				dp[x][y]=LCS(x-1,y-1)+1;
			else 
				dp[x][y]=Math.max(LCS(x-1,y), LCS(x,y-1));
		}
		return dp[x][y];
	}
}
