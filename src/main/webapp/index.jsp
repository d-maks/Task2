<!doctype html>
<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>

<script>
	var formApp = angular.module('formApp', []);
	function formController($scope, $http) {
		$scope.processForm = function() {
			$http({
				method : 'POST',
				url : 'controller?page=search',
				data : $.param($scope.formData),
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function(data) {
				$scope.message = data.message;
			});
		};
		$scope.addForm = function() {
			$http({
				method : 'POST',
				url : 'controller?page=add',
				data : $.param($scope.formData),
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function(data) {
				$scope.addMessage = data.addMessage;
			});
		};

	}
</script>
</head>
<body ng-app="formApp" ng-controller="formController">
	<div class="col-md-4">

		<form ng-submit="processForm()">
			<div id="keyword-group" class="form-group">
				<label>Keyword</label> <input type="text" name="keyword"
					class="form-control" placeholder="" ng-model="formData.keyword">
			</div>

			<button type="submit" class="btn btn-success btn-lg btn-block">
				Search!</button>
		</form>
		<div id="messages" class="well" ng-show="message">{{ message }}</div>
	</div>
	<div class="col-md-4 col-md-offset-4">

		<form ng-submit="addForm()">
			<div id="class-group" class="form-group">
				<label>Class</label> <input type="text" name="class"
					class="form-control" placeholder="Class" ng-model="formData.class"
					required="required">
			</div>

			<div id="addKeyword-group" class="form-group">
				<label>Keyword</label> <input type="text" name="addKeyword"
					class="form-control" placeholder="Keyword"
					ng-model="formData.addKeyword" required="required">
			</div>

			<button type="submit" class="btn btn-success btn-lg btn-block">
				Add!</button>
		</form>
		<div id="addMessage" class="well" ng-show="addMessage">{{
			addMessage }}</div>
	</div>
</body>
</html>