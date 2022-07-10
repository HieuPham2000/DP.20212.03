package dao.media;

import entity.db.AIMSDB;
import entity.media.Media;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public abstract class MediaDAO {

    public static List getAllMedia() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from Media");
        ArrayList medium = new ArrayList<>();
        while (res.next()) {
            Media media = new Media(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getInt("quantity"),
                    res.getString("category"),
                    res.getString("imageUrl"),
                    res.getInt("price"),
                    res.getString("type"));
            medium.add(media);
        }
        return medium;
    }

    public static int getCurrentQuantity(int id) throws SQLException {
        String sql = "SELECT * FROM Media WHERE id = ?";
        PreparedStatement stm = AIMSDB.getConnection().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet res = stm.executeQuery();

        if (res.next()) {
            return res.getInt("quantity");
        } else {
            throw new SQLException("Media not found");
        }
    }

    protected abstract String getSQLToQuery(int id);
    protected abstract Media parseResultSet(ResultSet res) throws SQLException;

    public final Media getMediaById(int id) throws SQLException {
        String sql = getSQLToQuery(id);
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);

        if (res.next()) {
            return parseResultSet(res);
        }
        return null;
    }


}
