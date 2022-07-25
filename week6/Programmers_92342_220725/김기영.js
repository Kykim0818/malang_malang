function solution(n, info) {
    // 점수차이가 크게 이길 수 있는 경우의 기록 리스트 
    var answer = [];
    // 현재까지 얻은 score 차이  
    const MAX_SCORE = 10;
    let scoreGap = 0;
    let maxScoreGap = Number.MIN_VALUE;
    const rInfo = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    shoot(n, 0);
    console.log(answer);
    return answer;
    // 초기 ryon 과녁 명중 수 
    // inner fuction 
    function shoot(n, scoreIndex) {
        if (n < 0 || scoreIndex >= 10) return;

        scoreGap = getScoreGap(info, rInfo);
        if (scoreGap > 0) console.log(rInfo);
        if (n === 0 && scoreGap > 0 && maxScoreGap <= scoreGap) {
            if (maxScoreGap < scoreGap) {
                answer = [...rInfo];
            } else {
                answer.push([...rInfo]);
            }
            maxScoreGap = scoreGap;
        }
        if (n === 0) return;
        // 같은칸에 쏘는 경우는 , 이번 점수의 적 개수보다 적거나 같을 경우 
        if (info[scoreIndex] > rInfo[scoreIndex]) {
            rInfo[scoreIndex]++;
            shoot(n - 1, scoreIndex);
            rInfo[scoreIndex]--;
        }

        if (info[scoreIndex] === rInfo[scoreIndex]) {
            rInfo[scoreIndex]++;
            shoot(n - 1, scoreIndex);
            rInfo[scoreIndex]--;

            rInfo[scoreIndex + 1]++;
            shoot(n - 1, scoreIndex + 1);
            rInfo[scoreIndex + 1]--;
        }

        // 다음칸에 쏘기
        rInfo[scoreIndex + 1]++;
        shoot(n - 1, scoreIndex + 1);
        rInfo[scoreIndex + 1]--;


    }
    /** ainfo : 어피치점수,  rinfo : ryon info */
    function getScoreGap(aInfo, rInfo) {
        let ret = 0;
        for (let i = 0; i < 10; i++) {
            const aCount = aInfo[i];
            const rCount = rInfo[i];
            if (aCount >= rCount && aCount !== 0) ret -= (MAX_SCORE - i)
            if (aCount < rCount) ret += (MAX_SCORE - i);
        }
        return ret;
    }
}

// 현재 상황은 어피치가 화살 n발을 다 쏜 후이고 라이언이 화살을 쏠 차례입니다.
// 라이언은 어피치를 가장 큰 점수 차이로 이기기 위해서,
// n발의 화살을 어떤 과녁 점수에 맞혀야 하는지를 구하려고 합니다.

// 가장 작은 원의 과녁 점수는 10점이고,
// 가장 큰 원의 바깥쪽은 과녁 점수가 0점입니다.

// 만약, k(k는 1~10사이의 자연수)점을 어피치가 a발을 맞혔고 라이언이 b발을 맞혔을 경우 ,
// 더 많은 화살을 k점에 맞힌 선수가 k 점을 가져갑니다.

// 단, a = b일 경우는 어피치가 k점을 가져갑니다.
// k점을 여러 발 맞혀도 k점 보다 많은 점수를 가져가는 게 아니고 k점만 가져가는 것을 유의하세요.
// 또한 a = b = 0 인 경우, 즉, 라이언과 어피치 모두 k점에 단 하나의 화살도 맞히지 못한 경우는
// 어느 누구도 k점을 가져가지 않습니다.
// 예를 들어, 어피치가 10점을 2발 맞혔고 라이언도 10점을 2발 맞혔을 경우 어피치가 10점을 가져갑니다.
// 다른 예로, 어피치가 10점을 0발 맞혔고 라이언이 10점을 2발 맞혔을 경우 라이언이 10점을 가져갑니다.

// 최종 점수가 더 높은 선수를 우승자로 결정합니다.
// 단, 최종 점수가 같을 경우 어피치를 우승자로 결정합니다.


/** 쏘는 화살 수  */
const n = 5;
/** info의 i번째 원소는 과녁의 10 - i 점을 맞힌 화살 개수입니다.
 *  ( i는 0~10 사이의 정수입니다.) 
 * */
const info = [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0];
solution(n, info);
