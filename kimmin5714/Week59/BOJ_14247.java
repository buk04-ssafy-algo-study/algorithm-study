import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Tree implements Comparable<Tree>{
    int height, growth;

    public Tree() {
    }

    @Override
    public int compareTo(Tree o) {
        return this.growth - o.growth;
    }
}
public class BOJ_14247 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Tree[] trees = new Tree[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees[i] = new Tree();
            trees[i].height = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            trees[i].growth = Integer.parseInt(st.nextToken());

        Arrays.sort(trees); // 기존 높이랑 성장 높이 차가 가장 큰 순서로 정렬
        long sum = 0;
        for(int i=0;i<n;i++)
            sum += trees[i].height + trees[i].growth*i; // 기존 높이 + i일 뒤 성장한 높이

        System.out.print(sum);
    }
}