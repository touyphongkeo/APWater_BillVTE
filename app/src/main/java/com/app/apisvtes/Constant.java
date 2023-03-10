package com.app.apisvtes;

public class Constant {

    Constant()
    {
        //write your action here if need
    }

    //We will use this to store the user token number into shared preference
    public static final String SHARED_PREF_NAME = "com.app.restaurantpos"; //pcakage name+ id

    public static final String SP_PHONE = "phone";
    public static final String SP_PASSWORD = "password";
    public static final String SP_USER_NAME = "user_name";
    public static final String SP_USER_TYPE = "user_type";
    public static final String PAYMENT_METHOD_ID = "payment_method_id";
    public static final String PAYMENT_METHOD_NAME="payment_method_name";

    public static final String CUSTOMER_ID="customer_id";
    public static final String CUSTOMER_NAME="customer_name";
    public static final String CUSTOMER_CELL="customer_cell";
    public static final String CUSTOMER_EMAIL="customer_email";
    public static final String CUSTOMER_ADDRESS="customer_address";

    public static final String INVOICE_ID="invoice_id";
    public static final String ORDER_DATE="order_date";
    public static final String ORDER_TIME="order_time";
    public static final String ORDER_PAYMENT_METHOD="order_payment_method";
    public static final String ORDER_TYPE="order_type";
    public static final String TABLE_NO="table_no";
    public static final String ORDER_STATUS="order_status";


    //order status
    public static final String PENDING="Pending";
    public static final String PROCESSING="Processing";
    public static final String COMPLETED="Completed";
    public static final String CANCEL="Cancel";





    public static final String TAX="tax";
    public static final String DISCOUNT="discount";
    public static final String PRICE="price";

    public static final String ID="id";
    public static final String USER_NAME="user_name";
    public static final String USER_TYPE="user_type";
    public static final String USER_PHONE="user_phone";
    public static final String USER_PASSWORD="user_password";


    public static final String SUPPLIERS_ID="suppliers_id";
    public static final String SUPPLIERS_NAME="suppliers_name";
    public static final String SUPPLIERS_CONTACT_PERSON="suppliers_contact_person";
    public static final String SUPPLIERS_CELL="suppliers_cell";
    public static final String SUPPLIERS_EMAIL="suppliers_email";
    public static final String SUPPLIERS_ADDRESS="suppliers_address";

    public static final String PRODUCT_ID="product_id";
    public static final String PRODUCT_NAME="product_name";
    public static final String PRODUCT_CODE="product_code";
    public static final String PRODUCT_CATEGORY="product_category";
    public static final String PRODUCT_DESCRIPTION="product_description";
    public static final String PRODUCT_WEIGHT_UNIT_ID="product_weight_unit_id";
    public static final String PRODUCT_WEIGHT_UNIT="product_weight_unit";
    public static final String PRODUCT_WEIGHT="product_weight";
    public static final String PRODUCT_PRICE="product_price";
    public static final String PRODUCT_QTY="product_qty";
    public static final String PRODUCT_SUPPLIER="product_supplier";
    public static final String PRODUCT_SELL_PRICE="product_sell_price";
    public static final String PRODUCT_IMAGE="product_image";
    public static final String PRODUCT_ORDER_DATE="product_order_date";

    public static final String CART_ID="cart_id";


    public static final String CATEGORY_ID="category_id";
    public static final String CATEGORY_NAME="category_name";

    public static final String ORDER_RECEIPT="order_receipt";
    public static final String YEARLY="yearly";
    public static final String DAILY="daily";
    public static final String MONTHLY="monthly";

    public static final String ADMIN="admin";
    public static final String MANAGER="manager";


    public static final String EXPENSE_ID="expense_id";
    public static final String EXPENSE_NAME="expense_name";
    public static final String EXPENSE_NOTE="expense_note";
    public static final String EXPENSE_AMOUNT="expense_amount";
    public static final String EXPENSE_DATE="expense_date";
    public static final String EXPENSE_TIME="expense_time";

    //all table names
    public static String customers="customers";
    public static String users="users";
    public static String suppliers="suppliers";
    public static String productCategory="product_category";
    public static String products="products";
    public static String paymentMethod="payment_method";
    public static String expense="expense";
    public static String productCart="product_cart";
    public static String orderList="order_list";
    public static String orderDetails="order_details";




}
