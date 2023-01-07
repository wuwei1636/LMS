$(function (){

    init()

    $("#add").click(function (){
        location.href = "/toAdminTeaAdd"
    })

    $("#search_l").click(function (){
        let teacher_id = $("#exampleInputName1").val();
        let name = $("#exampleInputName2").val();
        let faculty = $("#select1 option:selected").text();
        let professional_title = $("#select2 option:selected").text();

        $.ajax({
            type: "post",
            url: "http://localhost:8080/getTeaBy",
            data: {"teacher_id":teacher_id,"name":name ,"faculty":faculty ,"professional_title":professional_title ,},
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
            url: "http://localhost:8080/getAllTeacher",
            data: "",
            dataType: "json",
            success: function (response) {
                ajaa(response)
                console.log("1111")
                console.log(response)
            },

        });
    })

    $("#mytable").on('click',"button[id='btnn3']" ,function(){
        let text1 = $(this).parents("tr").find("#teacher_id").text();
        console.log(text1)
        let url = "/toAdminTeammsgView/"+text1;
        location.href = url
    })

    $("#mytable").on('click',"button[id='del']" ,function(){
        let text1 = $(this).parents("tr").find("#teacher_id").text();
        console.log(text1)
        $.ajax({
            type: "post",
            url: "http://localhost:8080/delTea",
            data: {"teacher_id":text1},
            dataType: "text",
            success: function (response) {
                alert(response)
                console.log(response)
                location.href = "/toAdminTeammsg";
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
            url: "http://localhost:8080/getAllTeacher",
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
            con += '<td id="teacher_id">'+ obj.teacher_id+'</td>';
            con += '<td>'+ obj.name+'</td>';
            con += '<td>'+ obj.faculty+'</td>';
            con += '<td>'+ obj.professional_title+'</td>';
            con += '<td>'+ '<button id="btnn3" class="btn btn-info">修改信息</button>'+
                '<button id="del" style="margin-left: 5px" class="btn btn-info">删除</button>'+'</td>';
            con += '</tr>';
        })

        $("#mytable").html(con)
    }

})