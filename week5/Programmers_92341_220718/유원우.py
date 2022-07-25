from math import ceil

def calc_t(in_t, out_t):
    in_h, in_m = in_t.split(":")
    out_h, out_m = out_t.split(":")
    
    return (int(out_h) - int(in_h)) * 60 + (int(out_m) - int(in_m))

def solution(fees, records):
    answer = []
    base_t, base_p, over_t, over_p = fees
    
    track = {}
    for r in records:
        t, plate, rec = r.split(" ")
        if plate not in track:
            track[plate] = {'IN': [], 'OUT': []}
            track[plate][rec].append(t)
        else:
            if rec not in track[plate]:
                track[plate][rec] = [t]
            else:
                track[plate][rec].append(t)
    total_t = {}
    for k, v in track.items():
        if len(v['OUT']) < len(v['IN']):
            v['OUT'].append('23:59')
        for i, o in zip(v['IN'], v['OUT']):
            if k not in total_t:
                total_t[k] = calc_t(i, o)
            else:
                total_t[k] += calc_t(i, o)
    
    sort_plate = sorted(total_t.keys())
    for plate in sort_plate:
        t = total_t[plate]
        if t <= base_t:
            price = base_p
        else:
            price = base_p + ceil((t - base_t) / over_t) * over_p
        answer.append(price)
    
    
    return answer
