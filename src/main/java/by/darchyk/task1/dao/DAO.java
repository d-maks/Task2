package by.darchyk.task1.dao;

import by.darchyk.task1.entity.Entity;

public interface DAO {
	String findClassname(String keyword);
	boolean addClass(Entity entity);
}
