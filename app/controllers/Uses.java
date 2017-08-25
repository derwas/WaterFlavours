package controllers;

import play.mvc.*;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Update;

import models.Use;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import views.html.*;


public class Uses extends Controller {
	
    
    public Result index() {
    	String msg = "Water Uses have been queried "+ Stats.getuCalls() +" times!";

    	List<Use> lst = Ebean.find(Use.class).findList();
    	Logger.info("number of uses : "+lst.size());
    	Iterator<Use> it = lst.iterator();
    	while(it.hasNext())
    	{
    		Use us = (Use) it.next();
    		us.description = us.activity+" requires "+us.wqt+" "
    					+us.wunit+"(s) of water." ;
    		us.save();
    		Ebean.save(us);
    	}
       return ok(views.html.uses.render(msg));
        }
    
    @Security.Authenticated(Secured.class)
    public Result addUse(){
    	DynamicForm requestData = Form.form().bindFromRequest();
    	String wqt = requestData.get("wqt");
    	String wunit = requestData.get("wunit");
        if (wunit=="") {wunit="L";}
        String activity = requestData.get("activity");
        String reference = requestData.get("reference");
        long id = UUID.randomUUID().getLeastSignificantBits();
    	//String description = "1 "+qt+" of water is equivalent to "+value+" "
		//		+unit+"(s) of " +label+".";
    	String image = requestData.get("image");
        Use us = new Use( id, wqt, wunit,   activity,  reference,image);
        //metaphor.description = description;
        us.save();
    	Logger.info("use: "+ us.toString());
    	return redirect(routes.Uses.index());
    }
    
    @Security.Authenticated(Secured.class)
    public Result deleteUse(String id){
    	List<Use> lst = Ebean.find(Use.class).findList();
    	Iterator<Use> it = lst.iterator();
    	while(it.hasNext())
    	{
    		Use us = it.next();
			if(us.id==Long.parseLong(id)){
    			Logger.info("id found");
    			Ebean.delete(us);
    		}
    	}
    	
    	return redirect(routes.Uses.index());
    }

}
