package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.UserDao;
import fydziama.in.ua.entity.User;
import fydziama.in.ua.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String isVisibility(List<User> obj, int count) {
        return obj.size()>count?"true":"false";
    }

    @Override
    public String isVisibility(Page<User> obj, int count) {
        return obj.getTotalPages()>count?"true":"false";
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAll(Sort sort) {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return userRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public List<User> search(String... searchString) {
        return userRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0]);
    }

    @Override
    public Page<User> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return userRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public User get(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<String> allLogin(){
        return userRepository.getUserLogin();
    }

    @Override
    public Long maxUserId() {
        return userRepository.getMaxUserId();
    }

    @Override
    public User checkoutLogin(String login, String password){
        return userRepository.findByLoginContainingIgnoreCaseAndPassword(login, password);
    }


}
