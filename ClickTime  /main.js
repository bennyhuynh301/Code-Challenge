function SearchCtrl($scope, $http, $templateCache) {
  var apibase = 'https://clicktime.herokuapp.com/api/1.0';
  var json_callback = '?callback=JSON_CALLBACK'
  var session_url = apibase+'/session'+json_callback;
  
  $scope.search = function() {
    $http({method: 'JSONP', url: session_url, cache: $templateCache}).
      success(function(data) {
        $scope.userName = data.UserName;
				$scope.userEmail = data.UserEmail;
        $scope.userId = data.UserID;
        $scope.companyId = data.CompanyID;
				getSearchResults($scope.userId, $scope.companyId, $scope.task);
      }).
      error(function(data, status) {
        $scope.data = data || "Request failed";
        $scope.status = status;
    });
  };

  function getSearchResults(userId, companyId, taskName) {
		var tasks_url = apibase+'/Companies/'+companyId+'/Users/'+userId+'/Tasks'+json_callback
		$scope.taskId = null;
		$http({method: 'JSONP', url: tasks_url, cache: $templateCache}).
      success(function(data) {
      	for(var i=0;i<data.length;i++){
					var task_obj = data[i];
					if (task_obj.Name == taskName){
						$scope.taskId = task_obj.TaskID;
						break;
					}
				}
				if ($scope.taskId != null) {
					findJobs(userId, companyId, $scope.taskId);
				}
      }).
      error(function(data, status) {
        $scope.data = data || "Request failed";
        $scope.status = status;
    });
  }

	function findJobs(userId, companyId, taskId) {
		var jobs_url = apibase+'/Companies/'+companyId+'/Users/'+userId+'/Jobs'+json_callback+'&withChildIDs=true';
		$scope.jobs = new Array();
		var client_ids = new Array();
		$http({method: 'JSONP', url: jobs_url, cache: $templateCache}).
      success(function(data) {
      	for(var i=0;i<data.length;i++){
					var job_obj = data[i];
					var task_list = job_obj.PermittedTasks;
					if (task_list.indexOf(taskId) !== -1) {
						$scope.jobs.push(job_obj.Name);
						client_ids.push(job_obj.ClientID)
					}	
				}
				findClients(userId, companyId, client_ids);
      }).
      error(function(data, status) {
        $scope.data = data || "Request failed";
        $scope.status = status;
    });
  }
	
	function findClients(userId, companyId, clientIds) {
		var clients_url = apibase+'/Companies/'+companyId+'/Users/'+userId+'/Clients'+json_callback;
		$scope.clients = new Array();
		$http({method: 'JSONP', url: clients_url, cache: $templateCache}).
      success(function(data) {
      	for(var i=0;i<data.length;i++){
					var client_obj = data[i];
					if (clientIds.indexOf(client_obj.ClientID) !== -1) {
						$scope.clients.push(client_obj.Name);
					}	
				}
      }).
      error(function(data, status) {
        $scope.data = data || "Request failed";
        $scope.status = status;
    });
  }	
}


