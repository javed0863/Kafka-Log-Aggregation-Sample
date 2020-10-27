package com.javedrpi.kaftaproducer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/publish/{msg}")
    public String publish(@PathVariable("msg") final String msg){
        logger.debug("Received Message: {}", msg);
        return "Published Successfully";
    }

}
