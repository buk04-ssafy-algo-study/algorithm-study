import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_17128_소가정보섬에올라온이유_양윤모 {
    static StringBuilder sb = new StringBuilder();
    static int allMulti;
    static int N;
    static int Q;
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        Q = Integer.parseInt(split[1]);
        split = br.readLine().split(" ");
        int[] arr = new int[N];
        int[] q = new int[Q];
        allMulti = 1;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(split[i]);
            allMulti *= arr[i];
        }
        int[] arrHap = new int[N];
        for (int i = 0; i < N; i++) {
            arrHap[i] = 1;
            for (int j = 0; j < 4; j++) {
                arrHap[i] *= arr[(i+j)%N];
            }
            sum += arrHap[i];
        }
        split = br.readLine().split(" ");
        for (int i = 0; i < Q; i++) {
            q[i] = Integer.parseInt(split[i])-1;
        }
        
        int tmpSum = sum;
        for (int i = 0; i < Q; i++) {
                for (int j = 0; j < 4; j++) {
                    int idx = 0;
                    if(q[i] - j < 0) {
                        int tmp = q[i]-j;
                        idx = N+tmp;
                    }
                    else {
                        idx = q[i]-j;
                    }
                    if(arrHap[idx] > 0) {
                    	tmpSum -= arrHap[idx];
                    }else {
                    	tmpSum += arrHap[idx]*-1;
                    }
                    tmpSum += arrHap[idx] * -1;
                    arrHap[idx] = arrHap[idx] * -1;
                    tmpSum += arrHap[idx];
                }
            sb.append(tmpSum + "\n");
        }
        System.out.println(sb);
    }
}