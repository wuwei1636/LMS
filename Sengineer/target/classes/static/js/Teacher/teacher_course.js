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

        console.log(time)
        console.log(year)

        $.ajax({
            type: "post",
            url: "http://localhost:8080/getAllTeach",
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
                    con += '<td>'+ '<button id="btnn1" class="btn btn-info">查看详情</button>'+'</td>';

                    con += '</tr>';
                })
                $("#mytable").html(con)
                console.log("1111")
                console.log(response)
            },

        });

    })

    $("#mytable").on('click',"button[id='btnn1']" ,function(){
        let text1 = $(this).parents("tr").find("#course_id").text();
        text1 = Number(text1)
        console.log(text1)
        let url = "/toTeacherCourseView/"+text1;
        location.href = url
    })

    function init(){
        $.ajax({
            type: "post",
            url: "http://localhost:8080/getAllTeach",
            data: {"year":"2022","semester":1},
            dataType: "json",
            success: function (response) {
                let con = '';
                $.each(response,function(index,obj){
                    con += '<tr>';
                    con += '<td id="course_id">'+ obj.course_id+'</td>';
                    con += '<td>'+ obj.course_name+'</td>';
                    con += '<td>'+ obj.teacher_name+'</td>';
                    con += '<td>'+ obj.credit+'</td>';
                    con += '<td>'+ obj.credit_hour+'</td>';
                    con += '<td>'+ obj.stu_num+'</td>';
                    con += '<td>'+ obj.session_time+'</td>';
                    con += '<td>'+ obj.classroom+'</td>';
                    con += '<td>'+ '<button id="btnn1" class="btn btn-info">查看详情</button>'+'</td>';
                    con += '</tr>';
                })

                $("#mytable").html(con)
                console.log("1111")
                console.log(response)
            },

        });
    }

})