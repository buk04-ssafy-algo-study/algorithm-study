#include <stdio.h>
#include <stdlib.h>

void merge(int *A, int p, int q, int r, int* k) {
	int *tmp=(int*)malloc(sizeof(int)*(r+2));
    int i= p;
	int j= q+1;
	int t= 1;
	
    while (i <= q && j <= r) {
        if (A[i] <= A[j]){
			tmp[t++] = A[i++]; 
        } else {
			tmp[t++] = A[j++];
    	}	
	}
    while (i<=q){  // left part left
        tmp[t++] = A[i++];
    }
	while (j<=r){  // right part left
        tmp[t++] = A[j++];
	}
	i=p; t=1;
    while (i<=r){	// save result -> A[p..r]
        A[i++] = tmp[t++];
        (*k)--;
    	if (*k==0){
    		printf("%d", A[i-1]);
		}
	}
	free(tmp);
}


void merge_sort(int* A, int p, int r, int* k) {  // sort A[p..r] 
    int q;
	if (p < r) {
        q = (p + r) / 2; 
        merge_sort(A, p, q, k);    
        merge_sort(A, q + 1, r, k); 
        merge(A, p, q, r, k);        //merge
    }
}

int main(){
	int *arr;
	int i; int n; int k;
	scanf("%d %d ", &n, &k);
	arr = (int *)malloc(sizeof(int)*n);
	
	for (i=0;i<n;i++){
		scanf("%d", &arr[i]);
	}
	merge_sort(arr, 0, n-1, &k);

	if (k>0){
		printf("-1");
	}
	free(arr);
	
	return 0;
}
