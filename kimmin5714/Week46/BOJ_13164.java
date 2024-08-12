import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int num[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            num[i] = Integer.parseInt(st.nextToken());

        int res = 0;
        List<Integer> diff = new ArrayList<>();

        for(int i=0;i<N-1;i++)
            diff.add(num[i+1]-num[i]); // 인접한 원생 키 차이 구하기

        Collections.sort(diff); // 오름차순 정렬

        for(int i=0;i<N-K;i++) // K-1개의 구분선 필요 : (N-1)-(K-1)
            res+=diff.get(i);

        System.out.println(res);
    }
}
