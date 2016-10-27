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
import xyz.renhono.aapp1.domain.CategoryFlagThree;

/**
 * Created by GT70 on 2016/10/24 0024.
 */

public class StackChildListAD extends BaseAdapter {

    private List<CategoryFlagThree.PublishCategoryListBean> publishCategoryListBeanList = new ArrayList<>();
    private Context context;

    public StackChildListAD(List<CategoryFlagThree.PublishCategoryListBean> publishCategoryListBeanList, Context context) {
        this.publishCategoryListBeanList = publishCategoryListBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return publishCategoryListBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return publishCategoryListBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

//        for (int i = 0; i < publishCategoryListBeanList.size(); i++) {


//        }


        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.stack_child_listview, null);

            holder.tvshu = (ImageView) convertView.findViewById(R.id.ivshu);
            holder.tvshu2 = (ImageView) convertView.findViewById(R.id.ivshu2);
            holder.tvshu3 = (ImageView) convertView.findViewById(R.id.ivshu3);

            holder.tvtitle = (TextView) convertView.findViewById(R.id.tvtitle);
            holder.tvcount = (TextView) convertView.findViewById(R.id.tvcount);


            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        List<Integer> colorList = new ArrayList<>();

        colorList.add(R.color.grid1);
        colorList.add(R.color.grid2);
        colorList.add(R.color.grid3);
        colorList.add(R.color.grid4);

//      int xxx=  context.getResources().getColor(R.color.grid1);

        String st = publishCategoryListBeanList.get(position).getBids();

        String[] sq = st.split(",");

        String tu1 = "http://wfqqreader.3g.qq.com/cover/" + (sq[0].substring(sq[0].length() - 3, sq[0].length())).replaceFirst("^0*", "") + "/" + sq[0] + "/t5_" + sq[0] + ".jpg";
        String tu2 = "http://wfqqreader.3g.qq.com/cover/" + (sq[1].substring(sq[1].length() - 3, sq[1].length())).replaceFirst("^0*", "") + "/" + sq[1] + "/t5_" + sq[1] + ".jpg";
        String tu3 = "http://wfqqreader.3g.qq.com/cover/" + (sq[2].substring(sq[2].length() - 3, sq[2].length())).replaceFirst("^0*", "") + "/" + sq[2] + "/t5_" + sq[2] + ".jpg";

        Log.i("tutututututu", "" + tu1);
        Log.i("tutututututu", "" + tu2);
        Log.i("tutututututu", "" + tu3);

        Log.i("cccccccccccccc", "" + publishCategoryListBeanList.get(position).getBookCount());

        Picasso.with(context).load(tu1).into(holder.tvshu);
        Picasso.with(context).load(tu2).into(holder.tvshu2);
        Picasso.with(context).load(tu3).into(holder.tvshu3);

//        holder.tvshu.setBackgroundColor(context.getResources().getColor(R.color.grid1));
//        Log.i("color====",colors[position]+"");

        holder.tvtitle.setText(publishCategoryListBeanList.get(position).getCategoryName());

        holder.tvcount.setText(publishCategoryListBeanList.get(position).getBookCount() + "");


        return convertView;
    }


    class ViewHolder {

        ImageView tvshu;
        ImageView tvshu2;
        ImageView tvshu3;
        TextView tvtitle;
        TextView tvcount;

    }
}
