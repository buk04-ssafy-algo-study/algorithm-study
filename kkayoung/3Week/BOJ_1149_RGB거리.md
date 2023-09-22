### 문제  
[백준 1149번: RGB거리](https://www.acmicpc.net/problem/1149)  

### 풀이  
인접한 두 집의 색깔이 다르다.  

예제 입력 
```
3
26 40 83
49 60 57
13 89 99
```

1번 집을 R, G, B로 칠하는 데 각 비용은 26, 40, 83이다.  


1번 집을 칠하고 2번 집을 R,G,B로 칠하는 데 필요한 비용은  
```
3
26 40 83
49+min(40, 83) 60+min(26, 83) 57+min(26, 40)
= 89           = 86           = 83
```

1, 2번 집을 칠하고 3번 집을 R, G, B로 칠하는 데 필요한 비용은
```
3
26 40 83
89 86 83
13+min(86, 83) 89+min(89, 83) 99+min(89, 86)
= 96           = 172          = 185
```
N-1번째 행에서 최소값인 96이 정답이다. 1번집에서 R, 2번 집 G, 3번 집 R로 칠하려면 96의 비용이 필요하다.

즉 i행, j열에는 ((현재 집을 칠하는 비용) + (이전 집을 다른 색으로 칠하는 비용))의 최소값이 저장된다.  

### 코드  
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 입력받기
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3]; // R, G, B로 칠했을 때 비용
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
				// 비용 계산
        for(int i=1;i<N;i++){
            for(int j=0;j<3;j++){
                cost[i][j] += Math.min(cost[i-1][(j+1)%3], cost[i-1][(j+2)%3]);
            }
        }
        // N-1행의 최소값 출력
        int answer = Arrays.stream(cost[N-1]).min().getAsInt();
        bw.write(String.valueOf(answer)+"\n");
        bw.flush();
        bw.close();
    }
}
