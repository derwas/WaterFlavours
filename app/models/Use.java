package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class Use extends Model {
	@Id
	public long id;
	public String activity;
	public String wqt;//water quantity
	public String wunit; //water unit
	public String reference; // any reference if it exists  
	public String image;
	public String description; // a textual description of the metaphor
	public String label; // Same as activity, this field was added on request of one of the users of the API
	public String unit ="time (occurrence)" ; // uni of the activity
	public String value; // value of the uses
	
	public Use(long id, String wqt, String wunit,  String activity, String reference, String image) {
		super();
		this.id = id / 1000; // coffeescript does not read the last three digits! I have to remove them otherwise it will make them 000
		this.wqt = wqt;
		this.wunit = wunit;

		this.activity = activity;
		this.label = this.activity;
		this.reference = reference;
		this.image=image;
		this.description = activity +" requires "+wqt+" "+wunit+"(s) of water ";
		//this.htmldescription = "[<a href=\""+this.reference+"\" target=\"_blank\">ref</a>]"
		//					+ "<img src=\""+this.image+"\" alt=\""+this.description+"\" height=\"60px\">"
		//					+ "[<a href=\""+routes.Metaphors.deleteMetaphor(this.id)+"\">delete</a>]";
				
	}
	public Use(Use use) {
		super();
		this.id = use.id / 1000; // coffeescript does not read the last three digits! I have to remove them otherwise it will make them 000
		this.wqt = use.wqt;
		this.wunit = use.wunit;
		this.activity = use.activity;
		this.reference = use.reference;
		this.image=use.image;
		this.description = activity +" requires "+wqt+" "+wunit+"(s) of water ";
		this.label = use.label;
		this.unit = use.unit;
		this.value = use.value;

		//this.htmldescription = "[<a href=\""+this.reference+"\" target=\"_blank\">ref</a>]"
		//					+ "<img src=\""+this.image+"\" alt=\""+this.description+"\" height=\"60px\">"
		//					+ "[<a href=\""+routes.Metaphors.deleteMetaphor(this.id)+"\">delete</a>]";
			}
	@Override
	public String toString() {
		return "Use [id=" + id +", activity=" + activity+ ", wqt=" + wqt + ", wunit=" + wunit  
				+ ", reference=" + reference + ", description=" + description + "]";
	}

}
