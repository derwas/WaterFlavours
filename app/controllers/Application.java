package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;
import models.Cost;
import models.Footprint;
import models.Metaphor;
import models.User;
import play.mvc.*;
import play.data.Form.*;
import play.Logger;
import play.data.*;
import play.libs.Json;

import views.html.*;

public class Application extends Controller {

    public Result index() {
    	String msg = "This application has been used "+ Stats.getCalls() +" times!";
        return ok(index.render(msg));
    }
    
    public Result login() {
        return ok(
            login.render(Form.form(Login.class))
        );
    }
    
    public  Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.Application.login()
        );
    }
    
    public Result authenticate() {
    	Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
    	Logger.info("Authenticating!!!"+loginForm.toString());
        if (loginForm.hasErrors()) {
        	Logger.info("Authenticating!!!");
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("name", loginForm.get().name);
            return redirect(
                routes.Application.index()
            );
        }
    }
    
    public static class Login {

        public String name;
        public String password;
        
        public String validate() {
            if (!User.authenticate(name, password)) {
            	Logger.info("name + " +name + " passwd: "+ password);
              return "Invalid user or password";
            }
            return null;
        }
    }
}
