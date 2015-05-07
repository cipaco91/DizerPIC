'use strict';

app.factory("FeedService", function ($http) {
    return {

        findFeedFacebook: function () {
            return $http.get('rest/feedsFacebook');
        },
        findFeedTwitter: function () {
            return $http.get('rest/feedsTwitter');
        },
        findFeedLinkedin: function () {
            return $http.get('rest/feedsLinkedin');
        },
        addComment: function (id,message) {
            return $http.post('rest/addComment/'+id+"/"+message);
        },
        addLike: function (id) {
            return $http.post('rest/addLike/'+id);
        }
    }
});