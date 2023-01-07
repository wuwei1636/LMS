$(function (){

    init();

    $("#edit").click(function () {
        let isSpecial = $("#isSpecial option:checked").text();
        let required = $("#required option:checked").text();
        let className = $("#className").val();

        let sp = 1;
        let re = 1;
        if(isSpecial == "专业课"){
            sp = 1;
        }else {
            sp = 0;
        }

        if(required == "必修"){
            re = 1;
        }else re = 0;


        console.log(isSpecial)
        console.log(required)
        console.log(className)

        $.ajax({
            type:"post",
            url: "http://localhost:8080/xkSearch",
            data: {"specialized":sp,"isrequired":re,"course_name":className},
            dataType: "json",
            success: function (response) {
                // alert("获取成功");
                console.log(response);
                ajaa(response);

            }
        });

    })

    $("#mytable").on('click',"button[id='edit1']",function (){

        let uu = $(this).parents("tr").find("#course_name").text();
        let te1 = uu.split('[')[1].split(']')[0];
        te1 = Number(te1);
        console.log(te1)

        $.ajax({
            type:"post",
            url: "http://localhost:8080/course_select",
            data: {"course_id":te1},
            dataType: "text",
            success: function (response) {
                alert(response);
                console.log(response);


            }
        });
    })

    $("#mytable").on('click',"button[id='edit2']",function (){

        let uu = $(this).parents("tr").find("#course_name").text();
        let te1 = uu.split('[')[1].split(']')[0];
        te1 = Number(te1);
        console.log(te1)

        $.ajax({
            type:"post",
            url: "http://localhost:8080/delSelect",
            data: {"course_id":te1},
            dataType: "text",
            success: function (response) {
                alert(response);

            }
        });
    })


    function  init(){
        $.ajax({
            type:"post",
            url: "http://localhost:8080/xkSearch",
            data: {"specialized":1,"isrequired":1,"course_name":""},
            dataType: "json",
            success: function (response) {
                // alert("获取成功");
                console.log(response);
                ajaa(response);

            }
        });
    }

    function ajaa(reponse){
        let con = '';
        $.each(reponse ,function (index,obj){

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

            con += '<tr>';
            con += '<td id="course_name">'+ '['+obj.course_id+']'+obj.course_name +'</td>';
            con += '<td>'+ obj.credit+'</td>';
            con += '<td>'+ obj.credit_hour+'</td>';
            con += '<td>'+ obj.teacher_name+'</td>';
            con += '<td>'+ obj.classroom+'</td>';
            con += '<td>'+ obj.session_time+'</td>';
            con += '<td>'+ specialized+'/'+require+'</td>';
            con += '<td>'+'<button type="button" class="btn btn-info" id="edit1" >选课</button>\n' +
'              <button type="button" class="btn btn-info" id="edit2" >退选</button>' +'</td>';

            con += '</tr>';

        })

        $("#mytable").html(con);
    }

})