#include <stdio.h>
#include <stdlib.h>

typedef struct sc{
	int doc;
	int itv;
}sc;

void merge(struct sc* arr, int p, int q){
	struct sc* tmp;
	int x=0, i=p, j=(p+q)/2 +1;
	int n = q-p+1;
	tmp=(struct sc*)malloc(sizeof(struct sc)*(n));
	
	while(i<=(p+q)/2 && j<=q){
		if(arr[i].doc <arr[j].doc){ //ascending-order
			tmp[x++] = arr[i++];
		} else{	
			tmp[x++] = arr[j++];
		}
	}
	while(i<=(p+q)/2){
			tmp[x++] = arr[i++];
	}
	while(j<=q){
			tmp[x++] = arr[j++];
	}	
	for(i=0;i<n;i++){
		arr[p+i] = tmp[i];	
	}
	
	free(tmp);
}


void msort(struct sc* arr, int p, int q){
	int r = (p+q)/2;
	if(p>=q){
		return;
	}
	msort(arr, p,r);
	msort(arr, r+1,q);
	merge(arr,p,q);	
}



int main(){
    int tcase=0, n=0, cmp=0,ans=0;
    int t=0,i=0;
    struct sc* scores;//<=100000

    scanf("%d",&tcase);

    for(t=0;t<tcase;t++){
    	scanf("%d",&n);
    	scores = (struct sc*)malloc(sizeof(struct sc)*n);
 		
	    for(i=0;i<n;i++){
	        scanf("%d %d",&scores[i].doc, &scores[i].itv);
	    }   	  
	    
	    msort(scores,0,n-1); //sort by doc place(asc) 

		ans=1;
		cmp=scores[0].itv;
	    for(i=1;i<n;i++){
	    	if(scores[i].itv<cmp){
				ans++;
	    		cmp = scores[i].itv;
			}	
		}
		
	    printf("%d\n", ans);
	    free(scores);
	}
    
    return 0;
}
