/**
 * @param {number[][]} grid
 * @return {number}
 */

// 1은 출발점
// 2는 도착점
// 0은 갈수잇는 곳
// -1은 못가는 곳
// 모든 칸을 한번만지나서 도착할수 잇는 경로의 수를 반환
var uniquePathsIII = function (grid) {
  const dr = [0, 0, -1, 1];
  const dc = [1, -1, 0, 0];

  let start;
  let goal;
  let canGoSquare = 0;
  let answer = 0;

  const sizeRow = grid.length;
  const sizeCol = grid[0].length;

  const visitGrid = Array.from(Array(sizeRow), () =>
    new Array(sizeCol).fill(false)
  );

  grid.forEach((row, rIdx) => {
    row.forEach((block, cIdx) => {
      if (block === 1) {
        start = [rIdx, cIdx];
      }
      if (block === 2) {
        goal = [rIdx, cIdx];
        canGoSquare++;
      }
      if (block === 0) canGoSquare++;
    });
  });
  visitGrid[start[0]][start[1]] = true;
  dfs(start[0], start[1], 0);

  return answer;

  // inner
  function dfs(row, col, count) {
    // 목적지일때
    if (row === goal[0] && col === goal[1]) {
      if (count === canGoSquare) {
        answer++;
      }
      return;
    }
    // 방문처리
    for (let j = 0; j < 4; j++) {
      const movRow = row + dr[j];
      const movCol = col + dc[j];
      if (
        isInBorder(movRow, movCol) &&
        visitGrid[movRow][movCol] === false &&
        grid[movRow][movCol] !== -1
      ) {
        visitGrid[movRow][movCol] = true;
        dfs(movRow, movCol, count + 1);
        visitGrid[movRow][movCol] = false;
      }
    }
  }
  function isInBorder(row, col) {
    return row >= 0 && col >= 0 && row < sizeRow && col < sizeCol;
  }
};

const grid = [
  [0, 1],
  [2, 0],
];
uniquePathsIII(grid);
