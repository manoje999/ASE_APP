// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('starter', ['ionic'])

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

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider

  // setup an abstract state for the tabs directive
    .state('tab.temp', {
    url: '/tab/temp',
    abstract: true,
    templateUrl: 'templates/activity.html'
  })
  $urlRouterProvider.otherwise('/tab/temp');
})
.controller('pagecontroller', function($scope, $http){
    
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
