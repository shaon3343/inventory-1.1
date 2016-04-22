package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model.Finder;
import play.db.ebean.Model;

@Entity
@Table(name="product")
public class Product extends Model {
	@Id
	public Long id;
	
	@Column(name = "product_name")
	public String productName;
	
	@Column(name = "product_code")
	public String productCode;
	
	@Column(name = "product_price")
	public float productPrice;
	
	@Column(name = "product_quantity")
	public float productqty;
	
	private static Finder<Integer, Product> find = new Finder<>(Integer.class, Product.class);
	
	public static List<Product> all() {
		return find.all();
	}

	public static Product findById(int id){
		return find.byId(id);

	}
	
	public static Product findByIdAndQty(int id){
		return find.where().eq("id",id).gt("productqty", 0).findUnique();

	}
	
	 public static Map<String,String> getProductsAsMap() {
        LinkedHashMap<String,String> prodList = new LinkedHashMap<String,String>();
        for(Product prod: Product.find.orderBy("productCode").findList()) {
        	prodList.put(prod.id.toString(), prod.productName);
        }
        
        return prodList;
    }
}
