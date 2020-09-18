package dao;

import java.sql.SQLException;
import java.util.List;

import model.Blog;

public interface BlogDaoInterface {
  void insertBlog(Blog blog) throws SQLException;
  Blog selectBlog(int blogId);
  List<Blog>selectAllBlogs();
  boolean deleteBlog(int blogId) throws SQLException;
  boolean updateBlog(Blog blog) throws SQLException,Exception;
}
