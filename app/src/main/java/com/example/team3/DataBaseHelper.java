package com.example.team3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.team3.mode.CartItem;
import com.example.team3.mode.Product;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private final static int SQL_VERSION = 1;
    private final static String SQL_NAME = "data";

    private static DataBaseHelper instance;

    public static DataBaseHelper instance(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context);
        }
        return instance;
    }

    private DataBaseHelper(@Nullable Context context ) {
        super(context, SQL_NAME, null, SQL_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table product(" +
                "id integer primary key autoincrement," +
                "icon integer," +
                "name varchar(64)," +
                "price double," +
                "description text" +
                ")";
        db.execSQL(sql);
        sql = "create table cart(" +
                "id integer primary key autoincrement," +
                "name varchar(64)," +
                "price double," +
                "num integer" +
                ")";
        db.execSQL(sql);

        sql = "create table my_order(" +
                "id integer primary key autoincrement," +
                "name varchar," +
                "phone varchar," +
                "email varchar," +
                "address varchar," +
                "zip_code varchar," +
                "cc_name varchar," +
                "cc_num varchar," +
                "cvc_code varchar," +
                "exp_date varchar," +
                "total double" +
                ")";
        db.execSQL(sql);

        sql = "create table order_item(" +
                "id integer primary key autoincrement," +
                "order_id integer," +
                "goods_name varchar," +
                "price double," +
                "num integer" +
                ")";
        db.execSQL(sql);

        addProduce(db, "Rose", R.drawable.rose, 5,"Price: $5");
        addProduce(db,"Christmas Tree", R.drawable.christmas_tree, 5, "Price: $5");
        addProduce(db,"Football", R.drawable.football, 5, "Price: $5");
        addProduce(db,"Conversation Heart",R.drawable.conversation_heart,  5,"Price: $5");
        addProduce(db,"Ginger Bread", R.drawable.gingerbread, 5, "Price: $5");
        addProduce(db,"Snow Man",R.drawable.snowman, 5, "Price: $5");
        addProduce(db,"Alien", R.drawable.alien,  5, "Price: $5");
        addProduce(db,"Lavender",R.drawable.lavender,  5, "Price: $5");
        addProduce(db,"Nag Champa", R.drawable.nag_champa,  5, "Price: $5");
        addProduce(db,"Unicorn",R.drawable.unicorn,  5, "Price: $5");
        addProduce(db,"Sweet Kisses", R.drawable.sweet_kisses, 5, "Price: $5");
        addProduce(db,"Apple", R.drawable.apple,  5, "Price: $5");
        addProduce(db,"Hello Kitty", R.drawable.hello_kitty, 5, "Price: $5");
        addProduce(db,"Lotus Blossom", R.drawable.lotus_blossom, 5, "Price: $5");
        addProduce(db,"Minions", R.drawable.minions, 5, "Price: $5");
        addProduce(db,"Owl", R.drawable.owl, 5, "Price: $5");
        addProduce(db,"Paisley", R.drawable.paisley, 5, "Price: $5");
        addProduce(db,"Pink Skull", R.drawable.pink_skull, 5, "Price: $5");
        addProduce(db,"Poop Emoji", R.drawable.poop_emoji, 5, "Price: $5");
        addProduce(db,"Rose Shaped", R.drawable.rose_shaped, 5, "Price: $5");
        addProduce(db,"Strawberry", R.drawable.strawberry, 5, "Price: $5");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private  void addProduce(SQLiteDatabase db, String name, int icon, double price, String description) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("icon", icon);
        values.put("price", price);
        values.put("description", description);
        db.insert("product", null, values);
    }

    public void addProduce(String name, int icon, double price, String description) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("icon", icon);
        values.put("price", price);
        values.put("description", description);
        getWritableDatabase().insert("product", null, values);
    }

    public ArrayList<Product> getAllProducts() {
        String[] cols = {
                "id", "name", "icon", "price", "description"
        };
        Cursor c = getReadableDatabase().query("product", cols, null, null, null, null, null);

        ArrayList<Product> products = new ArrayList<>();
        while (c.moveToNext()) {
            Product product = new Product(
                    c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("name")),
                    c.getInt(c.getColumnIndex("icon")),
                    c.getDouble(c.getColumnIndex("price")),
                    c.getString(c.getColumnIndex("description"))
            );
            products.add(product);
        }
        return products;
    }

    public Product getProductById(int id) {
        String[] cols = {
                "id", "name", "icon", "price", "description"
        };
        String[] args = {
                String.valueOf(id)
        };
        Cursor c = getReadableDatabase().query("product", cols, "id=?", args, null, null, null);

        if (c.moveToNext()) {
            return new Product(
                c.getInt(c.getColumnIndex("id")),
                c.getString(c.getColumnIndex("name")),
                c.getInt(c.getColumnIndex("icon")),
                c.getDouble(c.getColumnIndex("price")),
                c.getString(c.getColumnIndex("description"))
            );
        }
        return null;
    }

    public void addCart(String name, int count, double price) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("num", count);
        values.put("price", price);
        getWritableDatabase().insert("cart", null, values);
    }

    public ArrayList<CartItem> getCartList() {
        String[] cols = {
                "name", "price", "num"
        };
        Cursor c = getReadableDatabase().query("cart", cols, null, null, null, null, null);


        ArrayList<CartItem> cartItems = new ArrayList<>();
        while (c.moveToNext()) {
            CartItem cartItem = new CartItem();
            cartItem.setGoodsName(c.getString(c.getColumnIndex("name")));
            cartItem.setPrice(c.getDouble(c.getColumnIndex("price")));
            cartItem.setBuyNum(c.getInt(c.getColumnIndex("num")));
            cartItems.add(cartItem);
        }
        return cartItems;
    }

    public void clearCart() {
        getWritableDatabase().delete("cart", null, null);
    }

    public long placeOrder(String name,
                           String phone,
                           String email,
                           String address,
                           String zip_code,
                           String cc_name,
                           String cc_num,
                           String cvc_code,
                           String exp_date,
                           double total) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("email", email);
        values.put("address", address);
        values.put("zip_code", zip_code);
        values.put("cc_name", cc_name);
        values.put("cc_num", cc_num);
        values.put("cvc_code", cvc_code);
        values.put("exp_date", exp_date);
        values.put("total", total);
        return getWritableDatabase().insert("my_order", null, values);
    }

    public void addOrderItems(long order_id, ArrayList<CartItem> items) {
        for (CartItem item: items) {
            ContentValues values = new ContentValues();
            values.put("order_id", (int)order_id);
            values.put("goods_name", item.getGoodsName());
            values.put("price", item.getPrice());
            values.put("num", item.getBuyNum());
            getWritableDatabase().insert("order_item", null, values);
        }

    }
}
