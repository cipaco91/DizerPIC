'use strict';

app.factory('User', function ($resource) {
    var User = $resource('rest/users/:userId',
        { userId: '@id'},
        {
            query: {method:'GET', params:{userId:''}, isArray:true},
            create: {method:'POST', params: {userId: ''}},
            update: {method:'PUT', params: {userId: '@id'}},
            remove: {method:'DELETE'}
        }
    );

    User.prototype.update = function (cb) {
        this.id = parseInt(this.id);
        return User.update({userId: this.id},
            angular.extend({}, this, {userId: undefined}), cb);
    };

    User.prototype.destroy = function (cb) {
        return User.remove({userId: this.id}, cb);
    };

    return User;
});

app.factory("UsersService", function ($http) {
    return {

        findAll: function () {
            return $http.get('rest/users');
        },
        login: function (providerId) {
            return $http.get('rest/login/'+providerId);
        },
        loginFacebook: function () {
            return $http.post('rest/connect/facebook').success(function (response) {
                return response;
            }).error(function(err){
                return err;
            });
        }
    }
});
