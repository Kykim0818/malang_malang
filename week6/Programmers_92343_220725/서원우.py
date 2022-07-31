def solution(info, edges):
    def nextNodes(n):
        """View next nodes
        
        Args:
            n: Node
        
        Return:
            next nodes
        
        """
        temp = list()
        for node in edges:
            # i = parent node, j = child node
            i, j = node
            # compare parent nodes numbers
            if n == i:
                temp.append(j)
        return temp

    def dfs(sheep, wolf, current, path):
        """DFS for getting max sheeps
        
        Args:
            sheep: number of sheeps
            wolf: number of wolfes
            current: current node
            path: path until current node
            
        Return:
            max number of sheeps
        """
        
        # Check current node, identify sheep or wold
        if info[current]:
            wolf += 1
        else:
            sheep += 1

        # when wolves num > sheeps num
        if sheep <= wolf:
            return 0
        else:
            maxSheep = sheep

        # seach path
        for p in path:
            for n in nextNodes(p):
                if n not in path:
                    path.append(n)
                    
                    # get max sheeps
                    maxSheep = max(maxSheep, dfs(sheep, wolf, n, path))
                    
                    # remove path since it's not for max sheeps
                    path.pop()
                    
        return maxSheep

    answer = dfs(0, 0, 0, [0])

    return answer