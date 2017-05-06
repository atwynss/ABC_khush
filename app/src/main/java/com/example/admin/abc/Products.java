package com.example.admin.abc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class Products extends AppCompatActivity {

    ImageView back;
    final static String urlAddress = Config.productsUrlAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        final ListView lv = (ListView) findViewById(R.id.productLv);

        new ProductsDownloader(Products.this, urlAddress, lv).execute();
        Toolbar actionbar = (Toolbar) findViewById(R.id.toolbar);


        if (null != actionbar) {
            actionbar.setNavigationIcon(R.mipmap.backbutton);

            //  actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Products.this, Main2Activity.class);
                   // startActivity(in);
                    finish();
                }
            });
        }
        // Inflate a menu to be displayed in the toolbar
        actionbar.inflateMenu(R.menu.mainproducts);


        actionbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Handle menu item click event

                        int id = item.getItemId();

                        if (id == R.id.productsadd) {
                            Intent in = new Intent(Products.this, AddProducts.class);
                            startActivity(in);
                        }
                        if (id == R.id.productdelete) {
                            Intent in = new Intent(Products.this, DeleteProducts.class);
                            startActivity(in);
                        }
                        return true;
                    }
                });

    }

}



