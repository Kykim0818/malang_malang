import math 

def convert(n, k):
    result = ""
    while n:
        result += str(n % k)
        n //= k
    return result[::-1]

def check_prime(n):
    for i in range(2, int(math.sqrt(n))+1):
        if n%i == 0:
            return False
    
    return True

def solution(n, k):
    answer = 0
    
    binary_str = convert(n, k)
    binary_list = binary_str.split("0")
    print(binary_list)
    for b in binary_list:
        if b != "" and b!= "1":
            if check_prime(int(b)):
                answer += 1
    return answer
