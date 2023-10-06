/* https://www.acmicpc.net/problem/9095
 * dp
 * 1. 각 숫자 만드는 경우의 수 저장하는 배열 이용 
 * 
 * 2. 1,2,3으로 1,2,3을 만드는 경우의 수
 * 	1 = {1} --> 1가지
 * 	2 = {1+1, 2}  --> 2가지
 *  3 = {1+1+1, 1+2, 3}  --> 3가지
 *  
 * 3. 4 이후에는 (3만드는 경우의 수) + (2만드는 경우의 수) + (1만드는 경우의 수)
 *  4 = {1+3} --> 1만드는 경우에 +3 추가(1가지)
 * 	4 = {1+1+2, 2+2}  --> 2만드는 경우에 +2 추가(2가지)
 *  4 = {1+1+1+1, 1+2+1, 2+1+1, 3+1}  --> 3만드는 경우에 +1 추가(3가지)
 *  	총 1+2+3 = 6가지
 * 	5 = (4만드는 경우의 수) + (3만드는 경우의 수) + (2만드는 경우의 수)
 *  6 = (5만드는 경우의 수) + (4만드는 경우의 수) + (3만드는 경우의 수)
 *  ...
 *  n = (n-1 만드는 경우의 수) + (n-2 만드는 경우의 수) + (n-3 만드는 경우의 수)
 *  
 * 4. 배열의 n인덱스 값 출력
 * */
import java.util.Scanner;

public class Main_BOJ9095_123더하기 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();
		int[] array = new int[11];

		array[0] = 0;
		array[1] = 1;
		array[2] = 2;
		array[3] = 4;

		for(int i = 0; i < num; i++) {
			int n = sc.nextInt();
			for(int j = 4; j <= n; j++) {
				array[j] = array[j - 1] + array[j - 2] + array[j - 3];
			}
			System.out.println(array[n]);
		}
	}

}
