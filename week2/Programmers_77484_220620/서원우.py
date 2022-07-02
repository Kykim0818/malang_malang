def solution(lottos, win_nums):
    answer = []
    lottos.sort()
    win_nums.sort()
    
    num_of_zero = lottos.count(0)
    
    same_num = len(list(set(lottos).intersection(win_nums)))
    
    def score(num):
        if num < 2:
            return 6
        else:
            return 7-num
    
    return [score(same_num+num_of_zero), score(same_num)]