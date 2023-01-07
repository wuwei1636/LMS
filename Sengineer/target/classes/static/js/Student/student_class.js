$(function (){

    init();

    $("#bbb").click(function (){
        let text = $("#Ssemesters option:selected").text();
        let year = text.substring(0,4);
        let semster = text.substring(12,13);
        let time ;
        if(semster == "二"){
            time = 2;
        }else {
            time = 1;
        }

        $.ajax({
            type: "post",
            url: "http://localhost:8080/getScore",
            data: {"year":year,"semester":time},
            dataType: "json",
            success: function (response) {
                let con = '';
                $.each(response,function(index,obj){
                    con += '<tr>';
                    con += '<td>'+ obj.course_id+'</td>';
                    con += '<td>'+ obj.course_name+'</td>';
                    con += '<td>'+ obj.teacher_name+'</td>';
                    con += '<td>'+ obj.credit+'</td>';
                    con += '<td>'+ obj.credit_hour+'</td>';
                    con += '<td>'+ obj.stu_num+'</td>';
                    con += '<td>'+ obj.session_time+'</td>';
                    con += '<td>'+ obj.classroom+'</td>';
                    con += '</tr>';
                })
                $("#mytable").html(con)
                console.log("1111")
                console.log(response)
            },

        });

    });


    function init(){
        $.ajax({
            type: "post",
            url: "http://localhost:8080/getScore",
            data: "",
            dataType: "json",
            success: function (response) {
                let con = '';
                $.each(response,function(index,obj){
                    con += '<tr>';
                    con += '<td>'+ obj.course_id+'</td>';
                    con += '<td>'+ obj.course_name+'</td>';
                    con += '<td>'+ obj.teacher_name+'</td>';
                    con += '<td>'+ obj.credit+'</td>';
                    con += '<td>'+ obj.credit_hour+'</td>';
                    con += '<td>'+ obj.stu_num+'</td>';
                    con += '<td>'+ obj.session_time+'</td>';
                    con += '<td>'+ obj.classroom+'</td>';
                    con += '</tr>';
                })
                $("#mytable").html(con)

                console.log(response);
                console.log("11111");
                console.log(typeof response);
            }
        });
    }
})