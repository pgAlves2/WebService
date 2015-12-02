package br.com.ibring.model.user;

import java.util.List;

public interface UserDAO {

	public void save(User user);

	public void update(User user);

	public void delete(User user);

	public User load(Integer id);

	public User FindByFaceId(String faceId);

	public List<User> list();
	
}
