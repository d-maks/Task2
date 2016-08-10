package by.darchyk.task1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.darchyk.task1.pool.Pool;
import by.darchyk.task1.dao.DAO;
import by.darchyk.task1.entity.Entity;

public class DAOImpl implements DAO {

	private static final String QUERY_FIND_CLASS = "Select classname from classes join keywords on classes.id = keywords.binded_class where keywords.keyword = ?";
	private static final String QUERY_ADD_CLASS = "Insert into classes(classname) value(?)";
	private static final String QUERY_ADD_KEYWORD = "Insert into keywords(keyword,binded_class) value(?,(SELECT id FROM classes where classname = ?))";
	private static final String QUERY_CHECK_CLASSNAME = "Select * from classes where classname = ?";
	private static final String QUERY_CHECK_KEYWORD = "Select * from keywords where keyword = ?";

	private void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	@Override
	public String findClassname(String keyword) {
		Connection connect = null;
		PreparedStatement statement = null;
		String classname = null;
		try {
			connect = Pool.getInstance().getResource();
			statement = connect.prepareStatement(QUERY_FIND_CLASS);
			statement.setString(1, keyword);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				classname = resultSet.getString(1);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(statement);
			Pool.getInstance().returnResources(connect);
		}
		return classname;
	}

	public boolean addClass(Entity entity) {
		Connection connect = null;
		PreparedStatement checkClassStatement = null;
		PreparedStatement checkKeywordStatement = null;
		PreparedStatement addClassNameStatement = null;
		PreparedStatement addKeyWordStatement = null;
		boolean flag = false;
		try {
			connect = Pool.getInstance().getResource();
			checkClassStatement = connect.prepareStatement(QUERY_CHECK_CLASSNAME);
			checkClassStatement.setString(1, entity.getClassName());
			ResultSet resultSetClassnames = checkClassStatement.executeQuery();
			if (!resultSetClassnames.next()) {
				addClassNameStatement = connect.prepareStatement(QUERY_ADD_CLASS);
				addClassNameStatement.setString(1, entity.getClassName());
				addClassNameStatement.executeUpdate();
			}
			checkKeywordStatement = connect.prepareStatement(QUERY_CHECK_KEYWORD);
			checkKeywordStatement.setString(1, entity.getKeyword());
			ResultSet resultSetKeywords = checkKeywordStatement.executeQuery();
			if (!resultSetKeywords.next()) {
			addKeyWordStatement = connect.prepareStatement(QUERY_ADD_KEYWORD);
			addKeyWordStatement.setString(1, entity.getKeyword());
			addKeyWordStatement.setString(2, entity.getClassName());
			addKeyWordStatement.executeUpdate();
			}
			flag = true;
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
			flag = false;
		} finally {
			close(checkClassStatement);
			close(addClassNameStatement);
			close(addKeyWordStatement);
			Pool.getInstance().returnResources(connect);
		}
		return flag;
	}
}
