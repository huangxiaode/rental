package edu.dongnao.rental.uc.provider.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.dubbo.config.annotation.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.dongnao.rental.lang.ServiceResult;
import edu.dongnao.rental.uc.api.IUserService;
import edu.dongnao.rental.uc.domian.RoleGrantedAuthority;
import edu.dongnao.rental.uc.domian.UserInfo;
import edu.dongnao.rental.uc.provider.entity.Role;
import edu.dongnao.rental.uc.provider.entity.User;
import edu.dongnao.rental.uc.provider.repository.RoleRepository;
import edu.dongnao.rental.uc.provider.repository.UserRepository;

/**
 *
 */
@Service(protocol = "dubbo")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ServiceResult<UserInfo> findUserByUserName(String userName) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            return ServiceResult.of(null);
        }

        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new RoleGrantedAuthority("ROLE_"+role.getName())));
        user.setAuthorityList(authorities);
        UserInfo userInfo = modelMapper.map(user, UserInfo.class);
        return ServiceResult.of(userInfo);
    }

    @Override
    public ServiceResult<UserInfo> findById(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (! userOpt.isPresent()) {
            return ServiceResult.notFound();
        }
        User user = userOpt.get();
        UserInfo UserInfo = modelMapper.map(user, UserInfo.class);
        return ServiceResult.of(UserInfo);
    }

    @Override
    public ServiceResult<UserInfo> findUserByTelephone(String telephone) {
        User user = userRepository.findUserByPhoneNumber(telephone);
        if (user == null) {
            return ServiceResult.of(null);
        }
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new RoleGrantedAuthority("ROLE_" + role.getName())));
        user.setAuthorityList(authorities);
        UserInfo userInfo = modelMapper.map(user, UserInfo.class);
        return ServiceResult.of(userInfo);
    }

    @Override
    @Transactional
    public ServiceResult<UserInfo> addUserByTelephone(String telephone) {
        User user = new User();
        user.setPhoneNumber(telephone);
        user.setName(telephone.substring(0, 3) + "****" + telephone.substring(7, telephone.length()));
        Date now = new Date();
        user.setCreateTime(now);
        user.setLastLoginTime(now);
        user.setLastUpdateTime(now);
        user = userRepository.save(user);

        Role role = new Role();
        role.setName("USER");
        role.setUserId(user.getId());
        roleRepository.save(role);
        user.setAuthorityList(Lists.newArrayList(new RoleGrantedAuthority("ROLE_USER")));
        UserInfo userInfo = modelMapper.map(user, UserInfo.class);
        return ServiceResult.of(userInfo);
    }

    @Override
    @Transactional
    public ServiceResult<Boolean> modifyUserProfile(String profile, String value, Long userId) {
        if (profile == null || profile.isEmpty()) {
            return new ServiceResult<Boolean>(false, "属性不可以为空");
        }
        switch (profile) {
            case "name":
                userRepository.updateUsername(userId, value);
                break;
            case "email":
                userRepository.updateEmail(userId, value);
                break;
            case "password":
                userRepository.updatePassword(userId, this.passwordEncoder.encode(value));
                break;
            default:
                return new ServiceResult<Boolean>(false, "不支持的属性");
        }
        return ServiceResult.success();
    }
}
