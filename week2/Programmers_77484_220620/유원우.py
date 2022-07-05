def solution(lottos, win_nums):
    answer = []
    rank_order = [6, 6, 5, 4, 3, 2, 1]
    
    z_cnt = lottos.count(0)
    same_cnt = 0
    for lotto in lottos:
        if lotto in win_nums:
            same_cnt += 1
    
    answer.append(rank_order[z_cnt + same_cnt])
    answer.append(rank_order[same_cnt])
    
    return answer
