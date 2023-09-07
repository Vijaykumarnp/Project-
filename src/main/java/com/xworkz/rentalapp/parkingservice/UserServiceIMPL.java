/*
 * package com.xworkz.rentalapp.parkingservice;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.BeanUtils; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.xworkz.rentalapp.entity.UserEntity; import
 * com.xworkz.rentalapp.parkingdto.UserDTO; import
 * com.xworkz.rentalapp.repository.UserRepo;
 * 
 * @Service public class UserServiceIMPL implements UserService {
 * 
 * @Autowired UserRepo repo;
 * 
 * 
 * @Override public UserDTO onSave(UserDTO dto) {
 * 
 * UserEntity entity = new UserEntity();
 * 
 * BeanUtils.copyProperties(dto, entity); repo.onSave(entity);
 * 
 * return dto; }
 * 
 * }
 */