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
        }
    }
});