package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	//faz a dependeência e injeta a dependência usando o padrão factory
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll(){
		return dao.findAll();
	}
	
	// operação para salvar ou atualizar um novo departamento
	public void saveOrUpdate(Department obj) {
	if(obj.getId() == null) {
		dao.insert(obj);
	}
	else {
		dao.update(obj);
	}
	
}
	
	//criamos um método para remover um departamento
	public void remove(Department obj) {
		dao.deleteById(obj.getId());
	}
}

