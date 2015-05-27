'use strict';

app.factory("FriendsService", function ($http) {
    return {

        findFriendsFacebook: function () {
            return $http.get('rest/friendsFacebook');
        },
        findFriendsTwitter: function () {
            return $http.get('rest/friendsTwitter');
        },
        findFriendsLinkedin: function () {
            return $http.get('rest/friendsLinkedin');
        },
        findFriendsGoogle: function () {
            return $http.get('rest/friendsGoogle');
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