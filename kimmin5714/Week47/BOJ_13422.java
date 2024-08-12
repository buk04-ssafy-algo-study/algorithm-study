import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13422 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 집 수
            int M = Integer.parseInt(st.nextToken()); // 훔칠집 수
            int K = Integer.parseInt(st.nextToken()); // 방범 장치 작동
            int[] money = new int[N]; // 집에 있는 돈

            long getMoney = 0;

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                money[i] = Integer.parseInt(st.nextToken());
                if(i<M) getMoney+=money[i]; // 맨 처음 M개 집 훔쳤을 때
            }

            int left = 0, right=M-1; // 초기 인덱스
            int res = 0;

            while(left<N) {
                if(getMoney<K)
                    res++;
                if(N==M) break;
                if(right+1>=N) right = -1; // 집이 원형이므로 인덱스 조절

                // 슬라이딩 윈도우
                getMoney-=money[left++];
                getMoney+=money[++right];
            }

            sb.append(res+"\n");
        }
        System.out.print(sb);
    }
}
