package com.snappenio.mitosis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MitosisController {

    public String index() {
        return "Welcome to snappen.io!";
    }

}
