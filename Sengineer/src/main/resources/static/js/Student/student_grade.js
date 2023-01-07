$(function (){

    init();

    $("#edit").click(function (){
        if($("#radio1").is(":checked")){
            $.ajax({
                type: "post",
                url: "http://localhost:8080/getGrade",
                data: "",
                dataType: "json",
                success: function (response) {
                    console.log(response);
                    ajaa(response);

                }
            });

        }else if($("#radio2").is(":checked")){
            let termYear = $("#termNum").val();
            console.log(termYear);
            $.ajax({
                type: "post",
                url: "http://localhost:8080/getGrade",
                data: {"year":termYear},
                dataType: "json",
                success: function (response) {
                    console.log(response);
                    ajaa(response);

                }
            });
        }else if($("#radio3").is(":checked")){
            let termYear = $("#termNum").val();
            let semseter = $("#semester option:checked").text();
            let time = 1;
            if(semseter == "第一学期"){
                time = 1;
            }else if(semseter == "第二学期"){
                time = 2;
            }
            $.ajax({
                type: "post",
                url: "http://localhost:8080/getGrade",
                data: {"year":termYear,"semester":time},
                dataType: "json",
                success: function (response) {
                    // alert("获取成功");
                    console.log(response);
                    ajaa(response);

                }
            });
        }
    });


    function init(){
        $.ajax({
            type: "post",
            url: "http://localhost:8080/getGrade",
            data: "",
            dataType: "json",
            success: function (response) {
                console.log(response);
                ajaa(response);

            }
        });
    }

    function ajaa(response){
        let con = '';
        $.each(response,function(index,obj){

            let require = "";
            if(obj.isrequired == true){
                require = "必修课";
            }else {
                require = "选修课";
            }
            let specialized = "";
            if(obj.specialized == true){
                specialized = "专业基础平台课";
            }else {
                specialized = "公共基础平台课";
            }
            let grade ;
            if(obj.grade == 0){
                grade = "无"
            }else {
                grade = obj.grade;
            }
            console.log(grade)
            console.log(obj.grade == 0)
            console.log(obj.grade)
            console.log(response)
            con += '<tr>';
            con += '<td>'+(index + 1) + '</td>';
            con += '<td>'+ '['+obj.course_id+']'+obj.course_name +'</td>';
            con += '<td>'+ obj.credit+'</td>';
            con += '<td>'+ obj.credit_hour+'</td>';
            con += '<td>'+ specialized+'/'+require+'</td>';
            con += '<td>'+ '初修'+'</td>';
            con += '<td>'+ obj.assessment_method+'</td>';
            con += '<td>'+ '初修获得'+'</td>';
            con += '<td>'+ grade+'</td>';
            con += '<td>'+ '</td>';

            con += '</tr>';
        })
        $("#mytable").html(con)
    }
})

