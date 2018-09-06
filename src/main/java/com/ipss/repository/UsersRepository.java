/**
 * 
 */
package com.ipss.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ipss.model.Users;

/**
 * @author TH292310
 *
 */
public interface UsersRepository extends CrudRepository<Users, Integer>{

	List<Users> findByName(String name);

}
