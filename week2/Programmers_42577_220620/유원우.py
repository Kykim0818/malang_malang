# 효율성 탈락 코드
 def solution(phone_book):
    sort_book = sorted(phone_book)
    for i in range(len(sort_book)-1):
        cur_num = sort_book[i]
        for next_num in sort_book[i+1:]:
            if next_num.startswith(cur_num):
                return False
    return True
# 정답
 def solution(phone_book):
    sort_book = sorted(phone_book)
    for i in range(len(sort_book)-1):
        if sort_book[i+1].startswith(sort_book[i]):
            return False
    return True
