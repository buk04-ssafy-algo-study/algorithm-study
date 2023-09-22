package probs0825w;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class BOJ1339 {
private static StringBuilder sb = new StringBuilder();

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=0,i,j,sum=0;
		int[] al = new int[26];
		String in;
		n =Integer.parseInt(br.readLine());
		for(i=0; i<n; i++) {
			in = br.readLine();
			for(j=0; j<in.length();j++) {
				al[in.charAt(j)-65]+=Math.pow(10, in.length()-j-1);
			}
		}
		
		Arrays.sort(al);
		
		int x=9;
		for(i=25; i>=0; i--) {
			sum+=al[i]*x;
			x--;		
		}

		System.out.println(sum);
		
	}
}
