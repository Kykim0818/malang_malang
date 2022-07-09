# 해설 참조: https://prgms.tistory.com/101

def solution(grid):
    dir = [[1, 0], [0, -1], [-1, 0], [0, 1]] # 방향
    
    rows, columns = len(grid), len(grid[0]) #그리드 길이, 넓이
    visited = [[[False for _ in range(4)] for _ in range(columns)] for _ in range(rows)] # 방문하지 않은 그리드 그리기
    ans = []
    
    for i in range(rows):
        for j in range(columns):
            for k in range(4): # 방향
                if not visited[i][j][k]:
                    dx, dy, dd = i, j, k
                    count = 0
                    
                    # 방문하지 않았다면
                    while not visited[dx][dy][dd]:
                        visited[dx][dy][dd] = True
                        count += 1
                        
                        # 새로운 방향 L/R, 그리드 밖으로 나간다면 다시 돌아오도록
                        nx, ny = dir[dd]
                        dx, dy = (dx + nx) % rows, (dy + ny) % columns

                        if grid[dx][dy] == 'L':
                            dd = (dd-1) % 4
                        elif grid[dx][dy] == 'R':
                            dd = (dd+1) % 4
                            
                    ans.append(count)

    return sorted(ans)