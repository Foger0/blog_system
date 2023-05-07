package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    private ObjectMapper objectMapper=new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogId=req.getParameter("blogId");
        BlogDao blogDao=new BlogDao();
        if (blogId==null){

            List<Blog> blogs=blogDao.selectAll();
            String respJson=objectMapper.writeValueAsString(blogs);
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write(respJson);
        }else{
            Blog blog=blogDao.selectById(Integer.parseInt(blogId));
            if(blog==null){
                System.out.println("当前blogId="+blogId+"对应博客不存在");
            }
        String respJson=objectMapper.writeValueAsString(blog);
        resp.setContentType("application/json;charset=utf8");
        resp.getWriter().write(respJson);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession=req.getSession(false);
        if (httpSession==null){
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("当前未登录,无法发布博客!");
            return;
        }
        User user=(User)httpSession.getAttribute("user");
        if (user==null){
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("当前未登录,无法发布博客!");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        String title=req.getParameter("title");
        String content=req.getParameter("content");
        if (title==null||"".equals(title)||content==null||"".equals(content)){
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("当前提交数据有误!标题或正文为空");
            return;
        }
        Blog blog=new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUserId(user.getUserId());
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        BlogDao blogDao=new BlogDao();
        blogDao.add(blog);
        resp.sendRedirect("blog_list.html");
    }
}
