package de.radammuc.cloud.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "ping")
class PingController {

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String ping() {
        return "pong";
    }

}
