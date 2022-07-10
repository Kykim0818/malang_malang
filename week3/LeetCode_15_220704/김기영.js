/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var threeSum = function (nums) {
  const answer = [];
  // nums 최소 3개이상

  // 3개
  if (nums.length === 3) {
    if (nums[0] + nums[1] + nums[2] === 0) {
      answer.push([...nums]);
      return answer;
    }
  }
  // 그 외
  else {
    // 전처리
    let plusArr = [];
    let minusArr = [];
    const numberCount = {};
    // 오름차순 정렬
    nums.sort((a, b) => a - b);
    nums.forEach((num) => {
      if (num > 0) {
        plusArr.push(num);
        addNumberCount(numberCount, num);
      } else if (num < 0) {
        minusArr.push(num);
        addNumberCount(numberCount, num);
      } else {
        addNumberCount(numberCount, num);
      }
    });
    console.log(numberCount[0]);
    // 1. 0 0 0 확인
    if (numberCount[0] >= 3) {
      answer.push([0, 0, 0]);
    }
    // 양수배열 이나 음수배열이 없는 경우 그대로 return
    if (plusArr.length === 0 || minusArr.length === 0) return answer;
    // 중복제거
    plusArr = [...new Set(plusArr)];
    minusArr = [...new Set(minusArr)];
    // case1. 양수 2개 음수 1개
    if (plusArr.length >= 1 && minusArr.length >= 1) {
      for (let i = 0; i < plusArr.length; i++) {
        if (numberCount[plusArr[i]] >= 2) {
          const key = (plusArr[i] + plusArr[i]) * -1;
          if (numberCount[key]) answer.push([plusArr[i], plusArr[i], key]);
        }
        for (let j = i + 1; j < plusArr.length; j++) {
          const key = (plusArr[i] + plusArr[j]) * -1;
          if (numberCount[key]) answer.push([plusArr[i], plusArr[j], key]);
        }
      }
    }
    // case2. 양수 1개 음수 2개
    if (plusArr.length >= 1 && minusArr.length >= 1) {
      for (let i = 0; i < minusArr.length; i++) {
        if (numberCount[minusArr[i]] >= 2) {
          const key = (minusArr[i] + minusArr[i]) * -1;
          if (numberCount[key]) answer.push([minusArr[i], minusArr[i], key]);
        }
        for (let j = i + 1; j < minusArr.length; j++) {
          const key = (minusArr[i] + minusArr[j]) * -1;
          if (numberCount[key]) answer.push([minusArr[i], minusArr[j], key]);
        }
      }
    }
    // case3. 양수 1개 0 음수 1개 -> 중복처리필요
    if (plusArr.length >= 1 && minusArr.length >= 1 && numberCount[0] >= 1) {
      for (let i = 0; i < plusArr.length; i++) {
        const key = plusArr[i] * -1;
        if (numberCount[key]) answer.push([key, 0, plusArr[i]]);
      }
    }
  }
  //
  console.log(answer);
  return answer;

  function addNumberCount(numberCount, num) {
    if (numberCount[num]) {
      numberCount[num]++;
    } else {
      numberCount[num] = 1;
    }
  }
};

const nums = [0, 0, 0];
threeSum(nums);
