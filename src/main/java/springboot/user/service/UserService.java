package springboot.user.service;

import springboot.user.dao.UserDao;
import springboot.user.domain.Level;
import springboot.user.domain.User;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradeLevels() {
        List<User> users = userDao.getAll();

        for (User user : users) {
            if (canUpgradeLevel(user)) {
                upgradeLevel(user);
            }
        }
    }

    private void upgradeLevel(User user) {
        if (user.getLevel() == Level.BASIC) user.setLevel(Level.SILVER);
        else if (user.getLevel() == Level.SILVER) user.setLevel(Level.GOLD);
        userDao.update(user);
    }

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }

        userDao.add(user);
    }


    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC: return user.getLogin() >= 50;
            case SILVER: return user.getRecommend() >= 30;
            case GOLD: return false;
            default: throw new IllegalArgumentException("Unknown Level : " + currentLevel);
        }
    }
}
