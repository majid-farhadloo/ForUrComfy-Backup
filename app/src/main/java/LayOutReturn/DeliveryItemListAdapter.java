package LayOutReturn;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.majid.forurcomfy.R;
import com.example.majid.forurcomfy.Remote.APIService;

import java.util.List;

public class DeliveryItemListAdapter extends ArrayAdapter<DeliveryItem> {

    List<DeliveryItem> mDataItems;
    LayoutInflater mInflater;
    private APIService mAPIService;


    public DeliveryItemListAdapter(Context context, List<DeliveryItem> objects,APIService mAPIService) {
        super(context, R.layout.activity_d_r, objects);

        mDataItems = objects;
        mInflater = LayoutInflater.from(context);
        this.mAPIService = mAPIService;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_d_r, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.currentUser);
        TextView tvLocation = (TextView) convertView.findViewById(R.id.location);
        TextView tvCell = (TextView) convertView.findViewById(R.id.cell);
        Button tvId = (Button) convertView.findViewById(R.id.id);
        TextView tvQuantity = (TextView) convertView.findViewById(R.id.quantity);


        DeliveryItem item = mDataItems.get(position);

        tvName.setText(item.getName());
        tvLocation.setText(item.getLocation());
        tvCell.setText(item.getCell());
        tvId.setText(item.getId());
        tvQuantity.setText(item.getName());
//        imageView.setImageResource(R.drawable.apple_pie);

//        InputStream inputStream = null;
//        try {
//            String imageFile = item.getImage();
//            inputStream = getContext().getAssets().open(imageFile);
//            Drawable d = Drawable.createFromStream(inputStream, null);
//            imageView.setImageDrawable(d);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return convertView;
    }
}
