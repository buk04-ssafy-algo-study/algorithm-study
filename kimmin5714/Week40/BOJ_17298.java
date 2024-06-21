// 시간 초과 1<=N<=백만 // 백만*백만 = .. 1초 이상

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 1; i <=n ; i++) {
            while(!stack.isEmpty() && arr[stack.peek()] < arr[i])
                arr[stack.pop()] = arr[i];
            stack.push(i);
        }

        while(!stack.isEmpty())
            arr[stack.pop()] = -1;

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++)
            sb.append(arr[i]+" ");

        System.out.println(sb);
    }
}
