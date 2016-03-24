// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('starter', ['ionic','ngCordova'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    if(window.cordova && window.cordova.plugins.Keyboard) {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);

      // Don't remove this line unless you know what you are doing. It stops the viewport
      // from snapping when text inputs are focused. Ionic handles this internally for
      // a much nicer keyboard experience.
      cordova.plugins.Keyboard.disableScroll(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})
.config(function($stateProvider, $urlRouterProvider) {
 
  $stateProvider
  .state('map', {
    url: '/map',
    templateUrl: 'templates/map.html',
    controller: 'MapCtrl'
  })
 .state('register', {
    url: '/register',
        templateUrl: 'templates/register.html',
//        controller: 'AccountCtrl'
    
  });
  $urlRouterProvider.otherwise("/register");
 
})

.controller('MapCtrl', function($scope, $state, $cordovaGeolocation) {
  var options = {timeout: 10000, enableHighAccuracy: true};
 
  $cordovaGeolocation.getCurrentPosition(options).then(function(position){
	      var lat = position.coords.latitude;
    var lng = position.coords.longitude;
 
    var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
	  
	  var address;
	  var geocoder = geocoder = new google.maps.Geocoder();
                geocoder.geocode({ 'latLng': latLng }, function (results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
						console.log(results);
                        if (results[1]) {
						 address = results[0].formatted_address;
												}
					}
				});
	  
	  
	  
	  
	  
	  
	  
 
    var mapOptions = {
      center: latLng,
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
 
    $scope.map = new google.maps.Map(document.getElementById("map"), mapOptions);
	  google.maps.event.addListenerOnce($scope.map, 'idle', function(){
 
  var marker = new google.maps.Marker({
      map: $scope.map,
      animation: google.maps.Animation.DROP,
      position: latLng
  });      
		    var infoWindow = new google.maps.InfoWindow({
      content: address
  });
 
  google.maps.event.addListener(marker, 'click', function () {
      infoWindow.open($scope.map, marker);
  });
 
});
 
  }, function(error){
    console.log("Could not get location");
  });
})

.controller('pagecontroller', function($scope, $http, $location){
	
	$scope.Locate=function(){
		$location.path('/map');
		
	}
	
    
    $scope.getplaces=function(){
        var start = document.getElementById('city').value;
        $http.get("http://api.walmartlabs.com/v1/stores?format=json&city=" + start + "&apiKey=qejgezm6355nt3vmhck3zw9c").success(function (data) {
            console.log(data);
            Entdata = data;
			
            Review = [{}];
			Review[0]="Always the fresh stock is available";
			Review[1]="The Last Time I bougth the chicken leg quarters they were not so good";
			Review[2]="The prices here vary  a lot";
			Review[3]="Frozen items are not good here";
				Review[4]="Vegetables here are very good";
            newname = [{}];
			 address = [{}];
                city = [{}];
                type = [{}];
            zipcode=  [{}];
            
            for (var i = 0; i < 5; i++) {
                
                newname[i]=Entdata[i].name;
                address[i]= data[i].streetAddress;
                    city[i]= Entdata[i].city;
                    zipcode[i]= Entdata[i].zip;
            }
            $scope.chats = [{
                    name: newname[0],
                    address: address[0],
                    city: city[0],
                    zipcode: zipcode[0],
				    review:  Review[0]
                }, {
                    name: newname[1],
                    address: address[1],
                    city: city[1],
                    zipcode: zipcode[1],
					review:  Review[1]
                }, {
                    name: newname[2],
                    address: address[2],
                    city: city[2],
                    zipcode: zipcode[2],
					review:  Review[2]
                }, {
                    name: newname[3],
                    address: address[3],
                    city: city[3],
                    zipcode: zipcode[3],
					review:  Review[3]
                }, {
                    name: newname[4],
                    address: address[4],
                    city: city[4],
                    zipcode: zipcode[4],
					review:  Review[4]
                }];
    })
    
    }
    
});
