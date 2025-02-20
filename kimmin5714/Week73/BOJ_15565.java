import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        List<Integer> ryanList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] == 1) ryanList.add(i); // 라이언 인덱스를 저장
        }

        int res = 1000000, left = 0, right = K - 1; // K개 이상이어야하므로 K만큼 차이나는 투포인터 설정

        if (ryanList.size() < K) res = -1; // K개가 안되면 불가능
        else {
            while (right < ryanList.size()) { // 투포인터 옮겨가며 최소 집합 크기 갱신
                res = Math.min(res, ryanList.get(right) - ryanList.get(left) + 1);
                left++;
                right++;
            }
        }
        System.out.print(res);
    }
}
