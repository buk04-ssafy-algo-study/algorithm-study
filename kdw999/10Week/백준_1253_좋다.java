package Week10;

import java.util.*;
import java.io.*;

public class 백준_1253_좋다 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0 ; i < N ; i++) num[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(num);
        
        int cnt = 0;
        for(int i = 0 ; i < N ; i++){
            int left = 0;
            int right = N-1;
            
            while(true){
            	
                if(left == i) left++;
                else if(right == i) right--;

                if(left >= right) break;

                // 두 수의 합이 더 크다면 right값을 줄여서 더 작은 수와 더해서 현재(i) 인덱스에 있는 값을 탐색해야됨 
                // 반대의 경우엔 left값을 늘려야 됨
                if(num[left] + num[right] > num[i]) right--;
                else if(num[left] + num[right] < num[i]) left++;
                else{      
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }

}