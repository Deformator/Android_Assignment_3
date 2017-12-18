package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.controls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Clerk;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Customer;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Order;
import com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the database helper class where all database related methods are located.
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
    private static final String KEY_PRODUCT_IMAGE = "productImage";
    private static final String KEY_PRODUCT_NAME = "productName";
    private static final String KEY_PRODUCT_PRICE = "price";
    //    private static final String KEY_PRODUCT_QUANTITY = "quantity";
    private static final String KEY_PRODUCT_CATEGORY = "category";

    //order table and columns
    private static final String TABLE_ORDERS = "orders";
    private static final String KEY_ORDER_ID = "orderId";
    private static final String KEY_ORDER_CUSTOMER_ID = "customerId";
    private static final String KEY_ORDER_PRODUCT_IDS = "productIds";
    private static final String KEY_ORDER_EMPLOYEE_ID = "employeeId";
    private static final String KEY_ORDER_DATE = "orderDate";
    private static final String KEY_ORDER_PRICE = "orderPrice";
    private static final String KEY_ORDER_STATUS = "OrderStatus";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + " ("
                + KEY_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CUSTOMER_USER_NAME + " TEXT,"
                + KEY_CUSTOMER_PASSWORD + " TEXT,"
                + KEY_CUSTOMER_FIRST_NAME + " TEXT,"
                + KEY_CUSTOMER_LAST_NAME + " TEXT,"
                + KEY_CUSTOMER_CITY + " TEXT,"
                + KEY_CUSTOMER_POSTAL_CODE + " TEXT)";
        db.execSQL(CREATE_CUSTOMERS_TABLE);

        String CREATE_CLERKS_TABLE = "CREATE TABLE " + TABLE_CLERKS + " ("
                + KEY_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_EMPLOYEE_USERNAME + " TEXT,"
                + KEY_EMPLOYEE_PASSWORD + " TEXT,"
                + KEY_EMPLOYEE_FIRST_NAME + " TEXT,"
                + KEY_EMPLOYEE_LAST_NAME + " TEXT)";
        db.execSQL(CREATE_CLERKS_TABLE);

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + " ("
                + KEY_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PRODUCT_IMAGE + " BLOB,"
                + KEY_PRODUCT_NAME + " TEXT,"
                + KEY_PRODUCT_PRICE + " INTEGER,"
                + KEY_PRODUCT_CATEGORY + " TEXT)";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + " ("
                + KEY_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_ORDER_CUSTOMER_ID + " TEXT,"
                + KEY_ORDER_PRODUCT_IDS + " TEXT,"
                + KEY_ORDER_EMPLOYEE_ID + " TEXT,"
                + KEY_ORDER_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                + KEY_ORDER_PRICE + " INTEGER,"
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

    /**
     * Add a new customer to DB
     */
    public void addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CUSTOMER_USER_NAME, customer.getUserName());
        values.put(KEY_CUSTOMER_PASSWORD, customer.getPassword());
        values.put(KEY_CUSTOMER_FIRST_NAME, customer.getFirstName());
        values.put(KEY_CUSTOMER_LAST_NAME, customer.getLastName());
        values.put(KEY_CUSTOMER_CITY, customer.getCity());
        values.put(KEY_CUSTOMER_POSTAL_CODE, customer.getPostalCode());

        db.insert(TABLE_CUSTOMERS, null, values);
        close();
    }

    /**
     * Add a new Clerk to DB
     */
    public void addClerk(Clerk customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_EMPLOYEE_USERNAME, customer.getUserName());
        values.put(KEY_EMPLOYEE_PASSWORD, customer.getPassword());
        values.put(KEY_EMPLOYEE_FIRST_NAME, customer.getFirstName());
        values.put(KEY_EMPLOYEE_LAST_NAME, customer.getLastName());

        db.insert(TABLE_CLERKS, null, values);
        close();
    }

    /**
     * Add a new Product to DB.
     */
    public void addProducts(List<Product> products) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (Product product : products) {

            ContentValues values = new ContentValues();

            values.put(KEY_PRODUCT_IMAGE, product.getImage());
            values.put(KEY_PRODUCT_NAME, product.getProductName());
            values.put(KEY_PRODUCT_CATEGORY, product.getCategory());
            values.put(KEY_PRODUCT_PRICE, product.getPrice());

            db.insert(TABLE_PRODUCTS, null, values);
        }

        close();
    }

    /**
     * Add a new order to DB.
     */
    public void addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ORDER_CUSTOMER_ID, order.getCustomerId());
        values.put(KEY_ORDER_PRODUCT_IDS, order.getProductNames());
        values.put(KEY_ORDER_PRICE, order.getPrice());
        values.put(KEY_ORDER_STATUS, order.getStatus());

        db.insert(TABLE_ORDERS, null, values);
        close();
    }

    /**
     * Get list of all products from DB.
     */
    public List<Product> getProducts() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, null, null, null
                , null, null, null);

        List<Product> products = new ArrayList<>();
        try {
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    int productId = cursor.getInt(cursor.getColumnIndex(KEY_PRODUCT_ID));
                    String productImage = cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_IMAGE));
                    String productName = cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_NAME));
                    String productCategory = cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_CATEGORY));
                    int productPrice = cursor.getInt(cursor.getColumnIndex(KEY_PRODUCT_PRICE));

                    Product product = new Product(productId, productImage, productName, productCategory
                            , productPrice);

                    products.add(product);
                    cursor.moveToNext();
                }
                return products;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    /**
     * Get list of all orders from DB for clerks to view.
     */
    public List<Order> getAllOrders() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ORDERS, null, null, null
                , null, null, null);

        List<Order> orders = new ArrayList<>();
        try {
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    int orderId = cursor.getInt(cursor.getColumnIndex(KEY_ORDER_ID));
                    String customerId = cursor.getString(cursor.getColumnIndex(KEY_ORDER_CUSTOMER_ID));
                    String productIds = cursor.getString(cursor.getColumnIndex(KEY_ORDER_PRODUCT_IDS));
                    String employeeId = cursor.getString(cursor.getColumnIndex(KEY_ORDER_EMPLOYEE_ID));
                    String orderDate = cursor.getString(cursor.getColumnIndex(KEY_ORDER_DATE));
                    int price = cursor.getInt(cursor.getColumnIndex(KEY_ORDER_PRICE));
                    String status = cursor.getString(cursor.getColumnIndex(KEY_ORDER_STATUS));

                    Order order = new Order(orderId, customerId, productIds, employeeId, orderDate, price, status);

                    orders.add(order);
                    cursor.moveToNext();
                }
                return orders;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    /**
     * Get orders specific to a particular customer by its id.
     */
    public List<Order> getOrdersByCustomerId(String customerId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ORDERS, null, KEY_ORDER_CUSTOMER_ID + "=?", new String[]{customerId}
                , null, null, null);

        List<Order> orders = new ArrayList<>();
        try {
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    int orderId = cursor.getInt(cursor.getColumnIndex(KEY_ORDER_ID));
                    customerId = cursor.getString(cursor.getColumnIndex(KEY_ORDER_CUSTOMER_ID));
                    String productIds = cursor.getString(cursor.getColumnIndex(KEY_ORDER_PRODUCT_IDS));
                    String employeeId = cursor.getString(cursor.getColumnIndex(KEY_ORDER_EMPLOYEE_ID));
                    String orderDate = cursor.getString(cursor.getColumnIndex(KEY_ORDER_DATE));
                    int price = cursor.getInt(cursor.getColumnIndex(KEY_ORDER_PRICE));
                    String status = cursor.getString(cursor.getColumnIndex(KEY_ORDER_STATUS));

                    Order order = new Order(orderId, customerId, productIds, employeeId, orderDate, price, status);

                    orders.add(order);
                    cursor.moveToNext();
                }
                return orders;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    /**
     * Get details of an order by order Id.
     */
    public Order getOrderDetailsById(int orderId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ORDERS, null, KEY_ORDER_ID + "=?", new String[]{String.valueOf(orderId)}, null, null, null);

        try {
            if (cursor != null) {
                cursor.moveToFirst();
                orderId = cursor.getInt(cursor.getColumnIndex(KEY_ORDER_ID));
                String customerId = cursor.getString(cursor.getColumnIndex(KEY_ORDER_CUSTOMER_ID));
                String productIds = cursor.getString(cursor.getColumnIndex(KEY_ORDER_PRODUCT_IDS));
                String employeeId = cursor.getString(cursor.getColumnIndex(KEY_ORDER_EMPLOYEE_ID));
                String orderDate = cursor.getString(cursor.getColumnIndex(KEY_ORDER_DATE));
                int price = cursor.getInt(cursor.getColumnIndex(KEY_ORDER_PRICE));
                String status = cursor.getString(cursor.getColumnIndex(KEY_ORDER_STATUS));

                return new Order(orderId, customerId, productIds, employeeId, orderDate, price, status);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    /**
     * Update the order details in DB>
     */
    public void updateOrderDetailsByClerk(int orderId, String employeeId, String orderStatus) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ORDER_EMPLOYEE_ID, employeeId);
        values.put(KEY_ORDER_STATUS, orderStatus);

        db.update(TABLE_ORDERS, values, KEY_ORDER_ID + "=?", new String[]{String.valueOf(orderId)});

        close();
    }

    /**
     * Delete an order by order Id.
     */
    public void deleteOrderById(int orderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ORDERS, KEY_ORDER_ID + "=?", new String[]{String.valueOf(orderId)});
        close();
    }

    /**
     * Check if a customer with the same username already exists in DB.
     */
    public Boolean isCustomerExisting(String customerName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Boolean isPresent = false;

        String[] projection = {
                KEY_CUSTOMER_ID,
                KEY_CUSTOMER_USER_NAME
        };

        Cursor cursor = db.query(TABLE_CUSTOMERS, projection, KEY_CUSTOMER_USER_NAME + "=?", new String[]{customerName}, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(KEY_CUSTOMER_USER_NAME));
            if (name.equals(customerName)) {

                isPresent = true;
                cursor.close();
                break;

            }
        }
        cursor.close();
        return isPresent;
    }

    /**
     * Check if a clerk with the same username already exists in DB.
     */
    public Boolean isClerkExisting(String clerkName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Boolean isPresent = false;

        String[] projection = {
                KEY_EMPLOYEE_ID,
                KEY_EMPLOYEE_USERNAME
        };

        Cursor cursor = db.query(TABLE_CLERKS, projection, KEY_EMPLOYEE_USERNAME + "=?", new String[]{clerkName}, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(KEY_EMPLOYEE_USERNAME));
            if (name.equals(clerkName)) {

                isPresent = true;
                cursor.close();
                break;

            }
        }
        cursor.close();
        return isPresent;
    }

    /**
     * check customer username and password pair in DB
     */
    public Boolean isCustomerCredsCorrect(String userName, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Boolean isPresent = false;

        String[] projection = {
                KEY_CUSTOMER_USER_NAME,
                KEY_CUSTOMER_PASSWORD
        };

        Cursor cursor = db.query(TABLE_CUSTOMERS, projection, KEY_CUSTOMER_USER_NAME + " =? AND " + KEY_CUSTOMER_PASSWORD + " =?", new String[]{userName, password}, null, null, null);

        if ((cursor != null) && (cursor.getCount() > 0)) {
            isPresent = true;
            cursor.close();
        }
        return isPresent;
    }

    /**
     * check clerk username and password pair in DB
     */
    public Boolean isClerkCredsCorrect(String userName, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Boolean isPresent = false;

        String[] projection = {
                KEY_EMPLOYEE_USERNAME,
                KEY_EMPLOYEE_PASSWORD
        };

        Cursor cursor = db.query(TABLE_CLERKS, projection, KEY_EMPLOYEE_USERNAME + " =? AND " + KEY_EMPLOYEE_PASSWORD + " =?", new String[]{userName, password}, null, null, null);

        if ((cursor != null) && (cursor.getCount() > 0)) {
            isPresent = true;
            cursor.close();
        }
        return isPresent;
    }
}

