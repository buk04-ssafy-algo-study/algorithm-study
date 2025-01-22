import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_23843 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if(N < M) M = N; // 콘센트가 더 많은 경우에 갯수 조정

        int[] power = new int[M];
        int[] time = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time); // 오래 걸리는 것부터 충전하기 위한 정렬

        int idx = 0;
        int lastIdx = -1;
        for(int i=N-1;i>N-1-M;i--) { //오래 걸리는 전자기기 충전
            power[idx++] = time[i];
            lastIdx = i;
        }
        lastIdx--;

        int res = 1; // 걸리는 시간 카운트
        boolean[] isCharged = new boolean[M];

        while(true) {
            for(int i=0;i<M;i++) {
                power[i]--; // 1시간에 1만큼 충전

                if(power[i] == 0){ // 콘센트가 비고 충전할 기기가 없는 경우 
                    if(lastIdx == -1) { 
                        isCharged[i] = true;
                        continue;
                    }
                    power[i] = time[lastIdx--]; // 콘센트가 비면 다음 전자기기 충전
                }
            }
            if(allCharged(isCharged)) break; // 모든 충전이 끝나면 종료
            res++;
        }

        System.out.print(res);
    }

    private static boolean allCharged(boolean[] isCharged) {
        for(boolean b : isCharged){
            if(!b) return false;
        }
        return true;
    }
}
