package SSAFY.study.algo.week44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드3_2457_공주님의정원 {

    /*
     * 1. 3월 1일 이전에 피는 꽃 중에서 가장 늦게 지는 꽃을 고른다: 그 날짜를 endDate이라고 기억을 해둠
     * 2. endDate '미만'에 피는 꽃 중에서 가장 늦게 지는 꽃을 고른다: 그 날짜를 endDate이라 갱신...
     *
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] monthToDate = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334}; //3월 1일: 60, 11월 30일 334
        int endDate = 60;
        int minFlower = 0;
        List<ArrayList<Integer>> flowers = new ArrayList<ArrayList<Integer>>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            tempList.add(monthToDate[Integer.parseInt(st.nextToken())] + Integer.parseInt(st.nextToken()));
            tempList.add(monthToDate[Integer.parseInt(st.nextToken())] + Integer.parseInt(st.nextToken()));
            flowers.add(tempList);
        }

        Collections.sort(flowers, (o1, o2)->{
            return Integer.compare(o1.get(0), o2.get(0));
        });

        boolean flag = false; // 현재 index에서 가장 늦게 꽃 피는 날짜(==endDate)와 그 다음 꽃 피는 날짜 사이에 빈 공간이 있으면 false
        int tempMaxEndDate = 0; // endDate보다 같거나 먼저 꽃 피는 날짜들 중 최대로 늦게 꽃 피는 날짜
        for (int i = 0; i < N; i++) {
            if (flowers.get(i).get(0) <= endDate) {
                flag = true; // 하나의 EndDate에 대하여 한 번이라도 이 쪽을 들르면 flag는 true가 됨.
                tempMaxEndDate = Math.max(tempMaxEndDate, flowers.get(i).get(1));
            }

            else {
                if (!flag) break;
                i--;
                endDate = tempMaxEndDate;
                flag = false; // 다시 flag = false로 초기화
                minFlower++;
                if (endDate > 334) {
                    flag = true;
                    break;
                }
            }
        }

        // 끝까지 왔을 때: endDate > 334일 경우는 꽃이 충분해서 바로 minFlower 출력하면 됨.
        if (endDate <= 334) {
            if (tempMaxEndDate > 334) {
                minFlower++;
                flag = true;
            }
            else {
                flag = false;
            }
        }

        System.out.println(flag? minFlower: 0);
    }

}