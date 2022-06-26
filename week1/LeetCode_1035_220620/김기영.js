/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var maxUncrossedLines = function (nums1, nums2) {
  const memo = new Array(nums1.length + 1).fill(0);
  for (let i = 0; i < memo.length; i++) {
    memo[i] = new Array(nums2.length + 1).fill(0);
  }
  for (let i = 1; i < nums1.length + 1; i++) {
    for (let j = 1; j < nums2.length + 1; j++) {
      if (nums1[i - 1] === nums2[j - 1]) {
        // 양쪽 선택이전 배열 크기에 라인 추가 되므로 + 1
        memo[i][j] = memo[i - 1][j - 1] + 1;
      } else {
        // 같지 않으면
        memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
      }
    }
  }
  console.table(memo);
  return memo[nums1.length][nums2.length];
};
// Sample

const nums1 = [1, 4, 2];
const nums2 = [1, 2, 4];
maxUncrossedLines(nums1, nums2);
