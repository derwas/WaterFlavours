package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import com.avaje.ebean.Model;

@Entity
public class User extends Model {

    @Id
    public String name;
    public String password;
    public String email;

    
    public User(String email, String name, String password) {
      this.email = email;
      this.name = name;
      this.password = password;
    }

    @SuppressWarnings("deprecation")
	public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    ); 
    
    public static boolean authenticate(String name, String password) {
        return name.equals("Admin") && password.equals("waternomics");
    }
}