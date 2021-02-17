package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerService {
	
	//faz a depende�ncia e injeta a depend�ncia usando o padr�o factory
	private SellerDao dao = DaoFactory.createSellerDao();
	
	public List<Seller> findAll(){
		return dao.findAll();
	}
	
	// opera��o para salvar ou atualizar um novo vendedor
	public void saveOrUpdate(Seller obj) {
	if(obj.getId() == null) {
		dao.insert(obj);
	}
	else {
		dao.update(obj);
	}
	
}
	
	//criamos um m�todo para remover um vendedor
	public void remove(Seller obj) {
		dao.deleteById(obj.getId());
	}
}

