package cz.project.c3.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

    /**
     * Только залогиненные
     *
     * @return
     */
    @RequestMapping(value = "/logged_user", method = RequestMethod.GET)
    public ResponseEntity<Void> getCurrentUser() {
        return ResponseEntity.ok().build();
    }

    /**
     * Все
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register() {
        return ResponseEntity.ok().build();
    }

    /**
     * Только сам себя
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Только админ
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }
}
