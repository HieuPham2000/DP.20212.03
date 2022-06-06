package dao.media;

import entity.media.Book;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author
 */
public class BookDAO extends MediaDAO {

    @Override
    protected String getSQLToQuery(int id) {
        return "SELECT * FROM "+
                "aims.Book " +
                "INNER JOIN aims.Media " +
                "ON Media.id = Book.id " +
                "where Media.id = " + id + ";";
    }

    @Override
    protected Media parseResultSet(ResultSet res) throws SQLException {
        // from Media table
        String title = "";
        int id = res.getInt("id");
        String type = res.getString("type");
        int price = res.getInt("price");
        String category = res.getString("category");
        int quantity = res.getInt("quantity");

        // from Book table
        String author = res.getString("author");
        String coverType = res.getString("coverType");
        String publisher = res.getString("publisher");
        Date publishDate = res.getDate("publishDate");
        int numOfPages = res.getInt("numOfPages");
        String language = res.getString("language");
        String bookCategory = res.getString("bookCategory");

        return new Book(id, title, category, price, quantity, type,
                author, coverType, publisher, publishDate, numOfPages, language, bookCategory);
    }

}
