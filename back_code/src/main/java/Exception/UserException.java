package Exception;
/**
 * 用户注册或登录失败异常类
 */
public class UserException extends Exception {

    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
