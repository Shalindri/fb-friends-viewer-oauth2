/**
 * 
 */

app.factory('fbService', ['$http', '$q', function ($http, $q) {

    var serviveFactory = {
    	getCode: getCode,
    	getAccess:getAccess,
    	getFeeds,getFeeds
    }

    return serviveFactory;

    function getCode() {

        var deferred = $q.defer();
        var url = 'https://www.facebook.com/dialog/oauth?response_type=code&client_id=1681012872028869&redirect_uri=http%3A%2F%2Flocalhost%3A9000%2Foauthlogin&scope=public_profile%20user_posts%20user_friends%20user_photos'
        window.location.assign(url) ;
       
    }


    function getAccess(code) {
        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: 'http://localhost:9000/friendlistviewapp/callback?code='+code
        }).then(function (responce) {
            deferred.resolve(responce);

        }, function (errResponce) {
            deferred.reject(errResponce)
        });

        return deferred.promise;
    }
    
    function getFeeds() {
        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: 'http://localhost:9000/friendlistviewapp/feed'
        }).then(function (responce) {
            deferred.resolve(responce);

        }, function (errResponce) {
            deferred.reject(errResponce)
        });

        return deferred.promise;
    }


}]);