package xyz.renhono.aapp1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.aapp1.R;
import xyz.renhono.aapp1.domain.CategoryFlagOne;
import xyz.renhono.aapp1.domain.CategoryFlagThree;

/**
 * Created by GT70 on 2016/10/24 0024.
 */

public class StackChildListBGAD extends BaseAdapter {

    private CategoryFlagOne categoryFlagOne;
    private Context context;
    private List<CategoryFlagOne.BoyCategoryListBean> boyCategoryListBeen=new ArrayList<>();


    public StackChildListBGAD(CategoryFlagOne categoryFlagOne, Context context) {
        this.categoryFlagOne = categoryFlagOne;
        this.context = context;



//        boyCategoryListBeen.addAll(categoryFlagOne.getBoyCategoryList());

    }

    @Override
    public int getCount() {
        return categoryFlagOne.getBoyCategoryList().size();
    }

    @Override
    public Object getItem(int position) {
        return categoryFlagOne.getBoyCategoryList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.stack_child_listview, null);

            holder.tvshu = (ImageView) convertView.findViewById(R.id.ivshu);
            holder.tvtitle = (TextView) convertView.findViewById(R.id.tvtitle);
            holder.tvcount = (TextView) convertView.findViewById(R.id.tvcount);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        Log.i("cccccccccccccc", "" + categoryFlagOne.getBoyCategoryList().get(position).getBookCount());

        Picasso.with(context).load(categoryFlagOne.getBoyCategoryList().get(position).getImg()).into(holder.tvshu);

        holder.tvtitle.setText(categoryFlagOne.getBoyCategoryList().get(position).getCategoryName());
        holder.tvcount.setText(categoryFlagOne.getBoyCategoryList().get(position).getBookCount() + "");


        return convertView;
    }


    class ViewHolder {

        ImageView tvshu;
        TextView tvtitle;
        TextView tvcount;


    }
}
