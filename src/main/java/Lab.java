

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionUtil;

/**
 * JDBC stands for Java DataBase Connectivity.  It is utilized to connect our java code with a database.
 * JDBC will allow us to execute SQL statements from java and retrieve the result set of that query to be utilized in java
 *
 * JDBC datatypes to know:
 *  - Connection: Creates an active connection to the database.
 *  - Statement: An object that represents a SQL statement to be executed.
 *  - ResultSet: An object that represents the virtual table return from a query (Only needed for executing DQL statements)
 *
 * Background:
 * Assume we have the following table:
 *      songs table
 *      |   id  |      title        |        artist         |
 *      -----------------------------------------------------
 *      |1      |'Let it be'        |'Beatles'              |
 *      |2      |'Hotel California' |'Eagles'               |
 *      |3      |'Kashmir'          |'Led Zeppelin'         |
 *
 * Assignment: Write JDBC logic in the methods below to achieve the following
 *                  - create a new song in our songs database table
 *                  - retrieve all songs from our database table
 *
 * If this is your first time working with JDBC, I recommend reading through the JDBCWalkthrough file that displays how to use JDBC for a similar scenario.
 */
public class Lab {

    public void createSong(Song song)  {
        //write jdbc code here
        try (Connection connection = ConnectionUtil.getConnection();) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO songs(id, title, artist) VALUES (4, 'Clams', 'Clamshell')");
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }

    public List<Song> getAllSongs(){
        List<Song> songs = new ArrayList<>();
        // Song songFound = new Song();

        //write jdbc code here
        try {
            Connection con = ConnectionUtil.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM songs");
            while (rs.next()) {
                Song songFound = new Song();
                songFound.setId(rs.getInt(1));
                songFound.settitle(rs.getString(2));
                songFound.setArtist(rs.getString(3));
                songs.add(songFound);
            } 
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error occured!");
        }

        return songs;
    }
}
