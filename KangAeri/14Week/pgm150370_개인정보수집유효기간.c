#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int* solution(const char* today, const char* terms[], size_t terms_len, const char* privacies[], size_t privacies_len) {

    int i=0, j=0, idx=0, flag=0, todayY=0, todayM=0, todayD=0, yy=0,mm=0,dd=0, tt=0;
    char tmp[15];
    char* ptr;
    int* answer = (int*)malloc(sizeof(int)*privacies_len);
    int* term = (int*)malloc(sizeof(int)*30);
    for(i=0; i<30; i++){
         term[i]=0;
	}
    //today yymmdd get
    todayY = atoi(strtok(today, "."));   
    todayM = atoi(strtok(NULL, "."));
    todayD = atoi(strtok(NULL, " "));
    // get terms info
    for(i=0; i<terms_len; i++){ //A:0...
         j = *(strtok(terms[i]," "))-65;
    	 term[j]=atoi(strtok(NULL, " "));
	}
    //compute
    for(i=0; i<privacies_len; i++){
        yy = atoi(strtok(privacies[i], "."));
        mm = atoi(strtok(NULL, "."));
        dd = atoi(strtok(NULL, " "));
        tt = term[*(strtok(NULL," ")) -65];
        
        dd+= tt*28-1;	//유효기간 날수로 바꿔서 더함 
        mm+= ((dd-1)/28); 
        if(dd%28==0){
            dd=28;
        } else{
            dd%=28;
        }
        yy+= ((mm-1)/12);
        if(mm%12==0){
            mm=12;
        } else{
            mm%=12;
        }
    
        flag=0;
        if(yy==todayY){
            if(mm==todayM){
                if(dd<todayD){
                    flag=1;
                }
            } else if(mm<todayM){
                flag=1;
            }
        } else if(yy<todayY){
            flag=1;
        }
    	
		if(flag==1){
			answer[idx++]=i+1;
		}
    	
	}
   
    free(terms);
    return answer;
}
