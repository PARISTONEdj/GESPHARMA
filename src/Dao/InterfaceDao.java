package Dao;

import java.util.ArrayList;
import java.util.List;

import Models.CategorieModel;

public interface InterfaceDao<T> {
	public boolean inserer(T obj);
	public boolean delete (T obj);
	public boolean update (T obj);
}
