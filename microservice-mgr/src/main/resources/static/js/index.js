var apiList = new Vue({
    el: '#api-list',
    data: {
        apis: []
    },
    mounted: function () {
        console.log("done");
        $.ajax({
            url: 'list/',
            datatype: 'json',
            type: "get",
            contentType: "application/json",
            success: function (data) {
                $.each($.parseJSON(data), function(i, v){
                    apiList.apis.push(v);
                });
            },
            error: function () {
                alert("访问数据错误");
            }
        });
    }
});

var machineList = new Vue({
    el: '#machine-list',
    data: {
        rows: []
    },
    mounted: function () {
        console.log("done");
        $.ajax({
            url: 'machine/list/',
            datatype: 'json',
            type: "get",
            contentType: "application/json",
            success: function (data) {
                $.each($.parseJSON(data), function(i, v){
                    machineList.rows.push(v);
                });
            },
            error: function () {
                alert("访问数据错误");
            }
        });
    }
});

var serviceList = new Vue({
    el: '#service-list',
    data: {
        rows: []
    },
    mounted: function () {
        console.log("done");
        $.ajax({
            url: 'service/list/',
            datatype: 'json',
            type: "get",
            contentType: "application/json",
            success: function (data) {
                $.each($.parseJSON(data), function(i, v){
                    serviceList.rows.push(v);
                });
            },
            error: function () {
                alert("访问数据错误");
            }
        });
    }
});

