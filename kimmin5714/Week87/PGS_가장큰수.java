import java.util.Arrays;

public class PGS_가장큰수 {
    public String solution(int[] numbers) {
        String answer = "";

        String[] str = new String[numbers.length];

        // String으로 바꾸기
        for (int i = 0; i < numbers.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }

        // 두 문자열 붙혔을 때 더 큰 값 만들 수 있는 수로 정렬
        Arrays.sort(str, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        if (str[0].equals("0")) // 제일 큰 수가 0이면 0반환
            answer += "0";
        else
            for (int i = 0; i < str.length; i++)
                answer += str[i];

        return answer;
    }
}
