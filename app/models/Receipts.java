package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
@Table(name="receipts")
public class Receipts extends Model {
	@Id
	public Long id;
	
	@Column(name = "product_id")
	public Long productId;
	
	@Column(name = "product_quantity")
	public float productqty;
	
	@Column(name = "sales_man_id")
	public Long salesMan;
	
	@Column(name = "sales_date")
	public Date salesDate;
	
	@Column(name = "total_due")
	public float totalDue;
	
	@Column(name = "total_price")
	public float totalPrice;
	
	private static Finder<Integer, Receipts> find = new Finder<>(Integer.class, Receipts.class);

	public static List<Receipts> all() {
		return find.all();
	}

	public static Receipts findById(int id){
		return find.byId(id);

	}
}
