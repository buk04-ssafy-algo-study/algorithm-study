import java.util.*;
import java.io.*;

public class Main_거짓말{
    static int N,M;
    static int[] parent;
    static int parentTrue; // 진실을 아는 집합
    static int ans = 0;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N  + 1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }

        parentTrue = 0;
        st = new StringTokenizer(br.readLine());
        int participants = Integer.parseInt(st.nextToken());
        int[] truePeople = new int[participants];
        for(int i=0;i<participants;i++) {
            truePeople[i] = Integer.parseInt(st.nextToken());
        }
        for(int p = 0; p <participants-1;p++){
               union(truePeople[p],truePeople[p+1]);
           }
        if(truePeople.length!=0){
            parentTrue=truePeople[0];
        }


        int [][] party = new int[M][];

        for(int i = 0 ; i <M ;i++){ 
               st = new StringTokenizer(br.readLine());
               int p = Integer.parseInt(st.nextToken());

               party[i] = new int[p]; 

               for(int j = 0; j < p ; j++){
                   party[i][j]=Integer.parseInt(st.nextToken());
               }

               for(int j = 0; j < p-1 ; j++){
                  union(party[i][j],party[i][j+1]);
               }

           }


        for(int i=0; i< M; i++) {
            boolean flag = false;
            for(int j=0; j < party[i].length; j++) {
                if(find(party[i][j]) == parentTrue) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                ans++;
            }
        }
        System.out.println(ans);
    }
    static int find(int a){
        //자기 자신이 부모면 자기를 리턴
        if(parent[a]==a){
            return a;
        }

        return parent[a]= find(parent[a]);
    }
    static void union(int a,int b){

        a= find(a);
        b= find(b);

        if(a!=b){ 
            if(a==parentTrue){
                parent[b]=a;
            }else if(b==parentTrue) { 
                parent[a]=b; 
            }else {
                parent[b]=a;
            }
        }
    }
}