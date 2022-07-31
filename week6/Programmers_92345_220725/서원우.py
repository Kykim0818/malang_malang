# define directions
dir = ((-1,0),(0,1),(1,0),(0,-1))

def player_turn(player,aloc,bloc,count,board):
    """Each player's move for both win or lose position
    
    Args:
        player: Player's turn in string
        aloc: my location
        bloc: opponent's location
        count: number of moves
        board: current board state
    
    """
    # players' locations
    ar,ac,br,bc = aloc[0],aloc[1],bloc[0],bloc[1]
    
    # player in board with 0 num
    if board[ar][ac] == 0:
        return (1,count)
    
    winner = []
    loser = []
    flag = False
    for dr,dc in dir:
        # next player
        nloc = [ar+dr,ac+dc]
        nr,nc = nloc[0], nloc[1]
        
        # when game continues
        if 0 <= nr < len(board) and 0 <= nc < len(board[0]) and board[nr][nc] == 1:
            flag = True
            temp = [row[:] for row in board]
            temp[ar][ac] = 0
            
            # next player move
            if player == 'A':
                iswin,turn = player_turn('B',bloc,nloc,count+1,temp)
            elif player == 'B':
                iswin,turn = player_turn('A',bloc,nloc,count+1,temp)
                
            # define next player's move
            if iswin:
                winner.append(turn)
            else:
                loser.append(turn)
                
    # when the flag is True
    if flag:
        if winner:
            return (0,min(winner))
        else:
            return (1,max(loser))
    else:
        return (1,count)

def solution(board, aloc, bloc):
    answer = player_turn('A',aloc,bloc,0,board)[1]
    return answer