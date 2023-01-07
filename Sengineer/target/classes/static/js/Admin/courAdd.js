$(function (){

    $("#sub1").click(function (){
        let course_name = $("#course_name").val().trim().replace(/\s/g,"");
        let course_id = $("#course_id").val().trim().replace(/\s/g,"");
        let teacher_id = $("#teacher_id").val().trim().replace(/\s/g,"");
        let teacher_name = $("#teacher_name").val().trim().replace(/\s/g,"");
        let credit_hour = $("#credit_hour").val().trim().replace(/\s/g,"");
        let credit = $("#credit").val().trim().replace(/\s/g,"");
        let year_semester = $("#year_semester").val().trim().replace(/\s/g,"");
        let classroom = $("#classroom").val().trim().replace(/\s/g,"");
        let session_time = $("#session_time").val().trim().replace(/\s/g,"");
        let isrequired = $("#isrequired").val().trim().replace(/\s/g,"");
        let specialized = $("#specialized").val().trim().replace(/\s/g,"");
        let assessment_method = $("#assessment_method").val().trim().replace(/\s/g,"");

        if(course_name == null || teacher_id == null || course_id == null
            || teacher_name == null   || credit_hour == null ||
            credit == null || year_semester == null || classroom == null ||
            session_time == null || isrequired == null || specialized == null
            || assessment_method == null  ||
            course_name == "" || teacher_id == "" || course_id == ""
            || teacher_name == ""  || credit_hour == ""||
            credit == "" || year_semester == "" || classroom == "" ||
            session_time == "" || isrequired == "" || specialized == ""
            || assessment_method == ""){
            alert("请填写完整信息");
        }else{
            course_id = Number(course_id)
            credit_hour = parseFloat(credit_hour)
            credit = Number(credit)

            let year = year_semester.substring(0,4);
            let semester = year_semester.substring(12,13);
            let time = 2;
            if(semester == "一") time = 1;
            else time = 2;

            if(isrequired == "是") isrequired = true;
            else isrequired = false

            if(specialized == "是") specialized = true;
            else specialized = false;

            $.ajax({
                type: "post",
                url: "http://localhost:8080/insertCour",
                data: {"course_name":course_name,"teacher_id":teacher_id,"course_id":course_id,"teacher_name":teacher_name,
                    "credit_hour":credit_hour,"credit":credit,"year":year,
                    "semester":time,"classroom":classroom,"session_time":session_time,
                    "isrequired":isrequired,"specialized":specialized,"assessment_method":assessment_method,
                    },
                dataType: "text",
                success: function (response) {
                    alert(response)
                    console.log(response)
                    location.href = "/toAdminCoursemsg";
                },

            });
        }
    })


})