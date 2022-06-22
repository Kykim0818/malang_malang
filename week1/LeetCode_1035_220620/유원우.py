class Solution:
    def maxUncrossedLines(self, nums1: List[int], nums2: List[int]) -> int:
        col_l = len(nums1)
        row_l = len(nums2)
        
        dp = [[0 for _ in range(col_l+1)] for _ in range(row_l+1)]
        
        for r_i in range(1, row_l+1):
            for c_i in range(1, col_l+1):
                if nums2[r_i-1] == nums1[c_i-1]:
                    dp[r_i][c_i] = 1 + dp[r_i-1][c_i-1]
                else:
                    dp[r_i][c_i] = max(dp[r_i-1][c_i], dp[r_i][c_i-1])
        return dp[row_l][col_l]