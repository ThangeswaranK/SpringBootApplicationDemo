/**
 * 
 */
package com.ipss.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipss.model.Users;
import com.ipss.repository.UsersRepository;

/**
 * @author TH292310
 *
 */

@RestController
@RequestMapping("/rest/users")
public class IPSSResource {
	
	@Autowired
	UsersRepository usersRepository;
	
	/*@Autowired
	UsersRepositoryJPA usersRepositoryJPA;
	*/
	@GetMapping("/allUsers")
	public List<Users> getAll(){
		List<Users> users = new ArrayList<>();
		 usersRepository.findAll().forEach(users::add);
		 return users;
	}
	
	@GetMapping("/{name}")
	public List<Users> getUser(@PathVariable("name") final String name){
		List<Users> users = usersRepository.findByName(name);
		return users;
		  
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,readOnly=false,timeout=100, rollbackFor=Exception.class)
	@PostMapping("/load")
	public List<Users> loadUsers(@RequestBody final Users users){
		usersRepository.save(users);
		return (List<Users>) usersRepository.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Users getById(@PathVariable("id") final Integer id){
		return usersRepository.findOne(id);
	}
	
	@GetMapping("/update/{id}/{name}")
	public void updateByName(@PathVariable("id") final Integer id, @PathVariable("name") final String name){
		Users user = getById(id);
		user.setName(name);
		System.out.println("user" + user);
		usersRepository.save(user);
	}
	
	@GetMapping("/delete/{id}")
	public void deleteById(@PathVariable("id") final Integer id){
		usersRepository.delete(id);
	}
	
}
