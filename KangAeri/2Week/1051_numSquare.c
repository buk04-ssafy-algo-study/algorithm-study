#include <stdio.h>
#include <stdlib.h>


int main() {
	int n,m,l,i=0,j=0,k=0;
	char chk;
	int max=0,flag=0;
    char** arr;
    scanf("%d %d", &n, &m);
    arr = (char**)malloc(sizeof(char*)*n);
    
    for(i=0;i<n;i++){ //init
    	arr[i] = (char*)malloc(sizeof(char)*(m+1));
		scanf("%s", arr[i]);
	}
	
	if(m<n){
    	l=m;
	} else{
		l=n;
	}
	flag=0;
	for(k=l;k>=1;k--){	
		if(flag==1){
			break;
		}
		for(i=0;i<n;i++){
			if((i+k-1>=n) || (flag==1)){
				break;
			}
	    	for(j=0;j<m;j++){
	    		if((j+k-1>=m) || (flag==1)){
	    			break;
				}
	    		chk=arr[i][j];
				if((i+k-1<n) && (j+k-1 <m) && arr[i+k-1][j]==chk && arr[i+k-1][j+k-1]==chk && arr[i][j+k-1]==chk) {
					max = k*k;
					flag=1;
					break;
				}
			}
		}
	}
	
	printf("%d",max);
	for(i=0;i<n;i++){ //free mem
    	free(arr[i]);
	}
	free(arr);
	
	
	return 0;
}
