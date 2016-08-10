package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.ebean.Model;
import util.AppConst;

@Entity
@Table(name="receipts")
public class Receipts extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	/*@Column(name = "product_id")
	public Long productId;*/

	@Column(name="receipt_id")
	public String receiptId;

	@ManyToOne
	@JoinColumn(name="product_id",referencedColumnName="id")
	public Product productId;

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

	public static boolean saveReceipts(List<Map<String, String>> prodList) {
		try{
			String nowDate = new SimpleDateFormat(AppConst.DATE_TIME_FORMAT_MIL).format(new Date());
			for(Map<String,String> prod:prodList){
				Product p = Product.findById(Integer.parseInt(prod.get(AppConst.productId)));
				Receipts r = new Receipts();
				r.productId = p;
				r.receiptId = nowDate;
				int qty = Integer.parseInt(prod.get(AppConst.productQty));
				r.productqty = qty;
				r.totalPrice = p.productPrice * qty;
				r.salesDate = new Date();
				r.save();

				p.productQty = p.productQty -qty;
				p.update();

			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
