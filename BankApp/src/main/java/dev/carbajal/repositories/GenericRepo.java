package dev.carbajal.repositories;

import java.util.List;

public interface GenericRepo <T> {
	
	public T add(T t);
	
	public T getById(Integer id);
	
	public List<T> getAll();
	
	public boolean update(T change);
	
	public boolean delete(T t);

}
