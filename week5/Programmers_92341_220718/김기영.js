function solution(fees, records) {
  //1. 차번호목록,
  //2. 차번호별 이용시간 계산
  //3. 요금계산
  //4. return answer
  let carList = [];
  const carRecords = {};
  var answer = [];
  init();
  calculatePay();
  console.log(carRecords);
  console.log(answer);
  return answer;

  function init() {
    records.forEach((each) => {
      const [time, carNumber, state] = each.split(" ");
      carList.push(carNumber);
      if (carRecords[carNumber] === undefined) {
        carRecords[carNumber] = [{ time, state }];
      } else {
        carRecords[carNumber].push({ time, state });
      }
    });
    carList = [...new Set(carList)];
    // 차량번호가 작은순으로 정렬
    carList.sort((a, b) => {
      return Number(a) - Number(b);
    });
  }

  function calculatePay() {
    carList.forEach((carNumber) => {
      const his = carRecords[carNumber];
      if (his && his.length > 0) {
        let usingTime = 0;
        let start = "";
        his.forEach((each, index) => {
          if (each.state === "IN") {
            start = each.time;
            if (index === his.length - 1) {
              usingTime += getTimeStampGap(start, "23:59");
            }
          }
          if (each.state === "OUT") {
            usingTime += getTimeStampGap(start, each.time);
            start = "";
          }
        });
        //
        answer.push(getPay(usingTime));
      }
    });
  }
  /** a is earlier time than b */
  function getTimeStampGap(a, b) {
    let [aHour, aMin] = a.split(":");
    let [bHour, bMin] = b.split(":");
    let [rHour, rMin] = [0, 0];
    getRMin = () => {
      if (Number(aMin) > Number(bMin)) {
        bHour = Number(bHour) - 1;
        return 60 + Number(bMin) - Number(aMin);
      }
      return Number(bMin) - Number(aMin);
    };
    rMin = getRMin();
    rHour = Number(bHour) - Number(aHour);
    return 60 * rHour + rMin;
  }

  //
  function getPay(time) {
    // 기본 요금
    if (fees[0] >= time) return fees[1];
    let ret = fees[1];
    const overTime = time - fees[0];
    const overCount = Math.ceil(overTime / fees[2]);
    return (ret = ret + overCount * fees[3]);
  }
}
// 어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주합니다.
// 0000번 차량은 18:59에 입차된 이후, 출차된 내역이 없습니다. 따라서, 23:59에 출차된 것으로 간주합니다.
// 00:00부터 23:59까지의 입/출차 내역을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 정산합니다.
// 누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구합니다.
// 누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구합니다.
// 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림합니다.
// ⌈a⌉ : a보다 작지 않은 최소의 정수를 의미합니다. 즉, 올림을 의미합니다.
// 주차 요금을 나타내는 정수 배열 fees, 자동차의 입/출차 내역을 나타내는 문자열 배열 records가 매개변수로 주어집니다.
// 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return 하도록 solution 함수를 완성해주세요.

// fees[0] = 기본 시간(분)
// 1 ≤ fees[0] ≤ 1,439
// fees[1] = 기본 요금(원)
// 0 ≤ fees[1] ≤ 100,000
// fees[2] = 단위 시간(분)
// 1 ≤ fees[2] ≤ 1,439
// fees[3] = 단위 요금(원)
// 1 ≤ fees[3] ≤ 10,000
const fees = [180, 5000, 10, 600];
const records = [
  "05:34 5961 IN",
  "06:00 0000 IN",
  "06:34 0000 OUT",
  "07:59 5961 OUT",
  "07:59 0148 IN",
  "18:59 0000 IN",
  "19:09 0148 OUT",
  "22:59 5961 IN",
  "23:00 5961 OUT",
];
solution(fees, records);
