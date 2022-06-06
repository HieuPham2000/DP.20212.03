package dao.media;

import entity.media.CD;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author
 */
public class CDDAO extends MediaDAO {

    @Override
    protected String getSQLToQuery(int id) {
        return "SELECT * FROM "+
                "aims.CD " +
                "INNER JOIN aims.Media " +
                "ON Media.id = CD.id " +
                "where Media.id = " + id + ";";
    }

    @Override
    protected Media parseResultSet(ResultSet res) throws SQLException {
        // from media table
        String title = "";
        int id = res.getInt("id");
        String type = res.getString("type");
        int price = res.getInt("price");
        String category = res.getString("category");
        int quantity = res.getInt("quantity");

        // from CD table
        String artist = res.getString("artist");
        String recordLabel = res.getString("recordLabel");
        String musicType = res.getString("musicType");
        Date releasedDate = res.getDate("releasedDate");

        return new CD(id, title, category, price, quantity, type,
                artist, recordLabel, musicType, releasedDate);
    }

}
