package com.itlijunjie.pt.dao;

@SuppressWarnings("rawtypes")
public interface IBaseDAO {
	public abstract Object add(Object object);
	public abstract void update(Object object);
	public abstract void delete(Class clazz,int id);
	public abstract Object load(Class clazz,int id);
}