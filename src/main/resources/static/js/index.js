var loading;
var baseURL = '/';

var vm = new Vue({
    el: '#app',
    data: {
        title: null,
        flag: true,
        user: {},
        menuList:{}
    },
    mounted: function () {
        $.ajax({
            type: "POST",
            url: baseURL + "queryMenuList",
            contentType: "application/json",
            dataType: "json",
            success: function (r) {
                vm.menuList = r;
            },
            error: function (data) {
                console.info("error: " + data.responseText);
            }
        });
    },
    methods: {
        queryUserInfo: function () {
            vm.title = "个人资料";
            vm.flag = true;
            vm.user = {}
            $("#userManagement").modal({
                keyboard: false,
                show: true,
                backdrop: "static"
            });
            $.ajax({
                type: "POST",
                url: baseURL + "admin/queryUserByName",
                contentType: "application/json",
                dataType: "json",
                success: function (r) {
                    vm.user = r;
                },
                error: function (data) {
                    console.info("error: " + data.responseText);
                }
            });

        },
        setUser: function () {
            vm.flag = false;
            vm.title = "设置";
            vm.flag = false;
            vm.user = {}
            $("#userManagement").modal({
                keyboard: false,
                show: true,
                backdrop: "static"
            });
            $.ajax({
                type: "POST",
                url: baseURL + "admin/queryUserByName",
                contentType: "application/json",
                dataType: "json",
                success: function (r) {
                    vm.user = r;
                    vm.user.password=null;
                },
                error: function (data) {
                    console.info("error: " + data.responseText);
                }
            });

        },
        editBtn: function () {
            $.ajax({
                type: "POST",
                url: baseURL + "admin/update",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(vm.user),
                success: function (r) {
                    if (r.code === 0) {
                        alert('设置成功', function () {
                            $("#userManagement").modal('hide');
                        });
                    } else {
                        alert(r.msg);
                    }
                },
                error: function (data) {
                    console.info("error: " + data.responseText);
                }
            });
        },

        cancel: function () {
            $("#userManagement").modal('hide');
        }
    }
});