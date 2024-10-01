import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Meat implements Comparator<Meat> {
    int weight;
    int price;

    public Meat(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public Meat() {
    }

    @Override
    public String toString() {
        return "Meat{" +
                "weight=" + weight +
                ", price=" + price +
                '}';
    }

    @Override
    public int compare(Meat o1, Meat o2) {
        // 가격을 오름차순으로 정렬하되, 가격이 같은 경우에는 무게 내림차순으로 정렬
        if(o1.price == o2.price){
            return o2.weight - o1.weight;
        }
        else {
            return o1.price-o2.price;
        }
    }
}
public class BOJ_2258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Meat> meatList = new ArrayList<>();

        int allWeight = 0;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            meatList.add(new Meat(weight, price));
            allWeight += weight;
        }

        if(allWeight < M) { // 모든 고기 무게 합이 M보다 작은 경우 (불가능)
            System.out.print(-1);
            return;
        }
        Collections.sort(meatList, new Meat()); // 가격 오름차순, 무게 내림차순 정렬

        long weightSum = 0;
        long priceSum = 0;
        long prePrice = 0; // 가격이 달라졌는지 확인하기 위한 이전 가격
        long minPrice = Long.MAX_VALUE;
        for(int i=0;i< meatList.size();i++) {
            Meat meat = meatList.get(i);
            weightSum += meat.weight; // 가격 오름차순 정렬 되어있으므로 무게는 계속 더해줌
            if(prePrice < meat.price) { // 가격이 달라진 경우 (추가 비용x)
                prePrice = meat.price; // 이전 가격 초기화
                priceSum = meat.price; // 가격 합 초기화
            }
            else { // 가격이 같은 경우
                priceSum += meat.price; // 가격 더함
            }
            if(weightSum >= M){ // 무게 합이 M이상인 경우
                minPrice = Math.min(minPrice, priceSum); // 최소가격 갱신
            }
        }
        System.out.print(minPrice);
    }
}
