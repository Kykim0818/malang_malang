class Solution {

  // 어려운점: 최선의 case(이기는입장, 지는입장 모두)를 고려해야 한다
  // 이길 수 있는 경우 최선으로 이기고자 할 것이고 지는 경우는 최대한 오랫동안 끌려고 할 것이다
  // 결국, dfs()의 탐색결과는 (승패여부, 총 이동횟수)라고 결론을 내릴 수 있다.

  int N, M;
  int[] dx = {0, 1, 0, -1};
  int[] dy = {1, 0, -1, 0};

  class Result{
    boolean result;
    int count;

    public Result(boolean result, int count) {
      this.result = result;
      this.count = count;
    }
  }

  public int solution(int[][] board, int[] aloc, int[] bloc) {
    N = board.length;
    M = board[0].length;

    Result answer = dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0, 0, 1, board);
    return answer.count;
  }

  // 무지 헷갈림...귀납적 사고할 것!!! n -> n + 1
  private Result dfs(int ax, int ay, int bx, int by, int aCount, int bCount, int order, int[][] board) {
    // 리턴시킬 변수들
    boolean result = false;
    int minCount = Integer.MAX_VALUE; // 이기는 경우; 최대한 빨리끝내려고 할 것
    int maxCount = aCount + bCount; // 지는 경우; 최대한 도망가자고할 것

    // A이동
    if(order == 1 && board[ax][ay] == 1){
      // 새롭게 이동할 곳 찾기
      for(int i=0; i<4; i++){
        int nAx = ax + dx[i];
        int nAy = ay + dy[i];
        if(nAx < 0 || nAx >= N || nAy < 0 || nAy >= M) continue;
        if(board[nAx][nAy] == 0) continue;

        board[ax][ay] = 0;
        Result tempResult = dfs(nAx, nAy, bx, by, aCount + 1, bCount, 1 - order, board);
        result |= !tempResult.result; // 이기는 경우가 있다면 무조건 이기는 방향으로 간다

        if(!tempResult.result) minCount = Math.min(minCount, tempResult.count); // 다른 사람의 결과를 받으므로 토글시킴
        else maxCount = Math.max(maxCount, tempResult.count);

        board[ax][ay] = 1;
      }
    } else if(order == 0 && board[bx][by] == 1){
      for(int i=0; i<4; i++){
        int nBx = bx + dx[i];
        int nBy = by + dy[i];
        if(nBx < 0 || nBx >= N || nBy < 0 || nBy >= M) continue;
        if(board[nBx][nBy] == 0) continue;

        board[bx][by] = 0;
        Result tempResult = dfs(ax, ay, nBx, nBy, aCount, bCount + 1, 1 - order, board);
        result |= !tempResult.result;

        if(!tempResult.result) minCount = Math.min(minCount, tempResult.count); // 다른 사람의 결과를 받으므로 토글시킴
        else maxCount = Math.max(maxCount, tempResult.count);

        board[bx][by] = 1;
      }
    }
    return new Result(result, result ? minCount : maxCount);
  }
}