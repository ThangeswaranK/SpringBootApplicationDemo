/**
 * 
 */
package com.ipss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ipss.model.Users;

/**
 * @author TH292310
 *
 */
public interface UsersRepositoryJPA extends JpaRepository<Users, Integer>{

}
