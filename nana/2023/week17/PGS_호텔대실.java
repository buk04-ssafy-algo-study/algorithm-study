{\rtf1\ansi\ansicpg949\cocoartf2758
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 class Solution \{\
    \
    public int solution(String[][] book_time) \{\
        int answer = 0;\
        \
        int size = book_time.length;\
        \
        int[] room = new int[24*60+10];\
            \
        for(int i = 0; i<size; i++)\{\
            String[] start = book_time[i][0].split("\\"|\\"|:");\
            String[] end = book_time[i][1].split("\\"|\\"|:");\
            \
            int startTime = Integer.parseInt(start[0])*60 + Integer.parseInt(start[1]);\
            int endTime = Integer.parseInt(end[0])*60 + Integer.parseInt(end[1]) + 10;\
            \
            room[startTime] += 1;\
            room[endTime] += -1;\
        \}\
        \
        for(int i =1; i<24*60+10; i++)\{\
            room[i] += room[i-1];\
            answer = Math.max(answer, room[i]);\
        \}\
        \
        return answer;\
    \}\
\}}