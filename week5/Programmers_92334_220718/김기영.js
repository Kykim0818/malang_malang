
function solution(id_list, report, k) {
    // 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록 solution 함수를 완성해주세요.
    const reportTable = {};
    const 통보메일테이블 = {};
    // 유저별 , 해당 유저를 신고한 리스트
    id_list.forEach(id => {
        reportTable[id] = [];
        통보메일테이블[id] = 0;
    });
    // { muzi: [], frodo: [], apeach: [], neo: [] }

    report.forEach(eachReport => {
        const [신고한사람, 신고받은사람] = eachReport.split(' ');
        if (!reportTable[신고받은사람].includes(신고한사람)) {
            reportTable[신고받은사람].push(신고한사람);
        }
    })

    Object.entries(reportTable).forEach(([key, value]) => {
        if (value.length >= k) {
            value.forEach(id => {
                통보메일테이블[id]++;
            })
        }
    })
    var answer = [];
    id_list.forEach((id) => {
        answer.push(통보메일테이블[id]);
    })
    return answer;
}

// 이용자의 ID가 담긴 문자열 배열 id_list
// 2 ≤ id_list의 길이 ≤ 1,000
const id_list = ["muzi", "frodo", "apeach", "neo"];

// 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 report
// 1 ≤ report의 길이 ≤ 200,000
const report = ["muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"];

// 정지 기준이 되는 신고 횟수 k
// 1 ≤ k ≤ 200, k는 자연수입니다.
const k = 2;

console.log(solution(id_list, report, k));