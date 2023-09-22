#include <stdio.h>
#include <stdlib.h>
#define MIN(a,b) (((a)<(b))? (a):(b))

int main() {
    int n=0,m=0,r=0,key=0;
    int i=0,j=0,oi=0,oj=0;
	int k=0,p=0, tmp=0;
    int** arr;
    
    scanf("%d %d %d", &n, &m, &r);
    key=MIN(n,m);
	key/=2;
	//mem alloc
    arr = (int**)malloc(sizeof(int*)*n);
    for(i=0;i<n;i++){
        arr[i] = (int*)malloc(sizeof(int)*m);
    }  
    //init
    for(i=0;i<n;i++){
        for(j=0;j<m;j++){
            scanf("%d", &arr[i][j]);
        }
    }
    while(r>0){	
	    oi=0;oj=0;
	    for(k=0;k<key;k++){
	    	tmp=arr[oi][oj];
			i=oi;
			j=oj+1;
			while(!(oi==i && oj==j)){
				for(p=1;p<m-2*k;p++){
					arr[i][j-1]=arr[i][j];
					j++;
				}
				j--;
				i++;
				for(p=1;p<n-2*k;p++){
					arr[i-1][j]=arr[i][j];
					i++;
				}
				i--;
				j--;
				for(p=1;p<m-2*k;p++){
					arr[i][j+1]=arr[i][j];
					j--;
				}
				j++;
				i--;
				for(p=1;p<n-2*k-1;p++){
					arr[i+1][j]=arr[i][j];
					i--;
				}
			}
			arr[oi+1][oj]=tmp;
			oi++;
			oj++;
	    }
	    r--;
    }
    
    
   for(i=0;i<n;i++){
        for(j=0;j<m;j++){
            printf("%d ", arr[i][j]);
        }
        printf("\n");
    }
    
    for(i=0;i<n;i++){
        free(arr[i]);
    }
    free(arr);

    return 0;
}
