package Week54;

import java.util.*;
import java.io.*;


class Meat implements Comparable<Meat> {
    int weight;
    int price;

    public Meat(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int compareTo(final Meat o) {
        if (this.price == o.price)
            return o.weight - this.weight;
        return this.price - o.price;
    }
}
 
    public class 백준_2258_정육점 {
		    public static void main(String[] args) throws Exception {
		    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		        StringTokenizer st = new StringTokenizer(br.readLine());
		        
		        int n = Integer.parseInt(st.nextToken());
		        int m = Integer.parseInt(st.nextToken());

		        Meat[] arr = new Meat[n];
		        int total = 0;
		        for (int i = 0; i < n; i++) {
		            st = new StringTokenizer(br.readLine());
		            int weight = Integer.parseInt(st.nextToken());
		            int price = Integer.parseInt(st.nextToken());
		            arr[i] = new Meat(weight, price);
		            total += weight;
		        }
		        if (total < m) {
		            System.out.println(-1);
		            return;
		        }


		        int bfPrice = 0;
		        int weightSum = 0;
		        int priceSum = 0;
		        int min = Integer.MAX_VALUE;
		        for (Meat cur : arr) {
		            weightSum += cur.weight;

		            if (bfPrice != cur.price) {
		                priceSum = bfPrice = cur.price;
		            } else {
		                priceSum += cur.price;
		            }

		            if (weightSum >= m) {
		                min = Math.min(min, priceSum);
		            }
		        }

		        System.out.println(min);
		    }
		}
