$(document).ready(function () {
    init();

    $("#edit").click(function () {
        $(".editok").removeAttr("disabled");
        $('.select').removeAttr("disabled");
        //$('textarea').removeAttr("disabled");

        if (edit.innerText === "编辑") {

            edit.innerText = "取消"

        } else {
            edit.innerText = "编辑"

        }
    })
    $("#back").click(function () {

        $("input").attr("disabled", "true");
        $('select').attr("disabled","true");
        //$('textarea').attr("disabled","true");
    })

    function init() {

        $("input").attr("disabled", "true");
        $('select').attr("disabled", "true");
        $('textarea').attr("disabled", "true");
    }
})
