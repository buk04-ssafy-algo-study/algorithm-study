import java.util.*;
import java.io.*;
public class Main_용돈관리 {
    static int N,M;
    static int[] arr;
    static int max = 0, ans = 0;
    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for(int i =0;i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Integer.max(max, arr[i]);
        }

        int left = max;
        int right = 10000 * 100000;

        while(left <= right) {
            int mid = (left + right) / 2;
            int count = 1;
            int money = mid;

            for (int i : arr) {
                money -= i;
                if (money < 0) {
                    ++count;
                    money = mid - i;
                }
            }
            if(M >= count) {
                ans= mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }
}