package controllers;

import play.mvc.*;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Update;

import models.Metaphor;
import play.*;
import play.api.mvc.Session;
import play.data.DynamicForm;
import play.data.Form;
import views.html.*;


public class Metaphors extends Controller {
	
    
    public Result index() {
    	String msg = "Water Metaphors have been queried "+ Stats.getmCalls() +" times!";

    	List<Metaphor> lst = Ebean.find(Metaphor.class).findList();
    	Iterator<Metaphor> it = lst.iterator();
    	while(it.hasNext())
    	{
    		Metaphor mt = it.next();
    		mt.description = "1 "+mt.qt+" of water is equivalent to "+mt.value+" "
							+mt.unit+"(s) of " +mt.label+".";
    		mt.save();
    		Ebean.save(mt);
    	}
       return ok(views.html.metaphors.render(msg));
        }
    
    @Security.Authenticated(Secured.class)
    public Result addMetaphor(){
    	DynamicForm requestData = Form.form().bindFromRequest();
        String qt = requestData.get("qt");
        if (qt=="") {qt="L";}
        String unit = requestData.get("unit");
        String value = requestData.get("value");
        String label = requestData.get("label");
        String reference = requestData.get("reference");
        long id = UUID.randomUUID().getLeastSignificantBits();
    	//String description = "1 "+qt+" of water is equivalent to "+value+" "
		//		+unit+"(s) of " +label+".";
    	String image = requestData.get("image");
        Metaphor metaphor = new Metaphor( id,  qt,  value,  unit,  label,  reference,image);
        //metaphor.description = description;
    	metaphor.save();
    	Logger.info("metaphor: "+ metaphor.toString());
    	return redirect(routes.Metaphors.index());
    }
    
    @Security.Authenticated(Secured.class)
    public Result deleteMetaphor(String id){
    	List<Metaphor> lst = Ebean.find(Metaphor.class).findList();
    	Iterator it = lst.iterator();
    	while(it.hasNext())
    	{
    		Metaphor mt = (Metaphor) it.next();
			if(mt.id==Long.parseLong(id)){
    			Logger.info("id found");
    			Ebean.delete(mt);
    		}
    	}
    	return redirect(routes.Metaphors.index());
    
    }
}
