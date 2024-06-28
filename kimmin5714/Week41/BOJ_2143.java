// cnt, ASum, BSum, dupLeft, dupRight : long 사용..
// A 부분 배열 고르는 방법 50만, B 부분 배열 고르는 방법 50만
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        Long T = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++)
            A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++)
            B[i] = Integer.parseInt(st.nextToken());

        long[] ASum = new long[n*(n+1)/2];
        long[] BSum = new long[m*(m+1)/2];

        int idx = 0;
        for(int i=0;i<n;i++){
            int tmp = 0;
            for(int j=i;j<n;j++){
                tmp+=A[j];
                ASum[idx++] = tmp;
            }
        }

        idx = 0;
        for(int i=0;i<m;i++){
            int tmp = 0;
            for(int j=i;j<m;j++){
                tmp+=B[j];
                BSum[idx++] = tmp;
            }
        }

        Arrays.sort(ASum);
        Arrays.sort(BSum);

        int left = 0;
        int right = BSum.length-1;
        long cnt = 0;

        while(left<ASum.length && right>-1) {
            long sum = ASum[left]+BSum[right];

            if(sum<T)
                left++;
            else if(sum>T)
                right--;
            else {
                long dupLeft = 0, dupRight = 0;
                long tmpLeft = ASum[left];
                long tmpRight = BSum[right];

                while (left< ASum.length && ASum[left] == tmpLeft) {
                    dupLeft++;
                    left++;
                }

                while(right> -1 && BSum[right] == tmpRight ) {
                    dupRight++;
                    right--;
                }

                cnt += dupLeft * dupRight;
            }
        }
        System.out.print(cnt);
    }
}
