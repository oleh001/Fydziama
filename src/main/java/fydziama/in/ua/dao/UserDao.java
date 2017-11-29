package fydziama.in.ua.dao;

import fydziama.in.ua.entity.User;

import java.util.List;

public interface UserDao extends GeneralDao<User> {

    List<String> allLogin();

    Long maxUserId();

    User checkoutLogin(String login, String password);
}
