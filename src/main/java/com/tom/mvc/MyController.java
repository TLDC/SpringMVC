package com.tom.mvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by thomasc on 11/04/2016.
 */



@Controller
public class MyController {

    private static final MediaType[] mediaTypes = {MediaType.APPLICATION_JSON, MediaType.TEXT_HTML};

    @RequestMapping(path="/test", method= RequestMethod.GET, produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_HTML_VALUE})
    public String foo(ModelMap modelmap) {
        //MyModel is a simple POJO with one int property called property
        MyModel myModel = new MyModel();
        myModel.setProperty(1);
        modelmap.addAttribute("view", myModel);
        //Either returns WEB-INF/view.jsp if accept header is text/html or myModel if accept header is application/json
        return "view";
    }

}
