#include <stdio.h>

int min(int a, int b){
    if(a<b){
        return a;
    } else{
        return b;
    }
}

int main(){
	int i=0,n=0,m=0, top=0,front=1, rear=1, tar=0,mov=0,ans=0; 
	int arr[51*51];
	scanf("%d %d", &n, &m);
	for(i=1; i<=n; i++){
        arr[i]=i;
    }
    rear = n;
    for(i=0; i<m; i++){
        scanf("%d", &tar);
        mov=0;
        while(mov<n) {
        	top = arr[front++];
        	if(top==tar) {
        		break;
        	}
        	arr[++rear]=top;
        	mov++;
        }
        ans+=min(mov, rear-front+2-mov);

    }
    printf("%d",ans);
    return 0;

}
