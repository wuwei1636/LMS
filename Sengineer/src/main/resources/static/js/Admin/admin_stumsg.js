$(function (){

    init()

    $("#add").click(function (){
        location.href = "/toAdminStuAdd"
    })

    $("#search_l").click(function (){
        let stu_id = $("#exampleInputName1").val();
        let name = $("#exampleInputName2").val();
        let year_age = $("#select1 option:selected").text();
        year_age = year_age.substring(0,4)
        let faculty = $("#select2 option:selected").text();
        console.log(year_age)
        console.log(name)
        console.log(stu_id)
        console.log(faculty)
        $.ajax({
            type: "post",
            url: "http://localhost:8080/getStuBy",
            data: {"stu_id":stu_id,"name":name ,"year_age":year_age ,"faculty":faculty ,},
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
            url: "http://localhost:8080/AllStu",
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
        let text1 = $(this).parents("tr").find("#stu_id").text();
        console.log(text1)
        let url = "/toAdminStumsgView/"+text1;
        location.href = url
    })

    $("#mytable").on('click',"button[id='del']" ,function(){
        let text1 = $(this).parents("tr").find("#stu_id").text();
        console.log(text1)
        $.ajax({
            type: "post",
            url: "http://localhost:8080/delStu",
            data: {"stu_id":text1},
            dataType: "text",
            success: function (response) {
                alert(response)
                console.log(response)
                location.href = "/toAdminStumsg";
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
            url: "http://localhost:8080/AllStu",
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
            con += '<td id="stu_id">'+ obj.stu_id+'</td>';
            con += '<td>'+ obj.name+'</td>';
            con += '<td>'+ obj.year_age+'级'+'</td>';
            con += '<td>'+ obj.faculty+'</td>';
            con += '<td>'+ obj.major+'</td>';
            con += '<td>'+ '<button id="btnn4" class="btn btn-info">修改信息</button>'+
                '<button id="del" style="margin-left: 5px" class="btn btn-info">删除</button>'+'</td>';
            con += '</tr>';
        })

        $("#mytable").html(con)
    }

})