import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_19942 {
    static int N;
    static int arr[][]; // 들어오는 영양정보 저장할 배열
    static  int result = Integer.MAX_VALUE; //최소 가격 저장할 변수
    static int min_nutrution[]; // 단백질, 지방, 탄수화물, 비타민의 최소 영양성분
    static int isSelected[]; //나중에 dfs 통해서 무엇을 선택한 지 저장할 배열
    static ArrayList<String> list; //뭐 선택했는지 저장할 list
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N+1][5];
        min_nutrution = new int[4];
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<4;i++){
            min_nutrution[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //한개 고르는 것 부터 N개 모두 다 고르는 것 까지 고려
        for(int i=1;i<=N;i++){
            isSelected = new int[N];
            dfs(0,i,1);
        }

        if(result == Integer.MAX_VALUE) System.out.println(-1);
        else{
            Collections.sort(list);
            System.out.println(result);
            System.out.println(list.get(0));

        }

    }

    static void dfs(int cnt,int choiceNum,int start){
        if(cnt == choiceNum){
            isCheck(choiceNum);
            return;
        }
        
        for(int i=start;i<=N;i++){
            isSelected[cnt] = i; //현재 선택한 번호 넣어주고
            dfs(cnt+1,choiceNum,i+1); //다시 탐색
        }
    }

    static void isCheck(int choiceNum){
    
        int sum_nutrition[] = new int[4]; //최소 영양정보의 합 담을 배열
        int price = 0; //현재 선택한 영양정보의 가격 담을 변수
        
        for(int i=0;i<choiceNum;i++){
            for(int j=0;j<4;j++){
                sum_nutrition[j] += arr[isSelected[i]][j];
            }
            price += arr[isSelected[i]][4];
        } //현재 선택한 정보의 영양 정보 들어가 있다.


        for(int i=0;i<4;i++)
            if(min_nutrution[i] > sum_nutrition[i]) return ; //최소비용 만족 못할 시 return
            
            if(result >= price){
                //이전에 선택한 가격 보다 지금 선택한 것이 더 싸다면
                //갱신해주기
                if(result > price) list.clear();
                StringBuilder sb = new StringBuilder();
                for(int i=0;i<choiceNum;i++){
                    sb.append(isSelected[i]+" ");
                }

                list.add(sb.toString());
                result = price;
            }


    }
}