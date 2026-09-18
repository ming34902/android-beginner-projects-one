
package derry.s2;

interface ResponseResultOne {
    void result(String msg, int code);
}

// 模拟数据SQLServer


public class KTBase29 {
    public static final String DB_SAVE_USER_NAME = "Derry";
    public static final String  DB_SAVE_USER_PWD = "123456";

    public static void main (String [] args) {
        loginAPI("Derry", "123456", new ResponseResultOne() {
            @Override
            public void result(String msg, int code) {
                System.out.println(String.format("最终登录的情况如下： msg:%s, code: %d", msg, code));
            }
        });
    }

    // 登录api 模仿前端
    public static void loginAPI(String userName, String userPwd, ResponseResultOne responseResultOne) {
        if (userName == null || userPwd == null) {
            // todo
        }
        if (userName.length() > 3 && userPwd.length() > 3) {
            if (wbeServiceLoginAPI(userName, userPwd)) {
                // todo
                responseResultOne.result("login success", 200);
            } else {
                responseResultOne.result("login error",  404);
            }
        } else {
            // todo
        }
    }

    public static boolean wbeServiceLoginAPI(String name, String pwd) {
       if (name == DB_SAVE_USER_NAME && pwd == DB_SAVE_USER_PWD) {
           return true;
       } else  {
           return false;
       }
    }
}

