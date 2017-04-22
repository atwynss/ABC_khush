package com.example.admin.abc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Atwyn on 4/21/2017.
 */

class ProductSizeImagesGirdAdapter  extends BaseAdapter {
    Context c;

    ArrayList<ProductTypeSizeImageItem> productTypeSizeImageItems;
    LayoutInflater inflater;
    String  psize;
    int pid,psid;


    public ProductSizeImagesGirdAdapter(Context c, ArrayList<ProductTypeSizeImageItem> productTypeSizeImageItems,int pid,int psid,String psize) {
        this.c = c;
        this.productTypeSizeImageItems = productTypeSizeImageItems;
        this.pid = pid;
        this.psid = psid;
        this.psize =psize;

        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productTypeSizeImageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return productTypeSizeImageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_layout, parent, false);
        }
        TextView typeNameTxt = (TextView) convertView.findViewById(R.id.txtTypeSizePro);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgTypeSizePro);
        //BIND DATA
        ProductTypeSizeImageItem productTypeSizeImageItem = (ProductTypeSizeImageItem) this.getItem(position);
        typeNameTxt.setText(productTypeSizeImageItem.getName());
        //IMG
        PicassoClient.downloadImage(c, productTypeSizeImageItem.getImagePath(), img);
        //BIND DATA
        final String name = productTypeSizeImageItem.getName();
        final String url = productTypeSizeImageItem.getImagePath();
        final String brand = productTypeSizeImageItem.getBrand();
        final String color = productTypeSizeImageItem.getColor();
        final int sizeid = productTypeSizeImageItem.getProductSizeId();

        // open new activity
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open detail activity
                openDetailActivity(name, url, brand, color, psize);
            }
        });
        return convertView;
    }

    private void openDetailActivity(String... details) {
        Intent i = new Intent(c, SingleViewActivity.class);
        i.putExtra("NAME_KEY", details[0]);
        i.putExtra("IMAGE_KEY", details[1]);
        i.putExtra("BRAND_KEY", details[2]);
        i.putExtra("COLOR_KEY", details[3]);
        i.putExtra("SIZE_KEY", details[4]);
        c.startActivity(i);
    }
}