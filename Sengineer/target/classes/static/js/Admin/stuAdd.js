$(function (){

    $("#sub1").click(function (){
        let name = $("#name").val().trim().replace(/\s/g,"");
        let stu_id = $("#stu_id").val().trim().replace(/\s/g,"");
        let password = $("#password").val().trim().replace(/\s/g,"");
        let id_card = $("#id_card").val().trim().replace(/\s/g,"");
        let classes = $("#classes").val().trim().replace(/\s/g,"");
        let faculty = $("#faculty").val().trim().replace(/\s/g,"");
        let major = $("#major").val().trim().replace(/\s/g,"");
        let email = $("#email").val().trim().replace(/\s/g,"");
        let phone = $("#phone").val().trim().replace(/\s/g,"");
        let year_age = $("#year_age").val().trim().replace(/\s/g,"");

        if(name == null || stu_id == null || password == null || classes == null
            || faculty == null  || major == null || year_age == null ||
            name == "" || stu_id == "" || password == "" || classes == ""
            || faculty == ""  || major == "" || year_age == ""){
            alert("请至少填写学号，姓名，密码，所属学院，专业和年级");
        }else{
            $.ajax({
                type: "post",
                url: "http://localhost:8080/insertStu",
                data: {"name":name,"stu_id":stu_id,"password":password,"id_card":id_card,
                    "classes":classes,"faculty":faculty,"major":major,"email":email,"phone":phone,
                    "year_age":year_age},
                dataType: "text",
                success: function (response) {
                    alert(response)
                    console.log(response)
                    location.href = "/toAdminStumsg";
                },

            });
        }
    })


})