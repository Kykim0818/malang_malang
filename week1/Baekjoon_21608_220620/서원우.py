# Runtime Error

import sys
from collections import defaultdict

# Setup
input = sys.stdin.readline
n = int(input())
dict = defaultdict(list)
# Direction from center table
x = [1,0,-1,0]
y = [0,-1,0,1]
# Table array
tables = [[0] * n for _ in range(n)]
# Get all available seats
available_seats = [[ix,iy] for ix, row in enumerate(tables) for iy, i in enumerate(row)]


# list of students and their preference
for _ in range(n**2):
    studnet_input = list(map(int, input().rstrip().split()))
    students = studnet_input[0]
    preference = studnet_input[1:]
    dict[students].append(preference)

# Let students sit where they like
for s, p in dict.items():

    # Check location of preferred friends in table    
    friends_location = [[ix,iy] for ix, row in enumerate(tables) for iy, i in enumerate(row) if i in p[0]]

    # Update available empty seats
    for i in friends_location:
        for j in available_seats:
            if i == j or tables[j[0]][j[1]] != 0:
                available_seats.remove(j)

    good_seats = []
    # Check surrouding seats
    for available in available_seats:
        emptiness = 0
        happiness = 0
        # Check all 4 directions
        for dir in range(4):
            dx, dy = available[0] - x[dir], available[1] - y[dir]
            # Continue if not out of range
            if dx < 0 or dy < 0 or dx >= n or dy >= n:
                continue
            # If seat in that diretion is friend's or empty, give score
            if [dx, dy] in friends_location:
                happiness += 1
            elif tables[dx][dy] == 0:
                emptiness += 1


        good_seats.append([happiness, emptiness, available])
    
    '''
    Select best seat with conditions:
    1. friends
    2. empty seats
    3. lower row
    4. lower column
    '''
    good_seats.sort(key=lambda x: (-x[0], -x[1], x[2][0], x[2][1]))

    # Sit at first ordered seat
    tables[good_seats[0][2][0]][good_seats[0][2][1]] = s
 

# Count the score.
result = 0
# Iterate each sit, check the number of friends on each direction, score
for i in range(n):
    for j in range(n):
        # Number of nearby friends
        amount = 0
        # Each directions
        for k in range(4):
            nx, ny = i + x[k], j +- y[k]
            if nx < 0 or ny < 0 or nx >= n or ny >= n:
                continue
            if tables[nx][ny] in dict[tables[i][j]][0]:
                amount += 1

        # Score for each sits
        if amount == 0:
            result += 0
        elif amount == 1:
            result += 1
        else:
            result += 10 ** (amount-1)

print(result)
