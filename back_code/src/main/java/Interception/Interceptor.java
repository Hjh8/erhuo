package Interception;

import Utils.Token;
import Exception.TokenException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;


public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request,
                             javax.servlet.http.HttpServletResponse response,
                             Object handler) {
        //拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            System.out.println("Method:OPTIONS");
            return true;
        }

        // token验证
        String token = request.getHeader("Authorization");
        int status = Token.verifyToken(token);
        System.out.println("==============" + status);
        if(status == -1){
            // token无效
            response.setStatus(411);
            return false;
        }
        else if(status == 0){
            // token过期
            response.setStatus(412);
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "*");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//            response.setHeader("Access-Control-Allow-Credentials","true");
            return false;
        }
        return true;
    }
}
