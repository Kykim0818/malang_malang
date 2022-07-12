import sys
from collections import deque
m,n,h = map(int, input().split())
storage = []
q = deque()

for i in range(h):
    cur_box = []
    for j in range(n):
        cur_box.append(list(map(int, sys.stdin.readline().split())))
        for k in range(m):
            if cur_box[j][k] == 1:
                q.append((i, j, k))
    storage.append(cur_box)

dirs = [(-1, 0, 0), (0, -1, 0), (0, 0, -1), (1, 0, 0), (0, 1, 0), (0, 0, 1)]

while q:
    c_h, c_n, c_m = q.popleft()

    for d in dirs:
        n_h = c_h + d[0]
        n_n = c_n + d[1]
        n_m = c_m + d[2]

        if 0<=n_h<h and 0<=n_m<m and 0<=n_n<n and storage[n_h][n_n][n_m] == 0:
            q.append((n_h, n_n, n_m))
            storage[n_h][n_n][n_m] = storage[c_h][c_n][c_m] + 1

answer = 0
for h in storage:
    for n in h:
        for m in n:
            if m == 0:
                print(-1)
                sys.exit()
        answer = max(answer, max(n))
print(answer-1)