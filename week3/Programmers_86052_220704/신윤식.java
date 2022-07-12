import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

  private static final char S = 'S';
  private static final char L = 'L';
  private static final char R = 'R';
  private static final int DIRECTION_TO_DOWN = 0;
  private static final int DIRECTION_TO_UP = 1;
  private static final int DIRECTION_TO_LEFT = 2;
  private static final int DIRECTION_TO_RIGHT = 3;
  private char[][] map;
  private boolean[][][] visited;
  private int xLength;
  private int yLength;
  private List<Integer> answer;


  public int[] solution(String[] grid) {
    answer = new ArrayList<>();

    xLength = grid.length;
    yLength = grid[0].length();

    map = new char[xLength][yLength];
    for (int i = 0; i < xLength; i++) {
      for (int j = 0; j < yLength; j++) {
        map[i][j] = grid[i].charAt(j);
      }
    }

    visited = new boolean[xLength][yLength][4];
    for (int i = 0; i < xLength; i++) {
      for (int j = 0; j < yLength; j++) {
        for (int k = 0; k < 4; k++) {
          move(new Path(i, j, k, 0));
        }
      }
    }

    Collections.sort(answer);
    return answer.stream().mapToInt(i->i).toArray();
  }

  private void move(Path path) {
    while (!visited[path.x][path.y][path.direction]) {
      visited[path.x][path.y][path.direction] = true;

      if (map[path.x][path.y] == S) {
        path = moveStraight(path);
      } else if (map[path.x][path.y] == R) {
        path = moveRight(path);
      } else if (map[path.x][path.y] == L) {
        path = moveLeft(path);
      }

      path.count++;
    }

    if (path.count > 0) {
      answer.add(path.count);

    }
  }

  private Path moveLeft(Path path) {
    if (path.direction == DIRECTION_TO_DOWN) {
      path.y = (path.y + 1) % yLength;
      path.direction = DIRECTION_TO_RIGHT;

      return path;
    }

    if (path.direction == DIRECTION_TO_UP) {
      path.y = (path.y + yLength - 1) % yLength;
      path.direction = DIRECTION_TO_LEFT;

      return path;
    }

    if (path.direction == DIRECTION_TO_RIGHT) {
      path.x = (path.x + xLength - 1) % xLength;
      path.direction = DIRECTION_TO_UP;

      return path;
    }

    path.x = (path.x + 1) % xLength;
    path.direction = DIRECTION_TO_DOWN;

    return path;
  }

  private Path moveRight(Path path) {
    if (path.direction == DIRECTION_TO_DOWN) {
      path.y = (path.y + yLength - 1) % yLength;
      path.direction = DIRECTION_TO_LEFT;

      return path;
    }

    if (path.direction == DIRECTION_TO_UP) {
      path.y = (path.y + 1) % yLength;
      path.direction = DIRECTION_TO_RIGHT;

      return path;
    }

    if (path.direction == DIRECTION_TO_RIGHT) {
      path.x = (path.x + 1) % xLength;
      path.direction = DIRECTION_TO_DOWN;

      return path;
    }

    path.x = (path.x + xLength - 1) % xLength;
    path.direction = DIRECTION_TO_UP;
    return path;
  }

  private Path moveStraight(Path path) {
    if (path.direction == DIRECTION_TO_DOWN) {
      path.x = (path.x + 1) % xLength;

      return path;
    }

    if (path.direction == DIRECTION_TO_UP) {
      path.x = (path.x + xLength - 1) % xLength;

      return path;
    }

    if (path.direction == DIRECTION_TO_RIGHT) {
      path.y = (path.y + 1) % yLength;

      return path;
    }

    path.y = (path.y + yLength - 1) % yLength;
    return path;
  }

  class Path {
    int x;
    int y;
    int direction;
    int count;

    public Path(int x, int y, int direction, int count) {
      this.x = x;
      this.y = y;
      this.direction = direction;
      this.count = count;
    }
  }
}