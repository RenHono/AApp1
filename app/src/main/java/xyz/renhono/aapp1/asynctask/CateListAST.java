package xyz.renhono.aapp1.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import xyz.renhono.aapp1.domain.CateListMod;
import xyz.renhono.aapp1.domain.CategoryFlagOne;
import xyz.renhono.aapp1.interfaces.CateListCallBack;
import xyz.renhono.aapp1.interfaces.StackBGCallBack;
import xyz.renhono.aapp1.utils.OkHttpUtil;

/**
 * Created by GT70 on 2016/10/24 0024.
 */

public class CateListAST extends AsyncTask<String, Void, CateListMod> {

    private CateListCallBack cateListCallBack;

    public CateListAST(CateListCallBack cateListCallBack) {
        this.cateListCallBack = cateListCallBack;
    }

    @Override
    protected CateListMod doInBackground(String... params) {

        Log.i("urlll", params[0]);

        String jsonStr = new OkHttpUtil().urlJx(params[0]);

        Gson gson = new Gson();


        CateListMod cateListMod = gson.fromJson(jsonStr, CateListMod.class);
//        Log.i("getBookList", cateListMod.getBookList().get(0).getCategoryName());


        return cateListMod;
    }


    @Override
    protected void onPostExecute(CateListMod cateListMod) {
        super.onPostExecute(cateListMod);

//        Log.i("getBookList", cateListMod.getBookList().get(0).getCategoryName());

        cateListCallBack.cateListCB(cateListMod);

    }
}
