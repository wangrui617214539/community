package life.majiang.community.interceptor;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class SessionInterceptor implements HandlerInterceptor {//实现这个接口，有三个抽象方法
    @Autowired
    private UserMapper userMapper;
    @Override
    //程序Controller处理之前执行拦截，全部返回true，为了只是拦截下来，把user信息注入到session中。
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length!=0){
            for(Cookie cookie:cookies) {
                if (cookie.getName().equals("token")) {//如果cookie名字为token，到数据库中找到token这行数据
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);//create拼接各种sql
                    List<User> users = userMapper.selectByExample(userExample);//此处就用到了userExample
                    if (users.size() != 0) {//如果有这行数据，则放到session中，以便后续显示登陆者的名称"我"
                        request.getSession().setAttribute("user", users.get(0));
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
