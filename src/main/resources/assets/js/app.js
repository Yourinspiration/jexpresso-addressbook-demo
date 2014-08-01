var app = angular.module('AddressBook', ['ngResource']);

app.controller('Controller', ['$scope', 'Address', function($scope, Address) {
	$scope.currentAddress = {};
	
	$scope.addresses = Address.query();

	$scope.newAddress = function() {
		$scope.currentAddress = {};
		$('#addressModal').modal('show');
	}

	$scope.saveAddress = function() {
		Address.save($scope.currentAddress, function(address) {
			$scope.addresses = Address.query();
			$scope.currentAddress = {};
			$('#addressModal').modal('hide');
		});
	}
	
	$scope.editAddress = function(address) {
		$scope.currentAddress = address;
		$('#addressModal').modal('show');
	}
			
	$scope.deleteAddress = function(address) {
		Address.delete({id:address.id}, function() {
			$scope.addresses = Address.query();
		});
	}
}]);

app.factory('Address', ['$resource', function($resource) {
	return $resource('/address/:id');
}]);