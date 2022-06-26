package entity.media;

import dao.media.MediaDAO;
import entity.db.AIMSDB;
import utils.Utils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * The general media class, for another media it can be done by inheriting this class
 * @author nguyenlm
 */
public class Media {

    protected int id;
    protected String title;
    protected String category;
    protected int value; // the real price of product (eg: 450)
    protected int price; // the price which will be displayed on browser (eg: 500)
    protected int quantity;
    protected String type;
    protected String imageURL;

    public Media(int id, String title, String category, int price, int quantity, String type) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public Media(int id, String title, int quantity, String category, String imageUrl, int price, String type) {
        this(id, title, category, price, quantity, type);
        this.imageURL = imageUrl;
    }

    // Vi phạm SRP: vì method này connect đến CSDL, không phải nhiệm vụ của class entity
    // pthieu 18.4.2022
    public int getQuantity() throws SQLException {
        int updated_quantity = MediaDAO.getCurrentQuantity(id);
        this.quantity = updated_quantity;
        return updated_quantity;
    }

    // getter and setter 
    public int getId() {
        return this.id;
    }

    private Media setId(int id){
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public Media setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public Media setCategory(String category) {
        this.category = category;
        return this;
    }

    public int getPrice() {
        return this.price;
    }

    public Media setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getImageURL(){
        return this.imageURL;
    }

    public Media setMediaURL(String url){
        this.imageURL = url;
        return this;
    }

    public Media setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Media setType(String type) {
        this.type = type;
        return this;
    }
}