$(document).ready(function () {
    var events = [];
    $.ajax({
        type: "GET",
        url: "/Api/GestionarAgenda/listarCitas/18",
        success: function (data) {
            $.each(data, function (i, v) {
                events.push({
                    title: v.act_nombre,
                    description: v.act_descripcion,
                    start: moment(v.act_inicio),
                    end: v.act_fin !== null ? moment(v.act_fin) : null
                });
            })
            GenerateCalender(events);
        },
        error: function (error) {
            alert('failed');
        }
    })

    function GenerateCalender(events) {
        $('#calender').fullCalendar('destroy');
        $('#calender').fullCalendar({
            contentHeight: 400,
            defaultDate: new Date(),
            timeFormat: 'h(:mm)a',
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,basicWeek,basicDay,agenda'
            },
            eventLimit: true,
            eventColor: '#378006',
            events: events,
            eventClick: function (calEvent, jsEvent, view) {
                $('#myModal #eventTitle').text(calEvent.title);
                var $description = $('<div/>');
                $description.append($('<p/>').html('<b>Inicio:</b>' + calEvent.start.format("DD-MMM-YYYY HH:mm a")));
                if (calEvent.end != null) {
                    $description.append($('<p/>').html('<b>Final:</b>' + calEvent.end.format("DD-MMM-YYYY HH:mm a")));
                }
                $description.append($('<p/>').html('<b>Descripción:</b>' + calEvent.description));
                $('#myModal #pDetails').empty().html($description);

                $('#myModal').modal();
            }
        })
    }
});