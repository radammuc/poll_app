package de.radammuc.cloud.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping(path = "hostname")
class HostnameController {

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String getHostname() throws UnknownHostException {
        String hostname = System.getenv("HOSTNAME");
        if (hostname == null) {
            hostname = System.getenv("COMPUTERNAME");
        }

        if (hostname == null) {
            InetAddress localHost = InetAddress.getLocalHost();

            hostname = localHost.getHostName();

            if (hostname == null || "localhost".equals(hostname)) {
                hostname = localHost.getHostAddress();
            }
        }

        return hostname;
    }

}
