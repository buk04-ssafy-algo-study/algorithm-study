// https://www.acmicpc.net/problem/5525
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		char[] str = br.readLine().toCharArray();
		int[] cnt = new int[M];     // 몇 번째 IO인지 저장
		if(str[0]=='I') cnt[0] = 1;
		else cnt[0] = 0;

		int answer = 0;
		for(int i=1;i<M;i++){
			char c = str[i];
			if(c=='O'){
				cnt[i] = (str[i-1]=='I') ? cnt[i-1]:0;
				if(cnt[i]>=N && i+1<M && str[i+1]=='I') answer++;
			} 
			else { // 'I'
				cnt[i] = (str[i-1]=='O') ? cnt[i-1]+1:1;
			}
		}

		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}
