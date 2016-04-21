package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
@Table(name="sales_man")
public class SalesMen extends Model {
	@Id
	public Long id;
	
	@Column(name = "sales_name")
	public String salesManName;
	
	@Column(name = "sales_address")
	public String salesManAddress;
	
	@Column(name = "sales_contact")
	public String salesManContact;
	
	@Column(name = "sales_tot_due")
	public float salesManTotalDue;
	
	private static Finder<Integer, SalesMen> find = new Finder<>(Integer.class, SalesMen.class);
	
	public static List<SalesMen> all() {
		return find.all();
	}

	public static SalesMen findById(int id){
		return find.byId(id);

	}
	
}
