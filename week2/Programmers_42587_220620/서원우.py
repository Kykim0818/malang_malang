def solution(priorities, location):
    answer = 0
    while True:
        x = priorities.pop(0)
        location -= 1
        if len(priorities) == 0:
            answer +=1
            break

        if x >= sorted(priorities)[-1]:
            answer += 1
            if location == -1:
                break
        else:
            priorities.append(x)

        if location == -1:
            location = len(priorities)-1

    return answer