function solution(id_list, report, k) {
    let reported={};
    let mails={};
    
    id_list.forEach((id)=>{reported[id]=new Set();
                         mails[id]=0;});
    
    report.forEach((name)=>{
        const names=name.split(' ');
        reported[names[1]].add(names[0]);
    })
    
    Object.keys(reported).forEach((name)=>{
        if(reported[name].size>=k) {
        for(let n of reported[name]){
            mails[n]++;
        }
        }
    })
    
    return Object.values(mails);
}
