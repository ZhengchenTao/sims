package com.tao.service;

import java.util.List;

import com.tao.model.Classes;
import com.tao.model.Teacher;

public interface ClassesService {

	public void saveClasses(Classes classes);

	public void updateClasses(Classes classes);

	public Classes findClassesById(int id);

	public void deleteClasses(Classes classes);

	public List<Classes> findAllList();

	public List<Classes> findByPage(int page, int rows);

	public int getCount();

}
