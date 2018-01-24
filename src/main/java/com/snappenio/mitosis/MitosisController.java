package com.snappenio.mitosis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MitosisController {

    @RequestMapping("")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Welcome to snappen.io!", HttpStatus.OK);
    }

}
