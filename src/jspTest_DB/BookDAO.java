package jspTest_DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	private static Book getBookFrom(ResultSet resultSet) throws SQLException{
		Book book = new Book();
		book.setId(resultSet.getInt("id"));
		book.setTitle(resultSet.getString("title"));
		book.setAuthor(resultSet.getString("author"));
		book.setCategoryId(resultSet.getInt("categoryId"));
		book.setPrice(resultSet.getInt("price"));
		book.setPublisher(resultSet.getString("publisher"));
		book.setCategoryName(resultSet.getString("categoryName"));
		return book;
	}

	public static List<Book> findAll() throws Exception{
		String sql = "SELECT b.*, c.categoryName "
				+ "FROM book b LEFT JOIN category c ON b.categoryId = c.id";
		try(Connection connection = DB.getConnection("student1");
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery()){
			ArrayList<Book> list = new ArrayList<Book>();
			while(resultSet.next())
				list.add(getBookFrom(resultSet));
			return list;
		}
	}
	
	public static List<Book> findByName(String name) throws Exception{
		String sql= "SELECT b.*, c.categoryName "
				+ "FROM book b LEFT JOIN category c ON b.categoryId = c.id";
		try(Connection connection = DB.getConnection("student1");
			 PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, name+"%");
			try(ResultSet resultSet = statement.executeQuery()) {
				ArrayList<Book> list = new ArrayList<Book>();
				while(resultSet.next())
					list.add(getBookFrom(resultSet));
				return list;
			}
		}
	}
	
	public static Book findById(int id) throws Exception{
		String sql= "SELECT b.*, c.categoryName "
				+ "FROM book b LEFT JOIN category c ON b.categoryId = c.id";
		try(Connection connection = DB.getConnection("student1");
			 PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, id);
			try(ResultSet resultSet = statement.executeQuery()){
				return resultSet.next()?getBookFrom(resultSet):null;
			}
		}
	}
}
