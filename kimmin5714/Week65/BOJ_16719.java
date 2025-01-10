import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16719 {
    private static StringBuilder sb;
    private static String str;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        str = br.readLine();
        visited = new boolean[str.length()]; // 추가한 숫자인지 구분

        dfs(0, str.length()-1);
        System.out.print(sb);
    }

    private static void dfs(int left, int right) {
        if(left > right) return;

        int minIdx = left;
        for(int i=left;i<=right;i++) { // 범위 내에 가장 작은 문자 찾기
            if(str.charAt(minIdx) > str.charAt(i) && !visited[i]) {
                minIdx = i;
            }
        }
        visited[minIdx] = true;
        for(int i=0;i<str.length();i++) { // 가장 작은 문자 추가하여 문자열 만들기
            if(visited[i]) {
                sb.append(str.charAt(i));
            }
        }
        sb.append("\n");

        // 사전순
        dfs(minIdx+1, right);
        dfs(left, minIdx-1);
    }
}