package com.hw.controller.api.user;

import com.hw.model.User;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @RequestMapping(value = "{id}/mainInformation", method = RequestMethod.PUT)
    public ResponseEntity updateMainInformation(@PathVariable Long id,
                                                @RequestBody User user,
                                                PersistentEntityResourceAssembler assembler) {

        return null;
    }
}
