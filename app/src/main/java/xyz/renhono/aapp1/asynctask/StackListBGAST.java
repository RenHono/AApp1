package xyz.renhono.aapp1.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.aapp1.domain.CategoryFlagOne;
import xyz.renhono.aapp1.domain.CategoryFlagThree;
import xyz.renhono.aapp1.interfaces.StackBGCallBack;
import xyz.renhono.aapp1.interfaces.StackCallBack;
import xyz.renhono.aapp1.utils.OkHttpUtil;

/**
 * Created by GT70 on 2016/10/24 0024.
 */

public class StackListBGAST extends AsyncTask<String, Void, CategoryFlagOne> {

    private StackBGCallBack stackBGCallBack;

    public StackListBGAST(StackBGCallBack stackBGCallBack) {
        this.stackBGCallBack = stackBGCallBack;
    }


    @Override
    protected CategoryFlagOne doInBackground(String... params) {


        String jsonStr = new OkHttpUtil().urlJx(params[0]);

        Gson gson = new Gson();


        CategoryFlagOne categoryFlagOne = gson.fromJson(jsonStr, CategoryFlagOne.class);

        Log.i("cococococco",categoryFlagOne.getBoyCategoryList().get(0).getCategoryName());

        return categoryFlagOne;
    }


    @Override
    protected void onPostExecute(CategoryFlagOne categoryFlagOne) {
        super.onPostExecute(categoryFlagOne);
        stackBGCallBack.stackListBGCB(categoryFlagOne);

    }
}
