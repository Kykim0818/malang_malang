class Solution:
    def uniquePathsIII(self, grid: List[List[int]]) -> int:        
        row = len(grid)
        col = len(grid[0])
        
        dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)] # up down left right       

        # start from 1 for end 
        empties = 1
        
        for r in range(row):
            for c in range(col):
                if grid[r][c] == 1:
                    start_p = (r, c)
                elif grid[r][c] == 2:
                    end_p = (r, c)
                elif grid[r][c] == 0:
                    empties += 1
                    
        self.output = 0
        visit = set()
        
        def dfs(cur_p, visit, walks):
            if cur_p == end_p:
                if walks == empties:
                    self.output += 1
                return 
                
            r, c = cur_p
            if 0 <= r < row and 0 <= c < col and grid[r][c] != -1 and (r,c) not in visit:
                visit.add((r, c))
                for d in dirs:
                    dfs((r+d[0], c+d[1]), visit, walks+1)
                visit.remove((r, c))
        
        
        dfs(start_p, visit, 0)
        
        return self.output
