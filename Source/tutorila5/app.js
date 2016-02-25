/**
 * Created by ManojPrabhakar on 2/24/2016.
 */
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