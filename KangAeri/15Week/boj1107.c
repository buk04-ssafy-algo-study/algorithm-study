#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

char tar[10];
int channel[10];

int main(){
	int i=0,j=0,n=0, brk=0, tarnum=0, ans=0,min=0;
	char tmp[10];
	char tp[2];
	for(i=0;i<10;i++){
		channel[i]=1;
	}
	scanf("%s",tar);
	tarnum=atoi(tar);
	
	scanf("%d", &brk);
	
	for(i=0; i<brk; i++){
		scanf("%d",&j);
		channel[j]=0;
	}
	///////////////
	min=abs(tarnum-100);
	if(brk<10){
		for(i=0; i<=999900; i++){
			snprintf(tmp, 10, "%d",i);
			for(j=0; j<strlen(tmp); j++){	
				if(channel[tmp[j]-48]!=1){
					break;
				}
			}
			if(j>=strlen(tmp)){
				ans=strlen(tmp) + abs(tarnum-i);
				if(ans<min){
					min=ans;
				}
			}
		}

	}
	printf("%d",min);
	
	return 0;
}