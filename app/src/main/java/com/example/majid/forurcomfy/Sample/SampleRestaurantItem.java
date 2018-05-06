package com.example.majid.forurcomfy.Sample;

import com.example.majid.forurcomfy.Restaurants.RestaurantItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by farha on 3/11/2018.
 */

public class SampleRestaurantItem {

    public static List<RestaurantItem> dataItemList;
    public static Map<String, RestaurantItem> dataItemMap;

    static {
        dataItemList = new ArrayList<>();
        dataItemMap = new HashMap<>();
        addItem(new RestaurantItem(null, "Panda Express", "panda_express.jpg"));
        addItem(new RestaurantItem(null, "Subway", "subway.jpg"));
        addItem(new RestaurantItem(null, "Taco Bell", "taco_bell.jpg"));
        addItem(new RestaurantItem(null, "The Bucket", "the_bucket.jpg"));
        addItem(new RestaurantItem(null, "MyRestaurant", "my_restaurant.jpg"));
    }
    private static void addItem(RestaurantItem item) {
        dataItemList.add(item);
        dataItemMap.put(item.getItemId(), item);
    }
}
