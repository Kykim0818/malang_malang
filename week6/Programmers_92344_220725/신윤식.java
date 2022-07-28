class Solution {
  public int solution(int[][] board, int[][] skills) {
    int N = board.length;
    int M = board[0].length;

    int[][] d = new int[N + 10][M + 10]; // 변화량 테이블
    for(int[] skill : skills){
      int type = skill[0];
      int startX = skill[1];
      int startY = skill[2];
      int endX = skill[3];
      int endY = skill[4];
      int degree = skill[5];

      if(type == 1) degree = -degree;
      d[startX][startY] += degree;
      d[endX + 1][startY] -= degree;
      d[endX + 1][endY + 1] += degree;
      d[startX][endY + 1] -= degree;
    }

    // 열 누적합
    for(int i=0; i<N; i++){
      for(int j=1; j<M; j++){
        d[i][j] += d[i][j - 1];
      }
    }

    // 행 누접합
    for(int i=1; i<N; i++){
      for(int j=0; j<M; j++){
        d[i][j] += d[i - 1][j];
      }
    }

    // 건물 카운트
    int building = 0;
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        if(board[i][j] + d[i][j] > 0) building++;
      }
    }
    return building;
  }
}