public class Main {

  static int N, M;
  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};
  static boolean[][] visited;
  static List<Node> startList;
  static int[][] map;

  static class Node{
    int x, y, date;

    public Node(int x, int y, int date) {
      this.x = x;
      this.y = y;
      this.date = date;
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

    public int getDate() {
      return date;
    }

    public void setDate(int date) {
      this.date = date;
    }
  }

  public static void main(String[] args) throws IOException {
    inputData();
    getAnswer();
  }

  private static void getAnswer() {
    // 큐에 들어간 노드들은 다 같은 depth에서의 노드들이다
    Queue<Node> q = new LinkedList<>();
    boolean[][] visited = new boolean[N][M];
    for(Node node : startList){
      q.add(new Node(node.getX(), node.getY(), node.getDate()));
      visited[node.getX()][node.getY()] = true;
    }

    int answer = 0;
    while(!q.isEmpty()){
      Node cur =q.poll();
      for(int dir=0; dir<4; dir++){
        int nx = cur.getX() + dx[dir];
        int ny = cur.getY() + dy[dir];
        int nDate = cur.getDate() + 1;

        if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
        if(map[nx][ny] == -1) continue;
        if(visited[nx][ny]) continue;

        answer = Integer.max(answer, nDate);
        visited[nx][ny] = true;
        q.add(new Node(nx, ny, nDate));
      }
    }

    if(checkTomato(visited)) System.out.println(-1);
    else System.out.println(answer);
  }

  private static boolean checkTomato(boolean[][] visited) {
    boolean ret = false;
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        if(!visited[i][j] && map[i][j] == 0) ret = true;
      }
    }
    return ret;
  }

  private static void inputData() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] NM = br.readLine().split(" ");
    N = Integer.parseInt(NM[1]);
    M = Integer.parseInt(NM[0]);
    startList = new ArrayList<>();
    map = new int[N][M];

    for(int i=0; i<N; i++){
      String[] input = br.readLine().split(" ");
      for(int j=0; j<M; j++){
        map[i][j] = Integer.parseInt(input[j]);
        if(map[i][j] == 1) startList.add(new Node(i, j, 0));
      }
    }
  }
}