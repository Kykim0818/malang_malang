def solution(phone_book):
    
    phone_book = sorted(phone_book)
    new_phonebook = phone_book[1:]
    
    for i,j in zip(phone_book,new_phonebook):
        if j.startswith(i):
            return False
    return True

