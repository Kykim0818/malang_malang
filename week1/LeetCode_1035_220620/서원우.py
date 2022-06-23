class Solution:
    def maxUncrossedLines(self, nums1: List[int], nums2: List[int]) -> int:
        dp = collections.defaultdict(int)
        a = len(nums1)
        b = len(nums2)
        
        for i in range(1,a+1):
            for j in range(1,b+1):
                if nums1[i-1] == nums2[j-1]:
                    dp[i,j] = dp[i-1, j-1] + 1
                else:
                    dp[i,j] = max(dp[i-1, j], dp[i,j-1])
                    
        return dp[a,b]