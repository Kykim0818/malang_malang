class Solution {

  static int N, M, answer, count;
  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};
  static int startX, startY, endX, endY;

  public int uniquePathsIII(int[][] grid) {

    init(grid);
    findStartEnd(grid);
    bfs(grid);

    return answer;
  }

  private void init(int[][] grid){
    N = grid.length;
    M = grid[0].length;
    answer = count = 0;
    startX = startY = endX = endY = 0;
  }

  private static void findStartEnd(int[][] grid) {
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        if(grid[i][j] != -1) count++;
        if(grid[i][j] == 1){
          startX = i;
          startY = j;
        }else if(grid[i][j] == 2){
          endX = i;
          endY = j;
        }
      }
    }
  }

  private static void bfs(int[][] grid) {
    Queue<Node> q = new LinkedList<>();
    boolean[][] visited = new boolean[N][M];
    visited[startX][startY] = true;
    q.add(new Node(startX, startY, 1, visited));

    while(!q.isEmpty()){
      Node curNode = q.poll();
      boolean[][] curVisited = curNode.getVisited();

      if(curNode.getX() == endX && curNode.getY() == endY){
        if(curNode.getDistance() == count) answer++;
        continue;
      }

      for(int i=0; i<4; i++){
        int newX = curNode.getX() + dx[i];
        int newY = curNode.getY() + dy[i];
        boolean[][] newVisited = new boolean[N][M];
        deepCopy(newVisited, curVisited);

        if(newX < 0 || newX >= N || newY < 0 || newY >= M) continue; // 범위를 넘어선 경우
        if(grid[newX][newY] == -1) continue; // 장애물
        if(curVisited[newX][newY]) continue; // 이미 방문한 경우

        newVisited[newX][newY] = true;
        q.add(new Node(newX, newY, curNode.getDistance() + 1, newVisited));
      }
    }
  }

  private static void deepCopy(boolean[][] newVisited, boolean[][] curVisited) {
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        newVisited[i][j] = curVisited[i][j];
      }
    }
  }
}

class Node{
  private int x, y;
  private int distance;
  private boolean[][] visited;

  public Node(int x, int y, int distance, boolean[][] visited) {
    this.x = x;
    this.y = y;
    this.distance = distance;
    this.visited = visited;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  public boolean[][] getVisited() {
    return visited;
  }

  public void setVisited(boolean[][] visited) {
    this.visited = visited;
  }
}