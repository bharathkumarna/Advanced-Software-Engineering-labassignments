/**
 * Created by bn4n5 on 10/24/2016.
 */

var app=angular.module("calculate",[]);
app.controller("calculatectrl",function ($scope,$http) {
    $scope.price= function () {
        var currency=$scope.c;
        var amount=$scope.a;



        var words = $http.get("http://127.0.0.1:8081/getdata/"+currency+"/"+amount);
        words.success(function (data) {
            console.log(data);
           //alert(data.exchange[0].Goldrate);
            $scope.rate={"gold":data.exchange[0].Goldrate,"silver":data.exchange[0].Silverrate, "code":currency, "cost":amount};

        });
    }
});