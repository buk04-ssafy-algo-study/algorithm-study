import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2668 {

    static StringBuilder sb = new StringBuilder();
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        boolean[] visited = new boolean[n+1];

        for(int i=1;i<=n;i++){
            visited[i] = true;
            dfs(i,i,arr,visited);
            visited[i] = false;
        }
        Collections.sort(list);
        System.out.println(list.size());
        for(int i=0;i<list.size();i++)
            System.out.println(list.get(i));
    }
    private static void dfs(int start, int target, int[] arr, boolean[] visited) {
        if(!visited[arr[start]]){
            visited[arr[start]] = true;
            dfs(arr[start], target, arr, visited);
            visited[arr[start]] = false;
        }

        if(arr[start] == target){
           list.add(target);
           return;
        }
    }
}
