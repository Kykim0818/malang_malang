from collections import deque
from copy import deepcopy
def solution(n, info):
    answer = []

    # ryan: n_shot, shots
    q = deque([(0, [0] * len(info))])
    max_gap = -1

    while q:
        state, shots = q.popleft()
        
        if sum(shots) == n:
            apache, ryan = 0, 0
            for i in range(11):
                if info[i] != 0 or shots[i] != 0:
                    if info[i] >= shots[i]:
                        apache += 10 - i
                    else:
                        ryan += 10 - i
            if ryan > apache:
                cur_gap = ryan - apache
                if cur_gap > max_gap:
                    max_gap = cur_gap
                    answer.clear()
                    answer.append(shots)
                if cur_gap == max_gap:
                    answer.append(shots)
                
        elif sum(shots) > n:
            continue
            
        elif state == 10:
            if sum(shots) != n:
                shots[state] = n - sum(shots)
                q.append((-1, shots))
        else:
            # shoot more than one
            new_shots = deepcopy(shots)
            new_shots[state] = info[state] + 1
            q.append((state + 1, new_shots))
            
            # no shot
            new_shots = deepcopy(shots)
            new_shots[state] = 0
            q.append((state + 1, new_shots))
    
    if len(answer) == 0:
        answer.append([-1])
    
    return answer[-1]
