function solution(N, number) {
    let numSet=new Array(9).fill().map(()=>new Set());
    
    
    for(let i=1;i<=8;i++){
        numSet[i].add(Number(N.toString().repeat(i)));
        for (let j=1;j<i;j++){
            for(num1 of numSet[j]){
                for(num2 of numSet[i-j]){
                    numSet[i].add(num1+num2);
                    numSet[i].add(num1-num2);
                    numSet[i].add(num1*num2);
                    numSet[i].add(num1/num2);
                }
            }
        }
        if(numSet[i].has(number))return i;
    }
    return -1;
}
