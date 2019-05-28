package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "index";
    }

    @RequestMapping(value = "/message/{number}", method = RequestMethod.POST)
    public String addParam(@RequestParam(name = "string", required = false) String string,
                           @PathVariable(name = "number") int number,
                           Model model) {

        model.addAttribute("number", number);
        if (string != null) model.addAttribute("string", string);

        return "message";
    }
}
