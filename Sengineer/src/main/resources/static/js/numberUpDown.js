$(document).ready(function ($) {
    init();
    $('.spinner .btn:first-of-type').on('click', function() {
        $('.spinner input').val( parseInt($('.spinner input').val(), 10) + 1);
        $('#termyear').text(parseInt($('.spinner input').val(), 10) + 1)
    });
    $('.spinner .btn:last-of-type').on('click', function() {
        $('.spinner input').val( parseInt($('.spinner input').val(), 10) - 1);
        $('#termyear').text(parseInt($('.spinner input').val(), 10) + 1)
    });

    function init() {
        $('#termyear').text(parseInt($('.spinner input').val(), 10) + 1)       
    }
    });