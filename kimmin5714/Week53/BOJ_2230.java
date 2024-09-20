import java.awt.font.LineMetrics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 오름차순 정렬

        int left = 0;
        int right = 0;
        int minDiff = Integer.MAX_VALUE;

        while(right<N) {
            int diff = arr[right] - arr[left];
            if(diff>=M) { // 차이가 M 이상인 경우
                minDiff = Math.min(minDiff, diff);
                left++; // left 포인터 이동
                if(left>right) right = left; // left가 right 보다 커지면 안됨
            }
            else { // 차이가 M 미만인 경우
                right++; // right 포인터 이동
            }
        }

        System.out.print(minDiff);
    }
}
