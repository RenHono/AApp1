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

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.DecimalFormat;
import java.util.List;

import xyz.renhono.aapp1.R;
import xyz.renhono.aapp1.domain.CateListMod;

/**
 * Created by GT70 on 2016/10/26 0026.
 */

public class CateListAD extends BaseAdapter {

    private List<CateListMod.BookListBean> bookListBeanList;
    private Context context;

    public CateListAD(List<CateListMod.BookListBean> bookListBeanList, Context context) {
        this.bookListBeanList = bookListBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bookListBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookListBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.cate_list_item, null);
            holder = new ViewHolder();

            x.view().inject(holder, convertView);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        int sqx = bookListBeanList.get(position).getBid() % 1000;
        int sq = bookListBeanList.get(position).getBid();

        String tu = "http://wfqqreader.3g.qq.com/cover/" + sqx + "/" + sq + "/t5_" + sq + ".jpg";

        Picasso.with(context).load(tu).into(holder.tvshu);

        holder.tvtitle.setText(bookListBeanList.get(position).getTitle());
        holder.tvcontext.setText(bookListBeanList.get(position).getIntro());
        holder.tvauthor.setText(bookListBeanList.get(position).getAuthor());

        holder.tvw1.setText(bookListBeanList.get(position).getExt().getRecTitle());
        holder.tvw2.setText(bookListBeanList.get(position).getCategoryName());

        float x = (float)Integer.parseInt(bookListBeanList.get(position).getNum()) / 10000;

        DecimalFormat df = new DecimalFormat("#0.0");

        Log.i("float", x + "");
        String tt = df.format(x);


        holder.tvw3.setText(tt +"万"+ bookListBeanList.get(position).getExt().getUnit());


        return convertView;
    }


    private class ViewHolder {

        @ViewInject(R.id.ivshu)
        ImageView tvshu;
        @ViewInject(R.id.tvtitle)
        TextView tvtitle;
        @ViewInject(R.id.tvcontext)
        TextView tvcontext;
        @ViewInject(R.id.tvauthor)
        TextView tvauthor;

        @ViewInject(R.id.tvw1)
        TextView tvw1;
        @ViewInject(R.id.tvw2)
        TextView tvw2;
        @ViewInject(R.id.tvw3)
        TextView tvw3;

    }
}