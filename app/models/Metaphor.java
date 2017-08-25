package models;

import javax.persistence.*;

import com.avaje.ebean.Model;

@Entity
public class Metaphor extends Model{

	@Id
	public long id;
	public String qt;
	public String vWater ="1";
	public String value; // the value of the metaphor       5
	public String unit; //the unit of measurement of the metaphor   cup
	public String label; // the label  coffee
	public String reference; // any reference if it exists  
	public String image;
	public String description; // a textual description of the metaphor
	
	public Metaphor(long id, String qt, String value, String unit, String label, String reference, String image) {
		super();
		this.id = id / 1000; // coffeescript does not read the last three digits! I have to remove them otherwise it will make them 000
		this.qt = qt;
		this.value = value;
		this.unit = unit;
		this.label = label;
		this.reference = reference;
		this.image=image;
		this.description = vWater+" "+qt+" of water is equivalent to "+value+" "
						+unit+"(s) of " +label+"." ;
		//this.htmldescription = "[<a href=\""+this.reference+"\" target=\"_blank\">ref</a>]"
		//					+ "<img src=\""+this.image+"\" alt=\""+this.description+"\" height=\"60px\">"
		//					+ "[<a href=\""+routes.Metaphors.deleteMetaphor(this.id)+"\">delete</a>]";
				
	}
	
	public Metaphor(Metaphor mt) {
		super();
		this.id = mt.id / 1000; // coffeescript does not read the last three digits! I have to remove them otherwise it will make them 000
		this.qt = mt.qt;
		this.value = mt.value;
		this.unit = mt.unit;
		this.label = mt.label;
		this.reference = mt.reference;
		this.image=mt.image;
		this.description = vWater+" "+qt+" of water is equivalent to "+value+" "
						+unit+"(s) of " +label+"." ;
		//this.htmldescription = "[<a href=\""+this.reference+"\" target=\"_blank\">ref</a>]"
		//					+ "<img src=\""+this.image+"\" alt=\""+this.description+"\" height=\"60px\">"
		//					+ "[<a href=\""+routes.Metaphors.deleteMetaphor(this.id)+"\">delete</a>]";
				
	}
	@Override
	public String toString() {
		return "Metaphor [id=" + id + ", qt=" + qt + ", value=" + value + ", unit=" + unit + ", label=" + label
				+ ", reference=" + reference + ", description=" + description + "]";
	}
	
	
	
}
