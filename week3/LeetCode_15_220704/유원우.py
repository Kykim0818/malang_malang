class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        answer = []
        nums.sort()
        
        for start in range(len(nums)-2):
            if start > 0 and nums[start] == nums[start]-1:
                continue
            else:
                l, r = start+1, len(nums) - 1
                while l < r:
                    cur_sum = nums[start] + nums[l] + nums[r]
                    if cur_sum < 0:
                        l += 1
                    elif cur_sum > 0:
                        r -= 1
                    else:
                        if [nums[start], nums[l], nums[r]] not in answer:
                            answer.append([nums[start], nums[l], nums[r]])
                        l += 1
                        r -= 1
                    
        return answer
