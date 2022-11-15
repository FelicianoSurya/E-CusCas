package id.ac.umn.e_cuscas;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM tblUser")
    LiveData<List<User>> getAllUser();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User usr);

    @Delete
    void delete(User usr);

    @Update
    void update(User usr);

    @Query("DELETE FROM tblUser")
    void deleteAll();
}
