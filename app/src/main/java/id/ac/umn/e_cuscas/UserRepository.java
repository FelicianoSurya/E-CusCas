package id.ac.umn.e_cuscas;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDAO daoUser;
    private LiveData<List<User>> daftarUser;

    UserRepository(Application app){
        UserRoomDatabase db = UserRoomDatabase.getDatabase(app);
        daoUser = db.daoUser();
        daftarUser = daoUser.getAllUser();
    }

    LiveData<List<User>> getDaftarUser(){
        return this.daftarUser;
    }

    public void insert(User usr){
        new insertAsyncTask(daoUser).execute(usr);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(daoUser).execute();
    }

    public void delete(User usr){
        new deleteAsyncTask(daoUser).execute(usr);
    }

    public void update(User usr){
        new updateAsyncTask(daoUser).execute(usr);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDAO asyncDaoUser;
        insertAsyncTask(UserDAO dao){
            this.asyncDaoUser = dao;
        }
        @Override
        protected  Void doInBackground(final User... users){
            asyncDaoUser.insert(users[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserDAO asyncDaoUser;
        deleteAllAsyncTask(UserDAO dao){
            this.asyncDaoUser = dao;
        }
        @Override
        protected  Void doInBackground(final Void... voids){
            asyncDaoUser.deleteAll();
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDAO asyncDaoUser;
        deleteAsyncTask(UserDAO dao){
            this.asyncDaoUser = dao;
        }
        @Override
        protected  Void doInBackground(final User... users){
            asyncDaoUser.delete(users[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDAO asyncDaoUser;
        updateAsyncTask(UserDAO dao){
            this.asyncDaoUser = dao;
        }
        @Override
        protected  Void doInBackground(final User... users){
            asyncDaoUser.update(users[0]);
            return null;
        }
    }
}
