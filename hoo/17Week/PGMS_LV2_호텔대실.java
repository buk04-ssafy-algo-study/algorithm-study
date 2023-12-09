package SSAFY.study.algo.week17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PGMS_LV2_호텔대실 {

    class Solution {

        int usedRoomCount;  // 사용한 방의 개수 세는 변수
        List<String[]> usingRooms;

        public int solution(String[][] book_time) {
            int answer = 0;
            usedRoomCount = 0;
            usingRooms = new ArrayList<>();
            Arrays.sort(book_time, new Comparator<String[]>() { // 일단 방들 대실 시작시간 기준 오름차순 정렬

                @Override
                public int compare(String[] o1, String[] o2) {
                    return o1[0].compareTo(o2[0]);
                }
            });
            if (book_time.length == 1) return 1;
            usingRooms.add(book_time[0]);
            usedRoomCount += 1; // 맨 처음에는 방 하나를 사용했으므로 카운트 +1
            countRooms(book_time);
            answer = usedRoomCount;

            return answer;
        }

        String calcCleanEndTime(String endTime) {  // 방청소가 끝난 시간 구하는 함수
            String cleanEndTime;
            String endHour = endTime.substring(0, 2);
            String endMinute = endTime.substring(3);
            int cleanEndHour = Integer.parseInt(endHour);
            int cleanEndMinute = Integer.parseInt(endMinute) + 10;
            if (cleanEndMinute >= 60) {
                cleanEndHour += 1;
                cleanEndMinute -= 60;
            }
            String cleanEndHourStr = String.valueOf(cleanEndHour);
            String cleanEndMinuteStr = String.valueOf(cleanEndMinute);
            if (cleanEndHour < 10) cleanEndHourStr = "0" + cleanEndHourStr;
            if (cleanEndMinute < 10) cleanEndMinuteStr = "0" + cleanEndMinuteStr;
            cleanEndTime = cleanEndHourStr + ":" + cleanEndMinuteStr;

            return cleanEndTime;
        }

        void countRooms(String[][] book_time) {

            for (int i = 1; i < book_time.length; i++) {    // 두 번째 방부터 방 개수 카운팅 시작
                boolean canEnterFlag = false;   // 사용 중이었던 방 중 입실 가능한 방이 있는지 체크하는 변수
                int canEnterIdx = -1;   // 입실 가능한 방의 인덱스 저장하는 변수

                for (int j = 0; j < usingRooms.size(); j++) {   // 사용 중인 방들에 대해 사용시간 끝났는지 탐색
                    String roomEndTime = usingRooms.get(j)[1];
                    String cleanEndTime = calcCleanEndTime(roomEndTime);
                    if (book_time[i][0].compareTo(cleanEndTime) < 0) continue;  // 청소 끝나서 사용할 수 있는 방이 없으면 다음 방 탐색
                    else {
                        canEnterFlag = true;
                        canEnterIdx = j;
                        break;
                    }
                }
                if (canEnterFlag) { // 입실 가능한 방이 있으면 그 방으로 입실
                    usingRooms.set(canEnterIdx, book_time[i]);
                } else {    // 입실 가능한 방이 없으면 새로운 방으로 들여보냄
                    usingRooms.add(book_time[i]);
                    usedRoomCount += 1;
                }
            }
        }
    }

}
