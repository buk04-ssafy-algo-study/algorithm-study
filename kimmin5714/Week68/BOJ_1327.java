import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Num {
    String str;
    int depth;

    public Num(String str, int depth) {
        this.str = str;
        this.depth = depth;
    }
}

public class BOJ_1327 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String input = "";
        for (int i = 0; i < n; i++)
            input += st.nextToken();

        char[] ch = input.toCharArray();
        Arrays.sort(ch); // 오름차순 결괏값 저장해두기
        String ascInput = String.valueOf(ch);

        boolean[] visited = new boolean[99_999_999]; // 만들었던 문자열인지 체크
        Queue<Num> q = new ArrayDeque<>();
        q.offer(new Num(input, 0));
        visited[Integer.parseInt(input)] = true;

        int res = 0;
        boolean correct = false;
        while (!q.isEmpty()) {
            Num curNum = q.poll();
            String curStr = curNum.str;
            int curDepth = curNum.depth;
            
            String newStr = ""; 
            if(curStr.equals(ascInput)) {
                correct = true;
                break;
            }

            for (int i = 0; i <= curStr.length() - k; i++) { // 앞에서부터 k개씩 뒤집어서 새로운 문자열 만들기
                String firstStr = curStr.substring(0, i);
                String secondStr = curStr.substring(i, i + k);
                String reverseSecStr = "";
                for (int j = secondStr.length() - 1; j >= 0; j--) // k개 뒤집기
                    reverseSecStr += secondStr.charAt(j);
                String thirdStr = curStr.substring(i + k, curStr.length());

                newStr = firstStr + reverseSecStr + thirdStr;

                if(newStr.equals(ascInput)) { // 새롭게 만들어진 문자열이 오름차순인 경우
                    res = curDepth+1;
                    correct = true;
                    break;
                }
                if(visited[Integer.parseInt(newStr)]) continue; // 이미 만들었던 문자열인 경우
                q.offer(new Num(newStr, curDepth+1));
                visited[Integer.parseInt(newStr)] = true;
            }
            if(correct) break;
        }
        if(correct)
            System.out.print(res);
        else // 오름차순이 되지 않는 경우
            System.out.print(-1);
    }
}