function solution(n, k) {
let result=0;
    let num = n.toString(k);  
    const nums=num.split("0");

    nums.forEach((num)=>{
        if(num!==""){
        num=parseInt(num)
        let prime=true;
        if(num!==1){
            for(let i=2;i<=Math.sqrt(num);i++){
                if(num%i===0){
                    prime=false;
                    break;
                }
            }
            if(prime) result++;
        }}
    })
    return result;
}
