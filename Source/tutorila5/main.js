/**
 * Created by ManojPrabhakar on 2/23/2016.
 */
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
    $scope.submit1 = function(){
        var sampleapp = angular.module('sampleApp',['ngRoute']);
        window.fbAsyncInit = function() {
            FB.init({
                appId      : '1664102320508268',
                xfbml      : true,
                version    : 'v2.5'
            });
        };

        (function(d, s, id){
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) {return;}
            js = d.createElement(s); js.id = id;
            js.src = "//connect.facebook.net/en_US/sdk.js";
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));


        FB.login(function(response) {
            if (response.authResponse) {
                console.log('Welcome!  Fetching your information.... ');
                FB.api('/me', function(response) {
                    console.log('Good to see you, ' + response.name + '.');
                });
            } else {
                console.log('User cancelled login or did not fully authorize.');
            }
        });



    }

});
sampleapp.controller('Location',function($scope,$http, $sce){
    $scope.venueList = new Array();
    var map;
    var mapOptions;
    //var directionsDisplay = new google.maps.DirectionsRenderer({
    //    draggable: true
    //});
    //var directionsService = new google.maps.DirectionsService();
    //
    $scope.initialize = function () {
        var pos = new google.maps.LatLng(0, 0);
        var mapOptions = {
            zoom: 3,
            center: pos,
            mapTypeId: google.maps.MapTypeId.TERRAIN
        };

        map = new google.maps.Map(document.getElementById('map-canvas'),
            mapOptions);
    };
    //map = new google.maps.Map(document.getElementById('map-canvas'),
    //    mapOptions);

    $scope.locatevenue = function (venue) {

        //$scope.venueList = [];
        cord = venue.coordinates;

            var marker = new google.maps.Marker({
                map: map,
                position: new google.maps.LatLng(cord[0], cord[1]),
                title: venue.city,
                mapTypeId: google.maps.MapTypeId.TERRAIN
            });

    //
    //        //marker.content = '<div class="infoWindowContent">' + venue.address + '</div>';
    //
    //        //google.maps.event.addListener(marker, 'click', function(){
    //        //    infoWindow.setContent('<h2>' + marker.title + '</h2>' + marker.content);
    //        //    infoWindow.open(map, marker);
    //        //});
    //
    //
    //        //$scope.venueList.push(marker);
    //
      }

    $scope.submit = function () {
        var start = document.getElementById('From').value;
        $http.get("http://api.walmartlabs.com/v1/stores?format=json&city=" + start + "&apiKey=qejgezm6355nt3vmhck3zw9c").success(function (data) {
            //$http.get("http://api.openweathermap.org/data/2.5/weather?q=" + start + "&appid=44db6a862fba0b067b1930da0d769e98").success(function(data) {
            console.log(data);
            Entdata = data;
            SAMPLE = Entdata[0].streetAddress;
            cord =  Entdata[0].coordinates;
            for (var i = 0; i < 5; i++) {
                $scope.venueList[i] = {
                    name: Entdata[i].name,
                    address: Entdata[i].streetAddress,
                    city: Entdata[i].city,
                    zipcode: Entdata[i].zip,
                    coordinates: Entdata[i].coordinates,
                };

            }


        });



    }

    google.maps.event.addDomListener(window, 'load', $scope.initialize);

    $scope.renderHtml = function(htmlCode){
        return $sce.trustAsHtml(htmlCode);
    }
});
