/**
 * 
 */
app.controller('FeedController',['$scope','fbService',function($scope,fbService){
 
	$scope.feeds=[];
	$scope.nameList=[];
	
	console.log(" app controller fired")
	var loadFeeds = function(){
		fbService.getFeeds().then(function(responce){
			$scope.feeds=responce.data[0];
			var names = [];
			console.log(responce.data[0]);

			responce.data[0].forEach(function(item){
	if(item.story !== undefined){
		
				var str1= item.story;
				var pattern = "added";
				var str2 = str1.substr(0,str1.indexOf(pattern));	
				console.log(str2);
				if(!(names.includes(str2))){
					  names.push(str2);	
				}
			
			}
	
			});
			
			console.log(names);
			$scope.nameList=names
			console.log($scope.nameList);
			
		},function(error){
			console.log("error_while_retreiving");
		})
	}
	
	loadFeeds();
	
}]);