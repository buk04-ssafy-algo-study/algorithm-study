/*
https://www.acmicpc.net/problem/14891 
구현
-꼭 연속적으로 톱니바퀴가 돈다고 생각할 필요x
  -> 한 타임에 모든 바퀴 동시에 회전함
  -> 한 번에 모든 바퀴 회전 방향 구한 후, 한 번에 회전
-각 톱니바퀴 회전 정보 저장해서 한 번에 회전
-시계방향 회전 시 (배열 오른쪽으로 한 칸씩 옮길 때)
  -> 배열 맨 뒤부터 값 변경해주고, 마지막에 저장해뒀던 맨 마지막 인덱스 값 맨 처음 인덱스에 넣어주기
  -> 반시계 방향은 반대 

* 배열 뒤에서부터 처리하는 경우 잘 고려하기

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int input[][], N, rotation[][], score, d[];

	public static void main(String[] args) throws IOException {
		input = new int[5][8];

		for(int i=1;i<=4;i++) {
			String str = br.readLine();
			for(int j=0;j<8;j++) {
				input[i][j] = str.charAt(j)-'0'; //톱니바퀴 상태
			}
		}

		N = Integer.parseInt(br.readLine()); //톱니 회전 수
		rotation = new int[N][2]; //회전 바퀴와 방향

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			rotation[i][0] = Integer.parseInt(st.nextToken()); //회전 바퀴
			rotation[i][1] = Integer.parseInt(st.nextToken()); //회전 방향

			//각 톱니바퀴 방향 구하기
			d = new int[5];
			findDel(rotation[i][0], rotation[i][1]);

			//구한 방향에 따라 회전시키기
			move();			
		}
		
		if(input[1][0] == 1) score+=1;
		if(input[2][0] == 1) score+=2;
		if(input[3][0] == 1) score+=4;
		if(input[4][0] == 1) score+=8;
		
		System.out.println(score);
	}	
	private static void findDel(int start, int del)
	{	
		d[start] = del;
		for(int i=start-1;i>=1;i--) {
			if(input[i+1][6] != input[i][2]) //우측에 있는 톱니와 숫자가 다르면
				d[i] = d[i+1]*(-1); //반대 방향으로 전환
			else //숫자 같으면
				break; //그대로 
		}
		for(int i=start+1;i<=4;i++) { 
			if(input[i-1][2] != input[i][6]) //좌측에 있는 톱니와 숫자가 다르면
				d[i] = d[i-1]*(-1); //반대 방향으로 환
			else //숫자 같으면
				break; //그대로
		}
	}
	private static void move() { 		
		for(int i=1;i<=4;i++) {
			
			int[] arr = input[i]; //i 톱니바퀴 
			
			if(d[i] == -1) { //시계 반대 방향인 경우
				int tmp = arr[0];
				for(int j=0;j<7;j++) {
					arr[j] = arr[j+1];
				}
				arr[7] = tmp;
			}
			//주의!!!!!!!!
			else if(d[i] == 1) { //시계 방향인 경우
				int tmp = arr[7];
				for(int j=7;j>=1;j--) {
					arr[j] = arr[j-1];
				}
				arr[0] = tmp;
			}
		}	
	}
}
