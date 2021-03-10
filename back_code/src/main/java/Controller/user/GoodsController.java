package Controller.user;

import Result.Result;
import Utils.myDate;
import entity.GoodsDetail;
import entity.GoodsList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.user.GoodsService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @RequestMapping(value = "/queryLatestGoods", method = RequestMethod.GET)
    @ResponseBody
    public Result queryLatestGoods(Integer page){
        Result result = new Result();
        List<GoodsList> goodsList= goodsService.queryLatestGoods(page);
        result.setData(goodsList);
        result.setMessage("获取最新商品列表成功!");
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result queryGoodsList(String sort, Integer page){
        Result result = new Result();
        List<GoodsList> goodsList= goodsService.querySortGoods(sort, page);
        result.setData(goodsList);
        result.setMessage("获取"+sort+"商品列表成功!");
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    @ResponseBody
    public Result queryGoodsList(@RequestBody GoodsDetail goodsDetail){
        Result result = new Result();
        goodsDetail.setTime(myDate.getDate());

        int nums = goodsService.addGoods(goodsDetail);
        if(nums!=1){
            result.setData(null);
            result.setMessage("发布失败！");
            result.setCode(403);
        }
        result.setMessage("发布成功！");
        result.setData(null);
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/totalNum", method = RequestMethod.GET)
    @ResponseBody
    public Result queryTotalNum(String sort){
        Result result = new Result();
        int totalNum = goodsService.queryTotalNum(sort);
        result.setMessage("查询商品分类总数成功！");
        result.setData(totalNum);
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Result queryGoodsDetail(String goodsId){
        Result result = new Result();
        GoodsDetail goodsDetail = goodsService.queryGoodsDetail(goodsId);
        result.setMessage("查询商品详情成功！");
        result.setData(goodsDetail);
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadAccountFile(@RequestParam MultipartFile file, HttpServletRequest req) {
        Result result = new Result();
        File targetFile = null;
//        String basePath = "http://47.115.46.57:9999/erhuo/static/goodsImage/";
//        String path = req.getServletContext().getRealPath("static/goodsImage");

        String path = "D:\\erhuo\\webapp\\src\\assets\\pictures\\upload";
        // 新的文件名
        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        try
        {
            // 封装文件保存位置的全路径
            targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //上传
            file.transferTo(targetFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        result.setCode(200);
        result.setMessage("图片上传成功！");
//        result.setData(basePath + fileName);
        result.setData(fileName);
        return result;
    }
}
