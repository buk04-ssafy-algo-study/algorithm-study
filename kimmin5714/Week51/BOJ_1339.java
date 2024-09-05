import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] ch = new char[N][];
        int[] alpha = new int[26]; // A~Z

        for(int i=0;i<N;i++){
            ch[i] = br.readLine().toCharArray();
            for(int j=0;j<ch[i].length;j++){
                // 알파벳마다 자릿수 계산
                alpha[ch[i][j]-'A'] += Math.pow(10, ch[i].length-1-j);
            }
        }

        Arrays.sort(alpha); // 오름차순 정렬
        int num = 9;
        int res = 0;
        for(int i=alpha.length-1;i>=0;i--) { // 가장 큰 자릿수의 알파벳부터 숫자 부여
            if(alpha[i]==0) break;

            res += alpha[i]*num;
            num--;
        }
        System.out.print(res);
    }
}
