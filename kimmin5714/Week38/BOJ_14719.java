import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] arr = new int[H][W];

        st = new StringTokenizer(br.readLine());
        int[] height = new int[W];

        for(int i=0;i<W;i++)
            height[i] = Integer.parseInt(st.nextToken());

        for(int i=0;i<W;i++){
            int h = height[i];
            for(int j=0;j<h;j++)
                arr[j][i] = 1;
        }
        int water = 0;
        for(int i=0;i<H;i++){
            List<Integer> list = new ArrayList<>();
            for(int j=0;j<W;j++)
                if(arr[i][j] == 1) list.add(j);

            if(list.size() <= 1) continue;
            if(list.size() == W) continue;

            for(int j=0;j<list.size()-1;j++){
                int cur = list.get(j);
                int next = list.get(j+1);

                if(next-cur == 1) continue;

                water += (next-cur-1);
            }
        }
        System.out.print(water);
    }
}
