package Week48;

import java.util.*;
import java.io.*;

public class 백준_1092_배 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int result = 0;
		
		int N = Integer.parseInt(br.readLine());
	    List<Integer> crain = new ArrayList<>();
	    
		st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	crain.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(crain, Collections.reverseOrder());
        
        int M = Integer.parseInt(br.readLine());
        List<Integer> box = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
        	box.add(Integer.parseInt(st.nextToken()));
        }
        
        Collections.sort(box, Collections.reverseOrder());
        
        while(true) {
            if(box.size()==0) break;
            
            if(crain.get(0)<box.get(0)) { 
                result = -1;
                break;
            }

            for (int i = 0; i < crain.size(); i++) {
                for (int j = 0; j < box.size(); j++) {

                	if (box.get(j) <= crain.get(i)) { 
                        box.remove(j); 
                        break; 
                    }
                }
            }
            result++;
        }
        System.out.print(result);
	}
}
