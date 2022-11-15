package id.ac.umn.e_cuscas;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository usrRepository;
    private LiveData<List<User>> daftarUser;

    public UserViewModel(@NonNull Application application){
        super(application);
        usrRepository = new UserRepository(application);
        daftarUser = usrRepository.getDaftarUser();
    }

    LiveData<List<User>> getDaftarUser(){
        return this.daftarUser;
    }

    public void insert(User usr){
        usrRepository.insert(usr);
    }

    public void deleteAll(){
        usrRepository.deleteAll();
    }

    public void delete(User usr){
        usrRepository.delete(usr);
    }

    public void update(User usr){
        usrRepository.update(usr);
    }
}
