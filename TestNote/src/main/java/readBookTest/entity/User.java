package readBookTest.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2022/12/26
 */
public class User {

    private  Integer id;
    private  String username;
    private  Map   map;



    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public User() {
        map = new HashMap();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
