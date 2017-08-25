package controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;

import controllers.Stats;
import javassist.bytecode.Descriptor.Iterator;
import jdk.nashorn.internal.objects.Global;
import models.Cost;
import models.CostResult;
import models.Footprint;
import models.Metaphor;
import models.Use;
import play.Logger;
import play.libs.Json;
import play.mvc.*;
import scala.util.parsing.json.JSONObject;

public class RestAPI extends Controller {
	
    public Result index() {
    	String msg = "This API have been queried "+ Stats.getCalls() +" times!";

      return ok(views.html.restapi.render(msg));
       }
    
    public Result getMetaphors(){
    	Stats.oneMoreMetaphorCall();
		List<Metaphor> metaphors = Ebean.find(Metaphor.class).findList();
    	return ok(Json.toJson(metaphors));
    }
    public Result getCosts(){
    	Stats.oneMoreCostCall();
		List<Cost> costs = Ebean.find(Cost.class).findList();
    	return ok(Json.toJson(costs));
    }
    
    public Result getFootprints(){
    	Stats.oneMoreFootprintCall();
		List<Footprint> footprints = Ebean.find(Footprint.class).findList();
    	return ok(Json.toJson(footprints));
    }
    
    public Result getUses(){
    	Stats.oneMoreUseCall();
		List<Use> uses = Ebean.find(Use.class).findList();
    	return ok(Json.toJson(uses));
    }
    
    public Result getRandomMetaphors(int number , double value , String unit){
    	Stats.oneMoreMetaphorCall();
    	Logger.info("One metaphor call!");
		List<Metaphor> metaphors = Ebean.find(Metaphor.class).findList();

    	if (number <=0){
    		number = 3;
    	}
    	
    	if(number > metaphors.size()){
    		number = metaphors.size();
    	}
    	
    	if (!unit.toLowerCase().equals("l") && !unit.toLowerCase().equals("m3") ){
    		unit = "l";
    	}
    	
		List<Metaphor> result = new ArrayList<Metaphor>();
		
		Random randomGenerator = new Random();
		Set<Integer> indices = new HashSet<Integer>();
		for (int i =0 ; i<number ; i++){
			int index;
			boolean ok = false;
					do{
					 index = randomGenerator.nextInt(metaphors.size());
					 if (!indices.contains(index)){
						 //Logger.info("choice number "+i+" = "+index);
						 indices.add(index);
						 ok = true;
					 }
					}while (!ok);
					Metaphor mt = new Metaphor(metaphors.get(index));
					int conversion1 = 1;
					if (unit.toLowerCase().equals("m3")){
						conversion1 = 1000;
					}
					int conversion2 = 1;
					if (mt.qt.toLowerCase().equals("m3")){
						conversion2 = 1000;
					}
					 mt.vWater = ""+value +"";
					 mt.qt=unit;
					 double rs = value * Double.parseDouble(mt.value) * conversion1 / conversion2;
					 NumberFormat formatter = new DecimalFormat("#0.000");
					 long a = (long) rs;
					 double f = rs - a;
					 //Logger.info("rs = "+rs+" a = "+a +" f = "+f );
					 if (f==0){
						 mt.value = ""+a+"";
					 }else{
						 mt.value = ""+formatter.format(rs)+"";

					 }
					 mt.description =  mt.vWater+" "+mt.qt+" of water is equivalent to "+mt.value+" "
								+mt.unit+"(s) of " +mt.label+"." ;
					 if (rs>0.1 && mt.qt.equals("l") || rs>0.001 && mt.qt.equals("m3")){
						 result.add(mt);
						 }
					 else{
						 i --;
					 }
			 // we need rs >0.1
			if(indices.size()==metaphors.size()) {
				i = number;
				}
		}
		if (result.size()>0){
	    	return ok(Json.toJson(result));
		}else{
			 HashMap<String, Object> res = new HashMap<String, Object>();
			 res.put("error", "The water quantity is too low!");
	    	return ok(Json.toJson(res));

		}
		
    }
    
    public Result getRandomFootprints(int number , double value , String unit){
    	Stats.oneMoreFootprintCall();
		List<Footprint> footprints = Ebean.find(Footprint.class).findList();

    	if (number <=0){
    		number = 3;
    	}
    	
    	if(number > footprints.size()){
    		number = footprints.size();
    	}
    	
    	if (!unit.toLowerCase().equals("l") && !unit.toLowerCase().equals("m3") ){
    		unit = "l";
    	}
    	
		List<Footprint> result = new ArrayList<Footprint>();
		
		Random randomGenerator = new Random();
		Set<Integer> indices = new HashSet<Integer>();
		for (int i =0 ; i<number ; i++){
			int index;
			boolean ok = false;
			do{
			 index = randomGenerator.nextInt(footprints.size());
			 if (!indices.contains(index)){
				 indices.add(index);
				 ok = true;
			 }
			}while (!ok);
			Footprint ft = new Footprint(footprints.get(index));
			int conversion1 = 1;
			if (unit.toLowerCase().equals("m3")){
				conversion1 = 1000;
			}
			int conversion2 = 1;
			if (ft.wunit.toLowerCase().equals("m3")){
				conversion2 = 1000;
			}
			 ft.wunit=unit;
			 double rs = value * (Double.parseDouble(ft.value)/Double.parseDouble(ft.wqt)) * conversion1 / conversion2;
			 NumberFormat formatter = new DecimalFormat("#0.000");
			 long a = (long) rs;
			 double f = rs - a;
			 //Logger.info("rs = "+rs+" a = "+a +" f = "+f );
			 if (f==0){
				 ft.value = ""+a+"";
			 }else{
				 ft.value = ""+formatter.format(rs)+"";

			 }
			 ft.wqt = ""+value+"";

			 ft.description =  ft.wqt+" "+ft.wunit+" of water is required to produce "+ft.value+" "
						+ft.unit+"(s) of " +ft.label+"." ;
			 if (rs>0.1){
				 result.add(ft);
				 }
			 else{
				 i --;
			 }
	 // we need rs >0.1
			 if(indices.size()==footprints.size()) {
				 i = number;
			 }
		}
		if (result.size()>0){
	    	return ok(Json.toJson(result));
		}else{
			 HashMap<String, Object> res = new HashMap<String, Object>();
			 res.put("error", "The water quantity is too low!");
	    	return ok(Json.toJson(res));

		}
    }
    
    
 
    
    public Result getRandomUses(int number , double value , String unit){
    	Stats.oneMoreUseCall();
		List<Use> uses = Ebean.find(Use.class).findList();

    	if (number <=0){
    		number = 3;
    	}
    	
    	if(number > uses.size()){
    		number = uses.size();
    	}
    	
    	if (!unit.toLowerCase().equals("l") && !unit.toLowerCase().equals("m3") ){
    		unit = "l";
    	}
    	
		List<Use> result = new ArrayList<Use>();
		
		Random randomGenerator = new Random();
		Set<Integer> indices = new HashSet<Integer>();
		for (int i =0 ; i<number ; i++){
			int index;
			boolean ok = false;
			do{
			 index = randomGenerator.nextInt(uses.size());
			 if (!indices.contains(index)){
				 indices.add(index);
				 ok = true;
			 }
			}while (!ok);
			Use us = new Use(uses.get(index));
			int conversion1 = 1;
			if (unit.toLowerCase().equals("m3")){
				conversion1 = 1000;
			}
			int conversion2 = 1;
			if (us.wunit.toLowerCase().equals("m3")){
				conversion2 = 1000;
			}
			 us.wunit=unit;
			 double rs = value /Double.parseDouble(us.wqt) * conversion1 / conversion2;
			 NumberFormat formatter = new DecimalFormat("#0.000");
			 long a = (long) rs;
			 //double f = rs - a;
			 //Logger.info("rs = "+rs+" a = "+a +" f = "+f );
			 //if (f==0){
				 us.value = ""+a+"";
				 us.wqt = ""+value;
			// }else{
			//	 us.wqt = ""+formatter.format(rs)+"";

			 //}

			 us.description =  value +" "+unit+" of water can be used "+a+" time (s) for "+us.activity.toLowerCase()+".";
						
			 if (a>0){
				 result.add(us);
				 }
			 else{
				 i --;
			 }
	 // we need rs >0.1
			 if(indices.size()==uses.size()) {
				 i = number;
			 }
		}
		if (result.size()>0){
	    	return ok(Json.toJson(result));
		}else{
			 HashMap<String, Object> res = new HashMap<String, Object>();
			 res.put("error", "The water quantity is too low!");
	    	return ok(Json.toJson(res));

		}
    }


public Result getCostByCountry(String country,double value , String unit){
	Stats.oneMoreCostCall();
	CostResult result;
	List<Cost> costs = Ebean.find(Cost.class).findList();
	if (!unit.toLowerCase().equals("l") && !unit.toLowerCase().equals("m3") ){
		unit = "m3";
	}
	java.util.Iterator<Cost> it = costs.iterator();
	while (it.hasNext()){
		Cost c = it.next();
		if(c.country.toLowerCase().equals(country.toLowerCase())){
			int conversion1 = 1;
			if(unit.toLowerCase().equals("l")){
				conversion1=1000;
			}
			int conversion2 = 1;
			if(c.service.toLowerCase().contains("water supply and wastewater services")){
				conversion2 = 2;
			}
			double amount = Double.parseDouble(c.value) * value / conversion1 * conversion2;
			result = new CostResult(c, amount);
			return ok(Json.toJson(result));

		}
	}
	
	return ok(Json.toJson(null));
}

}

