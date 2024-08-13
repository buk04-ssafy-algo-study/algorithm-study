from collections import deque

def bfs(maps):
    q = deque()
    q.append((0, 0, 1))
    
    while q:
        x, y, cnt = q.popleft()
        
        if x == len(maps[0])-1 and y == len(maps)-1:
            return cnt
        if maps[y][x] == 0:
            continue
        maps[y][x] = 0
        
        if x + 1 < len(maps[0]):
            q.append((x+1, y, cnt+1))
        if y + 1 < len(maps):
            q.append((x, y+1, cnt+1))
        if x >= 1:
            q.append((x-1, y, cnt+1))
        if y >= 1:
            q.append((x, y-1, cnt+1))
    
    return -1

def solution(maps):  
    answer = bfs(maps)
    
    return answer
