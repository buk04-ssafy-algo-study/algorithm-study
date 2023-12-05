#include <stdio.h>
#include <stdlib.h>

int main(){
	int i=0,j=0,tcase=0,t=0,first=0,second=0;
	int ans=0, m=0,n=0,x=0,y=0;
	scanf("%d",&t);
	
	for(tcase=0;tcase<t;tcase++){
		i=0;
		ans=-1;
		first=1;
		second=1;
		scanf("%d %d %d %d",&m,&n,&x,&y);
		for(i=x; i<=m*n; i+=m){
			if(x==((i-1)%m+1) && y==((i-1)%n+1) ){
				ans=i;
				break;
			}
		}	
		printf("%d\n",ans);	

	}
	return 0;
}
