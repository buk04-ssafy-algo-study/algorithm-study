#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int len;

int min(int a, int b){
    if(a<b){
        return a;
    } else{
        return b;
    }
}

typedef struct node{
    int l;
    int r;
}node;

struct node arr[500001];

void merge(int l, int r){
    int i=0, x=0, y=0, p=(r+l)/2;
    struct node* tmp = (struct node*)malloc(sizeof(struct node)*(r-l+1));
    x=l;
    y=p+1;
    
    while(x<=p && y<=r){
        if(arr[x].l<arr[y].l){
            tmp[i++]=arr[x++];
        } else if(arr[x].l>arr[y].l){ //구간시작점기준 정렬
            tmp[i++]= arr[y++];
        } else{//구간시작점 같으면 구간끝나는점기준 정렬
            if(arr[x].r<arr[y].r){
                tmp[i++]=arr[x++];
            } else{
                tmp[i++]= arr[y++];
            }  
        }
    }
    while(x<=p){
        tmp[i++]=arr[x++];
    }
    while(y<=r){
        tmp[i++]=arr[y++];
    }
    for(x=l; x<=r; x++){
        arr[x]=tmp[x-l];
    }
    free(tmp);
}

void msort(int l, int r){
    int p=(l+r)/2;
    if(l>=r){
        return;
    }
    msort(l,p);
    msort(p+1,r);
    merge(l,r);
} 


int solution(int** targets, size_t targets_rows, size_t targets_cols) {
    int answer = 0,i=0,j=0;
    int lbound=0, rbound=0;
    
    len=targets_rows;
    for(i=0; i<len; i++){
        arr[i].l = targets[i][0];
        arr[i].r = targets[i][1];
    }
    
    msort(0,targets_rows-1);//정렬
    i=0;
    //////////////////////////////
    
    while(i<len){
        lbound=arr[i].l; //lbound:같이요격하는애들 구간시작점 최댓값
        rbound=arr[i].r; //rbound:같이요격하는애들 구간끝점 최솟값
        answer++; //일단미사일(i)있으면 요격 한번 하는거
        for(j=i+1; j<len;j++){ //i 요격할 때 같이요격할수있는애들 최대로 찾음
            if(arr[j].l<rbound){
                lbound=arr[j].l; //lbound 갱신
                rbound=min(rbound, arr[j].r); //rbound 갱신
            } else{     
                break;
            }
        }
        //여기서 j : i요격할 때 요격안된 (정렬순서상)첫번째애
        i=j;// 
    }

    return answer;
}


