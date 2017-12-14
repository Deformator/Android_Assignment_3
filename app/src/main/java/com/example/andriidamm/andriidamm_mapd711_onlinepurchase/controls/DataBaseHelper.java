package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.ClerkModel;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.CustomerModel;

import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * Created by andriidamm on 2017-12-10.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    //dataBase name and version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "deliveryApp";

    //customer table and columns
    private static final String TABLE_CUSTOMERS = "customers";
    private static final String KEY_CUSTOMER_ID = "customerId";
    private static final String KEY_CUSTOMER_USER_NAME = "customerName";
    private static final String KEY_CUSTOMER_PASSWORD = "customerPassword";
    private static final String KEY_CUSTOMER_FIRST_NAME = "customerFirstName";
    private static final String KEY_CUSTOMER_LAST_NAME = "customerLastName";
    private static final String KEY_CUSTOMER_CITY = "city";
    private static final String KEY_CUSTOMER_POSTAL_CODE = "postalCode";

    //employee table and columns
    private static final String TABLE_CLERKS = "clerks";
    private static final String KEY_EMPLOYEE_ID = "employeeId";
    private static final String KEY_EMPLOYEE_USERNAME = "employeeName";
    private static final String KEY_EMPLOYEE_PASSWORD = "employeePassword";
    private static final String KEY_EMPLOYEE_FIRST_NAME = "employeeFirstName";
    private static final String KEY_EMPLOYEE_LAST_NAME = "employeeLastName";

    //product table and columns
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_PRODUCT_ID = "productId";
    private static final String KEY_PRODUCT_NAME = "productName";
    private static final String KEY_PRICE = "price";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_CATEGORY = "category";

    //order table and columns
    private static final String TABLE_ORDERS = "orders";
    private static final String KEY_ORDER_ID = "orderId";
    private static final String KEY_ORDER_CUSTOMER_ID = "customerId";
    private static final String KEY_ORDER_PRODUCT_ID = "productId";
    private static final String KEY_ORDER_EMPLOYEE_ID = "employeeId";
    private static final String KEY_ORDER_DATE = "orderDate";
    private static final String KEY_ORDER_STATUS = "orderStatus";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + " ("
                + KEY_CUSTOMER_ID + " INTEGER PRIMARY KEY,"
                + KEY_CUSTOMER_USER_NAME + " TEXT,"
                + KEY_CUSTOMER_PASSWORD + " TEXT,"
                + KEY_CUSTOMER_FIRST_NAME + " TEXT,"
                + KEY_CUSTOMER_LAST_NAME + " TEXT,"
                + KEY_CUSTOMER_CITY + " TEXT,"
                + KEY_CUSTOMER_POSTAL_CODE + " TEXT)";
        db.execSQL(CREATE_CUSTOMERS_TABLE);

        String CREATE_CLERKS_TABLE = "CREATE TABLE " + TABLE_CLERKS + " ("
                + KEY_EMPLOYEE_ID + " INTEGER PRIMARY KEY,"
                + KEY_EMPLOYEE_USERNAME + " TEXT,"
                + KEY_EMPLOYEE_PASSWORD + " TEXT,"
                + KEY_EMPLOYEE_FIRST_NAME + " TEXT,"
                + KEY_EMPLOYEE_LAST_NAME + " TEXT)";
        db.execSQL(CREATE_CLERKS_TABLE);

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + " ("
                + KEY_PRODUCT_ID + " INTEGER PRIMARY KEY,"
                + KEY_PRODUCT_NAME + " TEXT,"
                + KEY_PRICE + " INTEGER,"
                + KEY_QUANTITY + " INTEGER,"
                + KEY_CATEGORY + " TEXT)";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + " ("
                + KEY_ORDER_ID + " INTEGER PRIMARY KEY,"
                + KEY_ORDER_CUSTOMER_ID + " INTEGER,"
                + KEY_ORDER_PRODUCT_ID + " INTEGER,"
                + KEY_ORDER_EMPLOYEE_ID + " INTEGER,"
                + KEY_ORDER_DATE + " TEXT,"
                + KEY_ORDER_STATUS + " TEXT)";
        db.execSQL(CREATE_ORDERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLERKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);

        onCreate(db);
    }

    //add a new customer to DB
    public void addCustomer(CustomerModel customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CUSTOMER_USER_NAME, customer.getUserName());
        values.put(KEY_CUSTOMER_PASSWORD, customer.getPassword());
        values.put(KEY_CUSTOMER_FIRST_NAME, customer.getFirstName());
        values.put(KEY_CUSTOMER_LAST_NAME, customer.getLastName());
        values.put(KEY_CUSTOMER_CITY, customer.getCity());
        values.put(KEY_CUSTOMER_POSTAL_CODE, customer.getPostalCode());

        db.insert(TABLE_CUSTOMERS, null, values);
        db.close();
    }

    //add new Clerk ot DB
    public void addClerk(ClerkModel customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_EMPLOYEE_USERNAME, customer.getUserName());
        values.put(KEY_EMPLOYEE_PASSWORD, customer.getPassword());
        values.put(KEY_EMPLOYEE_FIRST_NAME, customer.getFirstName());
        values.put(KEY_EMPLOYEE_LAST_NAME, customer.getLastName());

        db.insert(TABLE_CLERKS, null, values);
        db.close();
    }

    //check presence of users name in DB
    public Boolean isUserExist(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean isPresent = false;

        String[] projection = {
                KEY_CUSTOMER_ID,
                KEY_CUSTOMER_USER_NAME
        };

        Cursor cursor = db.query(TABLE_CUSTOMERS, projection, KEY_CUSTOMER_USER_NAME + "=?", new String[]{userName}, null, null, null);


        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(KEY_CUSTOMER_USER_NAME));
            if (name.equals(userName)) {

                isPresent = true;
                cursor.close();
                break;

            }
        }
        cursor.close();
        return isPresent;
    }

    //check customer username and password pair in DB
    public Boolean isCustomerCredsCorrect(String userName, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        Boolean isPresent = false;

        String[] projection = {
                KEY_CUSTOMER_USER_NAME,
                KEY_CUSTOMER_PASSWORD
        };

        Cursor cursor = db.query(TABLE_CUSTOMERS, projection, KEY_CUSTOMER_USER_NAME + " =? AND " + KEY_CUSTOMER_PASSWORD + " =?", new String[]{userName,password}, null, null, null);

        if ((cursor != null) && (cursor.getCount() > 0)) {
            isPresent = true;
            cursor.close();
        }
        cursor.close();
        return isPresent;
    }

    public Boolean isClerkCredsCorrect(String userName, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        Boolean isPresent = false;

        String[] projection = {
                KEY_EMPLOYEE_USERNAME,
                KEY_EMPLOYEE_PASSWORD
        };

        Cursor cursor = db.query(TABLE_CLERKS, projection, KEY_EMPLOYEE_USERNAME + " =? AND " + KEY_EMPLOYEE_PASSWORD + " =?", new String[]{userName,password}, null, null, null);

        if ((cursor != null) && (cursor.getCount() > 0)) {
            isPresent = true;
            cursor.close();
        }
        cursor.close();
        return isPresent;
    }
}

