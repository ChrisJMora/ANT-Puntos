package com.demo.consultas_ant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ANTController {
    private final ANTService ANT_SERVICE = new ANTService();

    @GetMapping(value = "/ant/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response GetLicensePoints(@RequestParam String id) {
        return ANT_SERVICE.LicencePoints(id);
    }
}