/*
package com.example.mahmoudalzoghby.graduationproject.RoomImageSaved;

import android.app.Application;
import android.os.AsyncTask;

public class ImageRepo {

    private ImageDao mImageDao;

    public void insert(SaveImage image){
        new insertAsyncTask(mImageDao).execute(image);
    }

    ImageRepo(Application application){
        ImageRoomDB db = ImageRoomDB.getDatabase(application);
        mImageDao = db.imageDao();
    }

    private static class insertAsyncTask extends AsyncTask<SaveImage , Void , Void>{

        private ImageDao dao;

        insertAsyncTask(ImageDao dao){

            this.dao = dao;
        }

        @Override
        protected Void doInBackground(SaveImage... saveImages) {
            dao.insert(saveImages[0]);
            return null;
        }
    }
}
*/
