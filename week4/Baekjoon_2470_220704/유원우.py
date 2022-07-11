n = int(input())
liquids = list(map(int, input().split(' ')))

answer = []
liquids.sort()

max_n = 2e9 + 1

l, r = 0, len(liquids) - 1

while l < r:
    cur_s = liquids[l] + liquids[r]

    if abs(cur_s) < max_n:
        max_n = abs(cur_s)
        answer = [liquids[l], liquids[r]]

    if cur_s == 0:
        print(answer[0], answer[1])
        break
    elif cur_s > 0:
        r -= 1
    else:
        l += 1

print(answer[0], answer[1])
