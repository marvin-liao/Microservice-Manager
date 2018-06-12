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
    },
    methods: {
        filterApi: function (event) {
            query = $("#api-query").val();
            result = []
            if(query.length > 0){
                for(index in this.apis){
                    api = this.apis[index];
                    if(api.title.indexOf(query) != -1){
                        result.push(api);
                    }
                }
            }
            if(result.length >0){
                this.apis = result;
            }
        },
        filterApiItem: function (api) {
            path = $("#api-item-query").val();
            result = []
            if(path.length > 0){
                for(i in api.items){
                    it = api.items[i];
                    if(it.path.indexOf(path) != -1){
                        result.push(it);
                    }
                }
            }
            if(result.length >0){
                api.items = result;
            }
        }
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

