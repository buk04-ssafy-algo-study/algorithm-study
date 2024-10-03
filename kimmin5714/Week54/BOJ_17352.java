import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17352 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        for(int i=1;i<=N;i++)
            parents[i] = i;

        for(int i=0;i<N-2;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 유니온파인드
            int aParent = find(a); // 최상위 노드 찾기
            int bParent = find(b);
            if(aParent != bParent) {
                if (aParent < bParent) { // 작은 값을 더 상위에 저장
                    parents[bParent] = aParent;
                } else {
                    parents[aParent] = bParent;
                }
            }
        }
        int first = find(1); // 첫번째 값의 최상위 부모 저장
        for(int i=2;i<=N;i++){ // 두번째부터 끝까지 원소 탐색해서 부모가 다른 경우에 출력
            if(first != find(i)){
                sb.append(first+" "+i);
                break;
            }
        }
        System.out.print(sb);
    }

    private static int find(int n) {
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }
}