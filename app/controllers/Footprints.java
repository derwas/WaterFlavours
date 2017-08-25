package controllers;

import play.mvc.*;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Update;

import models.Footprint;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import views.html.*;


public class Footprints extends Controller {
	
    
    public Result index() {
    	String msg = "Water foorptins have been queried "+ Stats.getfCalls() +" times!";
    	List<Footprint> lst = Ebean.find(Footprint.class).findList();
    	Logger.info("number of footpritns : "+lst.size());
    	Iterator<Footprint> it = lst.iterator();
    	while(it.hasNext())
    	{
    		Footprint ft =  it.next();
    		ft.description = ft.wqt+" "+ft.wunit+" of water is required to produce "+ft.value+" "
    					+ft.unit+"(s) of " +ft.label+"." ;
    		ft.save();
    		Ebean.save(ft);
    	}
       return ok(views.html.footprints.render(msg));
        }
    @Security.Authenticated(Secured.class)
    public Result addFootprint(){
    	DynamicForm requestData = Form.form().bindFromRequest();
    	String wqt = requestData.get("wqt");
    	String wunit = requestData.get("wunit");
        if (wunit=="") {wunit="L";}
        String unit = requestData.get("unit");
        String value = requestData.get("value");
        String label = requestData.get("label");
        String reference = requestData.get("reference");
        long id = UUID.randomUUID().getLeastSignificantBits();
    	//String description = "1 "+qt+" of water is equivalent to "+value+" "
		//		+unit+"(s) of " +label+".";
    	String image = requestData.get("image");
        Footprint footprint = new Footprint( id, wqt, wunit,   value,  unit,  label,  reference,image);
        //metaphor.description = description;
        footprint.save();
    	Logger.info("footprint: "+ footprint.toString());
    	return redirect(routes.Footprints.index());
    }
    
    @Security.Authenticated(Secured.class)
    public Result deleteFootprint(String id){
    	List<Footprint> lst = Ebean.find(Footprint.class).findList();
    	Iterator it = lst.iterator();
    	while(it.hasNext())
    	{
    		Footprint ft = (Footprint) it.next();
			if(ft.id==Long.parseLong(id)){
    			Logger.info("id found");
    			Ebean.delete(ft);
    		}
    	}
    	
    	return redirect(routes.Footprints.index());
    }

}
