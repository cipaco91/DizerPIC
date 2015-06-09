'use strict';

app.factory("FriendsService", function ($http) {
    return {

        findFriendsFacebook: function (name) {
            return $http.get('rest/friendsFacebook/'+name);
        },
        findFriendsTwitter: function (name) {
            return $http.get('rest/friendsTwitter/'+name);
        },
        findFriendsLinkedin: function (name) {
            return $http.get('rest/friendsLinkedin/'+name);
        },
        findFriendsGoogle: function (name) {
            return $http.get('rest/friendsGoogle/'+name);
        },
        searchUsers: function (entity) {
            return $http.post('rest/searchUsers',entity);
        },
        searchUsersDTO: function () {
            return $http.get('rest/userDTO');
        },
        albumsProfile: function () {
            return $http.get('rest/albumsFacebook');
        }
    }
});