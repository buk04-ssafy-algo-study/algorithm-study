// https://www.acmicpc.net/problem/1107
import java.io.*;
import java.util.*;

public class Main{

	static boolean[] broken;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// input
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		broken = new boolean[10];
		if(M>0){
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++){
				broken[Integer.parseInt(st.nextToken())] = true;
			}
		}

		int answer = Math.abs(N-100); // + 또는 -버튼만 눌러서 N번 채널로 이동한 경우
		if(M==0) answer = Math.min(answer, Integer.toString(N).length()); // 고장난 버튼이 없는 경우 N번 채널의 자릿수만큼 누름
		if(answer!=0 && M>0){
			for(int channel=0;channel<=1_000_000;channel++){ // channel번 채널에서 N번 채널까지 이동할 때 버튼을 몇 번 눌러야 하는지 계산
				boolean brokenExists = false; // channel번 채널에 고장난 버튼이 존재하는지
				for(char c:Integer.toString(channel).toCharArray()){
					if(broken[c-'0']==true){ // 고장난 버튼 존재 -> 숫자 버튼만 눌러서 channel번으로 이동 불가능
						brokenExists = true;
						break;
					}
				}
				if(!brokenExists){ // 기존 최소 횟수와 (channel번으로 이동할 때 누른 숫자 버튼 개수)+(channel번에서 N번 채널로 이동하기 위해 +/- 버튼을 눌러야 하는 횟수) 비교
					answer = Math.min(answer, Integer.toString(channel).length()+Math.abs(N-channel));
				}
			}
		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}
