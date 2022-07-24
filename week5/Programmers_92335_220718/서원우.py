

def solution(n, k):
    answer = 0
    
    def convert(num, base):
        power = 0
        tmp = ''
        while num:
            tmp = str(num%base) + tmp
            num //= base
        return tmp

    def isprime(num):
        for n in range(2,int(num**0.5)+1):
            if num%n==0:
                return False
        return True
    
    converted_str = convert(n, k)

    for i in converted_str.split("0"):
        if not i or i == "1": 
            continue
        if isprime(int(i)):
                answer += 1
    return answer