package Controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/test")
public class test {

    @RequestMapping
    public void test(HttpServletRequest req){
        String savePath = req.getServletContext().getRealPath("/static/goodsImage");
        System.out.println(savePath);
    }
}
