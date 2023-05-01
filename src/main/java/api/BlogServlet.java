package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
}
