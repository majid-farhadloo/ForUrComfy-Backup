//package com.example.majid.forurcomfy.JasonShoppingCart;
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.example.majid.forurcomfy.YesNoDeliveryActivity;
//import com.example.majid.forurcomfy.R;
//
//import java.util.List;
//
//public class ShoppingCartActivity extends Activity {
//
//    private List<Product> mCartList;
//    private ProductAdapter mProductAdapter;
//    private TextView tvName, tvPrice;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.shoppingcart);
//        final Button btn2 = (Button) findViewById(R.id.Button02);
//
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent finish = new Intent(ShoppingCartActivity.
//                        this,YesNoDeliveryActivity.class);
//                startActivity(finish);
//            }
//        });
//        mCartList = ShoppingCartHelper.getCartList();
//
//        // Make sure to clear the selections
//        for(int i=0; i<mCartList.size(); i++) {
//            mCartList.get(i).selected = false;
//        }
//
//        // Create the list
//        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
//        mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true);
//        listViewCatalog.setAdapter(mProductAdapter);
//
//        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//                Intent productDetailsIntent = new Intent(getBaseContext(),ProductDetailsActivity.class);
//                productDetailsIntent.putExtra(ShoppingCartHelper.PRODUCT_INDEX, position);
//                startActivity(productDetailsIntent);
//            }
//        });
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // Refresh the data
//        if(mProductAdapter != null) {
//            mProductAdapter.notifyDataSetChanged();
//        }
//    }
//
//}
//
