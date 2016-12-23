package com.hngkyt.vr.net;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;

import com.hzgktyt.vr.baselibrary.utils.AppUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * 下载任务类
 * Created by wrf on 2016/12/22.
 */

public class DownloadTask extends AsyncTask<ResponseBody,Long,Void> {


    private ProgressDialog mProgressDialog;

    private String FilePath = Environment.getExternalStorageDirectory() + File.separator + "vrvideo.apk";

    public DownloadTask(ProgressDialog progressDialog) {
        mProgressDialog = progressDialog;
    }

    @Override
    protected Void doInBackground(ResponseBody... params) {
        Logger.e("doInBackground");
        writeResponseBodyToDisk(params[0]);
        return null;
    }




    @Override
    protected void onProgressUpdate(Long... progresses) {
        super.onProgressUpdate(progresses);

        long progres1 = progresses[0];
        int progres = (int) progres1;

        Logger.e("progres = "+progres);

        mProgressDialog.setProgress(progres);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        AppUtils.InstallApp(mProgressDialog.getContext(),new File(FilePath));
        mProgressDialog.dismiss();

    }

    /**
     * 下载的方法
     * @param body 请求的body
     * @return 是否下载成功
     */
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            File futureStudioIconFile = new File(FilePath);
            Logger.e("futureStudioIconFile = "+futureStudioIconFile);
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                    long l = fileSizeDownloaded*100 / fileSize ;

                    publishProgress(l);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
