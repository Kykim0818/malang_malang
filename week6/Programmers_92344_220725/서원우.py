def solution(board, skill):
    """Check how many building not destroyed
    
    Args:
        board: list in list of board with initial buildings' hp
        skill: list in list skills range and damage/heal
    
    """
    answer = 0
    rows = len(board)
    columns = len(board[0])

    # get temporary board for damage/heal records
    temp_board = [[0] * (columns+1) for _ in range(rows+1)]

    # record damange/heal amount in that range in temp board
    for ene_all, row1, column1, row2, column2, amount in skill:
        if ene_all == 1:
            temp_board[row1][column1] -= amount
            temp_board[row1][column2+1] += amount
            temp_board[row2+1][column1] += amount
            temp_board[row2+1][column2+1] -= amount
        else:
            temp_board[row1][column1] += amount
            temp_board[row1][column2+1] -= amount
            temp_board[row2+1][column1] -= amount
            temp_board[row2+1][column2+1] += amount

    # total damages/heal in columns
    for i in range(rows+1): 
        for j in range(1, columns+1):
            temp_board[i][j] += temp_board[i][j-1]
    
    # total damages/heal in rows
    for i in range(columns+1):
        for j in range(1, rows+1):
            temp_board[j][i] += temp_board[j-1][i]

    # combine with original board
    for i in range(rows):
        for j in range(columns):
            board[i][j] += temp_board[i][j]
            # cont safe buildings
            if board[i][j] > 0:
                answer += 1
    return answer