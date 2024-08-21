import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int height[] = new int[N];
        long res = 0;
        for(int i=0;i<N;i++)
            height[i] = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            int curHeight = height[i];
            for(int j=i+1;j<N;j++){
                if(curHeight<=height[j]) break;
                res++;
            }
        }
        System.out.print(res);
    }
}
