def solution(n, info):
    answer = []
    
    # Score board
    score = list(range(10,-1,-1))
    
    def dfs(n, index, result, total, apeach_score, priority):
        """DFS for finding my possible scores against opponent
        
        Args:
            n: number of arrows
            index: index for pointing which opponent's score to win first
            my result: my current score of winning opponent
            my_total: my total score of winning opponent
            apeach_score: apeach's score
            priority: priority for each my total scores
        
        Return:
            List of score and priority list
        """
        # get apeach's score
        if info[index]:
            oppo_score = score[index]
        else:
            oppo_score = 0
        
        # return when all 10 arrows are shot
        if index == 10:
            answer.append([total-apeach_score,result+[n],priority])
            return
        
        # when no arrorws left to shoot
        elif n == 0:
            dfs(n, index+1, result+[0], total, apeach_score+oppo_score,priority)
        else:
            # for 2 cases, win current index of opponent's score or not
            for i in range(2):
                my = info[index]+1
                
                # if skip current index
                if n < my or i == 0:
                    dfs(n, index+1, result+[0], total, apeach_score+oppo_score,priority)
                
                # if win current index
                else:
                    dfs(n-my,index+1, result+[my],total+score[index],
                        apeach_score, priority+score[10-index])
    
    
    dfs(n,0,[],0,0,0)
    
    answer.sort(key=lambda x: [x[0],x[2]], reverse = True)
    
    # if draw
    if answer[0][0] <= 0:
        return [-1]
    else:
    	# if I win
        return answer[0][1]