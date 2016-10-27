package xyz.renhono.aapp1.adapter;

import android.content.Context;
import android.graphics.Color;
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

public class StackChildGridAD extends BaseAdapter {

    private CategoryFlagThree categoryFlagThree;
    private Context context;
    private List<CategoryFlagThree.Ads2Bean> ads2BeanList;
    private List<CategoryFlagOne.Ads2Bean> ads2BeanListOne;
    private boolean flag;

//        boyCategoryListBeen.addAll(categoryFlagOne.getBoyCategoryList());


    public StackChildGridAD(Context context, List<CategoryFlagThree.Ads2Bean> ads2BeanList) {
        this.context = context;
        this.ads2BeanList = ads2BeanList;
        flag = true;
    }

    public StackChildGridAD(List<CategoryFlagOne.Ads2Bean> ads2BeanListOne, Context context) {
        this.ads2BeanListOne = ads2BeanListOne;
        this.context = context;
        flag = false;
    }

    @Override
    public int getCount() {

        if (flag) {

            return ads2BeanList.size();
        } else {

            return ads2BeanListOne.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (flag) {

            return ads2BeanList.get(position);
        } else {

            return ads2BeanListOne.get(position);
        }
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

            convertView = LayoutInflater.from(context).inflate(R.layout.stack_grid_item, null);

            holder.tvshu = (ImageView) convertView.findViewById(R.id.ivshu);
            holder.tvtitle = (TextView) convertView.findViewById(R.id.tvtitle);
            holder.tvcontext = (TextView) convertView.findViewById(R.id.tvcontext);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        List<Integer> colorList = new ArrayList<>();

        colorList.add(context.getResources().getColor(R.color.grid1));
        colorList.add(context.getResources().getColor(R.color.grid2));
        colorList.add(context.getResources().getColor(R.color.grid3));
        colorList.add(context.getResources().getColor(R.color.grid4));

        if (flag) {

                Picasso.with(context).load(ads2BeanList.get(position).getImageUrl()).into(holder.tvshu);

            holder.tvshu.setBackgroundColor(colorList.get(position));

            holder.tvtitle.setText(ads2BeanList.get(position).getTitle());
            holder.tvcontext.setText(ads2BeanList.get(position).getIntro());

        } else {

            Picasso.with(context).load(ads2BeanListOne.get(position).getImageUrl()).into(holder.tvshu);

            holder.tvshu.setBackgroundColor(colorList.get(position));

            holder.tvtitle.setText(ads2BeanListOne.get(position).getTitle());
            holder.tvcontext.setText(ads2BeanListOne.get(position).getIntro());

        }
        return convertView;
    }


    class ViewHolder {

        ImageView tvshu;
        TextView tvtitle;
        TextView tvcontext;


    }

}