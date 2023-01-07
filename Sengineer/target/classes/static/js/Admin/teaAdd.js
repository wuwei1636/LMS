$(function (){

    $("#sub1").click(function (){
        let name = $("#name").val().trim().replace(/\s/g,"");
        let teacher_id = $("#teacher_id").val().trim().replace(/\s/g,"");
        let password = $("#password").val().trim().replace(/\s/g,"");
        let id_card = $("#id_card").val().trim().replace(/\s/g,"");
        let faculty = $("#faculty").val().trim().replace(/\s/g,"");
        let email = $("#email").val().trim().replace(/\s/g,"");
        let phone = $("#phone").val().trim().replace(/\s/g,"");
        let professional_title = $("#professional_title").val().trim().replace(/\s/g,"");

        if(name == null || teacher_id == null || password == null
            || faculty == null   || professional_title == null ||
            name == "" || teacher_id == "" || password == ""
            || faculty == ""  || professional_title == ""){
            alert("请至少填写工号，姓名，密码，所属学院 和 职称");
        }else{
            $.ajax({
                type: "post",
                url: "http://localhost:8080/insertTea",
                data: {"name":name,"teacher_id":teacher_id,"password":password,"id_card":id_card,
                    "faculty":faculty,"email":email,"phone":phone,
                    "professional_title":professional_title},
                dataType: "text",
                success: function (response) {
                    alert(response)
                    console.log(response)
                    location.href = "/toAdminTeammsg";
                },

            });
        }
    })


})