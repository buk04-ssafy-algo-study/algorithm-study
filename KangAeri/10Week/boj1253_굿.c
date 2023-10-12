#include <stdio.h>
#include <stdlib.h>

int n;
int arr[2001];

void merge(int p, int q){
	int r = (p+q)/2, i=0, x=p, y=r+1;
	int* tmp= (int*)malloc(sizeof(int)*(q-p+1));
	while(x<=r && y<=q){
		if(arr[x]<arr[y]){
			tmp[i++] =arr[x++]; 
		} else{
			tmp[i++] = arr[y++];
		}
	}
	while(x<=r){
		tmp[i++] =arr[x++]; 
	}
	
	while(y<=q){
		tmp[i++] = arr[y++];
	}
	for(i=p; i<=q; i++){
		arr[i]=tmp[i-p];
	}

	free(tmp);	
}

void sort(int p, int q){
	int r = (p+q)/2;
	if(p>=q){
		return;
	}
	sort(p, r);
	sort(r+1, q);
	merge(p, q);		
}


////////////////////////////////
int search(int num, int m){
	int mid=0, l=m+1, r=n-1;
	int k=1;
	long long tar = arr[num]-arr[m];

	if(tar==arr[num]){
		k=1;
		while(num-k>=0){
			if((num-k)!=m && tar==arr[num-k]){
				return 1;
			}
			k++;
		}
		k=1;
		while(num+k<n){
			if( (num+k)!=m  && tar==arr[num+k] ){
				return 1;
			}
			k++;
		}
		return 0;
	}
	
	if(arr[m]==tar) {
		k=1;
		while(m-k>=0){
			if((m-k)!=num && tar==arr[m-k]){
				return 1;
			}
			k++;
		}
		k=1;
		while(m+k<n){
			if((m+k)!=num && tar==arr[m+k]){
				return 1;
			}
			k++;
		}
		return 0;
	}
	
	//binary search
	while(l<=r){
		mid=(l+r)/2;
		if(mid==num && arr[mid]==tar) {
			k=1;
			while(num-k>=0 && tar==arr[num-k]){
				if((num-k)!=m){
					return 1;
				}
				k++;
			}
			k=1;
			while(num+k<n && tar==arr[num+k]){
				if((num+k)!=m){
					return 1;
				}
				k++;
			}
			return 0;
		}
		
		if(m==mid && arr[mid]==tar) {
			k=1;
			while(m-k>=0 && tar==arr[m-k]){
				if((m-k)!=num){
					return 1;
				}
				k++;
			}
			k=1;
			while(m+k<n && tar==arr[m+k]){
				if((m+k)!=num){
					return 1;
				}
				k++;
			}
			return 0;
		}
		
		if(arr[mid]==tar){
			return 1;
		} else if(arr[mid]>tar){
			r=mid-1;
		} else if(arr[mid]<tar){
			l=mid+1;
		}
	}
	
	return 0;
}

int main(){
	int k=0,cnt=0;
	int i=0, j=0;
	scanf("%d", &n);
	for(i=0; i<n;i++){
		scanf("%d", &arr[i]);
	}

	sort(0, n-1);

	for(i=0; i<n;i++){
		for(j=0;j<n;j++){
			if(j==i){
				continue;
			}
			if(search(i,j)==1){
				cnt++;
				break;
			}

		}
	}

	printf("%d",cnt);	
	return 0;
}



