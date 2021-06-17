package by.tms.service;

import by.tms.dao.AddressDao;
import by.tms.dao.UserDaoImpl;
import by.tms.entity.Address;
import by.tms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserDaoImpl userDao;

    @Autowired
    AddressDao addressDao;

    public void save(User user) {
        if (!userDao.contains(user.getUsername())) {
            checkAddress(user);
            userDao.save(user);
        }
    }

    private void checkAddress(User user) {
        Address address = user.getAddress();
        if (!addressDao.contains(address.getStreet(), address.getHome())) {
            addressDao.save(address);
        }
        user.setAddress(addressDao.findByStreetAndHome(address.getStreet(), address.getHome()));
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByUserName(String username) {
        if (userDao.contains(username)) {
            return userDao.findByUsername(username);
        }
        return null;
    }

    public void updateName(String name, long id) {
        if (userDao.contains(id)) {
            userDao.updateName(name, id);
        }
    }

    public void delete(long id) {
        if (userDao.contains(id)) {
            userDao.delete(id);
        }
    }
}
