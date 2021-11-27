$(document).ready(function() {
    /// Show detail record
    $('.table .detail_record').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');


        $.get(href, function(record) {
            $('#r_staffname').html(record.staffs.name);
            $('#r_reason').html(record.reason);
            $('#r_type').html(record.type ? 'Thành tích' : 'Kỷ luật');
            $('#r_date').html($.date(record.date));
        });
        console.clear();
        $('#viewRecord').modal();

    });
    ///

    // Show detail staff
    $('.table .detail_staff').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function(staff) {
            $('#s_name').html(staff.name);
            $('#s_depart').html(staff.departs.name);
            $('#s_gender').html(staff.gender ? 'Nam' : 'Nữ');
            $('#s_birthday').html($.date(staff.birthday));
            $('#s_email').html(staff.email);
            $('#s_phone').html(staff.phone);
            $('#s_salary').html(staff.salary);
            $('#s_notes').html(staff.notes);
        });
        console.clear();
        $('#viewStaff').modal();
    });

    /// Format date
    $.date = function(dateObject) {
        var d = new Date(dateObject);
        var day = d.getDate();
        var month = d.getMonth() + 1;
        var year = d.getFullYear();
        if (day < 10) {
            day = "0" + day;
        }
        if (month < 10) {
            month = "0" + month;
        }
        var date = day + "/" + month + "/" + year;

        return date;
    };

});