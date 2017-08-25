package controllers;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;

import com.avaje.ebean.Ebean;

import models.Cost;
import models.Metaphor;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

@Entity
public class Costs extends Controller {
	
    public Result index() {
    	String msg = "Water Costs have been queried "+ Stats.getcCalls() +" times!";

    	List<Cost> lst = Ebean.find(Cost.class).findList();
    	Iterator it = lst.iterator();
    	while(it.hasNext())
    	{
    		Cost ct = (Cost) it.next();
    		ct.description = "In "+ct.country+ ", 1 "+ct.unit+" of water costs "+ct.value+" "
    				+ct.currency +".<br> In this country, customers are charged for "+ct.service+".";
    		ct.save();
    		Ebean.save(ct);
    	}
    	
    	return ok(views.html.costs.render(msg));
       }
    
    @Security.Authenticated(Secured.class)
    public Result addCost(){
    	DynamicForm requestData = Form.form().bindFromRequest();
        long id = UUID.randomUUID().getLeastSignificantBits();
    	 String country = requestData.get("country");// the country that is considered for this cost
    	 String unit = requestData.get("unit"); // the unit
    	 String value = requestData.get("value"); // the price of the indicated unit
    	 String currency = requestData.get("currency"); // the currency used
    	 String service= requestData.get("service");  // water supply vs. water supply + wastewater services
    	 String reference = requestData.get("reference"); // any reference if it exists  
    	
    
    	String description = "In "+country+ ", 1 "+unit+" of water costs "+value+" "
				+currency +". <br> In this country, customers are charged for  "+service+".";
    	
        Cost cost = new Cost( id,  country,  unit, value,  currency,  service,  reference, description);
    	cost.save();
    	Logger.info("cost: "+ cost.description);
    	return redirect(routes.Costs.index());
    }
    
    @Security.Authenticated(Secured.class)
    public Result deleteCost(String id){
    	List<Cost> lst = Ebean.find(Cost.class).findList();
    	Iterator<Cost> it = lst.iterator();
    	while(it.hasNext())
    	{
    		Cost ct = (Cost) it.next();
			if(ct.id==Long.parseLong(id)){
    			Logger.info("id found");
    			Ebean.delete(ct);
    		}
    	}
    	
    	return redirect(routes.Costs.index());
    }
}
