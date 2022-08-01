def solution(info, edges):    
    def get_childs(cur_node):
        childs = list()
        for p, c in edges:
            if p == cur_node:
                childs.append(c)
        return childs
    
    def dfs(sheep, wolf, current, path):
        if info[current] == 1:
            wolf += 1
        else:
            sheep += 1
        
        if wolf >= sheep:
            return 0
        
        max_sheep = max(sheep, 0)
        
        for p in path:
            for child in get_childs(p):
                if child not in path:
                    path.append(child)
                    max_sheep = max(max_sheep, dfs(sheep, wolf, child, path))
                    path.pop()
        return max_sheep
    
    answer = dfs(0, 0, 0, [0])
    
    return answer
