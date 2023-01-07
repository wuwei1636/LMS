$(function (){

    init()

    $("#add").click(function (){
        location.href = "/toAdminCourAdd"
    })

    $("#search_l").click(function (){
        let course_id = $("#exampleInputName1").val();
        let course_name = $("#exampleInputName2").val();
        let teacher_name = $("#exampleInputName2").val();
        console.log(course_id)
        console.log(course_name)
        console.log(teacher_name)
        $.ajax({
            type: "post",
            url: "http://localhost:8080/getCourseByMore",
            data: {"course_id":course_id,"course_name":course_name ,"teacher_name":teacher_name},
            dataType: "json",
            success: function (response) {
                ajaa(response)
                console.log("1111")
                console.log(response)
            },

        });

    })

    $("#search_all").click(function (){
        $.ajax({
            type: "post",
            url: "http://localhost:8080/getCourses",
            data: "",
            dataType: "json",
            success: function (response) {
                ajaa(response)
                console.log("1111")
                console.log(response)
            },

        });
    })

    $("#mytable").on('click',"button[id='btnn4']" ,function(){
        let text1 = $(this).parents("tr").find("#course_id").text();
        console.log(text1)
        let url = "/toAdminCoursemsgView/"+text1;
        location.href = url
    })

    $("#mytable").on('click',"button[id='del']" ,function(){
        let text1 = $(this).parents("tr").find("#course_id").text();
        console.log(text1)
        $.ajax({
            type: "post",
            url: "http://localhost:8080/delCour",
            data: {"course_id":text1},
            dataType: "text",
            success: function (response) {
                alert(response)
                console.log(response)
                location.href = "/toAdminCoursemsg";
            },
            error:function (){
                console.log("失败")
            }

        });
        console.log("11111")
        // location.href = "/toAdminStumsg";
    })

    function init(){
        $.ajax({
            type: "post",
            url: "http://localhost:8080/getCourses",
            data: "",
            dataType: "json",
            success: function (response) {
                ajaa(response)
                console.log("1111")
                console.log(response)
            },

        });
    }

    function ajaa(response){
        let con = '';
        $.each(response,function(index,obj){
            con += '<tr>';
            con += '<td>'+ (index + 1) +'</td>'
            con += '<td id="course_id">'+ obj.course_id+'</td>';
            con += '<td>'+ obj.course_name+'</td>';
            con += '<td>'+ obj.teacher_name+'</td>';
            con += '<td>'+ obj.credit+'</td>';
            con += '<td>'+ obj.credit_hour+'</td>';
            con += '<td>'+ obj.stu_num+'</td>';
            con += '<td>'+ obj.session_time+'</td>';
            con += '<td>'+ '<button id="btnn4" class="btn btn-info">查看详情</button>'+
                '<button id="del" style="margin-left: 5px" class="btn btn-info ">删除</button>'+'</td>';
            con += '</tr>';
        })

        $("#mytable").html(con)
    }

})