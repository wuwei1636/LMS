$(function (){


    init();

    $("#back").click(function (){
        let text = $("#inputWarning").val();
        text = parseFloat(text);
        console.log(text)
        let uu = "";
        $.ajax({
            type: "post",
            url: "http://localhost:8080/updateGra1",
            data: {"grade":text},
            dataType: "text",
            success: function (response) {
                // $("#inputWarning").attr("value",response)
                let ll = response.split(',');
                uu = Number(ll[2]);
                console.log(ll)
                alert(ll[0]);
                let url = "/toAdminCoursemsgView/"+uu;
                location.href = url
            },
        });
    })

    function init(){
        $.ajax({
            type: "post",
            url: "http://localhost:8080/getGraBytwo1",
            dataType: "json",
            success: function (response) {
                $("#inputWarning").attr("value",response)
            },

        });
    }



})