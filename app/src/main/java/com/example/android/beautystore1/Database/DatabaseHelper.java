package com.example.android.beautystore1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.android.beautystore1.Models.Category;
import com.example.android.beautystore1.Models.Customer;
import com.example.android.beautystore1.Models.Product;
import com.example.android.beautystore1.Models.Store;
import com.example.android.beautystore1.Models.Subcategory;
import com.example.android.beautystore1.Models.User;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //database name
    private static String DATABASE_NAME = "BeautyStore.db";
    //database version
    private static int DATABASE_VERSION = 11;

    //Category table
    private static final String TABLE_CATEGORY = "tbCategory";
    private static final String COLUMN_CATEGORY_ID = "category_id";
    private static final String COLUMN_CATEGORY_NAME= "category_name";

    //Subcategory table
    private static final String TABLE_SUBCATEGORY = "tbSubcategory";
    private static final String COLUMN_SUBCATEGORY_ID = "subcategory_id";
    private static final String COLUMN_SUBCATEGORY_NAME= "subcategory_name";
    private static final String COLUMN_CATEGORY_ID_FK = "category_id";

    //Store table
    private static final String TABLE_STORE = "tbStore";
    private static final String COLUMN_STORE_ID = "store_id";
    private static final String COLUMN_STORE_ADDRESS= "store_address";
    private static final String COLUMN_STORE_WORKING_HOURS = "store_working_hours";
    private static final String COLUMN_STORE_PHONE_NUMBER = "store_phoneNumber";

    //Customer table
    private static final String TABLE_CUSTOMER = "tbCustomer";
    private static final String COLUMN_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_CUSTOMER_FIRST_NAME = "customer_FName";
    private static final String COLUMN_CUSTOMER_LAST_NAME = "customer_LNAme";
    private static final String COLUMN_CUSTOMER_PHONE = "customer_phone_number";
    private static final String COLUMN_CUSTOMER_EMAIL = "customer_email";
    private static final String COLUMN_CUSTOMER_PASSWORD = "customer_password";
    private static final String COLUMN_CUSTOMER_PROMO = "customer_voucher";

    //Voucher table
    private static final String TABLE_VOUCHER = "tbPromo";
    private static final String COLUMN_VOUCHER_ID = "voucher_id";
    private static final String COLUMN_VOUCHER_NAME = "voucher_name";
    private static final String COLUMN_VOUCHER_DISCOUNT = "voucher_discount";
    private static final String COLUMN_VOUCHER_START_DATE = "voucher_start_date";
    private static final String COLUMN_VOUCHER_END_DATE = "voucher_end_date";

    //Product table
    private static final String TABLE_PRODUCT = "tbProduct";
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_PRODUCT_BRAND = "product_brand";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "product_description";
    private static final String COLUMN_PRODUCT_SUBCATEGORY = "product_subcategory";
    private static final String COLUMN_PRODUCT_IMG = "product_img";
    private static final String COLUMN_PRODUCT_VOLUME = "product_volume";
    private static final String COLUMN_PRODUCT_PRICE = "product_price";
    private static final String COLUMN_PRODUCT_STATUS = "product_status";

    //create table sql query
    private String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
            + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CATEGORY_NAME + " TEXT" + ")";

    private String CREATE_SUBCATEGORY_TABLE = "CREATE TABLE " + TABLE_SUBCATEGORY + "("
            + COLUMN_SUBCATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SUBCATEGORY_NAME + " TEXT,"
            + COLUMN_CATEGORY_ID_FK + " INTEGER, FOREIGN KEY(" +COLUMN_CATEGORY_ID_FK +") REFERENCES " +
            TABLE_CATEGORY +"("+ COLUMN_CATEGORY_ID + ") ON UPDATE CASCADE" + ")";

    private String CREATE_STORE_TABLE = "CREATE TABLE " + TABLE_STORE + "("
            + COLUMN_STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_STORE_ADDRESS + " TEXT,"
            + COLUMN_STORE_WORKING_HOURS + " TEXT,"+ COLUMN_STORE_PHONE_NUMBER + " TEXT" + ")";

    private String CREATE_VOUCHER_TABLE = "CREATE TABLE " + TABLE_VOUCHER + "("
            + COLUMN_VOUCHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_VOUCHER_NAME + " TEXT,"
            + COLUMN_VOUCHER_DISCOUNT + " DOUBLE,"+ COLUMN_VOUCHER_START_DATE + " DATE," + COLUMN_VOUCHER_END_DATE + " DATE" + ")";

    private String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_CUSTOMER + "("
            + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CUSTOMER_FIRST_NAME + " TEXT,"
            + COLUMN_CUSTOMER_LAST_NAME + " TEXT,"+ COLUMN_CUSTOMER_EMAIL + " TEXT,"
            + COLUMN_CUSTOMER_PHONE + " TEXT,"+ COLUMN_CUSTOMER_PASSWORD + " TEXT,"
            + COLUMN_CUSTOMER_PROMO + " INTEGER, FOREIGN KEY(" +COLUMN_CUSTOMER_PROMO +") REFERENCES " +
            TABLE_VOUCHER +"("+ COLUMN_VOUCHER_ID + ") ON UPDATE CASCADE" + ")";

    //OK

    private String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT + "("
            + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PRODUCT_NAME + " TEXT,"
            + COLUMN_PRODUCT_BRAND + " TEXT,"+ COLUMN_PRODUCT_DESCRIPTION + " TEXT,"
            + COLUMN_PRODUCT_SUBCATEGORY + " INTEGER,"+ COLUMN_PRODUCT_IMG + " TEXT,"
            + COLUMN_PRODUCT_VOLUME + " TEXT," + COLUMN_PRODUCT_PRICE + " TEXT,"
            + COLUMN_PRODUCT_STATUS + " INTEGER, FOREIGN KEY(" +COLUMN_PRODUCT_SUBCATEGORY +") REFERENCES " +
            TABLE_SUBCATEGORY +"("+ COLUMN_SUBCATEGORY_ID + ") ON UPDATE CASCADE" + ")";


//    private static String DATABASE_NAME = "BeautyStore.db";
//    private static int database_edition = 3;
//    private static String table_1 = "tbCustomer";
//    private static String table_2 = "tbProduct";
//    private static String table_7 = "tbStaff";
//    private static String table_8 = "tbInventory";
//    private static String table_9 = "tbPromo";
//    private static String table_10 = "tbCartItem";
//    private static String table_11 = "tbCart";
//    private static String table_12 = "tbOrder";
//    private static String table_13 = "tbPaymentInfo";
//    private static String table_14 = "tbDeliveryInfo";
//    private static String table_15 = "tbPickupInfo";
//    private static String table_16 = "tbDeliveryType";
//    private static String table_17 = "tbAddress";


    private Context context;
    private SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            //Category table updated
            db.execSQL(CREATE_CATEGORY_TABLE);
            toastMessage("Category Table CREATED");
            db.execSQL(CREATE_SUBCATEGORY_TABLE);
            toastMessage("Subcategory Table CREATED");
            db.execSQL(CREATE_STORE_TABLE);
            toastMessage("Store Table CREATED");
            db.execSQL(CREATE_VOUCHER_TABLE);
            toastMessage("Promo Table CREATED");
            db.execSQL(CREATE_CUSTOMER_TABLE);
            toastMessage("Customer Table CREATED");
            db.execSQL(CREATE_PRODUCT_TABLE);
            toastMessage("Product Table CREATED");

//
//            db.execSQL("create table " + table_2 + "(ID integer PRIMARY KEY AUTOINCREMENT, product_name varchar(225), brand_id integer, " +
//                    "subcategory_id integer, description varchar(225), image_URL varchar(225), volume varchar(50), price money, " +
//                    "status varchar(20), FOREIGN KEY(brand_id) REFERENCES  tbBrand (ID) ON UPDATE CASCADE," +
//                    "FOREIGN KEY(subcategory_id) REFERENCES  tbSubcategory (ID) ON UPDATE CASCADE)");
//            Toast.makeText(context, "Product Table CREATED", Toast.LENGTH_SHORT).show();
//
//            db.execSQL("create table " + table_7 + "(ID integer PRIMARY KEY AUTOINCREMENT, staff_position varchar(50)," +
//                    "staff_name varchar(50), staff_surname varchar(50), staff_phone varchar(10), staff_login varchar(10), " +
//                    "staff_password varchar(10))");
//            Toast.makeText(context, "Staff Table CREATED", Toast.LENGTH_SHORT).show();
//
//            db.execSQL("create table " + table_8 + "(ID integer PRIMARY KEY AUTOINCREMENT, product_id integer, " +
//                    " store_id integer, quantity integer, " +
//                    "FOREIGN KEY(product_id) REFERENCES  tbProduct (ID) ON UPDATE CASCADE," +
//                    "FOREIGN KEY(store_id) REFERENCES  tbStore (ID) ON UPDATE CASCADE)");
//            Toast.makeText(context, "Inventory Table CREATED", Toast.LENGTH_SHORT).show();
//
//
//            db.execSQL("create table " + table_16 + "(ID integer PRIMARY KEY AUTOINCREMENT, delivery_type varchar(50)," +
//                    "delivery_price money)");
//            Toast.makeText(context, "DeliveryType Table CREATED", Toast.LENGTH_SHORT).show();
//
//            db.execSQL("create table " + table_1 + "(ID integer PRIMARY KEY AUTOINCREMENT, first_name varchar(50), last_name varchar(50)," +
//                    "phone_number varchar(10), birth_date date, gender varchar(10), user_name varchar(10), password varchar(10)," +
//                    "address_id integer, voucher integer, FOREIGN KEY(address_id) REFERENCES  tbAddress (ID) ON UPDATE CASCADE," +
//                    "FOREIGN KEY(voucher) REFERENCES  tbPromo (ID) ON UPDATE CASCADE)");
//            Toast.makeText(context, "Customer Table CREATED", Toast.LENGTH_SHORT).show();
//
//            db.execSQL("create table " + table_10 + "(ID integer PRIMARY KEY AUTOINCREMENT, product_id integer, quantity integer, total_cost money, " +
//                    "FOREIGN KEY(product_id) REFERENCES  tbProduct (ID) ON UPDATE CASCADE)");
//            Toast.makeText(context, "CartItem Table CREATED", Toast.LENGTH_SHORT).show();
//
//            db.execSQL("create table " + table_11 + "(ID integer PRIMARY KEY AUTOINCREMENT, cart_item_id integer, " +
//                    "customer_id integer, total_sum money, " +
//                    "FOREIGN KEY(cart_item_id) REFERENCES  tbCartItem (ID) ON UPDATE CASCADE," +
//                    "FOREIGN KEY(customer_id) REFERENCES  tbCustomer (ID) ON UPDATE CASCADE)");
//            Toast.makeText(context, "Cart Table CREATED", Toast.LENGTH_SHORT).show();
//
//
//            db.execSQL("create table " + table_13 + "(ID integer PRIMARY KEY AUTOINCREMENT, payment_type varchar(10), card_number varchar(20), " +
//                    "card_owner_name varchar(50), expire_date date, CVC varchar(10))");
//            Toast.makeText(context, "Payment Info Table CREATED", Toast.LENGTH_SHORT).show();
//
//
//            db.execSQL("create table " + table_14 + "(ID integer PRIMARY KEY AUTOINCREMENT, delivery_type_id integer, " +
//                    "delivery_date datetime, contact_phone varchar(10), delivery_address integer," +
//                    "FOREIGN KEY(delivery_type_id) REFERENCES  tbDeliveryType (ID) ON UPDATE CASCADE," +
//                    "FOREIGN KEY(delivery_address) REFERENCES  tbAddress (ID) ON UPDATE CASCADE)");
//            Toast.makeText(context, "Delivery Info Table CREATED", Toast.LENGTH_SHORT).show();
//
//            db.execSQL("create table " + table_15 + "(ID integer PRIMARY KEY AUTOINCREMENT, delivery_type_id integer, " +
//                    "pickup_early_date datetime, pickup_latest_date datetime, store_id integer," +
//                    "FOREIGN KEY(delivery_type_id) REFERENCES  tbDeliveryType (ID) ON UPDATE CASCADE," +
//                    "FOREIGN KEY(store_id) REFERENCES  tbStore (ID) ON UPDATE CASCADE)");
//            Toast.makeText(context, "Pickup Table CREATED", Toast.LENGTH_SHORT).show();
//
//            db.execSQL("create table " + table_12 + "(ID integer PRIMARY KEY AUTOINCREMENT, cart_id integer, " +
//                    "voucher_id integer, dicount_sum money, final_cost money, date date, delivery_info integer, " +
//                    "pickup_info integer, payment_info integer, FOREIGN KEY(cart_id) REFERENCES  tbCart (ID) ON UPDATE CASCADE," +
//                    "FOREIGN KEY(voucher_id) REFERENCES  tbPromo (ID) ON UPDATE CASCADE," +
//                    "FOREIGN KEY(delivery_info) REFERENCES  tbDeliveryInfo (ID) ON UPDATE CASCADE, " +
//                    "FOREIGN KEY(pickup_info) REFERENCES  tbPickupInfo (ID) ON UPDATE CASCADE," +
//                    "FOREIGN KEY(payment_info) REFERENCES  tbPaymentInfo (ID) ON UPDATE CASCADE)");
//            Toast.makeText(context, "Pickup Table CREATED", Toast.LENGTH_SHORT).show();

        }
        catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SUBCATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_STORE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_VOUCHER);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRODUCT);

        onCreate(db);
    }

    /**
     * Custom Open and Close methods for database
     */
    public void Open(){
        db = this.getWritableDatabase();
    }

    public void  Close(){
        db.close();
    }


    /**
     * Method to add a new category to database
     * @param category
     * @return
     */
    public boolean addCategory(Category category){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_NAME, category.getName());
        Long status = db.insert(TABLE_CATEGORY, null, cv);

        if (status!= -1){return true;}
        else {return false;}
    }

    public List<Category> getAllCategories() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_CATEGORY_ID,
                COLUMN_CATEGORY_NAME,
        };
        // sorting orders
        String sortOrder =
                COLUMN_CATEGORY_NAME + " ASC";
        List<Category> categoryList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the Category table
        Cursor cursor = db.query(TABLE_CATEGORY, //Table to query
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setCategory_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID))));
                category.setName(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));

                // Adding category record to list
                categoryList.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return category list
        return categoryList;
    }

    /**
     * Method to display all categories from the table
     * @return
     */
    public Cursor getAllCategoriesCursor(){
        Cursor cursor;
        cursor = db.rawQuery("select * from " + TABLE_CATEGORY, null); //null - additional parameters
        return cursor;
    }

    /**
     * Extract category ID using the category name fetched from spinner
     * @param categoryName
     * @return
     */
    public Cursor fetch_categoryByName (String categoryName){
        Cursor cursor = db.query(TABLE_CATEGORY, null, COLUMN_CATEGORY_NAME + "=?", new String[]{categoryName+""},null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Method to add new subcategory to database
     * @param subcategory
     * @return
     */
    public boolean insert_subcategory (Subcategory subcategory){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SUBCATEGORY_NAME, subcategory.getSubcategory_name());
        cv.put(COLUMN_CATEGORY_ID_FK, subcategory.getCategoryID());
        long status = db.insert(TABLE_SUBCATEGORY, null, cv);
        if (status!=-1){return true;}
        else {return false;}
    }

    /**
     * Method to display all subcategories from the table
     * @return cursor
     */
    public Cursor getAllSubcategoriesCursor(){
        Cursor cursor;
        cursor = db.rawQuery("select * from " + TABLE_SUBCATEGORY, null); //null - additional parameters
        return cursor;
    }

    /**
     * Extract subcategory ID using the category name fetched from spinner
     * @param subcatName
     * @return
     */
    public Cursor fetch_subcategoryByName (String subcatName){
        Cursor cursor = db.query(TABLE_SUBCATEGORY, null, COLUMN_SUBCATEGORY_NAME
                + "=?", new String[]{subcatName+""},null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Method to add the store in database
     * @param store
     * @return true/false
     */
    public boolean insert_store (Store store){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_STORE_ADDRESS, store.getStore_address());
        cv.put(COLUMN_STORE_WORKING_HOURS, store.getWorking_hours());
        cv.put(COLUMN_STORE_PHONE_NUMBER, store.getPhone_number());
        Long status = db.insert(TABLE_STORE, null, cv);
        if (status!= -1){return true;}
        else {return false;}
    }

    /**
     * Method to register a new customer in database
     * @param customer
     * @return
     */
    public boolean insert_customer(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_FIRST_NAME, customer.getFirst_name());
        cv.put(COLUMN_CUSTOMER_LAST_NAME, customer.getLast_name());
        cv.put(COLUMN_CUSTOMER_PHONE, customer.getFirst_name());
        cv.put(COLUMN_CUSTOMER_EMAIL, customer.getEmail());
        cv.put(COLUMN_CUSTOMER_PASSWORD, customer.getPassword());

        // Inserting Row
        Long status = db.insert(TABLE_CUSTOMER, null, cv);
        db.close();
        if (status != -1){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to create new product
     * @param product
     * @return
     */
    public boolean addProduct (Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT_NAME, product.getName());
        cv.put(COLUMN_PRODUCT_BRAND, product.getBrand());
        cv.put(COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        cv.put(COLUMN_PRODUCT_SUBCATEGORY, product.getSubcategoryID());
        cv.put(COLUMN_PRODUCT_IMG, product.getImageURL());
        cv.put(COLUMN_PRODUCT_PRICE, product.getPrice());
        cv.put(COLUMN_PRODUCT_VOLUME, product.getVolume());
        cv.put(COLUMN_PRODUCT_STATUS, product.isInStock());

        // Inserting Row
        Long status = db.insert(TABLE_PRODUCT, null, cv);
        db.close();
        if (status != -1){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to fetch all products and read their records
     * @return
     */
    public List<Product> getAllProducts() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_PRODUCT_ID,
                COLUMN_PRODUCT_NAME,
                COLUMN_PRODUCT_BRAND,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_PRODUCT_SUBCATEGORY,
                COLUMN_PRODUCT_IMG,
                COLUMN_PRODUCT_VOLUME,
                COLUMN_PRODUCT_PRICE,
                COLUMN_PRODUCT_STATUS
        };

        // sorting orders
        String sortOrder =
                COLUMN_PRODUCT_ID + " ASC";
        List<Product> productsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the Product table
        Cursor cursor = db.query(TABLE_PRODUCT, //Table to query
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProduct_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID))));
                product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)));
                product.setBrand(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_BRAND)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
                product.setSubcategoryID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_SUBCATEGORY))));
                product.setImageURL(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMG)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));
                product.setVolume(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_VOLUME)));
                product.setInStock(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_STATUS))));

                // Adding product record to list
                productsList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return product list
        return productsList;
    }

    /**
     * Method to search product by ID
     * @param id
     * @return
     */
    public Product getProduct(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        // array of columns to fetch
        String[] columns = {
                COLUMN_PRODUCT_ID,
                COLUMN_PRODUCT_NAME,
                COLUMN_PRODUCT_BRAND,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_PRODUCT_SUBCATEGORY,
                COLUMN_PRODUCT_IMG,
                COLUMN_PRODUCT_VOLUME,
                COLUMN_PRODUCT_PRICE,
                COLUMN_PRODUCT_STATUS
        };

        // selection criteria
        String selection = COLUMN_PRODUCT_ID  + "=?";

        // selection argument
        String[] selectionArgs = {String.valueOf(id)};

        // query to search the product by ID
        Cursor cursor = db.query(TABLE_PRODUCT, //Table to query
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare Product object
        Product product = new Product(
                cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_BRAND)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_SUBCATEGORY)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMG)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));

        // close the db connection
        cursor.close();

        return product;
    }

//    public Cursor fetch_productByName (String productName){
//        Cursor cursor = db.query(TABLE_PRODUCT, null, COLUMN_PRODUCT_NAME
//                + "=?", new String[]{productName+""},null,null,null);
//        if (cursor!=null){
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }

    /**
     * Method to update product records
     * @param product
     */
    public void updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getName());
        values.put(COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRODUCT_BRAND, product.getBrand());
        values.put(COLUMN_PRODUCT_SUBCATEGORY, product.getSubcategoryID());
        values.put(COLUMN_PRODUCT_PRICE, product.getPrice());
        values.put(COLUMN_PRODUCT_VOLUME, product.getVolume());
        values.put(COLUMN_PRODUCT_IMG, product.getImageURL());
        values.put(COLUMN_PRODUCT_STATUS, product.isInStock());

        // updating row
        db.update(TABLE_PRODUCT, values, COLUMN_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getProduct_id())});
        db.close();
    }

    /**
     * This method is to delete product record
     * @param product
     */
    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete product record by id
        db.delete(TABLE_PRODUCT, COLUMN_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getProduct_id())});
        db.close();
    }


    /**
     * Method to check if the customer exists or not
     * @param email
     * @return true/false
     */
    public boolean checkEmailExist(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_CUSTOMER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_CUSTOMER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        Cursor cursor = db.query(TABLE_CUSTOMER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * Method to check the customer's email and password match
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkCustomerExist(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_CUSTOMER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_CUSTOMER_EMAIL + " = ?" + " AND " + COLUMN_CUSTOMER_PASSWORD + " = ?";

        // selection argument
        String[] selectionArgs = {email, password};

        // query user table with condition
        Cursor cursor = db.query(TABLE_CUSTOMER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage (String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
