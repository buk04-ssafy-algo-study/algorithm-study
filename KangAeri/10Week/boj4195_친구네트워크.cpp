#include <stdio.h>
#include <string>
#include <map>

using namespace std;

int par[200001];    //사람 수 <= 100000*2
int cnt[200001];    //사람 수 <= 100000*2

int main() {
    int t=0, tcase=0, rel=0, num=0, flag=0;
    int i=0, a=0, b=0; //a,b : 입력받은 사람이 속하는 그룹의 머리 원소 번호
	char p1[21]; //사람이름저장
	char p2[21]; //사람이름저장 
	
    scanf("%d", &t);
    
    for(tcase=0; tcase<t; tcase++){  //for- tcases begin
		
		scanf("%d", &rel);
    	for(i=0;i<=rel*2;i++){  // 사람 수 <= rel*2
		    par[i] =i;  //부모 번호 저장 : 초기에-각 원소는 자기자신 그룹의 머리 & 각 그룹 멤버 1명--> 부모==자기자신 
		    cnt[i]=1;   //각 그룹의 멤버 1명이라 ....
		}
		
    	map<string, int> m; // map사용 
        
    	num=0;  // num : 들어오는 사람한테 줄 번호 (사람최대번호) 저장할 변수 ..근데 0부터
    	for(i=0; i<rel; i++){	//for -relations begin	
    		scanf("%s %s",p1, p2);
    		
    		//p1 : a
    		flag=num; // flag: 새 사람한테 줄 번호 == 맵 원소 개수
    		m.insert({p1, num});  //map insert : 사람 p1 이미 있으면 중복이라 저장안됨 
    		if(flag!=m.size()){ //사람 p1 처음 들어왔으면:
    		    a=num++; //  a <- num(p1 새로 받은 번호) 하고 /num++ 로 다음번호 만들어둠
    		} else{ //사람 p1 들어온적 있으면(map insert 안됐으면):
    		    a=m[p1]; // a<- 맵에 저장해뒀던 p1 번호
    		}
    		
    		//p2 : b
    		flag=num;// flag: 새 사람한테 줄 번호 == 맵 원소 개수
    		m.insert({p2, num}); 
    		if(flag!=m.size()){ //사람 p2처음 들어왔으면 : 
    		   	b=num++; // b<- num 하고 , num++ 로 다음번호 만들어둠
    		}else{ //사람 p1 들어온적 있으면 :
    		    b=m[p2];   //b<- 맵에 저장해뒀던 p2 번호
    		}
    		
    		//////union///
			while(par[a]!=a){ //부모 번호 타고 올라감
				a = par[a]; 
			} // a: a가 속한 그룹의 머리 번호 저장
			while(par[b]!=b){ //부모 번호 타고 올라감
				b = par[b]; 
			} // b: b가 속한 그룹의 머리 번호 저장
			
			
            ///check 2p & print
			if(b>a){ //둘이 다른 그룹 : 한방향으로만 합쳐지는거 막으려고 번호비교함.. if(b!=a)~else{} 가능 
	            par[b] =a; //머리가 b인 그룹을 머리가 a인 그룹으로 합침 
	            cnt[a]+=cnt[b]; //유니온할 때 두 그룹 멤버 수도 합침 
	            printf("%d\n", cnt[a]);
	        } else if(b<a){ // 둘이 다른 그룹 
	            par[a] =b; 
				cnt[b]+=cnt[a];  
				printf("%d\n", cnt[b]);
				
	        } else{ // 이미 같은 그룹인 상황 .. 
	            printf("%d\n", cnt[a]);
	        }
	
		} //end for- rel
	} //end for-tcases
    
    return 0;
}
