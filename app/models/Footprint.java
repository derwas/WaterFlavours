package models;

import javax.persistence.*;

import com.avaje.ebean.Model;

@Entity
public class Footprint extends Model{

	@Id
	public long id;
	public String wqt;//water quantity
	public String wunit; //water unit
	public String value; // the value of the metaphor       5
	public String unit; //the unit of measurement of the metaphor   cup
	public String label; // the label  coffee
	public String reference; // any reference if it exists  
	public String image;
	public String description; // a textual description of the metaphor
	
	public Footprint(long id, String wqt, String wunit,  String value, String unit, String label, String reference, String image) {
		super();
		this.id = id / 1000; // coffeescript does not read the last three digits! I have to remove them otherwise it will make them 000
		this.wqt = wqt;
		this.wunit = wunit;
		this.value = value;
		this.unit = unit;
		this.label = label;
		this.reference = reference;
		this.image=image;
		this.description = wqt+" "+wunit+" of water is required to produce "+value+" "
						+unit+"(s) of " +label+"." ;
		//this.htmldescription = "[<a href=\""+this.reference+"\" target=\"_blank\">ref</a>]"
		//					+ "<img src=\""+this.image+"\" alt=\""+this.description+"\" height=\"60px\">"
		//					+ "[<a href=\""+routes.Metaphors.deleteMetaphor(this.id)+"\">delete</a>]";
				
	}
	public Footprint(Footprint footprint) {
		super();
		this.id = footprint.id / 1000; // coffeescript does not read the last three digits! I have to remove them otherwise it will make them 000
		this.wqt = footprint.wqt;
		this.wunit = footprint.wunit;
		this.value = footprint.value;
		this.unit = footprint.unit;
		this.label = footprint.label;
		this.reference = footprint.reference;
		this.image=footprint.image;
		this.description = wqt+" "+wunit+" of water is required to produce "+value+" "
						+unit+"(s) of " +label+"." ;
		//this.htmldescription = "[<a href=\""+this.reference+"\" target=\"_blank\">ref</a>]"
		//					+ "<img src=\""+this.image+"\" alt=\""+this.description+"\" height=\"60px\">"
		//					+ "[<a href=\""+routes.Metaphors.deleteMetaphor(this.id)+"\">delete</a>]";
			}
	@Override
	public String toString() {
		return "Footprint [id=" + id + ", wqt=" + wqt + ", wunit=" + wunit + ", value=" + value + ", unit=" + unit + ", label=" + label
				+ ", reference=" + reference + ", description=" + description + "]";
	}
	
	
	
}
