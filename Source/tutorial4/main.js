/**
 * Created by ManojPrabhakar on 2/15/2016.
 */
var sampleapp = angular.module('sampleApp',['ngRoute']);

sampleapp.config(function($routeProvider){
    $routeProvider
        .when('/',{
            templateUrl: 'login.html'
        })
        .when('/welcome',{
            templateUrl: 'welcome.html'
        })
    .when('/register',{
        templateUrl: 'register.html'
    });
});

sampleapp.controller('register',function($scope,$location){
    $scope.submit = function() {
        $location.path('/register');
    }
});

sampleapp.controller('loginCtrl',function($scope,$location){
    $scope.submit = function(){
        var uname =  $scope.username;
        var pwd =  $scope.password;
        if ($scope.username == 'manoj'&& $scope.password =='manoj'){
            $location.path('/welcome');

        }

    };


});
sampleapp.controller('registerCtrl',function($scope,$location){
    $scope.submit = function(){
        var Fname =  $scope.Fname;
        var Lname   =  $scope.Lname;
        var Mname   =  $scope.Mname;
        var Address = $scope.Address;
        var Place = $scope.Place;
        var State = $scope.State;
        var email   =  $scope.email;
        var cnumber = $scope.CNumber;
        var znumber = $scope.zipcode;
        var Login   =  $scope.Login;
        var Password1 = $scope.Password1;
        var Password2 = $scope.Password2;
        if ( Password1== Password2 ){
            $location.path('/');

        }

            };


});

sampleapp.controller('Location',function($scope,$http, $sce){

    var map;
    var mapOptions;
    var directionsDisplay = new google.maps.DirectionsRenderer({
        draggable: true
    });
    var directionsService = new google.maps.DirectionsService();

    $scope.initialize = function () {
        var pos = new google.maps.LatLng(0, 0);
        var mapOptions = {
            zoom: 3,
            center: pos
        };

        map = new google.maps.Map(document.getElementById('map-canvas'),
            mapOptions);
    };
    $scope.submit = function () {

        var end = document.getElementById('To').value;
        var start = document.getElementById('From').value;
        var request = {
            origin: start,
            destination: end,
            travelMode: google.maps.TravelMode.DRIVING
        };

        directionsService.route(request, function (response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setMap(map);
                directionsDisplay.setDirections(response);
                console.log(status);
            }

        });

        $http.get("http://api.openweathermap.org/data/2.5/weather?q=" + start + "&appid=44db6a862fba0b067b1930da0d769e98").success(function(data) {
            console.log(data);
            temp = data.main.temp;
            temp2 = Math.round(temp - 273.15);
            tempweather = data.weather
            weather = tempweather[0].main
            console.log(temp);
            $scope.currentweather ="Source:   " + start + "  Currently " + temp2 + " &deg; C and " + weather + "";

        })
        $http.get("http://api.openweathermap.org/data/2.5/weather?q=" + end + "&appid=44db6a862fba0b067b1930da0d769e98").success(function(data) {
            console.log(data);
            Etemp = data.main.temp;
            Etemp2 = Math.round(Etemp - 273.15);
            Etempweather = data.weather
            Eweather = Etempweather[0].main
            console.log(Etemp);
            $scope.currentweather2 ="Destination:   " + end + "  Currently " + Etemp2 + " &deg; C and " + Eweather + "";

        })
    };

    google.maps.event.addDomListener(window, 'load', $scope.initialize);

    $scope.renderHtml = function(htmlCode){
        return $sce.trustAsHtml(htmlCode);
    }



});



