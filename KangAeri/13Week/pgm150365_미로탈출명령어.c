#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int movcnt, finded; //finded:전역 flag: 답 찾았는지 확인..답 찾았으면 재귀 다 빠져나오게함(재귀 한단계씩 빠져나오면서 ans[] 배열에 답 저장함)
int row, col, dest;
int mov[4][2] = {{1,0}, {0,-1},{0,1},{-1,0}};
char dir[4] = {'d', 'l', 'r', 'u'}; //이거사전순..이순서대로 재귀 들어가서 가장 먼저 찾는 답이 사전순 정답 임 


void find(int cnt, int loc, char* ans){
    int p=0,cur=0, ni=0,nj=0;
    int len=abs(dest/col-loc/col)+abs(dest%col-loc%col); 
    if(movcnt-cnt < len || (movcnt-cnt)%2!=len%2){ //백트래킹 조건
       return;
    }
    
    if(cnt==movcnt){
        if(loc==dest){
            finded=1;
        } 
        return;
    }
    
    for(p=0; p<4; p++) { 
        ni = loc/col + mov[p][0];
        nj = loc%col + mov[p][1];
        if(ni>=0 && ni<row && nj>=0 && nj<col) {
            find(cnt+1, ni*col+nj, ans);
            if(finded==1){
                ans[cnt]=dir[p];
                break;
            }
        }  
    }
}

char* solution(int n, int m, int x, int y, int r, int c, int k) {
    int p=0, i=0,j=0,flag=0,lev=0, cnt=0,tmp=0;
    int cur=0, front=0, rear=0, ni=0, nj=0;
    int idx=0,chk=0;
    char* answer=(char*)malloc(sizeof(char)*(k+1));
    row=n;
    col=m;
    movcnt=k;
    finded=0;
    dest=(r-1)*m+c-1;
    if(abs(r-x)+abs(c-y)<=k && (abs(r-x)+abs(c-y))%2==k%2){ //계산 시작 전 거르는 조건
        find(0,(x-1)*m+y-1, answer);
    }
    
    
    if(finded==0){
        answer="impossible";
    }  else{
        answer[k]='\0';
    }

    return answer;
}
