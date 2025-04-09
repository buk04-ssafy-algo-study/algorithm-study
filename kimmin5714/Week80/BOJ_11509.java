import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[1000000+1]; // 인덱스 높이에 있는 화살 수

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            int h = Integer.parseInt(st.nextToken());

            if(arr[h]>0) arr[h]--; // 화살이 남아있으면 사용

            arr[h-1]++; // 1 줄어든 높이에 화살 추가
        }

        int res = 0;
        for(int i=0;i<arr.length;i++) { // 남은 화살 수 = 사용한 화살 수
            res += arr[i];
        }
        System.out.println(res);
    }
}
