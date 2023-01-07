$(document).ready(function() {
    init();
    $("#radio1").click(function(){       
        if(this.checked){
            $("#termNum").attr("disabled","true");
            $('#termbtn').prop('disabled', "true");
            $('#termbtn2').prop('disabled', "true");
            $('select').attr("disabled","true");
        }
    });
    $("#radio2").click(function(){       
        if(this.checked){
            $("#termNum").removeAttr("disabled");
            $('#termbtn').attr('disabled', false);
            $('#termbtn2').attr('disabled', false);
            $('select').attr("disabled","true");
        }
    });
    $("#radio3").click(function(){       
        if(this.checked){
            $("#termNum").removeAttr("disabled");
            $('#termbtn').attr('disabled', false);
            $('#termbtn2').attr('disabled', false);
            $('select').removeAttr("disabled");
        }
    });

    function init() {      
        $("#termNum").attr("disabled","true");
        $('#termbtn').prop('disabled', "true");
        $('#termbtn2').prop('disabled', "true");
        $('select').attr("disabled","true");     
    }
    
})
