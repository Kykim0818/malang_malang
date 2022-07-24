// 0P0처럼 소수 양쪽에 0이 있는 경우
// P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
// 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
// P처럼 소수 양쪽에 아무것도 없는 경우
// 단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
// 예를 들어, 101은 P가 될 수 없습니다.

function solution(n, k) {
  var answer = 0;
  const nNum = K진수화(n, k);
  const 조건에맞는숫자 = [];
  //case 1 0P0처럼 소수 양쪽에 0이 있는 경우
  // 0을 찾고 , 0을 찾는다.
  let zeroFlag = false;
  let currentNum = [];
  for (let i = 0; i < nNum.length; i++) {
    if (nNum[i] === "0") {
      if (zeroFlag === true) {
        // 초기화
        조건에맞는숫자.push(currentNum.join(""));
        currentNum = [];
      }
      if (zeroFlag === false) zeroFlag = true;
    } else {
      if (zeroFlag === true) {
        currentNum.push(nNum[i]);
      }
    }
  }
  // case2 P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
  currentNum = [];
  for (let i = 0; i < nNum.length; i++) {
    if (nNum[i] !== "0") {
      currentNum.push(nNum[i]);
    } else {
      조건에맞는숫자.push(currentNum.join(""));
      break;
    }
  }
  // case 3. 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
  zeroFlag = false;
  currentNum = [];
  for (let i = 0; i < nNum.length; i++) {
    if (nNum[i] === "0") {
      if (zeroFlag) {
        currentNum = [];
      }
      zeroFlag = true;
      continue;
    } else {
      if (zeroFlag) {
        currentNum.push(nNum[i]);
      }
      if (i === nNum.length - 1) 조건에맞는숫자.push(currentNum.join(""));
    }
  }
  // case 4 P처럼 소수 양쪽에 아무것도 없는 경우
  currentNum = [];
  zeroFlag = false;
  for (let i = 0; i < nNum.length; i++) {
    if (nNum[i] === "0") {
      zeroFlag = true;
      break;
    }
  }
  if (zeroFlag === false) 조건에맞는숫자.push(currentNum.join(""));

  조건에맞는숫자.forEach((숫자) => {
    if (숫자 !== "") {
      const num = parseInt(숫자, 10);
      if (isPrime(num)) answer++;
    }
  });
  return answer;
  //
  function K진수화(n, k) {
    return n.toString(k);
  }
  // 소수 판정
  function isPrime(val) {
    if (val <= 1) return false;
    if (val === 2 || val === 3) return true;
    for (let i = 2; i <= Math.sqrt(val); i++) {
      if (val % i === 0) return false;
    }
    return true;
  }
}

const n = 110011;
const k = 10;

console.log(solution(n, k));

// 소수판정필요
// 소수 1과 자신외의 약수를 가지지 않는 수

// K진수 변환 필요
