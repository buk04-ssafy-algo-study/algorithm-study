import java.io.*;
import java.util.*;

public class Main_1107_리모컨 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] broken = new boolean[10]; // 0 ~ 9 까지 10개의 버튼

        if(M!=0) {  // 고장난 버튼의 개수가 0이 아닐 때만 한줄을 더 읽는다.
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int temp = Integer.parseInt(st.nextToken());
                broken[temp] = true;    // 버튼이 고장 났다면 true
            }
        }

        int result = Math.abs(N - 100); // 100번 버튼에서 시작하기 때문에 -100 한 수의 절댓값을 결과로 둔다.
        // 예를 들어 102번이면 + 2번, 98번이면 - 2번을 눌러야하기 때문에 result = 2

        for (int i = 0; i <= 999999; i++) { // 가능한 모든 수를 다 검사한다.
            String num = String.valueOf(i); // string 값으로 변환

            boolean isBreak = false;    // 고장난 버튼을 누르는지 아닌지
            for (int j = 0; j < num.length(); j++) {
                if (broken[num.charAt(j) - '0']) {
                    isBreak = true; // 고장난 버튼을 눌렀다면 true 반환 후 break;
                    break;
                }
            }

            if (!isBreak) { // 고장난 버튼을 누르지 않았다면
                int min = Math.abs(N - i) + num.length();   // target 숫자 - 현재 숫자 + 현재 숫자의 길이
                // target 숫자 - 현재 숫자 = +나 - 버튼을 누르는 횟수
                // 현재 숫자의 길이 = 네자리수면 4번, 세자리수면 3번은 최소 눌러야함
                result = Math.min(min, result);
            }
        }

        System.out.println(result);
    }
}
