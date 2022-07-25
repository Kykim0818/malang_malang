def solution(id_list, report, k):
    answer = []
    report = set(report)

    count = {x: 0 for x in id_list}
    user_report = {x: set() for x in id_list}

    for i in report:
        x,y = i.split()
        count[y] += 1

        user_report[x].add(y)


    for i,j in count.items():
        if j < k:
            for z,x in user_report.items():
                if i in x:
                    user_report[z].remove(i)
                    
    for q,w in user_report.items():
        answer.append(len(w))
    
    return list(answer)