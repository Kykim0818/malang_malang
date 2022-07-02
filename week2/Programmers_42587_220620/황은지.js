function solution(priorities, location) {
    let count=0;
    while(priorities.length){
        let front=priorities[0];
        priorities.shift(); 
    if(front<Math.max.apply(null,priorities)){
        if(location===0) location=priorities.length;
        else location--;
        priorities.push(front);
    }else{
        count++;
        if(location===0) return count;
        else location --;
    }
    }
}
