$(function (){

    init();

    $("#search_l").click(function (){
        let stuid = $("#exampleInputName1").val();
        let stuname = $("#exampleInputName2").val();
        let year_age = $("#select1 option:checked").text();
        year_age = year_age.substring(0,4);
        let faculty = $("#select2 option:checked").text();

        console.log(stuid)
        console.log(stuname)
        console.log(year_age)
        console.log(faculty)

        $.ajax({
            type: "post",
            url: "http://localhost:8080/getStuGraCour1",
            data: {"stu_id":stuid,"name":stuname,"year_age":year_age,"faculty":faculty},
            dataType: "json",
            success: function (response) {
                ajaa(response)
            },

        });
    })

    $("#search_all").click(function (){
        $.ajax({
            type: "post",
            url: "http://localhost:8080/getStuGraCour1",
            data: "",
            dataType: "json",
            success: function (response) {
                ajaa(response)
            },

        });
    })

    $("#mytable").on('click',"button[id='btnn2']" ,function(){
        let text1 = $(this).parents("tr").find("#stu_id").text();
        text1 = Number(text1)
        console.log(text1)
        let url = "/toAdminCourseUpdate/"+text1;
        location.href = url
    })

    function init(){
        console.log("1111")
        $.ajax({
            type: "post",
            url: "http://localhost:8080/getStuGraCour1",
            data: "",
            dataType: "json",
            success: function (response) {
                ajaa(response)
            },

        });
    }

    function ajaa(response){

        let con = '';
        $.each(response,function(index,obj){

            let grade ;
            if(obj.grade == 0){
                grade = "无"
            }else {
                grade = obj.grade;
            }

            con += '<tr>';
            con += '<td>' + (index + 1)+'</td>';
            con += '<td id="stu_id">'+ obj.stu_id+'</td>';
            con += '<td>'+ obj.name+'</td>';
            con += '<td>'+ obj.year_age+'级'+'</td>';
            con += '<td>'+ obj.faculty+'</td>';
            con += '<td>'+ obj.major+'</td>';
            con += '<td>'+ grade+'</td>';
            con += '<td>'+ '<button id="btnn2" class="btn btn-info">修改成绩</button>'+'</td>';
            con += '</tr>';
        })

        $("#mytable").html(con)
        console.log("1111")
        console.log(response)
    }

})