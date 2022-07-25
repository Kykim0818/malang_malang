def solution(fees, records):
    answer = []
    
    def dateToMinutes(date):
        h, m = map(int, date.split(':'))
        return h*60 + m
    
    default_time, default_fee, cost_time, cost_fee = fees

    report_map = {x.split()[1]: [] for x in records}
    sorted(report_map)
    for record in records:
        time, car_num, IO = record.split()

        report_map[car_num].append((dateToMinutes(time),IO))
        
    for i in sorted(report_map):
        parking_time = 0
        for cur_time,cur_IO in report_map[i]:
            if cur_IO == "OUT":
                parking_time += cur_time
            else:
                parking_time -= cur_time
        if report_map[i][-1][1] == "IN":
            parking_time += dateToMinutes('23:59')

        if parking_time <= default_time:
            answer.append(default_fee)
        else:
            use_time = ((parking_time-default_time)/cost_time)
            use_time = int(use_time) + bool(use_time%1)
            answer.append(default_fee + use_time * cost_fee)
    return answer