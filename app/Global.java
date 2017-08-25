import java.io.File;
import java.io.IOException;
import java.util.List;

import com.avaje.ebean.Ebean;

import controllers.Costs;
import controllers.Stats;
import models.Cost;
import models.Footprint;
import models.Metaphor;
import models.Use;
import play.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.libs.Json;

public class Global extends GlobalSettings {


	public void onStart(Application app) {
        Logger.info("Application has started");
        
        Logger.info("Empty database!");
        Ebean.delete(Ebean.find(Cost.class).findList());
        Ebean.delete(Ebean.find(Metaphor.class).findList());
        Ebean.delete(Ebean.find(Footprint.class).findList());
        Ebean.delete(Ebean.find(Use.class).findList());

        Logger.info("Initialize Metaphors!");
        initMetaphors();
      
        Logger.info("Initialize Footprints!");
        initFootprints();
        
        Logger.info("Initialize Costs!");
        initCosts();
        
        Logger.info("Initialize Domestic Water Uses!");
        initUses();

    }
	
	private static void initMetaphors(){
		try{
			String all = "[{\"id\":-9197076582724689,\"qt\":\"L\",\"value\":\"0.00666666667\",\"unit\":\"daily need\",\"label\":\"a person\",\"reference\":\"http://www.irishexaminer.com/ireland/150-litres-of-water-consumed-daily-by-every-person-245927.html\",\"image\":\"http://vmwaternomics01.deri.ie/publicdisplay-nuig/images/metaphors/metaphor-04.jpg\",\"description\":\"1 L of water is equivalent to 0.00666666667 daily need(s) of a person.\"},{\"id\":-8659639062612537,\"qt\":\"L\",\"value\":\"0.0526315789\",\"unit\":\"standard size\",\"label\":\"water cooler bottle\",\"reference\":\"\",\"image\":\"http://vmwaternomics01.deri.ie/publicdisplay-nuig/images/metaphors/metaphor-02.jpg\",\"description\":\"1 L of water is equivalent to 0.0526315789 standard size(s) of water cooler bottle.\"},{\"id\":-7520630639817632,\"qt\":\"L\",\"value\":\"1\",\"unit\":\"medium size\",\"label\":\"Coca Cola\",\"reference\":\"\",\"image\":\"http://vmwaternomics01.deri.ie/publicdisplay-nuig/images/metaphors/metaphor-05.jpg\",\"description\":\"1 L of water is equivalent to 1 medium size(s) of Coca Cola.\"},{\"id\":-6647129125386259,\"qt\":\"L\",\"value\":\"0.00272479564\",\"unit\":\"daily need\",\"label\":\"three-person household\",\"reference\":\"http://www.ccwater.org.uk/savewaterandmoney/averagewateruse/\",\"image\":\"http://vmwaternomics01.deri.ie/publicdisplay-nuig/images/metaphors/metaphor-03.jpg\",\"description\":\"1 L of water is equivalent to 0.00272479564 daily need(s) of three-person household.\"},{\"id\":-6235111952452435,\"qt\":\"L\",\"value\":\"3\",\"unit\":\"tin\",\"label\":\"Coca Cola\",\"reference\":\"\",\"image\":\"http://vmwaternomics01.deri.ie/publicdisplay-nuig/images/metaphors/metaphor-06.jpg\",\"description\":\"1 L of water is equivalent to 3 tin(s) of Coca Cola.\"},{\"id\":-5418105424596218,\"qt\":\"m3\",\"value\":\"0.000374170778\",\"unit\":\"standard size\",\"label\":\"swimming pool\",\"reference\":\"\",\"image\":\"http://vmwaternomics01.deri.ie/publicdisplay-nuig/images/metaphors/metaphor-07.jpg\",\"description\":\"1 m3 of water is equivalent to 0.000374170778 standard size(s) of swimming pool.\"},{\"id\":-5372454371685064,\"qt\":\"m3\",\"value\":\"0.00746268657\",\"unit\":\"annual need\",\"label\":\"three-person household\",\"reference\":\"http://www.ccwater.org.uk/savewaterandmoney/averagewateruse/\",\"image\":\"http://vmwaternomics01.deri.ie/publicdisplay-nuig/images/metaphors/metaphor-03.jpg\",\"description\":\"1 m3 of water is equivalent to 0.00746268657 annual need(s) of three-person household.\"},{\"id\":-5282210347195032,\"qt\":\"L\",\"value\":\"4\",\"unit\":\"cup\",\"label\":\"coffee\",\"reference\":\"https://blackbearcoffee.com/resources/83\",\"image\":\"http://vmwaternomics01.deri.ie/publicdisplay-nuig/images/metaphors/metaphor-01.jpg\",\"description\":\"1 L of water is equivalent to 4 cup(s) of coffee.\"}]";
			JsonParser jp = new ObjectMapper().getFactory().createParser(all);
			
	         try{
	        	JsonToken current = jp.nextToken();
	        	while(current != JsonToken.END_ARRAY){
		        	if (current == JsonToken.START_OBJECT) {
		                ObjectMapper mapper = new ObjectMapper();
		                JsonNode jsonMt = mapper.readTree(jp);
		        		if (jsonMt !=null) {
		        			Metaphor mt = Json.fromJson(jsonMt,Metaphor.class);
		        			mt.save();
		        		}
		        	}
		        		current = jp.nextToken();
	        	
	        	}
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} finally {
	            try {
					jp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} 
		Stats.setmCalls(224);
	}
	
	private static void initUses(){
		try{
			String all = "[{\"id\":-8904211989454116,\"activity\":\"Preparing food per person per day\",\"wqt\":\"10\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"http://img2.timeinc.net/health/images/slides/wash-food-400x400.jpg\",\"description\":\"Preparing food per person per day requires 10 L(s) of water.\"},{\"id\":-8650975149226912,\"activity\":\"Brushing teeth\",\"wqt\":\"8\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"https://www.bridgingthegap.org/wp-content/uploads/2013/09/Brushing-Teeth.jpg\",\"description\":\"Brushing teeth requires 8 L(s) of water.\"},{\"id\":-7954235826244276,\"activity\":\"Dishwashing by hand (per wash)\",\"wqt\":\"70\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"http://thepeoplespharmacy.graedonenterpris.netdna-cdn.com/wp-content/uploads/Hand-Washing_Dishes.jpg\",\"description\":\"Dishwashing by hand (per wash) requires 70 L(s) of water.\"},{\"id\":-7378718063585373,\"activity\":\"Dishwashing using a dishwasher (per load)\",\"wqt\":\"20\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"http://image.brands-list.com/articleimg/20150730/1438244666_blist1921.jpg\",\"description\":\"Dishwashing using a dishwasher (per load) requires 20 L(s) of water.\"},{\"id\":-7322389543151870,\"activity\":\"Handwashing with running water\",\"wqt\":\"4\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"http://www.rediclinic.com/wp-content/uploads/2015/10/hand_washing.jpg\",\"description\":\"Handwashing with running water requires 4 L(s) of water.\"},{\"id\":-7297496883711895,\"activity\":\"Taking a bath\",\"wqt\":\"200\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"http://img.medicalexpo.com/images_me/photo-g/100834-8192223.jpg\",\"description\":\"Taking a bath requires 200 L(s) of water.\"},{\"id\":-5836474613324215,\"activity\":\"Watering the grass (per m2)\",\"wqt\":\"8\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"http://jimmykuehnle.com/fulbright/wp-content/uploads/2008/08/grass_bike_rear_corner_water.jpg\",\"description\":\"Watering the grass (per m2) requires 8 L(s) of water.\"},{\"id\":-5691803903487989,\"activity\":\"Brushing teeth with running water\",\"wqt\":\"40\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"https://www.bridgingthegap.org/wp-content/uploads/2013/09/Brushing-Teeth.jpg\",\"description\":\"Brushing teeth with running water requires 40 L(s) of water.\"},{\"id\":-5635496278928251,\"activity\":\"Washing clothes in a washing machine (per load) \",\"wqt\":\"50\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"https://www.surfexcel.in/wp-content/uploads/sites/14/2012/05/shutterstock_48560482.jpg\",\"description\":\"Washing clothes in a washing machine (per load)  requires 50 L(s) of water.\"},{\"id\":-4810010742962903,\"activity\":\"Shaving\",\"wqt\":\"13\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"http://www.kholamon.com/wp-content/uploads/2015/11/shave-clip-art-654502.png\",\"description\":\"Shaving requires 13 L(s) of water.\"},{\"id\":-4639355222278780,\"activity\":\"Flushing the toilet\",\"wqt\":\"6\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"http://icons.iconarchive.com/icons/icons8/ios7/512/Household-Toilet-Pan-icon.png\",\"description\":\"Flushing the toilet requires 6 L(s) of water.\"},{\"id\":-4625849829995688,\"activity\":\"Taking an average shower\",\"wqt\":\"30\",\"wunit\":\"L\",\"reference\":\"http://www.wastewatergardens.com/pdf/WWG_InfoSheet_InternationalWaterConsump.pdf\",\"image\":\"http://ignorelimits.com/wp-content/uploads/2014/05/shower-99263_640.png\",\"description\":\"Taking an average shower requires 30 L(s) of water.\"}]";
			JsonParser jp = new ObjectMapper().getFactory().createParser(all);
			
	         try{
	        	JsonToken current = jp.nextToken();
	        	while(current != JsonToken.END_ARRAY){
		        	if (current == JsonToken.START_OBJECT) {
		                ObjectMapper mapper = new ObjectMapper();
		                JsonNode jsonMt = mapper.readTree(jp);
		        		if (jsonMt !=null) {
		        			Use us = Json.fromJson(jsonMt,Use.class);
		        			us.label = us.activity;
		        			us.unit = "time (occurrence)" ;
		        			us.save();
		        		}
		        	}
		        		current = jp.nextToken();
	        	
	        	}
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} finally {
	            try {
					jp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} 
		Stats.setuCalls(135);

	}
	
	private static void initFootprints(){
		try{
			 
			String jsonArray = "[{\"id\":-8676443896393788,\"wqt\":\"132\",\"wunit\":\"L\",\"value\":\"125\",\"unit\":\"ml\",\"label\":\"coffee\",\"reference\":\"http://waterfootprint.org\",\"image\":\"http://wfn.project-platforms.com/product-gallery/images/coffee.jpg\",\"description\":\"132 L of water is required to produce 125 ml(s) of coffee.\"},{\"id\":-8344395016929261,\"wqt\":\"17196\",\"wunit\":\"L\",\"value\":\"1\",\"unit\":\"kg\",\"label\":\"chocolate\",\"reference\":\"http://waterfootprint.org\",\"image\":\"http://wfn.project-platforms.com/product-gallery/images/chocolate.jpg\",\"description\":\"17196 L of water is required to produce 1 kg(s) of chocolate.\"},{\"id\":-7505749971032979,\"wqt\":\"2497\",\"wunit\":\"L\",\"value\":\"1\",\"unit\":\"kg\",\"label\":\"rice\",\"reference\":\"http://waterfootprint.org\",\"image\":\"http://wfn.project-platforms.com/product-gallery/images/rice.jpg\",\"description\":\"2497 L of water is required to produce 1 kg(s) of rice.\"},{\"id\":-7289720285875692,\"wqt\":\"255\",\"wunit\":\"L\",\"value\":\"250\",\"unit\":\"ml\",\"label\":\"milk\",\"reference\":\"http://waterfootprint.org\",\"image\":\"http://wfn.project-platforms.com/product-gallery/images/milk.jpg\",\"description\":\"255 L of water is required to produce 250 ml(s) of milk.\"},{\"id\":-6036834808253083,\"wqt\":\"822\",\"wunit\":\"L\",\"value\":\"1\",\"unit\":\"kg\",\"label\":\"apple\",\"reference\":\"http://waterfootprint.org\",\"image\":\"http://wfn.project-platforms.com/product-gallery/images/apple.jpg\",\"description\":\"822 L of water is required to produce 1 kg(s) of apple.\"},{\"id\":-4981171593351832,\"wqt\":\"15415\",\"wunit\":\"L\",\"value\":\"1\",\"unit\":\"kg\",\"label\":\"beef\",\"reference\":\"http://waterfootprint.org\",\"image\":\"http://wfn.project-platforms.com/product-gallery/images/beef.jpg\",\"description\":\"15415 L of water is required to produce 1 kg(s) of beef.\"},{\"id\":-4915269530379899,\"wqt\":\"790\",\"wunit\":\"L\",\"value\":\"1\",\"unit\":\"kg\",\"label\":\"banana\",\"reference\":\"http://waterfootprint.org\",\"image\":\"http://wfn.project-platforms.com/product-gallery/images/banana.jpg\",\"description\":\"790 L of water is required to produce 1 kg(s) of banana.\"}]";
			JsonParser jp = new ObjectMapper().getFactory().createParser(jsonArray);
	        try{
	        	JsonToken current = jp.nextToken();
	        	while(current != JsonToken.END_ARRAY){
		        	if (current == JsonToken.START_OBJECT) {
		                ObjectMapper mapper = new ObjectMapper();
		                JsonNode jsonFt = mapper.readTree(jp);
		        		if (jsonFt !=null) {
		        			Footprint ft = Json.fromJson(jsonFt,Footprint.class);
		        			ft.save();
		        		}
		        	}
		        		current = jp.nextToken();
	        	
	        	}
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} finally {
	            try {
					jp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} 
		Stats.setfCalls(380);

	}

	private static void initCosts(){
		try{
			String jsonArray = "[{\"id\":-7990861330440610,\"country\":\"Netherlands\",\"unit\":\"m3\",\"value\":\"1.289\",\"currency\":\"Euro - EUR\",\"service\":\"water supply and wastewater services\",\"reference\":\"https://www.evides.nl/tarieven\",\"description\":\"In Netherlands, 1 m3 of water costs 1.289 Euro - EUR.<br> In this country, customers are charged for water supply and wastewater services.\"},{\"id\":-7654385277100987,\"country\":\"Ireland\",\"unit\":\"m3\",\"value\":\"1.85\",\"currency\":\"Euro - EUR\",\"service\":\"water supply and wastewater services\",\"reference\":\"https://www.water.ie/billing-and-charges/charges/\",\"description\":\"In Ireland, 1 m3 of water costs 1.85 Euro - EUR.<br> In this country, customers are charged for water supply and wastewater services.\"}]";

	        JsonParser jp = new ObjectMapper().getFactory().createParser(jsonArray);
	        try{
	        	JsonToken current = jp.nextToken();
	        	while(current != JsonToken.END_ARRAY){
		        	if (current == JsonToken.START_OBJECT) {
		                ObjectMapper mapper = new ObjectMapper();
		                JsonNode jsonCt = mapper.readTree(jp);
		        		if (jsonCt !=null) {
		        			Cost ct = Json.fromJson(jsonCt,Cost.class);
		        			ct.save();
		        		}
		        	}
		        		current = jp.nextToken();
	        	
	        	}
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} finally {
	            try {
					jp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} 
		Stats.setcCalls(142);

	}

    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }

};