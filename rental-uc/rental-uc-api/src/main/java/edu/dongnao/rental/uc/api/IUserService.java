package edu.dongnao.rental.uc.api;

import edu.dongnao.rental.lang.ServiceResult;
import edu.dongnao.rental.uc.domian.UserInfo;

/**
 * 
 * 用户信息服务接口
 *
 */
public interface IUserService {
	/**
	 * 通过手机号码注册用户
	 * @param phoneNumber
	 * @return
	 */
	ServiceResult<UserInfo> addUserByTelephone(String phoneNumber);
	
	/**
	 * 通过用户手机号查找用户
	 * @param phoneNumber
	 * @return
	 */
	ServiceResult<UserInfo> findUserByTelephone(String phoneNumber);
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	ServiceResult<UserInfo> findUserByUserName(String userName);
	
	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 */
	ServiceResult<UserInfo> findById(Long userId);
	
	/**
     * 修改指定属性值
     * @param profile
     * @param value
     * @return
     */
    ServiceResult<Boolean> modifyUserProfile(String profile, String value, Long userId);
}
