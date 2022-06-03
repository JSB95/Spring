function write(){
    console.log("write in");
    $.ajax({
        url : 'save',
        data : {"content" : $("#content").val()},
        success : function(re){
            alert("java 통신 : " + re);
        }

    })

}

let list = [];

function call(){
    $.ajax({
        url : 'getlist',
        success : function(re){
            list = re;
            console.log("list[0]['content']");
            let html = "<tr>" +
                           "<td>번호</td>" +
                           "<td>내용</td>" +
                           "<td>비고</td>" +
                       "</tr>"
            for (let i = 0; i < list.length; i++){
                html += "<tr>" +
                             "<td>" + list[i]['no'] + "</td>" +
                             "<td>" + list[i]['content'] + "</td>" +
                             "<td><button onclick=\"update(" + list[i]['no'] + ")\">수정</button> </td>" +
                         "</tr>";
            }
            $("#viewtable").html(html);
        }
    })
}

function update(no){
    $.ajax({
        url : "update",
        data : {"no" : no, "content" : $("#content").val()},
        success : function(re){
            if (re == "1") { alert("수정성공"); }
            else {alert("수정실패")}
        }
    })

}