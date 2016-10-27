package xyz.renhono.aapp1.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.aapp1.domain.CategoryFlagThree;
import xyz.renhono.aapp1.interfaces.StackCallBack;
import xyz.renhono.aapp1.utils.OkHttpUtil;

/**
 * Created by GT70 on 2016/10/24 0024.
 */

public class StackListAST extends AsyncTask<String, Void, List<CategoryFlagThree.PublishCategoryListBean>> {

    private StackCallBack stackCallBack;

    public StackListAST(StackCallBack stackCallBack) {
        this.stackCallBack = stackCallBack;
    }

    @Override
    protected List<CategoryFlagThree.PublishCategoryListBean> doInBackground(String... params) {


        String jsonStr = new OkHttpUtil().urlJx(params[0]);

        Gson gson = new Gson();

        CategoryFlagThree categoryFlagThree = gson.fromJson(jsonStr, CategoryFlagThree.class);


        List<CategoryFlagThree.PublishCategoryListBean> list = new ArrayList<>();

        list.addAll(categoryFlagThree.getPublishCategoryList());

        Log.i("list" ,list.get(0).getCategoryName());

        return list;
    }


    @Override
    protected void onPostExecute(List<CategoryFlagThree.PublishCategoryListBean> publishCategoryListBeen) {


        super.onPostExecute(publishCategoryListBeen);

        Log.i("publishCategoryListBeen", publishCategoryListBeen.get(0).getCategoryName());

        stackCallBack.stackListCB(publishCategoryListBeen);


    }
}
