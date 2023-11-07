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
        } else if(arr[x].l>arr[y].l){
            tmp[i++]= arr[y++];
        } else{
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
    
    msort(0,targets_rows-1);
    i=0;
    while(i<len){
        lbound=arr[i].l;
        rbound=arr[i].r;
        answer++;
        for(j=i+1; j<len;j++){
            if(arr[j].l<rbound){
                lbound=arr[j].l;
                rbound=min(rbound, arr[j].r);
            } else{     
                break;
            }
        }
        i=j;
    }

    return answer;
}


