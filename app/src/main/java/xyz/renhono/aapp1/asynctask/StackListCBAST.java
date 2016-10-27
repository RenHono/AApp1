package xyz.renhono.aapp1.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.aapp1.domain.CategoryFlagOne;
import xyz.renhono.aapp1.domain.CategoryFlagThree;
import xyz.renhono.aapp1.interfaces.StackCBCallBack;
import xyz.renhono.aapp1.interfaces.StackCallBack;
import xyz.renhono.aapp1.utils.OkHttpUtil;

/**
 * Created by GT70 on 2016/10/24 0024.
 */

public class StackListCBAST extends AsyncTask<String, Void, CategoryFlagThree> {

    private StackCBCallBack stackCBCallBack;

    public StackListCBAST(StackCBCallBack stackCallBack) {
        this.stackCBCallBack = stackCallBack;
    }

    @Override
    protected CategoryFlagThree doInBackground(String... params) {


        String jsonStr = new OkHttpUtil().urlJx(params[0]);

        Gson gson = new Gson();

        CategoryFlagThree categoryFlagThree= gson.fromJson(jsonStr, CategoryFlagThree.class);



        return categoryFlagThree;
    }

    @Override
    protected void onPostExecute(CategoryFlagThree categoryFlagThree) {
        super.onPostExecute(categoryFlagThree);

        stackCBCallBack.stackListCBCB(categoryFlagThree);

    }
}
