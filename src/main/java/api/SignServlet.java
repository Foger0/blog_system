package api;

import model.User;
import model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign")
public class SignServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if(username==null||"".equals(username)||password==null||"".equals(password)){
            String html="<h3>注册失败!缺少username或者password字段</h3>";
            resp.getWriter().write(html);
            return;
        }
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao=new UserDao();
        userDao.add(user);
        resp.sendRedirect("login.html");
    }
}
