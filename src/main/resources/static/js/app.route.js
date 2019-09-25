/**
 * 
 */

app.config(function($stateProvider,$urlRouterProvider,$locationProvider){
	
	$urlRouterProvider.otherwise('/');
	
	$stateProvider
	.state('login',{
		url:"/login",
		templateUrl:"templates/oauthlogin.html",
		controller:'LoginController'
		
	})
	.state('auth',{
		url:"/oauthlogin?code",
		templateUrl:"templates/fbauth.html",
		controller:'ContentController'
	})
	.state('main',{
		url:"/main",
		templateUrl:"templates/dashboard.html"
	})
	
	
	$locationProvider.html5Mode(true);
});

