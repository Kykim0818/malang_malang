const lottos = [0, 0, 0, 0, 0, 0];
const win_nums = [38, 19, 20, 40, 15, 25];

function solution(lottos, win_nums) {
    var answer = [];
    let worstWin = 0;
    let bestWin = 0;
    let unMatchCount = 0;

    lottos.forEach(eachNumber => {
        if (eachNumber === 0) return;

        if (win_nums.includes(eachNumber)) {
            worstWin++;
        } else {
            unMatchCount++;
        }
    })
    bestWin = 6 - unMatchCount;
    answer = [getRank(bestWin), getRank(worstWin)];
    console.log(answer);
    return answer;

    function getRank(matchCount) {
        switch (matchCount) {
            case 6:
                return 1;
            case 5:
                return 2;
            case 4:
                return 3;
            case 3:
                return 4;
            case 2:
                return 5;
            default:
                return 6;
        }
    }
}


solution(lottos, win_nums);