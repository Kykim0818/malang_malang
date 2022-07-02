function numToRank(num){
    if(num<=1) return 6;
    else return (7-num);
}

function solution(lottos, win_nums) {
    let result=[];
    let wins={};
    win_nums.forEach(num=>{
        wins[num]=true;
    })
    // return wins
    let correct=0;
    let temp=0;
    lottos.forEach(lotto=>{
        if(Object.keys(wins).includes(String(lotto))) correct++;
        else if(lotto===0) temp++;
    })
    result.push(numToRank(correct+temp),numToRank(correct));
    return result
}
