package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface{

	private static final String INSERT_BLOG_SQL="INSERT  INTO BLOG(blogId,blogTitle,blogDescription,postedOn)VALUES(seq_blog.nextval,?,?,?)";
	private static final String SELECT_ALL_BLOGS = "select * from BLOG";
	private static final String DELETE_BLOG_BY_ID="delete from BLOG where blogId=?";
	@Override
	public void insertBlog(Blog blog) throws SQLException {
		System.out.println( INSERT_BLOG_SQL);
		try {
			Connection con= ConnectionManager.getConnection();
			PreparedStatement ps= con.prepareStatement(INSERT_BLOG_SQL);
			ps.setInt(1, blog.getBlogId());
			ps.setString(2, blog.getBlogTitle());
			ps.setDate(3, java.sql.Date.valueOf(blog.getPostedOn()));
			System.out.println(ps);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public Blog selectBlog(int blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> selectAllBlogs() {
		Blog blog =null;
		List<Blog> blogList =new ArrayList<>();
		
		try {
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps= con.prepareStatement( SELECT_ALL_BLOGS);
			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int Id=rs.getInt("blogId");
				String blogTitle=rs.getString("blogTitle");
				String blogDescription=rs.getString("blogDescription");
				LocalDate postedOn=rs.getDate("postedOn").toLocalDate();
				
			    blog=new Blog();
				blog.setBlogTitle(blogTitle);
				blog.setBlogId(Id);
				blog.setBlogDescription(blogDescription);
				blog.setPostedOn(postedOn);
				blogList.add(blog);
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return blogList;
	}

	@Override
	public boolean deleteBlog(int id) throws SQLException {
		
		System.out.println(id);
		boolean rowDeleted;
		try(Connection con=ConnectionManager.getConnection();
				PreparedStatement ps= con.prepareStatement(DELETE_BLOG_BY_ID)){
			ps.setInt(1, id);
			rowDeleted=ps.executeUpdate()>0;
		}

		return rowDeleted;
	}

	@Override
	public boolean updateBlog(Blog blog) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
