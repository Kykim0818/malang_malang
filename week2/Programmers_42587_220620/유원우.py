# Run Time Error 85/100
def solution(priorities, location):
    answer = 0
    while len(priorities) > 0:
        cur = priorities.pop(0)
        cur_max = max(priorities)
        if cur >= cur_max:
            answer += 1
            if location == 0:
                return answer
            else:
                location -= 1
        else:
            priorities.append(cur)
            if location == 0:
                location = len(priorities)-1
            else:
                location -= 1

# Pass Answer 100/100
def solution(priorities, location):
    answer = 0
    while len(priorities) > 0:
        cur_max = max(priorities)
        cur = priorities.pop(0)
        if cur == cur_max:
            answer += 1
            if location == 0:
                return answer
            else:
                location -= 1
        else:
            priorities.append(cur)
            if location == 0:
                location = len(priorities)-1
            else:
                location -= 1
