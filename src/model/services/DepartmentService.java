package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	//faz a depende�ncia e injeta a depend�ncia usando o padr�o factory
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll(){
		return dao.findAll();
	}
	
	// opera��o para salvar ou atualizar um novo departamento
	public void saveOrUpdate(Department obj) {
	if(obj.getId() == null) {
		dao.insert(obj);
	}
	else {
		dao.update(obj);
	}
	
}
	
	//criamos um m�todo para remover um departamento
	public void remove(Department obj) {
		dao.deleteById(obj.getId());
	}
}

