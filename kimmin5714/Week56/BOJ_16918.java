import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char[][] arr = new char[r][c];

        for (int i = 0; i < r; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < c; j++)
                arr[i][j] = tmp[j];
        }

        int time = 1; // 맨 처음 폭탄 설치 후, 1초
        int[] delr = {-1,1,0,0};
        int[] delc = {0,0,-1,1};

        while (time < n) { // n초가 될 때까지 반복

            // 3초 전 상태 임시 저장
            char[][] tmp = new char[r][c];
            for (int i = 0; i < r; i++)
                tmp[i] = Arrays.copyOf(arr[i], c);

            // 모두 폭탄으로 채워짐
            for(int i=0;i<r;i++)
                Arrays.fill(arr[i], 'O');
            time++;
            if(time>=n) break;

            // 4방으로 폭탄 터짐
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (tmp[i][j]=='O') {
                        arr[i][j] = '.';
                        for(int k=0;k<4;k++) {
                            int nr = i+delr[k];
                            int nc = j+delc[k];
                            if(nr<0 || nr>= r || nc<0 || nc>= c) continue;
                            arr[nr][nc] = '.';
                        }
                    }
                }
            }
            time++;
        }
        
        // 출력
        for(int i=0;i<r;i++){
            sb.append(Arrays.copyOf(arr[i],c));
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
