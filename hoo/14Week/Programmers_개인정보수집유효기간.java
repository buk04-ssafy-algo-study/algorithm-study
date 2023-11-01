import java.util.*;

class Solution {
    
    static Map<String, Integer> termMap;
    static int nowYear;
    static int nowMonth;
    static int nowDay;
    
    public Integer[] solution(String today, String[] terms, String[] privacies) {
        String[] splittedToday = today.split("\\.");
        nowYear = Integer.parseInt(splittedToday[0]);
        nowMonth = Integer.parseInt(splittedToday[1]);
        nowDay = Integer.parseInt(splittedToday[2]);
        termMap = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            String[] term = terms[i].split(" ");
            termMap.put(term[0], Integer.parseInt(term[1]));
        }
        Integer[] answer = findDestroy(privacies);
        
        return answer;
    }
    
    static boolean calcDate(String startDate, int duration) {
        String calcedDate = "";
        String[] splittedDate = startDate.split("\\.");
        int year = Integer.parseInt(splittedDate[0]);
        int month = Integer.parseInt(splittedDate[1]);
        int day = Integer.parseInt(splittedDate[2]);
        
        month += duration;
        int overredYear = 0;
        if (month % 12 == 0) overredYear = (month / 12) - 1; // 12의 배수일 때
        else overredYear = month / 12;
        year += overredYear;
        month -= overredYear * 12;
        
        if (nowYear > year) return true;
        else if (year == nowYear && nowMonth > month) return true;
        else if (year == nowYear && month == nowMonth && nowDay >= day) return true;
        
        return false;
    }
    
    static Integer[] findDestroy(String[] privacies) {
        List<Integer> destroies = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            String date = privacy[0];
            String term = privacy[1];
            boolean isOverred = calcDate(date, termMap.get(term));
            if (isOverred) destroies.add(i+1);
        }
        Collections.sort(destroies);
        
        return destroies.toArray(new Integer[destroies.size()]);
    }
    
}
