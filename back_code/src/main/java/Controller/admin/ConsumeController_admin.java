package Controller.admin;

import Result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.admin.ConsumeService_admin;
import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/admin/consume")
public class ConsumeController_admin {
    @Resource
    private ConsumeService_admin consumeService_admin;

    @RequestMapping(value = "/get30DaySale", method = RequestMethod.GET)
    @ResponseBody
    public Result queryOrder(){
        Result result = new Result();
        List<Double> doubles = consumeService_admin.get30DaySale();
        result.setData(doubles);
        result.setMessage("查询月每天销售金额成功!");
        result.setCode(200);
        return result;
    }

}
