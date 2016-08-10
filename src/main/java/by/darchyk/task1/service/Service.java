package by.darchyk.task1.service;

import by.darchyk.task1.dao.impl.DAOImpl;
import by.darchyk.task1.entity.Entity;

public class Service {

	private DAOImpl daoImpl;
	
	public Service(){
		daoImpl = new DAOImpl();
	}

	public String findClassName(String keyword){
		return daoImpl.findClassname(keyword);
	}
	
	public boolean addClass(Entity entity){
		return daoImpl.addClass(entity);
	}
}
