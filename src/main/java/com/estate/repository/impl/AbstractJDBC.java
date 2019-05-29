package com.estate.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.estate.repository.GenericJDBC;
import com.estate.annotation.Column;
import com.estate.annotation.Table;
import com.estate.mapper.ResultSetMapper;

public class AbstractJDBC<T> implements GenericJDBC<T> {

	private Class<T> zClass;

	@SuppressWarnings("unchecked")
	public AbstractJDBC() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/estate4month2019";
			String username = "root";
			String password = "abc123";
			return DriverManager.getConnection(dbURL, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<T> query(String sql, Object... parameters) {
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			// set parameters to statement
			if (conn != null) {
				for (int i = 0; i < parameters.length; i++) {
					int index = i + 1;
					statement.setObject(index, parameters[i]);
				}
				return resultSetMapper.mapRow(resultSet, this.zClass);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;

	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql);
			Class.forName("com.mysql.jdbc.Driver");
			if (conn != null) {
				// set parameters to statement
				for (int i = 0; i < parameters.length; i++) {
					int index = i + 1;
					statement.setObject(index, parameters[i]);
				}
				statement.executeUpdate();
				conn.commit();
			}
		} catch (ClassNotFoundException | SQLException ex) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (conn != null) {
				// set parameters to statement
				for (int i = 0; i < parameters.length; i++) {
					int index = i + 1;
					statement.setObject(index, parameters[i]);
				}

				int rowsInserted = statement.executeUpdate();
				resultSet = statement.getGeneratedKeys();
				conn.commit();
				if (rowsInserted > 0) {
					while (resultSet.next()) {
						Long id = resultSet.getLong(1);
						return id;
					}
				}
			}
		} catch (SQLException ex) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			String sql = createSQLInsert();
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			if (conn != null) {
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				// set parameters to statement
				for (int i = 0; i < fields.length; i++) {
					int index = i + 1;
					Field field = fields[i];
					field.setAccessible(true);
					statement.setObject(index, field.get(object));
				}

				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				while (parentClass != null) {
					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						statement.setObject(indexParent, field.get(object));
						indexParent += 1;
					}
					parentClass = parentClass.getSuperclass();
				}

				int rowsInserted = statement.executeUpdate();
				resultSet = statement.getGeneratedKeys();
				conn.commit();
				if (rowsInserted > 0) {
					while (resultSet.next()) {
						Long id = resultSet.getLong(1);
						return id;
					}
				}
			}
		} catch (SQLException | IllegalAccessException ex) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	private String createSQLInsert() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder params = new StringBuilder("");

		for (Field field : zClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				params.append(",");
			}
			if (field.isAnnotationPresent(Column.class)) {
				// note
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
			}
		}

		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				if (fields.length() > 1) {
					fields.append(",");
					params.append(",");
				}
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					fields.append(column.name());
					params.append("?");

				}
			}
			parentClass = parentClass.getSuperclass();
		}

		String sql = "INSERT INTO " + tableName + " (" + fields.toString() + ") VALUES (" + params.toString() + ")";
		return sql;
	}

	@Override
	public void update(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			String sql = createSQLUpdate();
			statement = conn.prepareStatement(sql);

			if (conn != null) {
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
			
				for (int i = 0; i < fields.length; i++) {
					int index = i + 1;
					Field field = fields[i];
					field.setAccessible(true);
					statement.setObject(index, field.get(object));
				}

				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				Object id = null;
				while (parentClass != null) {
					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						String name = field.getName();
						if(!name.equals("id")) {
							statement.setObject(indexParent, field.get(object));
							indexParent += 1;
						}else {
							id = field.get(object);
						}
						
					}
					parentClass = parentClass.getSuperclass();
				}
				statement.setObject(indexParent, id);
				statement.executeUpdate();
				conn.commit();

			}
		} catch (SQLException | IllegalAccessException ex) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	private String createSQLUpdate() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder sets = new StringBuilder("");
		String where = null;
		for (Field field : zClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				String columnName = column.name();
				String value = columnName + " = ? ";
				if (!columnName.equals("id")) {
					if (sets.length() > 1) {
						sets.append(", ");
					}
					sets.append(value);
				} 
			}
		}

		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					String columnName = column.name();
					String value = columnName + " = ? ";
					if (!columnName.equals("id")) {
						if (sets.length() > 1) {
							sets.append(", ");
						}
						sets.append(value);
					} else {
						where = " WHERE " + value;
					}
				}
			}
			parentClass = parentClass.getSuperclass();
		}

		String sql = "UPDATE " + tableName + " SET " + sets.toString() + where;
		return sql;
	}

	@Override
	public void delete(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			String sql = createSQLDelete();
			statement = conn.prepareStatement(sql);

			if (conn != null) {
				Class<?> zClass = object.getClass();			
				Class<?> parentClass = zClass.getSuperclass();
				Object id = null;
				while (parentClass != null) {
					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						String name = field.getName();
						if(name.equals("id")) {
							id = field.get(object);
							break;
						}
					}
					parentClass = parentClass.getSuperclass();
				}
				statement.setObject(1, id);
				statement.executeUpdate();
				conn.commit();

			}
		} catch (SQLException | IllegalAccessException ex) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	private String createSQLDelete() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		String id = null;
		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					String columnName = column.name(); 
					if (columnName.equals("id")) {
						id = columnName + " = ? ";
					}
				}
			}
			parentClass = parentClass.getSuperclass();
		}

		String sql = "DELETE FROM " + tableName + " WHERE " + id;
		return sql;
	}

	@Override
	public void findById(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			String sql = createSQLFindById();
			statement = conn.prepareStatement(sql);

			if (conn != null) {
				Class<?> zClass = object.getClass();			
				Class<?> parentClass = zClass.getSuperclass();
				Object id = null;
				while (parentClass != null) {
					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						String name = field.getName();
						if(name.equals("id")) {
							id = field.get(object);
							break;
						}
					}
					parentClass = parentClass.getSuperclass();
				}
				statement.setObject(1, id);
				statement.executeUpdate();
				conn.commit();

			}
		} catch (SQLException | IllegalAccessException ex) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	private String createSQLFindById() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		String id = null;
		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					String columnName = column.name(); 
					if (columnName.equals("id")) {
						id = columnName + " = ? ";
					}
				}
			}
			parentClass = parentClass.getSuperclass();
		}

		String sql = "SELECT * FROM " + tableName + " WHERE " + id;
		return sql;
	}

}
