import jQuery from 'jquery';

const BASE_URL = window.location.protocol + '//' + window.location.host;
const $ = jQuery;

/**
 * Helper function used to send AJAX request.
 *
 * @param {string} method
 * @param {string} url
 * @param {Object} data
 * @param {Object} headers
 * @returns {Promise}
 */
const request = (method, url, data = {}, headers = {}) => {
    return new Promise((resolve, reject) => {
        let options = {};

        options.url = `${BASE_URL}${url}`;
        options.type = method;
        options.data = data;
        options.success = (response) => {
            resolve(response);
        };
        options.error = (reason) => {
            reject(reason);
        };

        if (headers['Content-type']) {
            options.contentType = headers['Content-type'];
        } else if (method === 'POST') {
            options.contentType = 'application/json';
        }

        $.ajax(options);
    });
};
/**
 * /login POST
 * /logout GET
 * /info /metrics /health /mappings /trace GET
 *
 * /api/logged_user GET
 * /api/register POST
 * /api/users/:id PUT (update account)
 * /api/users/:id DELETE (delete account)
 *
 * /api/offers? GET
 * /api/meetups? GET
 *
 * /api/offers POST
 * /api/offers/:offerId PUT
 * /api/offers/:offerId/join GET
 * /api/offers/:offerId/leave GET
 * /api/offers/:offerId DELETE
 *
 * /api/meetups POST
 * /api/meetups/:meetupId/join GET
 * /api/meetups/:meetupId/leave GET
 * /api/meetups/:meetupId/cancel GET
 * /api/meetups/:meetupId PUT
 */
const apiService = {
    /**
     * Login to the application.
     *
     * @param {string} username
     * @param {string} password
     * @returns {Promise}
     */
    login({username, password}) {
        return request('POST', '/login', {username, password}, {'Content-Type': 'application/x-www-form-urlencoded'});
    },

    /**
     * Logout from the application.
     *
     * @returns {Promise}
     */
    logout() {
        return request('GET', '/logout');
    },

    /**
     * Get logged user data.
     *
     * @returns {Promise}
     */
    loggedUser() {
        return request('GET', '/api/users/logged_user');
    },

    /**
     * Register new user.
     *
     * @param {Object} data
     * @returns {Promise}
     */
    register(data) {
        return request('POST', '/api/users/register', data);
    },

    /**
     * Update user data.
     *
     * @param {number} id
     * @param {Object} data
     * @returns {Promise}
     */
    updateUserData(id, data) {
        return request('PUT', `/api/users/${id}`, data);
    },

    /**
     * Delete user.
     *
     * @param {number} id
     * @returns {Promise}
     */
    deleteUser(id) {
        return request('DELETE', `/api/user/${id}`);
    },

    /**
     * Get offers.
     *
     * @param {Object} params
     * @returns {Promise}
     */
    getOffers(params) {
        return request('GET', '/api/offers', params);
    },

    /**
     * Get meetups.
     *
     * @param {Object} params
     * @returns {Promise}
     */
    getMeetups(params) {
        return request('GET', '/api/meetups', params);
    },

    /**
     * Create offer.
     *
     * @param {Object} data
     * @returns {Promise}
     */
    createOffer(data) {
        return request('POST', '/api/offers', data);
    },

    /**
     * Create meetup.
     *
     * @param {Object} data
     * @returns {Promise}
     */
    createMeetup(data) {
        return request('POST', '/api/meetups', data);
    },

    /**
     * Delete offer.
     *
     * @param {number} id
     * @returns {Promise}
     */
    deleteOffer(id) {
        return request('DELETE', `/api/offers/${id}`);
    },

    /**
     * Cancel meetup.
     *
     * @param {number} id
     * @returns {Promise}
     */
    cancelMeetup(id) {
        return request('GET', `/api/meetups/${id}/cancel`);
    },

    /**
     * Join offer.
     *
     * @param {number} id
     * @returns {Promise}
     */
    joinOffer(id) {
        return request('GET', `/api/offers/${id}/join`);
    },

    /**
     * Leave offer.
     *
     * @param {number} id
     * @returns {Promise}
     */
    leaveOffer(id) {
        return request('GET', `/api/offers/${id}/leave`);
    },

    /**
     * Join meetup.
     *
     * @param {number} id
     * @returns {Promise}
     */
    joinMeetup(id) {
        return request('GET', `/api/meetups/${id}/join`);
    },

    /**
     * Leave meetup.
     *
     * @param {number} id
     * @returns {Promise}
     */
    leaveMeetup(id) {
        return request('GET', `/api/meetups/${id}/leave`);
    },

    /**
     * Update offer.
     *
     * @param {number} id
     * @param {Object} data
     * @returns {Promise}
     */
    updateOffer(id, data) {
        return request('PUT', `/api/offers/${id}`, data);
    },

    /**
     * Update meetup.
     *
     * @param {number} id
     * @param {Object} data
     * @returns {Promise}
     */
    updateMeetup(id, data) {
        return request('PUT', `/api/meetups/${id}`, data);
    }
};

export default apiService;
