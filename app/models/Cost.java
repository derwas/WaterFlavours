package models;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class Cost extends Model{
	@Id
	public long id;
	public String country;// the country that is considered for this cost
	public String unit ="m3"; // the unit of cost/charge
	public String value; // the price of the indicated unit
	public String currency; // the currency used
	public String service; // water supply vs. water supply + wastewater services
	public String reference; // any reference if it exists  
	public String description; // a textual description of the metaphor
	public Cost(long id, String country, String unit,String value, String currency, String service, String reference,
			String description) {
		super();
		this.id = id / 1000;
		this.country = country;
		this.unit = unit;
		this.value=value;
		this.currency = currency;
		this.service = service;
		this.reference = reference;
		this.description = description;
	}
	
	public Cost(Cost cost){
		super();
		this.id = cost.id /1000;
		this.country = cost.country;
		this.unit = cost.unit;
		this.value=cost.value;
		this.currency = cost.currency;
		this.service = cost.service;
		this.reference = cost.reference;
		this.description = cost.description;
	}
}
