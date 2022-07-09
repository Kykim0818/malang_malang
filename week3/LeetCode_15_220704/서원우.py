# First attempt (time out)
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        ans = []
        for idx, first in enumerate(nums):
            rest_nums = nums[idx+1:]
            for jdx, second in enumerate(rest_nums):
                third = 0 - first - second
                if third in rest_nums[jdx+1:]:
                    combination = list((first,second,third))
                    ans.append(sorted(combination))
                    
        ans = [list(x) for x in set(tuple(x) for x in ans)]
        return(ans)

# Second attempt (two pointer)

class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        ans = []
        nums_length = len(nums)
        
        for i in range(nums_length-2):
            if i > 0 and nums[i] == nums[i-1]:
                continue

            l = i+1
            r = nums_length-1

            while l < r:

                sum_of_trp = nums[i] + nums[l] + nums[r]

                if sum_of_trp < 0:
                    l += 1
                elif sum_of_trp > 0:
                    r -= 1

                else:
                    ans.append([nums[i],nums[l],nums[r]])

                    while l<r and nums[l] == nums[l+1]:
                        l += 1
                    while l>r and nums[r] == nums[r-1]:
                        r -= 1

                    l += 1
                    r -= 1
                        
        return ans