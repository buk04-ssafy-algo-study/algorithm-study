import java.util.*;
import java.io.*;


public class Main {
  static int N;
  static long B, C, cnt=0;
  static long[] arr;
  public static void main(String[] args) throws Exception {
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   N = Integer.parseInt(br.readLine());
   arr = new long[N];
   StringTokenizer st;
  st = new StringTokenizer(br.readLine());
   for(int i=0;i<N;i++) {
    arr[i] = Long.parseLong(st.nextToken());
   }
  st = new StringTokenizer(br.readLine());
    B = Long.parseLong(st.nextToken());
    C = Long.parseLong(st.nextToken());

  for(int i=0; i< N;i++) {

    if(arr[i] <= B) {
      cnt++;
    }else {
      cnt++;
      arr[i] -= B;
      if(arr[i] % C == 0) {
        cnt += arr[i] /C;
      }else {
        cnt += arr[i] / C + 1;
      }
    }
  }
    System.out.println(cnt);
  }
}
