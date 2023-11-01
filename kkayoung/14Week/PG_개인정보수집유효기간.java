// https://school.programmers.co.kr/learn/courses/30/lessons/150370
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        List<Integer> answer = new ArrayList<>();

        // 약관 조건
        Map<Character, Integer> term = new HashMap<>(); // (약관, 약관 기간)
        for(String t:terms){
            String[] splitedTerm = t.split(" ");
            term.put(splitedTerm[0].charAt(0), Integer.parseInt(splitedTerm[1]));
        }       

        // 개인정보와 약관
        int idx=1;
        for(String privacy:privacies){
            String[] date_term = privacy.split(" "); // 날짜, 약관 분리
            int[] privacy_ymd = convert(date_term[0]);
            char termType = date_term[1].charAt(0); // 약관 종류
            if(destroy(today,calExpiredDate(privacy_ymd, term.get(termType)))){
                answer.add(idx);
            }
            idx++;
        }
    
        return answer.stream().mapToInt(i -> i).toArray(); // Integer list -> int array
    }
    
    public boolean destroy(String today, String expiredDate){
        // 만기 시작 날짜(expiredDate)가 오늘(today)보다 작거나 같으면 파기해야함. return true
        // 그렇지 않으면 return false
        return expiredDate.compareTo(today)<=0;
    }
    public String calExpiredDate(int[] ymd, int addedMonth){ 
        // yyyy.mm.dd로부터 addedMonth 뒤 날짜 계산
        int addedYear = addedMonth/12;
        addedMonth %= 12;
        ymd[1] += addedMonth;
        
        if (ymd[1]>12){
            ymd[1] -= 12;
            ymd[0] += 1;
        }
        ymd[0] += addedYear;
        return String.format("%d.%02d.%02d",ymd[0],ymd[1],ymd[2]);        
    }
    
    public int[] convert(String date){ 
        // "yyyy.mm.dd" -> {yyyy,mm,dd}
        int[] result = new int[3];
        String[] ymd = date.split("\\.");
        result[0] = Integer.parseInt(ymd[0]);
        result[1] = Integer.parseInt(ymd[1]);
        result[2] = Integer.parseInt(ymd[2]);
        return result;
    }
}
