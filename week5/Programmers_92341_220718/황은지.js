function convertTime(time){
const times=time.split(':');
const result=parseInt(times[0])*60+parseInt(times[1]);
return result;
}

function solution(fees, records) {
    var answer = [];
    let feeMap={};
    let timeStack={};
    let result=[];

    records.forEach(s => {
        const words = s.split(' ');
        const time=convertTime(words[0]);
        if(words[2]=="IN" && timeStack[words[1]]==undefined){
            timeStack[words[1]]=[time,0];
        }else{
        if(timeStack[words[1]][0]!==-1){
            timeStack[words[1]][1]+=(time-timeStack[words[1]][0]);
            timeStack[words[1]][0]=-1;
        }else{
            timeStack[words[1]][0]=time;
        }
    }
    });
    time =convertTime("23:59");
    let keyArr=Object.keys(timeStack);
 keyArr.forEach(key=>{
    if(timeStack[key][0]!==-1){
        timeStack[key][1]+=(time-timeStack[key][0]);
    }
    if(timeStack[key][1]<=fees[0]){
feeMap[key]=fees[1];
    }else{
        feeMap[key]=fees[1]+Math.ceil((timeStack[key][1]-fees[0])/fees[2])*fees[3]
    }
})
keyArr.sort();
keyArr.forEach(s=>{
result.push(feeMap[s]);
})

    return result;
}
