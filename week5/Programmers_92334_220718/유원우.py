def solution(id_list, report, k):
    report = set(report)
    answer = []
    
    count = {x: 0 for x in id_list}
    report_map = {x: set() for x in id_list}
    
    
    for r in report:
        x, y = r.split()
        
        count[y] += 1 
        
        if y not in report_map[x]:
            report_map[x].add(y)
    
    banned = set()
    for key, val in count.items():
        if val >= k:
            banned.add(key)
    
    for i in id_list:
        answer.append(len(banned.intersection(report_map[i])))
    
    return answer
