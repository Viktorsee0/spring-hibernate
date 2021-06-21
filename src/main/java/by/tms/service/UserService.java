package by.tms.service;

import by.tms.dao.AddressDaoImpl;
import by.tms.dao.TagDaoImpl;
import by.tms.dao.UserDaoImpl;
import by.tms.entity.Address;
import by.tms.entity.Tag;
import by.tms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserDaoImpl userDao;

    @Autowired
    AddressDaoImpl addressDao;

    @Autowired
    TagDaoImpl tagDao;

    public void save(User user) {
        if (!userDao.contains(user.getUsername())) {
            checkAddress(user);
            userDao.save(user);
        }
    }

    public void addTage(String username, Tag tag) {
        User byUsername = userDao.findByUsername(username);
        List<Tag> all = tagDao.findAll();

        Optional<Tag> first = all.stream().filter(one -> one.getTagName().equals(tag.getTagName())).findFirst();

        if (first.isPresent()){
            tag.setId(first.get().getId());
        }else {
            tagDao.save(tag);
        }

        byUsername.addTag(tag);
        userDao.save(byUsername);
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

    public List<User> findAllByName(String name) {
        return userDao.findAllByName(name);
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

    public void updateUser(User user) {
        if (userDao.contains(user.getUsername())) {
            userDao.updateUser(user);
        }
    }

    public void delete(long id) {
        if (userDao.contains(id)) {
            User byId = userDao.findById(id);
            addressDao.delete(byId.getAddress());
            userDao.delete(id);
        }
    }

    public void delete(String username) {
        if (userDao.contains(username)) {
            User byUsername = userDao.findByUsername(username);
            addressDao.delete(byUsername.getAddress());
            userDao.delete(username);
        }
    }
}
