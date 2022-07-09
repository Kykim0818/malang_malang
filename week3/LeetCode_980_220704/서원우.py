class Solution:
    def uniquePathsIII(self, grid: List[List[int]]) -> int:
        n, m = len(grid), len(grid[0])
        self.ans = 0
        no_visit = 0
        
        def dfs(x, y, rest):
            if x < 0 or y < 0 or x >= n or y >= m or grid[x][y] < 0:
                return
            if grid[x][y] == 2 and rest == 0:
                self.ans += 1
                
            directions =  [[0,1],[1,0],[-1,0],[0,-1]]
            
            for dx, dy in directions:
                path = grid[x][y]
                grid[x][y] = -2
                dfs(x+dx, y+dy, rest-1)
                grid[x][y] = path
                
        for i,j in product(range(n),range(m)):
            if grid[i][j] == 1:
                (sx,sy) = (i,j)
            no_visit += (grid[i][j] != -1)
            
        dfs(sx,sy,no_visit-1)
        
        return self.ans