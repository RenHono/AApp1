package xyz.renhono.aapp1.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xyz.renhono.aapp1.R;
import xyz.renhono.aapp1.adapter.CateListAD;
import xyz.renhono.aapp1.asynctask.CateListAST;
import xyz.renhono.aapp1.domain.CateListMod;
import xyz.renhono.aapp1.interfaces.CateListCallBack;

public class CateListActivity extends AppCompatActivity implements CateListCallBack {

    private String url = "http://android.reader.qq.com/v6_2/listDispatch?action=categoryV3&actionTag=,-1,-1,-1,-1,6&actionId=13100&pagestamp=1";

    private final String URL_HEAD = "http://android.reader.qq.com/v6_2/listDispatch?actionTag=,-1,-1,-1,-1,6";


//    private String[] mStrings = {"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
//            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
//            "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
//            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
//            "Allgauer Emmentaler"};

//    private List<String> mlist;

    private CateListMod cateListMod;
    private List<CateListMod.BookListBean> bookListBeanList;

    private CateListAD cateListAD;

    private CateListCallBack cateListCallBack;

//    private ArrayAdapter<String> mAdapter;

    @ViewInject(R.id.rtrlv1)
    private PullToRefreshListView pullToRefreshListView;
    private int actionId;
    private int page = 1;

//    private RequestParams httpXutil() {
//
//
//        return params;
//
//    }

    private void gsonXutil() {



        RequestParams params = new RequestParams(URL_HEAD);

        params.addQueryStringParameter("action", "categoryV3");
        params.addQueryStringParameter("actionId", String.valueOf(actionId));
        params.addQueryStringParameter("pagestamp", String.valueOf(page));


        Log.i("page1x====", page + "");

        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();

                cateListMod = gson.fromJson(result, CateListMod.class);

                Log.i("getBookList", cateListMod.getBookList().get(0).getCategoryName());




                Log.i("page1====", page + "");

                bookListBeanList.addAll(cateListMod.getBookList());

                cateListAD.notifyDataSetChanged();


                Log.i("getBookListxx", bookListBeanList.get(0).getCategoryName());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                pullToRefreshListView.onRefreshComplete();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_list);

        x.view().inject(this);
        actionId = getIntent().getIntExtra("actionId", 13100);

        bookListBeanList = new ArrayList<>();
//        new CateListAST(this).execute(url);
        cateListAD = new CateListAD(bookListBeanList, this);


//        RequestParams requestParams = httpXutil();

        gsonXutil();
  /*      x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();

                CateListMod cateListMod = gson.fromJson(result, CateListMod.class);

                Log.i("getBookList", cateListMod.getBookList().get(0).getCategoryName());

                bookListBeanList.addAll(cateListMod.getBookList());
                Log.i("getBookList", bookListBeanList.get(0).getCategoryName());
                cateListAD.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
*/

//        Log.i("getBookList",bookListBeanList.get(0).getCategoryName());
//
//        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//
//                new GetDataTask().execute();
//
//            }
//        });


        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {



            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                Toast.makeText(CateListActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();

//                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {


                Toast.makeText(CateListActivity.this, "上拉加载", Toast.LENGTH_SHORT).show();


                page=cateListMod.getPagestamp();

                Log.i("page2====", page + "");

//                new GetDataTask().execute();

//                RequestParams requestParams = httpXutil();

                gsonXutil();
            }
        });


//        cateListAD = new CateListAD(bookListBeanList, this);

        pullToRefreshListView.setAdapter(cateListAD);
//        mlist = new ArrayList<>();
//
//        mlist.addAll(Arrays.asList(mStrings));
//
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mlist);


//        ListView listView = pullToRefreshListView.getRefreshableView();
//        listView.setAdapter(mAdapter);

//        pullToRefreshListView.setAdapter(mAdapter);

    }

    @Override
    public void cateListCB(CateListMod cateListMod) {


        bookListBeanList.addAll(cateListMod.getBookList());


        cateListAD.notifyDataSetChanged();

    }


    private class GetDataTask extends AsyncTask<Void, Void, String[]> {


        @Override
        protected String[] doInBackground(Void... params) {


            String[] str = {"加完刷新。。。我加"};


            return str;
        }

        @Override
        protected void onPostExecute(String[] strings) {


//            mlist.addAll(Arrays.asList(strings));
//
//            Log.i("xxxx", mlist.toString());
//
//            mAdapter.notifyDataSetChanged();

            pullToRefreshListView.onRefreshComplete();


            super.onPostExecute(strings);
        }
    }


}

