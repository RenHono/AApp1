package xyz.renhono.aapp1.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.aapp1.R;
import xyz.renhono.aapp1.activity.CateListActivity;
import xyz.renhono.aapp1.adapter.StackChildGridAD;
import xyz.renhono.aapp1.adapter.StackChildListAD;
import xyz.renhono.aapp1.adapter.StackChildListBGAD;
import xyz.renhono.aapp1.asynctask.StackListAST;
import xyz.renhono.aapp1.asynctask.StackListBGAST;
import xyz.renhono.aapp1.asynctask.StackListCBAST;
import xyz.renhono.aapp1.domain.CategoryFlagOne;
import xyz.renhono.aapp1.domain.CategoryFlagThree;
import xyz.renhono.aapp1.interfaces.StackBGCallBack;
import xyz.renhono.aapp1.interfaces.StackCBCallBack;
import xyz.renhono.aapp1.interfaces.StackCallBack;


/**
 * A simple {@link Fragment} subclass.
 */
public class Stack_childFragment extends Fragment {

    private ListView listView;
    private StackChildListAD stackChildListAD;
    private StackChildListBGAD stackChildListBGAD;

    private CategoryFlagOne categoryFlagOne;

    private List<CategoryFlagThree.PublishCategoryListBean> publishCategoryListBeanList = new ArrayList<>();

    private String urlx;
    private int fragFlag;
    private CategoryFlagThree categoryFlagThree;
    private StackChildGridAD stackChildGridAD;

    private GridView gridView;
    private View listHeadView;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    private List<CategoryFlagThree.Ads2Bean> ads2BeanList;


    public Stack_childFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stack_child, container, false);


        categoryFlagOne = new CategoryFlagOne();

        urlx = getArguments().getString("url");

        fragFlag = getArguments().getInt("page");

        listView = (ListView) view.findViewById(R.id.lvstackchild);

        listHeadView = getActivity().getLayoutInflater().inflate(R.layout.stack_list_head, listView, false);

        gridView = (GridView) listHeadView.findViewById(R.id.gvstack);


        tv1 = (TextView) listHeadView.findViewById(R.id.tvlei);
        tv2 = (TextView) listHeadView.findViewById(R.id.tvtotalcount);
        tv3 = (TextView) listHeadView.findViewById(R.id.tvnewadd);


        switch (fragFlag) {

            case 1:

                listX();
                break;
            case 2:
                listBG();

//                Log.i("xxxxxx","xxxxxxxxxxxxxx");
                break;


        }


        return view;
    }

    private void listX() {

        new StackListCBAST(new StackCBCallBack() {
            @Override
            public void stackListCBCB(CategoryFlagThree categoryFlagThreeX) {
                categoryFlagThree = categoryFlagThreeX;
                Log.i("grid=====", categoryFlagThree.getAds2().get(0).getImageUrl());
//                Log.i("ttttt=====", categoryFlagThree.getCount().getBookCount());

                tv1.setText("出版分类共");
                tv2.setText(categoryFlagThree.getCount().getBookCount() + "");
                tv3.setText(categoryFlagThree.getCount().getNewBookCount() + "");


//                ads2BeanList.addAll(categoryFlagThree.getAds2());

                stackChildGridAD = new StackChildGridAD(getActivity(), categoryFlagThree.getAds2());

                stackChildGridAD.notifyDataSetChanged();

                gridView.setAdapter(stackChildGridAD);

                listView.addHeaderView(listHeadView);
            }
        }).execute(urlx);


        stackChildListAD = new StackChildListAD(publishCategoryListBeanList, getActivity());

        new StackListAST(new StackCallBack() {
            @Override
            public void stackListCB(List<CategoryFlagThree.PublishCategoryListBean> publishCategoryListBeen) {


                Log.i("publishCategoryListBeen", publishCategoryListBeen.get(0).getCategoryName());

                publishCategoryListBeanList.clear();
                publishCategoryListBeanList.addAll(publishCategoryListBeen);

//                stackChildListAD = new StackChildListAD(publishCategoryListBeen, getActivity());


                stackChildListAD.notifyDataSetChanged();

                listView.setAdapter(stackChildListAD);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        Intent intent = new Intent(getActivity(), CateListActivity.class);

                        intent.putExtra("actionId", publishCategoryListBeanList.get(position - 1).getActionId());


                        startActivity(intent);


                    }
                });

            }
        }).execute(urlx);


    }

    private void listBG() {


        new StackListBGAST(new StackBGCallBack() {

            @Override
            public void stackListBGCB(CategoryFlagOne categoryFlagOneX) {


//                categoryFlagOne=new CategoryFlagOne();
                categoryFlagOne = categoryFlagOneX;

                Log.i("bgbgbggbgbgbg", categoryFlagOne.getBoyCategoryList().get(0).getCategoryName());


                tv1.setText("原创分类共");
                tv2.setText(categoryFlagOne.getCount().getBookCount() + "");
                tv3.setText(categoryFlagOne.getCount().getNewBookCount() + "");


                stackChildGridAD = new StackChildGridAD(categoryFlagOne.getAds2(), getActivity());

                stackChildGridAD.notifyDataSetChanged();

                gridView.setAdapter(stackChildGridAD);

//                stackChildListBGAD.notifyDataSetChanged();
                stackChildListBGAD = new StackChildListBGAD(categoryFlagOne, getActivity());
                stackChildListBGAD.notifyDataSetChanged();

                listView.addHeaderView(listHeadView);

                listView.setAdapter(stackChildListBGAD);

            }
        }).execute(urlx);


    }
}
