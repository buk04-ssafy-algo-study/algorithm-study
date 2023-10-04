#include <stdio.h>
#include <string>
#include <map>
using namespace std;

char person[200001][21];
int par[200001];
int cnt[200001];

int main() {
    int t=0, n=0,f=0, rel=0, tcase=0;
	int a=0,b=0,i=0,j=0, flag=0;
	int tmp;
	char p1[21];
	char p2[21];

    scanf("%d", &t);
    for(tcase=0; tcase<t; tcase++){   	
		scanf("%d", &rel);
    	for(i=0;i<=rel*2;i++){
		    par[i] =i;
		    cnt[i]=1;
		}
		
    	map<string, int> m;
    	n=0;
    	for(f=0; f<rel; f++){		
    		scanf("%s %s",p1, p2);
    		flag=n;
    		m.insert({p1, n});
    		if(flag!=m.size()){
    		    a=n++;
    		} else{
    		    a=m[p1];
    		}
    		flag=n;
    		m.insert({p2, n});
    		if(flag!=m.size()){
    		   	b=n++;
    		}else{
    		    b=m[p2];
    		}
			while(par[a]!=a){
				a = par[a];
			}
			while(par[b]!=b){
				b = par[b];
			}
			if(b>a){
	            par[b] =a;
	            cnt[a]+=cnt[b];
	            printf("%d\n", cnt[a]);
	        } else if(b<a){
	            par[a] =b; 
				cnt[b]+=cnt[a];  
				printf("%d\n", cnt[b]);
	        } else{
	            printf("%d\n", cnt[a]);
	        }
	
		}
	}
    
    return 0;
}
