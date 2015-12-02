package br.com.ibring.model.user;

import java.util.List;

import br.com.ibring.util.DAOFactory;


public class UserService {

	private UserDAO	userDAO;	
	
	public UserService() {
		this.userDAO = DAOFactory.createUserDAO();
	}
	
	public User load(Integer id) {
		return this.userDAO.load(id);
	}

	public User FindByFaceId(String faceId) {
		return this.userDAO.FindByFaceId(faceId);
	}
	
	public void save(User user) {
		Integer codigo = user.getId();
		if (codigo == null || codigo == 0) {
			this.userDAO.save(user);
		} else {
			this.userDAO.update(user);
		}
	}
	
	public void delete(User user) {
		this.userDAO.delete(user);
	}

	public List<User> listar() {
		return this.userDAO.list();
	}
	
}
