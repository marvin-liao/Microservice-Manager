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
