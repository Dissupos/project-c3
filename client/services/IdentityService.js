import ApiService from './ApiService.js';

let instance = null;
let user = null;

class IdentityService {
    constructor() {
        if (!instance) {
            instance = this;
        }

        this.getUserIdentity = this.getUserIdentity.bind(this);
        this.setUserIdentity = this.setUserIdentity.bind(this);

        return instance;
    }

    /**
     * Get saved user identity.
     * If no identity is saved, get identity from the server and return it.
     *
     * @returns {Promise}
     */
    getUserIdentity() {
        return new Promise((resolve, reject) => {
            if (!user) {
                ApiService.loggedUser().then((response) => {
                    user = response.data;
                    resolve(user);
                }).catch((reason) => {
                    reject(reason);
                });
            } else {
                resolve(user);
            }
        });
    }

    /**
     * Set identity.
     *
     * @param identity
     */
    setUserIdentity(identity) {
        user = identity;
    }
}

export default new IdentityService();
