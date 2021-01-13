package edu.dongnao.rental.uc.provider.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.dongnao.rental.uc.provider.entity.Role;

/**
 * 角色数据DAO
 * 
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
	/**
	 * 根据用户Id查询角色
	 * @param userId
	 * @return
	 */
    List<Role> findRolesByUserId(Long userId);
}
