package com.app.apisvtes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.apisvtes.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import java.sql.Connection;

import com.app.apisvtes.conn.DB_CON;

import static com.app.apisvtes.conn.ClassLibs.fCheck;
import static com.app.apisvtes.conn.ClassLibs.fstr;
import static com.app.apisvtes.conn.ClassLibs.fCalls;
import static com.app.apisvtes.conn.ClassLibs.fCallss;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private  Context Context;

    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {


        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public boolean deleteWaterBill(String id) {
        long check = database.delete("tbl_water", "AREACODE=?", new String[]{id});

        database.close();

        if (check == 1) {
            return true;
        } else {
            return false;
        }

    }


    //update category
    public boolean CheckLogin(String UserID,String Password) {
        boolean chk=false;
        try{

            String sql ="SELECT * FROM tbl_user where UserID= '"+ UserID +"' AND Password='" + Password +"'";
            Cursor cursor = database.rawQuery(sql, null);

            if (cursor.getCount()!=0) {
                chk=true;
            }else{
                chk=false;
            }
            cursor.close();
        }catch (Exception e){
           // Toasty.error(instance.Context,e.getMessage(), 0).show();

        }

        database.close();
        return chk;

    }














    public ArrayList<HashMap<String, String>> getDatarate(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        try{
        String sqlAdd ="";
        sqlAdd=" SELECT SUR3,CURCODE,SLAB2,CCODE,CATNAME,RATE1,SLAB1,RATE2,RATE3,SLAB3,RATE4,SLAB4,SLAB5,RATE5,MINCONSUMP,CURNCYTYPE,SURCHARGE,SUR1,SDAY1,SUR2,SDAY2,SUR3,SDAY3,SEWER,VAT FROM RATE WHERE CCODE='"+s+"' and Call=" + fCalls + " ORDER BY CCODE ASC ";
        Cursor cursor = database.rawQuery(sqlAdd, null);
        if (cursor.moveToFirst()) {
            do {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("SUR3", cursor.getString(0));
                map.put("CURCODE", cursor.getString(1));
                map.put("SLAB2", cursor.getString(2));
                map.put("CCODE", cursor.getString(3));
                map.put("CATNAME", cursor.getString(4));
                map.put("RATE1",cursor.getString(5));
                map.put("SLAB1", cursor.getString(6));
                map.put("RATE2",cursor.getString(7));
                map.put("RATE3",cursor.getString(8));
                map.put("SLAB3", cursor.getString(9));
                map.put("RATE4", cursor.getString(10));
                map.put("SLAB4", cursor.getString(11));
                map.put("SLAB5",cursor.getString(12));
                map.put("RATE5", cursor.getString(13));
                map.put("MINCONSUMP",cursor.getString(14));
                map.put("CURNCYTYPE", cursor.getString(15));
                map.put("SURCHARGE", cursor.getString(16));
                map.put("SUR1", cursor.getString(17));
                map.put("SDAY1", cursor.getString(18));
                map.put("SUR2", cursor.getString(19));
                map.put("SDAY2", cursor.getString(20));
                map.put("SUR3", cursor.getString(21));
                map.put("SDAY3", cursor.getString(22));
                map.put("SEWER",cursor.getString(23));
                map.put("VAT", cursor.getString(24));
                employee.add(map);
            } while (cursor.moveToNext());
        }
            cursor.close();
            database.close();
        }catch (Exception e){

        }

        return employee;

    }





    public ArrayList<HashMap<String, String>> getDataratejfg(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        try{
            String sqlAdd ="";
            sqlAdd=" SELECT SUR3,CURCODE,SLAB2,CCODE,CATNAME,RATE1,SLAB1,RATE2,RATE3,SLAB3,RATE4,SLAB4,SLAB5,RATE5,MINCONSUMP,CURNCYTYPE,SURCHARGE,SUR1,SDAY1,SUR2,SDAY2,SUR3,SDAY3,SEWER,VAT FROM RATE WHERE CCODE='"+s+"' and Call=" + fCallss + " ORDER BY CCODE ASC ";
            Cursor cursor = database.rawQuery(sqlAdd, null);
            if (cursor.moveToFirst()) {
                do {

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("SUR3", cursor.getString(0));
                    map.put("CURCODE", cursor.getString(1));
                    map.put("SLAB2", cursor.getString(2));
                    map.put("CCODE", cursor.getString(3));
                    map.put("CATNAME", cursor.getString(4));
                    map.put("RATE1",cursor.getString(5));
                    map.put("SLAB1", cursor.getString(6));
                    map.put("RATE2",cursor.getString(7));
                    map.put("RATE3",cursor.getString(8));
                    map.put("SLAB3", cursor.getString(9));
                    map.put("RATE4", cursor.getString(10));
                    map.put("SLAB4", cursor.getString(11));
                    map.put("SLAB5",cursor.getString(12));
                    map.put("RATE5", cursor.getString(13));
                    map.put("MINCONSUMP",cursor.getString(14));
                    map.put("CURNCYTYPE", cursor.getString(15));
                    map.put("SURCHARGE", cursor.getString(16));
                    map.put("SUR1", cursor.getString(17));
                    map.put("SDAY1", cursor.getString(18));
                    map.put("SUR2", cursor.getString(19));
                    map.put("SDAY2", cursor.getString(20));
                    map.put("SUR3", cursor.getString(21));
                    map.put("SDAY3", cursor.getString(22));
                    map.put("SEWER",cursor.getString(23));
                    map.put("VAT", cursor.getString(24));
                    employee.add(map);
                } while (cursor.moveToNext());
            }
            cursor.close();
            database.close();
        }catch (Exception e){

        }

        return employee;

    }







    public ArrayList<HashMap<String, String>> getDatarates(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        try{
            String sqlAdd ="";
            sqlAdd=" SELECT SUR3,CURCODE,SLAB2,CCODE,CATNAME,RATE1,SLAB1,RATE2,RATE3,SLAB3,RATE4,SLAB4,SLAB5,RATE5,MINCONSUMP,CURNCYTYPE,SURCHARGE,SUR1,SDAY1,SUR2,SDAY2,SUR3,SDAY3,SEWER,VAT FROM RATE WHERE CCODE='"+s+"' and Call=" + fCallss + " ORDER BY CCODE ASC ";
            Cursor cursor = database.rawQuery(sqlAdd, null);
            if (cursor.moveToFirst()) {
                do {

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("SUR3", cursor.getString(0));
                    map.put("CURCODE", cursor.getString(1));
                    map.put("SLAB2", cursor.getString(2));
                    map.put("CCODE", cursor.getString(3));
                    map.put("CATNAME", cursor.getString(4));
                    map.put("RATE1",cursor.getString(5));
                    map.put("SLAB1", cursor.getString(6));
                    map.put("RATE2",cursor.getString(7));
                    map.put("RATE3",cursor.getString(8));
                    map.put("SLAB3", cursor.getString(9));
                    map.put("RATE4", cursor.getString(10));
                    map.put("SLAB4", cursor.getString(11));
                    map.put("SLAB5",cursor.getString(12));
                    map.put("RATE5", cursor.getString(13));
                    map.put("MINCONSUMP",cursor.getString(14));
                    map.put("CURNCYTYPE", cursor.getString(15));
                    map.put("SURCHARGE", cursor.getString(16));
                    map.put("SUR1", cursor.getString(17));
                    map.put("SDAY1", cursor.getString(18));
                    map.put("SUR2", cursor.getString(19));
                    map.put("SDAY2", cursor.getString(20));
                    map.put("SUR3", cursor.getString(21));
                    map.put("SDAY3", cursor.getString(22));
                    map.put("SEWER",cursor.getString(23));
                    map.put("VAT", cursor.getString(24));
                    employee.add(map);
                } while (cursor.moveToNext());
            }
            cursor.close();
            database.close();
        }catch (Exception e){

        }

        return employee;

    }









    public ArrayList<HashMap<String, String>> searchListbills(String s, String s2) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE FROM tbl_water where Consumption > 0 and ( ACCOUNT LIKE '" + s +"%' OR  NAME LIKE '%" + s +"%') AND AREACODE='" + s2 + "' AND  Consumption<>'0.0' AND UPLOAD<>'1'  order by ACCOUNT ASC", null);
        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("ACCOUNT", cursor.getString(0));
                map.put("NAME", cursor.getString(1));
                map.put("PREVREAD", cursor.getString(2));
                map.put("PREVDATE", cursor.getString(3));
                map.put("Consumption", cursor.getString(4));
                map.put("TOTALDUE", cursor.getString(5));

                product.add(map);
            } while (cursor.moveToNext());
        }

        database.close();
        return product;
    }


    public ArrayList<HashMap<String, String>> searchListBill(String s, String s2) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE FROM tbl_water where Consumption > 0 and ( ACCOUNT LIKE '" + s +"%' OR  NAME LIKE '%" + s +"%') AND AREACODE='" + s2 + "' AND UPLOAD <>'1' AND Consumption <> '0.0'   order by ACCOUNT ASC", null);
        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ACCOUNT", cursor.getString(0));
                map.put("NAME", cursor.getString(1));
                map.put("PREVREAD", cursor.getString(2));
                map.put("PREVDATE", cursor.getString(3));
                map.put("Consumption", cursor.getString(4));
                map.put("TOTALDUE", cursor.getString(5));

                product.add(map);
            } while (cursor.moveToNext());
        }

        database.close();
        return product;
    }

    public ArrayList<HashMap<String, String>> getAllWaterUpload(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT *  FROM tbl_water WHERE AREACODE LIKE'" + s + "%' AND UPLOAD='2' AND Consumption <>'0.0' order by ACCOUNT ASC";
        Cursor cursor = database.rawQuery(sqlAdd, null);
        if (cursor.moveToFirst()) {
            do {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("RENT", cursor.getString(0));
                map.put("ACCOUNT", cursor.getString(1));
                map.put("Tcode", cursor.getString(2));
                map.put("NAME", cursor.getString(3));
                map.put("RATRID", cursor.getString(4));
                map.put("PREVREAD",cursor.getString(5));
                map.put("PREVDATE", cursor.getString(6));
                map.put("PRESREAD",cursor.getString(7));
                map.put("PRESDATE", cursor.getString(8));
                map.put("Consumption", cursor.getString(9));
                map.put("Deviation", cursor.getString(10));
                map.put("Detive", cursor.getString(11));
                map.put("waterBill",cursor.getString(12));
                map.put("Mrent", cursor.getString(13));
                map.put("Sewage",cursor.getString(14));
                map.put("Tax", cursor.getString(15));
                map.put("Surcharge", cursor.getString(16));
                map.put("Total_Bill", cursor.getString(17));
                map.put("TOTALDUE1", cursor.getString(18));
                map.put("KhetID", cursor.getString(19));
                map.put("AREACODE", cursor.getString(20));
                map.put("ZONE", cursor.getString(21));
                map.put("TOTALDUE", cursor.getString(22));
                map.put("Arrears",cursor.getString(23));
                map.put("Paid_date", cursor.getString(24));
                map.put("ConNew", cursor.getString(25));
                map.put("MTRNUMBER", cursor.getString(26));
                map.put("Latitude", cursor.getString(27));
                map.put("Longitude", cursor.getString(28));
                map.put("BILLNO", cursor.getString(30));

                employee.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;

    }

    public ArrayList<HashMap<String, String>> getAllWaterpritse(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT *  FROM tbl_water WHERE ACCOUNT LIKE'" + s + "%'";
        Cursor cursor = database.rawQuery(sqlAdd, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("RENT", cursor.getString(0));
                map.put("ACCOUNT", cursor.getString(1));
                map.put("Tcode", cursor.getString(2));
                map.put("NAME", cursor.getString(3));
                map.put("RATRID", cursor.getString(4));
                map.put("PREVREAD",cursor.getString(5));
                map.put("PREVDATE", cursor.getString(6));
                map.put("PRESREAD",cursor.getString(7));
                map.put("PRESDATE", cursor.getString(8));
                map.put("Consumption", cursor.getString(9));
                map.put("Deviation", cursor.getString(10));
                map.put("Detive", cursor.getString(11));
                map.put("waterBill",cursor.getString(12));
                map.put("Mrent", cursor.getString(13));
                map.put("Sewage",cursor.getString(14));
                map.put("Tax", cursor.getString(15));
                map.put("Surcharge", cursor.getString(16));
                map.put("Total_Bill", cursor.getString(17));
                map.put("TOTALDUE1", cursor.getString(18));
                map.put("KhetID", cursor.getString(19));
                map.put("AREACODE", cursor.getString(20));
                map.put("ZONE", cursor.getString(21));
                map.put("TOTALDUE", cursor.getString(22));
                map.put("Arrears",cursor.getString(23));
                map.put("Paid_date", cursor.getString(24));
                map.put("ConNew", cursor.getString(25));
                map.put("MTRNUMBER", cursor.getString(26));
                map.put("MTRWIDTH", cursor.getString(27));
                map.put("AVGCONSUM", cursor.getString(28));
                map.put("UPLOAD", cursor.getString(29));
                map.put("Latitude", cursor.getString(30));
                map.put("Longitude", cursor.getString(31));
                map.put("printer", cursor.getString(32));
                map.put("BILLNO", cursor.getString(33));
                map.put("pay", cursor.getString(34));
                map.put("Arrears2",cursor.getString(43));
                employee.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;
    }

    //get product weight unit name
    public String getZoneName(String category_id) {
        String product_category = "n/a";
        Cursor cursor = database.rawQuery("SELECT ZONECODE,ZONENAME FROM ZONE  WHERE ZONECODE='" + category_id + "'", null);
        if (cursor.moveToFirst()) {
            do {
                product_category = cursor.getString(1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return product_category;
    }

    public String getZoneNamessss(String category_id) {

        String product_category = "n/a";
        Cursor cursor = database.rawQuery(" SELECT ZONECODE,ZONENAME FROM ZONE  WHERE ZONECODE='" + category_id + "'", null);


        if (cursor.moveToFirst()) {
            do {
                product_category = cursor.getString(1);
            } while (cursor.moveToNext());
        }


        cursor.close();
        database.close();
        return product_category;
    }
    //get product weight unit name
    public String getVillName(String category_id) {
        String product_category = "n/a";
        Cursor cursor = database.rawQuery("SELECT Khet_ID,Khet_NameLao FROM KHET WHERE Khet_ID LIKE'" + category_id + "%'", null);
        if (cursor.moveToFirst()) {
            do {

                product_category = cursor.getString(1);

            } while (cursor.moveToNext());
        }


        cursor.close();
        database.close();
        return product_category;
    }


    //get product weight unit name
    public String getVillNamesss(String category_id) {

        String product_category = "n/a";
        Cursor cursor = database.rawQuery("SELECT Khet_ID,Khet_NameLao FROM KHET WHERE Khet_ID LIKE'" + category_id + "%'", null);


        if (cursor.moveToFirst()) {
            do {


                product_category = cursor.getString(1);


            } while (cursor.moveToNext());
        }


        cursor.close();
        database.close();
        return product_category;
    }




    //get product weight unit name
    public String getACCOUNT() {
        String ACCOUNT = "n/a";
        Cursor cursor = database.rawQuery("SELECT count(ACCOUNT)AS ACCOUNT FROM tbl_water", null);
        if (cursor.moveToFirst()) {
            do {
                ACCOUNT = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return ACCOUNT;
    }





    public String ACCOUNT(String ACCOUNT) {
        String  m = "0";

        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                //  String query="select Cut_qty from AP_Category Where Cat_id = N'"+Cat_id+"'";
                String query="SELECT AVGCONSUM FROM MASTER WHERE ACCOUNT = '"+ ACCOUNT +"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        m = String.valueOf(rs.getString("ACCOUNT").toString());
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {

        }
        return m;
    }







    public boolean addWaterBill(String RENT,String ACCOUNT,String Tcode,String NAME,String RATRID,String PREVREAD,String PREVDATE,String PRESREAD,String PRESDATE,
                                String Consumption,String Deviation,String Detive,String waterBill,String Mrent, String Sewage,String Tax, String Surcharge,String Total_Bill,
                                String TOTALDUE1,String KhetID,String AREACODE, String ZONE, String TOTALDUE,String Arrears,String Paid_date,String ConNew,String MTRNUMBER,String MTRWIDTH,String AVGCONSUM, String Latitude, String Longitude, String printer, String BILLNO,String moves,String CheckBill,String VALIDATED,String BPRINTED,String First,String WALKSEQ,String pay,String Arrears2) {

        ContentValues values = new ContentValues();
        values.put("RENT", RENT);
        values.put("ACCOUNT", ACCOUNT);
        values.put("Tcode", Tcode);
        values.put("NAME", NAME);
        values.put("RATRID", RATRID);
        values.put("PREVREAD", PREVREAD);
        values.put("PREVDATE", PREVDATE);
        values.put("PRESREAD", PRESREAD);
        values.put("PRESDATE", PRESDATE);
        values.put("Consumption", Consumption);
        values.put("Deviation", Deviation);
        values.put("Detive", Detive);
        values.put("waterBill", waterBill);
        values.put("Mrent", Mrent);
        values.put("Sewage", Sewage);
        values.put("Tax", Tax);
        values.put("Surcharge", Surcharge);
        values.put("Total_Bill", Total_Bill);
        values.put("TOTALDUE1", TOTALDUE1);
        values.put("KhetID", KhetID);
        values.put("AREACODE", AREACODE);
        values.put("ZONE", ZONE);
        values.put("TOTALDUE", TOTALDUE);
        values.put("Arrears", Arrears);
        values.put("Paid_date", Paid_date);
        values.put("ConNew", ConNew);
        values.put("MTRWIDTH", MTRWIDTH);
        values.put("AVGCONSUM", AVGCONSUM);
        values.put("Latitude", Latitude);
        values.put("Longitude", Longitude);
        values.put("printer", printer);
        values.put("BILLNO", BILLNO);
        values.put("move", moves);
        values.put("CheckBill", CheckBill);
        values.put("VALIDATED", VALIDATED);
        values.put("BPRINTED", BPRINTED);
        values.put("First", First);
        values.put("WALKSEQ", WALKSEQ);
        values.put("call", 2);
        values.put("pay", pay);
        values.put("Arrears2", Arrears2);
        long check = database.insert("tbl_water", null, values);
        database.close();
        //if data insert success, its return 1, if failed return -1
        return check != -1;
    }








    public boolean addWaterBill2(String RENT,String ACCOUNT,String Tcode,String NAME,String RATRID,String PREVREAD,String PREVDATE,String PRESREAD,String PRESDATE,
                                String Consumption,String Deviation,String Detive,String waterBill,String Mrent, String Sewage,String Tax, String Surcharge,String Total_Bill,
                                String TOTALDUE1,String KhetID,String AREACODE, String ZONE, String TOTALDUE,String Arrears,String Paid_date,String ConNew,String MTRNUMBER,String MTRWIDTH,String AVGCONSUM, String Latitude, String Longitude, String printer, String BILLNO,String moves,String CheckBill,String VALIDATED,String BPRINTED,String First,String WALKSEQ,String Arrears2) {

        ContentValues values = new ContentValues();
        values.put("RENT", RENT);
        values.put("ACCOUNT", ACCOUNT);
        values.put("Tcode", Tcode);
        values.put("NAME", NAME);
        values.put("RATRID", RATRID);
        values.put("PREVREAD", PREVREAD);
        values.put("PREVDATE", PREVDATE);
        values.put("PRESREAD", PRESREAD);
        values.put("PRESDATE", PRESDATE);
        values.put("Consumption", Consumption);
        values.put("Deviation", Deviation);
        values.put("Detive", Detive);
        values.put("waterBill", waterBill);
        values.put("Mrent", Mrent);
        values.put("Sewage", Sewage);
        values.put("Tax", Tax);
        values.put("Surcharge", Surcharge);
        values.put("Total_Bill", Total_Bill);
        values.put("TOTALDUE1", TOTALDUE1);
        values.put("KhetID", KhetID);
        values.put("AREACODE", AREACODE);
        values.put("ZONE", ZONE);
        values.put("TOTALDUE", TOTALDUE);
        values.put("Arrears", Arrears);
        values.put("Paid_date", Paid_date);
        values.put("ConNew", ConNew);
        values.put("MTRWIDTH", MTRWIDTH);
        values.put("AVGCONSUM", AVGCONSUM);
        values.put("Latitude", Latitude);
        values.put("Longitude", Longitude);
        values.put("printer", printer);
        values.put("BILLNO", BILLNO);
        values.put("move", moves);
        values.put("CheckBill", CheckBill);
        values.put("VALIDATED", VALIDATED);
        values.put("BPRINTED", BPRINTED);
        values.put("First", First);
        values.put("WALKSEQ", WALKSEQ);
        values.put("call", 2);
        values.put("pay", 0);
        values.put("Arrears2", Arrears2);
        long check = database.insert("tbl_water", null, values);
        database.close();
        //if data insert success, its return 1, if failed return -1
        return check != -1;
    }




    public boolean addWaterBillas(String RENT,String ACCOUNT,String Tcode,String NAME,String RATRID,String PREVREAD,String PREVDATE,String PRESREAD,String PRESDATE,
                                String Consumption,String Deviation,String Detive,String waterBill,String Mrent, String Sewage,String Tax, String Surcharge,String Total_Bill,
                                String TOTALDUE1,String KhetID,String AREACODE, String ZONE, String TOTALDUE,String Arrears,String Paid_date,String ConNew,String MTRNUMBER,String MTRWIDTH,String AVGCONSUM, String Latitude, String Longitude, String printer, String BILLNO,String moves,String CheckBill,String VALIDATED,String BPRINTED,String First,String WALKSEQ,String pay,String Arrears2) {

        ContentValues values = new ContentValues();
        values.put("RENT", RENT);
        values.put("ACCOUNT", ACCOUNT);
        values.put("Tcode", Tcode);
        values.put("NAME", NAME);
        values.put("RATRID", RATRID);
        values.put("PREVREAD", PREVREAD);
        values.put("PREVDATE", PREVDATE);
        values.put("PRESREAD", PRESREAD);
        values.put("PRESDATE", PRESDATE);
        values.put("Consumption", Consumption);
        values.put("Deviation", Deviation);
        values.put("Detive", Detive);
        values.put("waterBill", waterBill);
        values.put("Mrent", Mrent);
        values.put("Sewage", Sewage);
        values.put("Tax", Tax);
        values.put("Surcharge", Surcharge);
        values.put("Total_Bill", Total_Bill);
        values.put("TOTALDUE1", TOTALDUE1);
        values.put("KhetID", KhetID);
        values.put("AREACODE", AREACODE);
        values.put("ZONE", ZONE);
        values.put("TOTALDUE", TOTALDUE);
        values.put("Arrears", Arrears);
        values.put("Paid_date", Paid_date);
        values.put("ConNew", ConNew);
        values.put("MTRWIDTH", MTRWIDTH);
        values.put("AVGCONSUM", AVGCONSUM);
        values.put("Latitude", Latitude);
        values.put("Longitude", Longitude);
        values.put("printer", printer);
        values.put("BILLNO", BILLNO);
        values.put("move", moves);
        values.put("CheckBill", CheckBill);
        values.put("VALIDATED", VALIDATED);
        values.put("BPRINTED", BPRINTED);
        values.put("First", First);
        values.put("WALKSEQ", WALKSEQ);
        values.put("call", 1);
        values.put("pay", pay);
        values.put("Arrears2", Arrears2);
        long check = database.insert("tbl_water", null, values);
        database.close();
        //if data insert success, its return 1, if failed return -1
        return check != -1;
    }



    public boolean addWaterBillas3(String RENT,String ACCOUNT,String Tcode,String NAME,String RATRID,String PREVREAD,String PREVDATE,String PRESREAD,String PRESDATE,
                                  String Consumption,String Deviation,String Detive,String waterBill,String Mrent, String Sewage,String Tax, String Surcharge,String Total_Bill,
                                  String TOTALDUE1,String KhetID,String AREACODE, String ZONE, String TOTALDUE,String Arrears,String Paid_date,String ConNew,String MTRNUMBER,String MTRWIDTH,String AVGCONSUM, String Latitude, String Longitude, String printer, String BILLNO,String moves,String CheckBill,String VALIDATED,String BPRINTED,String First,String WALKSEQ,String Arrears2) {

        ContentValues values = new ContentValues();
        values.put("RENT", RENT);
        values.put("ACCOUNT", ACCOUNT);
        values.put("Tcode", Tcode);
        values.put("NAME", NAME);
        values.put("RATRID", RATRID);
        values.put("PREVREAD", PREVREAD);
        values.put("PREVDATE", PREVDATE);
        values.put("PRESREAD", PRESREAD);
        values.put("PRESDATE", PRESDATE);
        values.put("Consumption", Consumption);
        values.put("Deviation", Deviation);
        values.put("Detive", Detive);
        values.put("waterBill", waterBill);
        values.put("Mrent", Mrent);
        values.put("Sewage", Sewage);
        values.put("Tax", Tax);
        values.put("Surcharge", Surcharge);
        values.put("Total_Bill", Total_Bill);
        values.put("TOTALDUE1", TOTALDUE1);
        values.put("KhetID", KhetID);
        values.put("AREACODE", AREACODE);
        values.put("ZONE", ZONE);
        values.put("TOTALDUE", TOTALDUE);
        values.put("Arrears", Arrears);
        values.put("Paid_date", Paid_date);
        values.put("ConNew", ConNew);
        values.put("MTRWIDTH", MTRWIDTH);
        values.put("AVGCONSUM", AVGCONSUM);
        values.put("Latitude", Latitude);
        values.put("Longitude", Longitude);
        values.put("printer", printer);
        values.put("BILLNO", BILLNO);
        values.put("move", moves);
        values.put("CheckBill", CheckBill);
        values.put("VALIDATED", VALIDATED);
        values.put("BPRINTED", BPRINTED);
        values.put("First", First);
        values.put("WALKSEQ", WALKSEQ);
        values.put("call", 1);
        values.put("pay", 0);
        values.put("Arrears2", Arrears2);
        long check = database.insert("tbl_water", null, values);
        database.close();
        //if data insert success, its return 1, if failed return -1
        return check != -1;
    }


    public boolean addWaterBillS(String RENT,String ACCOUNT,String Tcode,String NAME,String RATRID,String PREVREAD,String PREVDATE,String PRESREAD,String PRESDATE,
                                String Consumption,String Deviation,String Detive,String waterBill,String Mrent, String Sewage,String Tax, String Surcharge,String Total_Bill,
                                String TOTALDUE1,String KhetID,String AREACODE, String ZONE, String TOTALDUE,String Arrears,String Paid_date,String ConNew,String MTRNUMBER,String MTRWIDTH,String AVGCONSUM, String Latitude, String Longitude, String printer) {

        ContentValues values = new ContentValues();

        values.put("RENT", RENT);
        values.put("ACCOUNT", ACCOUNT);
        values.put("Tcode", Tcode);
        values.put("NAME", NAME);
        values.put("RATRID", RATRID);
        values.put("PREVREAD", PREVREAD);
        values.put("PREVDATE", PREVDATE);
        values.put("PRESREAD", PRESREAD);
        values.put("PRESDATE", PRESDATE);
        values.put("Consumption", Consumption);
        values.put("Deviation", Deviation);
        values.put("Detive", Detive);
        values.put("waterBill", waterBill);
        values.put("Mrent", Mrent);
        values.put("Sewage", Sewage);
        values.put("Tax", Tax);
        values.put("Surcharge", Surcharge);
        values.put("Total_Bill", Total_Bill);
        values.put("TOTALDUE1", TOTALDUE1);
        values.put("KhetID", KhetID);
        values.put("AREACODE", AREACODE);
        values.put("ZONE", ZONE);
        values.put("TOTALDUE", TOTALDUE);
        values.put("Arrears", Arrears);
        values.put("Paid_date", Paid_date);
        values.put("ConNew", ConNew);
        values.put("MTRWIDTH", MTRWIDTH);
        values.put("AVGCONSUM", AVGCONSUM);
        values.put("Latitude", Latitude);
        values.put("Longitude", Longitude);
        values.put("printer", 0);


        long check = database.insert("tbl_water", null, values);

        database.close();

        //if data insert success, its return 1, if failed return -1
        return check != -1;
    }









    //update expense
    public boolean updateWater(String PREVREAD, String PRESDATE, String PRESREAD, String Deviation, String Consumption, String waterBill, String Mrent, String Sewage, String Tax, String Surcharge, String Total_Bill, String TOTALDUE1, String Detive, String  ConNew, String Paid_date, String  ACCOUNT,String Latitude,String Longitude)
    {

        ContentValues values = new ContentValues();

        values.put("PREVREAD", PREVREAD);
        values.put("PRESDATE", PRESDATE);
        values.put("PRESREAD", PRESREAD);
        values.put("Deviation", Deviation);
        values.put("Consumption", Consumption);
        values.put("waterBill", waterBill);
        values.put("Mrent", Mrent);
        values.put("Sewage", Sewage);
        values.put("Tax", Tax);
        values.put("Surcharge", Surcharge);
        values.put("Total_Bill", Total_Bill);
     //   values.put("Arrears", Arrears);
        values.put("TOTALDUE1", TOTALDUE1);
        values.put("Detive", Detive);
        values.put("ConNew", ConNew);
        values.put("Paid_date", Paid_date);
        values.put("ACCOUNT", ACCOUNT);
        values.put("Latitude", Latitude);
        values.put("Longitude", Longitude);


        long check = database.update("tbl_water",  values,"ACCOUNT=?",new String[]{ACCOUNT});
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    //update expense
    public boolean updateWaterSV(String PREVREAD, String PRESDATE, String PRESREAD, String Deviation, String Consumption, String waterBill, String Mrent, String Sewage, String Tax, String Surcharge, String Total_Bill, String TOTALDUE1, String Detive, String  ConNew, String Paid_date, String  ACCOUNT,String Latitude,String  Longitude,String version) {
        fCheck=0;
        try {
            Connection con = DB_CON.CONN();
           String query = "Update BB set PREVREAD="+ PREVREAD +",PRESDATE='"+ PRESDATE +"',PRESREAD="+PRESREAD+",Deviation="+ Deviation +",Consumption="+Consumption+",";
            query +=" waterBill="+waterBill+",Mrent="+Mrent+",Sewage="+Sewage+",Tax="+Tax+",Surcharge="+Surcharge+",Total_Bill="+Total_Bill+",TOTALDUE="+TOTALDUE1+",";
            query +=" Detive="+Detive+",ConNew="+ConNew+",Paid_date='" +Paid_date+"',ENTERED='1',Latitude='"+ Latitude +"',Longitude='"+ Longitude +"',version_app='"+ version +"' where ACCOUNT='" + ACCOUNT +"'";

         //   String query = "Update BB set PRESREAD="+PRESREAD+" where ACCOUNT='" + ACCOUNT +"'";
            fstr=query;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            fCheck=1;
            con.close();
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        }

        if (fCheck == 0) {
            return false;
        } else {
            return true;
        }
    }



    //update expense
    public boolean updateWaterSVS(String PREVREAD, String PRESDATE, String PRESREAD, String Deviation, String Consumption, String waterBill, String Mrent, String Sewage, String Tax, String Surcharge, String Total_Bill, String TOTALDUE1, String Detive, String  ConNew, String Paid_date, String  ACCOUNT,String Latitude,String  Longitude) {
        // public boolean updateWaterSVS(String PRESREAD,String  ACCOUNT) {
        fCheck=0;
        try{
            Connection con = DB_CON.CONN();
            String query = "Update BB set PREVREAD="+ PREVREAD +",PRESDATE='"+ PRESDATE +"',PRESREAD="+PRESREAD+",Deviation="+ Deviation +",Consumption="+Consumption+",";
            query +=" waterBill="+waterBill+",Mrent="+Mrent+",Sewage="+Sewage+",Tax="+Tax+",Surcharge="+Surcharge+",Total_Bill="+Total_Bill+",TOTALDUE="+TOTALDUE1+",";
            query +=" Detive="+Detive+",ConNew="+ConNew+",Paid_date='" +Paid_date+"',ENTERED='1',Latitude='"+ Latitude +"',Longitude='"+ Longitude +"',CheckBill='1',BatchFile='1',VALIDATED='1',BPRINTED='1',First='1',move='0',printer='1' WHERE ACCOUNT='" + ACCOUNT +"'";
            // test
            //String query = "Update BB set PRESREAD='"+PRESREAD+"' where ACCOUNT='" + ACCOUNT +"'";
            fstr=query;

            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            fCheck=1;

            con.close();
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        }
        if (fCheck == 0) {
            return false;
        } else {
            return true;
        }
    }



    public ArrayList<HashMap<String, String>> getLoadMASER(String s) {
        ArrayList<HashMap<String, String>> master = new ArrayList<>();
        try {
            String MICODE, TCODE, BILLNO, ACCOUNT, NAME, AREACODE, ZONE, RATRID, CCODE, HUNIT, CONADDR1, MAILADDR1, CUCODE,CNAME, CONADDR2, MAILADDR2, MEMONO, CONFEES, DEP_DT, CON_DT, SIZECODE, MTRWIDTH,
                    MTRNUMBER, MTRMFGCODE, DMETHOD, LOCATION, ALTITUTE,LONGTITUTE,STATUS,WALKSEQ,MCODE,AVGCONSUM,TOTALDUE, overpay,MOnno,PCAREA,COCODE,DMA,CmbAccount,chk,MUseDate,GroupBill,
                    NPP,CallectID,BILLDATE,Cost1,Cost2,Cost3,Rem_Cost,Unit,Lst_Updt,Lst_Usr, PC_Nm,OLD_ACCOUNT,move,Cost4,CONTax,Page_Grp,MM,Pro_ID;
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                String query = "SELECT MICODE, TCODE, BILLNO, ACCOUNT, NAME, AREACODE, ZONE, RATRID, CCODE, HUNIT, CONADDR1, MAILADDR1, CUCODE,CNAME, CONADDR2, MAILADDR2, MEMONO, CONFEES, DEP_DT, CON_DT, SIZECODE, MTRWIDTH,\n" +
                        "MTRNUMBER, MTRMFGCODE, DMETHOD, LOCATION, ALTITUTE,LONGTITUTE,STATUS,WALKSEQ,MCODE,AVGCONSUM,TOTALDUE, overpay,MOnno,PCAREA,COCODE,DMA,CmbAccount,chk,MUseDate,GroupBill,\n" +
                        "NPP,CallectID,BILLDATE,Cost1,Cost2,Cost3,Rem_Cost,Unit,Lst_Updt,Lst_Usr, PC_Nm,OLD_ACCOUNT,move,Cost4,CONTax,Page_Grp,MM,Pro_ID FROM MASTER WHERE AREACODE='"+s+"'  ORDER BY ACCOUNT";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("MICODE", rs.getString("MICODE"));
                        map.put("TCODE", rs.getString("TCODE"));
                        map.put("BILLNO", rs.getString("BILLNO"));
                        map.put("ACCOUNT", rs.getString("ACCOUNT"));
                        map.put("NAME", rs.getString("NAME"));
                        map.put("AREACODE", rs.getString("AREACODE"));
                        map.put("ZONE", rs.getString("ZONE"));
                        map.put("RATRID", rs.getString("RATRID"));
                        map.put("CCODE", rs.getString("CCODE"));
                        map.put("HUNIT", rs.getString("HUNIT"));
                        map.put("CONADDR1", rs.getString("CONADDR1"));
                        map.put("MAILADDR1", rs.getString("MAILADDR1"));
                        map.put("CUCODE", rs.getString("CUCODE"));
                        map.put("CNAME", rs.getString("CNAME"));
                        map.put("CONADDR2", rs.getString("CONADDR2"));
                        map.put("MAILADDR2", rs.getString("MAILADDR2"));
                        map.put("MEMONO", rs.getString("MEMONO"));
                        map.put("CONFEES", rs.getString("CONFEES"));
                        map.put("DEP_DT", rs.getString("DEP_DT"));
                        map.put("CON_DT", rs.getString("CON_DT"));
                        map.put("SIZECODE", rs.getString("SIZECODE"));
                        map.put("MTRWIDTH", rs.getString("MTRWIDTH"));
                        map.put("MTRNUMBER", rs.getString("MTRNUMBER"));
                        map.put("MTRMFGCODE", rs.getString("MTRMFGCODE"));
                        map.put("DMETHOD", rs.getString("DMETHOD"));
                        map.put("LOCATION", rs.getString("LOCATION"));
                        map.put("ALTITUTE", rs.getString("ALTITUTE"));
                        map.put("LONGTITUTE", rs.getString("LONGTITUTE"));
                        map.put("STATUS", rs.getString("STATUS"));
                        map.put("WALKSEQ", rs.getString("WALKSEQ"));
                        map.put("MCODE", rs.getString("MCODE"));
                        map.put("AVGCONSUM", rs.getString("AVGCONSUM"));
                        map.put("TOTALDUE", rs.getString("TOTALDUE"));
                        map.put("overpay", rs.getString("overpay"));
                        map.put("MOnno", rs.getString("MOnno"));
                        map.put("PCAREA", rs.getString("PCAREA"));
                        map.put("COCODE", rs.getString("COCODE"));
                        map.put("DMA", rs.getString("DMA"));
                        map.put("CmbAccount", rs.getString("CmbAccount"));
                        map.put("chk", rs.getString("chk"));
                        map.put("MUseDate", rs.getString("MUseDate"));
                        map.put("GroupBill", rs.getString("GroupBill"));
                        map.put("NPP", rs.getString("NPP"));
                        map.put("CallectID", rs.getString("CallectID"));
                        map.put("BILLDATE", rs.getString("BILLDATE"));
                        map.put("Cost1", rs.getString("Cost1"));
                        map.put("Cost2", rs.getString("Cost2"));
                        map.put("Cost3", rs.getString("Cost3"));
                        map.put("Rem_Cost", rs.getString("Rem_Cost"));
                        map.put("Unit", rs.getString("Unit"));
                        map.put("Lst_Updt", rs.getString("Lst_Updt"));
                        map.put("Lst_Usr", rs.getString("Lst_Usr"));
                        map.put("PC_Nm", rs.getString("PC_Nm"));
                        map.put("OLD_ACCOUNT", rs.getString("OLD_ACCOUNT"));
                        map.put("move", rs.getString("move"));
                        map.put("Cost4", rs.getString("Cost4"));
                        map.put("CONTax", rs.getString("CONTax"));
                        map.put("Page_Grp", rs.getString("Page_Grp"));
                        map.put("MM", rs.getString("MM"));
                        map.put("Pro_ID", rs.getString("Pro_ID"));
                        master.add(map);
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {
        }
        return master;
    }
    public boolean addMASTERS(String MICODES,String TCODES,String BILLNOS,String ACCOUNTS,String  NAMES,String AREACODES,String ZONES,String RATRIDS,String CCODES,String HUNITS,String CONADDR1,String MAILADDR1,String CUCODE,String CNAME,String CONADDR2,String MAILADDR2,String MEMONO,String CONFEES,String DEP_DT,String CON_DT,String SIZECODE,String MTRWIDTHS, String MTRNUMBERS,String MTRMFGCODE,String DMETHOD,String LOCATION,String ALTITUTE,String LONGTITUTE, String STATUS,String WALKSEQ,String MCODE,String AVGCONSUMS,String TOTALDUES,String overpay,String MOnno,String PCAREA,String COCODE,String DMA,String CmbAccount,String chk,String MUseDate,String GroupBill,String NPP,String CallectID,String BILLDATE,String Cost1,String Cost2,String Cost3,String Rem_Cost,String Unit,String Lst_Updt,String Lst_Usr,String PC_Nm,String OLD_ACCOUNT,String move,String Cost4,String CONTax,String Page_Grp,String MM,String Pro_ID) {
        ContentValues values = new ContentValues();
        values.put("MICODE", MICODES);
        values.put("TCODE", TCODES);
        values.put("BILLNO", BILLNOS);
        values.put("ACCOUNT", ACCOUNTS);
        values.put("NAME", NAMES);
        values.put("AREACODE", AREACODES);
        values.put("ZONE", ZONES);
        values.put("RATRID", RATRIDS);
        values.put("CCODE", CCODES);
        values.put("HUNIT", HUNITS);
        values.put("CONADDR1", CONADDR1);
        values.put("MAILADDR1", MAILADDR1);
        values.put("CUCODE", CUCODE);
        values.put("CNAME", CNAME);
        values.put("CONADDR2", CONADDR2);
        values.put("MAILADDR2", MAILADDR2);
        values.put("MEMONO", MEMONO);
        values.put("CONFEES", CONFEES);
        values.put("DEP_DT", DEP_DT);
        values.put("CON_DT", CON_DT);
        values.put("SIZECODE", SIZECODE);
        values.put("MTRWIDTH", MTRWIDTHS);
        values.put("MTRNUMBER", MTRNUMBERS);
        values.put("MTRMFGCODE", MTRMFGCODE);
        values.put("DMETHOD", DMETHOD);
        values.put("LOCATION", LOCATION);
        values.put("ALTITUTE", ALTITUTE);
        values.put("LONGTITUTE", LONGTITUTE);
        values.put("STATUS", STATUS);
        values.put("WALKSEQ", WALKSEQ);
        values.put("MCODE", MCODE);
        values.put("AVGCONSUM", AVGCONSUMS);
        values.put("TOTALDUE", TOTALDUES);
        values.put("overpay", overpay);
        values.put("MOnno", MOnno);
        values.put("PCAREA", PCAREA);
        values.put("COCODE", COCODE);
        values.put("DMA", DMA);
        values.put("CmbAccount", CmbAccount);
        values.put("chk", chk);
        values.put("MUseDate", MUseDate);
        values.put("GroupBill", GroupBill);
        values.put("NPP", NPP);
        values.put("CallectID", CallectID);
        values.put("BILLDATE", BILLDATE);
        values.put("Cost1", Cost1);
        values.put("Cost2", Cost2);
        values.put("Cost3", Cost3);
        values.put("Rem_Cost", Rem_Cost);
        values.put("Unit", Unit);
        values.put("Lst_Updt", Lst_Updt);
        values.put("Lst_Usr", Lst_Usr);
        values.put("PC_Nm", PC_Nm);
        values.put("OLD_ACCOUNT", OLD_ACCOUNT);
        values.put("move", move);
        values.put("Cost4", Cost4);
        values.put("CONTax", CONTax);
        values.put("Page_Grp", Page_Grp);
        values.put("MM", MM);
        values.put("Pro_ID", Pro_ID);
        long check = database.insert("MASTER", null, values);
        database.close();
        return check != -1;
    }
    public ArrayList<HashMap<String, String>> getLoadBT(String s) {
        ArrayList<HashMap<String, String>> bt = new ArrayList<>();
        try {
            String MICODE,TCODE,ACCOUNT,BILLNO,BILLDATE,STATUS,PREVREAD,PREVDATE,PRESREAD,PRESDATE,CONSUMP,Deviation,WATERBILL,MRENT,TAX,Sewage,Surcharge,Total_Bill,Arrears,TOTALDUE,AMOUNT,PAYDATE,Payarreas,Total,Pay_Month,Overpay,Grand_total,KhetID,areacode,Checks,Paid,R_Code,Choose,ReadDateBill,CCODE,SIZECODE,RateID,Ram_Month,cnt,Detive,DateBefore,Edit,CONNew,MTRMFGCODE,GroupBill,NPP,STATU,Lst_Updt,Lst_Usr,PC_Nm,callectid,DIS_Amt,Amt,Pro_ID;

            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                String query = "SELECT MICODE,TCODE,ACCOUNT,BILLNO,BILLDATE,STATUS,PREVREAD,PREVDATE,PRESREAD,PRESDATE,CONSUMP,Deviation,WATERBILL,MRENT,TAX,Sewage,Surcharge,Total_Bill,Arrears,TOTALDUE,AMOUNT,PAYDATE,Payarreas,Total,Pay_Month,Overpay,Grand_total,KhetID,areacode,Checks,Paid,R_Code,Choose,ReadDateBill,CCODE,SIZECODE,RateID,Ram_Month,cnt,Detive,DateBefore,Edit,CONNew,MTRMFGCODE,GroupBill,NPP,STATU,Lst_Updt,Lst_Usr,PC_Nm,callectid,DIS_Amt,Amt,Pro_ID FROM BT WHERE MICODE='"+s+"' ORDER BY ACCOUNT";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("MICODE", rs.getString("MICODE"));
                        map.put("TCODE", rs.getString("TCODE"));
                        map.put("ACCOUNT", rs.getString("ACCOUNT"));
                        map.put("BILLNO", rs.getString("BILLNO"));
                        map.put("BILLDATE", rs.getString("BILLDATE"));
                        map.put("STATUS", rs.getString("STATUS"));
                        map.put("PREVREAD", rs.getString("PREVREAD"));
                        map.put("PREVDATE", rs.getString("PREVDATE"));
                        map.put("PRESREAD", rs.getString("PRESREAD"));
                        map.put("PRESDATE", rs.getString("PRESDATE"));
                        map.put("CONSUMP", rs.getString("CONSUMP"));
                        map.put("Deviation", rs.getString("Deviation"));
                        map.put("WATERBILL", rs.getString("WATERBILL"));
                        map.put("MRENT", rs.getString("MRENT"));
                        map.put("TAX", rs.getString("TAX"));
                        map.put("Sewage", rs.getString("Sewage"));
                        map.put("Surcharge", rs.getString("Surcharge"));
                        map.put("Total_Bill", rs.getString("Total_Bill"));
                        map.put("Arrears", rs.getString("Arrears"));
                        map.put("TOTALDUE", rs.getString("TOTALDUE"));
                        map.put("AMOUNT", rs.getString("AMOUNT"));
                        map.put("PAYDATE", rs.getString("PAYDATE"));
                        map.put("Payarreas", rs.getString("Payarreas"));
                        map.put("Total", rs.getString("Total"));
                        map.put("Pay_Month", rs.getString("Pay_Month"));
                        map.put("Overpay", rs.getString("Overpay"));
                        map.put("Grand_total", rs.getString("Grand_total"));
                        map.put("KhetID", rs.getString("KhetID"));
                        map.put("areacode", rs.getString("areacode"));
                        map.put("Checks", rs.getString("Checks"));
                        map.put("Paid", rs.getString("Paid"));
                        map.put("R_Code", rs.getString("R_Code"));
                        map.put("Choose", rs.getString("Choose"));
                        map.put("ReadDateBill", rs.getString("ReadDateBill"));
                        map.put("CCODE", rs.getString("CCODE"));
                        map.put("SIZECODE", rs.getString("SIZECODE"));
                        map.put("RateID", rs.getString("RateID"));
                        map.put("Ram_Month", rs.getString("Ram_Month"));
                        map.put("cnt", rs.getString("cnt"));
                        map.put("Detive", rs.getString("Detive"));
                        map.put("DateBefore", rs.getString("DateBefore"));
                        map.put("Edit", rs.getString("Edit"));
                        map.put("CONNew", rs.getString("CONNew"));
                        map.put("MTRMFGCODE", rs.getString("MTRMFGCODE"));
                        map.put("GroupBill", rs.getString("GroupBill"));
                        map.put("NPP", rs.getString("NPP"));
                        map.put("STATU", rs.getString("STATU"));
                        map.put("Lst_Updt", rs.getString("Lst_Updt"));
                        map.put("Lst_Usr", rs.getString("Lst_Usr"));
                        map.put("PC_Nm", rs.getString("PC_Nm"));
                        map.put("callectid", rs.getString("callectid"));
                        map.put("DIS_Amt", rs.getString("DIS_Amt"));
                        map.put("Amt", rs.getString("Amt"));
                        map.put("Pro_ID", rs.getString("Pro_ID"));
                        bt.add(map);
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {
        }
        return bt;

    }
    public boolean addBTS(String MICODE,String TCODE,String ACCOUNT,String BILLNO,String BILLDATE,String STATUS,String PREVREAD,String PREVDATE,String PRESREAD,String PRESDATE,String CONSUMP,String Deviation,String WATERBILL,String MRENT,String TAX,String Sewage,String Surcharge,String Total_Bill,String Arrears,String TOTALDUE,String AMOUNT,String PAYDATE,String Payarreas,String Total,String Pay_Month,String Overpay,String Grand_total,String KhetID,String areacode,String Checks,String Paid,String R_Code,String Choose,String ReadDateBill,String CCODE,String SIZECODE,String RateID,String Ram_Month,String cnt,String Detive,String DateBefore,String Edit,String CONNew,String MTRMFGCODE,String GroupBill,String NPP,String STATU,String Lst_Updt,String Lst_Usr,String PC_Nm,String callectid,String DIS_Amt,String Amt,String Pro_ID){
        ContentValues values = new ContentValues();
        values.put("MICODE", MICODE);
        values.put("TCODE", TCODE);
        values.put("ACCOUNT", ACCOUNT);
        values.put("BILLNO", BILLNO);
        values.put("BILLDATE", BILLDATE);
        values.put("STATUS", STATUS);
        values.put("PREVREAD", PREVREAD);
        values.put("PREVDATE", PREVDATE);
        values.put("CONSUMP", CONSUMP);
        values.put("Deviation", Deviation);
        values.put("WATERBILL", WATERBILL);
        values.put("MRENT", MRENT);
        values.put("TAX", TAX);
        values.put("Sewage", Sewage);
        values.put("Surcharge", Surcharge);
        values.put("Total_Bill", Total_Bill);
        values.put("Arrears", Arrears);
        values.put("TOTALDUE", TOTALDUE);
        values.put("AMOUNT", AMOUNT);
        values.put("PAYDATE", PAYDATE);
        values.put("Payarreas", Payarreas);
        values.put("Total", Total);
        values.put("Pay_Month", Pay_Month);
        values.put("Overpay", Overpay);
        values.put("Grand_total", Grand_total);
        values.put("KhetID", KhetID);
        values.put("areacode", areacode);
        values.put("Checks", Checks);
        values.put("Paid", Paid);
        values.put("R_Code", R_Code);
        values.put("Choose", Choose);
        values.put("ReadDateBill", ReadDateBill);
        values.put("CCODE", CCODE);
        values.put("SIZECODE", SIZECODE);
        values.put("RateID", RateID);
        values.put("Ram_Month", Ram_Month);
        values.put("cnt", cnt);
        values.put("Detive", Detive);
        values.put("DateBefore", DateBefore);
        values.put("Edit", Edit);
        values.put("CONNew", CONNew);
        values.put("MTRMFGCODE", MTRMFGCODE);
        values.put("GroupBill", GroupBill);
        values.put("NPP", NPP);
        values.put("STATU", STATU);
        values.put("Lst_Updt", Lst_Updt);
        values.put("Lst_Usr", Lst_Usr);
        values.put("PC_Nm", PC_Nm);
        values.put("callectid", callectid);
        values.put("DIS_Amt", DIS_Amt);
        values.put("Amt", Amt);
        values.put("Pro_ID", Pro_ID);
        long check = database.insert("BT", null, values);
        database.close();
        return check != -1;
    }
    public ArrayList<HashMap<String, String>> getLoadPAYMENT(String s) {
        ArrayList<HashMap<String, String>> payment = new ArrayList<>();
        try {
            String ETA,ACCOUNT,PAY_NO,Pay_Month,PAY_DATE,TOTALBill_Amt,Arreas_Amt,TOTAL_Amt,Overpay,PSTATUS,Cmd,Bank,BILLNO,Location,BankNm,Lst_updt,Lst_usr,pc_nm,h,UserID,UserNm,Amt_letter,DD,VV,dr,Cr;
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                String query = "SELECT ETA,ACCOUNT,PAY_NO,Pay_Month,PAY_DATE,TOTALBill_Amt,Arreas_Amt,TOTAL_Amt,Overpay,PSTATUS,Cmd,Bank,BILLNO,Location,BankNm,Lst_updt,Lst_usr,pc_nm,h,UserID,UserNm,Amt_letter,DD,VV,dr,Cr FROM PAYMENT WHERE VV='"+s+"' ORDER BY ACCOUNT";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("ETA", rs.getString("ETA"));
                        map.put("ACCOUNT", rs.getString("ACCOUNT"));
                        map.put("PAY_NO", rs.getString("PAY_NO"));
                        map.put("Pay_Month", rs.getString("Pay_Month"));
                        map.put("TOTALBill_Amt", rs.getString("TOTALBill_Amt"));
                        map.put("Arreas_Amt", rs.getString("Arreas_Amt"));
                        map.put("TOTAL_Amt", rs.getString("TOTAL_Amt"));
                        map.put("Overpay", rs.getString("Overpay"));
                        map.put("PSTATUS", rs.getString("PSTATUS"));
                        map.put("Cmd", rs.getString("Cmd"));
                        map.put("Bank", rs.getString("Bank"));
                        map.put("BILLNO", rs.getString("BILLNO"));
                        map.put("Location", rs.getString("Location"));
                        map.put("BankNm", rs.getString("BankNm"));
                        map.put("Lst_updt", rs.getString("Lst_updt"));
                        map.put("Lst_usr", rs.getString("Lst_usr"));
                        map.put("pc_nm", rs.getString("pc_nm"));
                        map.put("h", rs.getString("h"));
                        map.put("UserID", rs.getString("UserID"));
                        map.put("UserNm", rs.getString("UserNm"));
                        map.put("Amt_letter", rs.getString("Amt_letter"));
                        map.put("DD", rs.getString("DD"));
                        map.put("VV", rs.getString("VV"));
                        map.put("dr", rs.getString("dr"));
                        map.put("Cr", rs.getString("Cr"));
                        payment.add(map);
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {
        }
        return payment;

    }
    public boolean addPAYMENTSS( String ETA,String ACCOUNT,String PAY_NO,String Pay_Month,String PAY_DATE,String TOTALBill_Amt,String Arreas_Amt,String TOTAL_Amt,String Overpay,String PSTATUS,String Cmd,String Bank,String BILLNO,String Location,String BankNm,String Lst_updt,String Lst_usr,String pc_nm,String h,String UserID,String UserNm,String Amt_letter,String DD,String VV,String dr,String Cr){
        ContentValues values = new ContentValues();
        values.put("ETA", ETA);
        values.put("ACCOUNT", ACCOUNT);
        values.put("PAY_NO", PAY_NO);
        values.put("Pay_Month", Pay_Month);
        values.put("PAY_DATE", PAY_DATE);
        values.put("TOTALBill_Amt", TOTALBill_Amt);
        values.put("Arreas_Amt", Arreas_Amt);
        values.put("TOTAL_Amt", TOTAL_Amt);
        values.put("Overpay", Overpay);
        values.put("PSTATUS", PSTATUS);
        values.put("Cmd", Cmd);
        values.put("Bank", Bank);
        values.put("BILLNO", BILLNO);
        values.put("Location", Location);
        values.put("BankNm", BankNm);
        values.put("Lst_updt", Lst_updt);
        values.put("Lst_usr", Lst_usr);
        values.put("pc_nm", pc_nm);
        values.put("h", h);
        values.put("UserID", UserID);
        values.put("UserNm", UserNm);
        values.put("DD", DD);
        values.put("VV", VV);
        values.put("dr", dr);
        values.put("Cr", Cr);
        long check = database.insert("PAYMENT", null, values);
        database.close();
        return check != -1;
    }






    public boolean updatemasterMOVE(String ACCOUNT, String BILLNO) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET move=0 WHERE ACCOUNT = '"+ACCOUNT+"' AND BILLNO='"+BILLNO+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }
        catch (SQLException se)
        {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }


    public boolean updatemasterMOVEone(String ACCOUNT, String BILLNO) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE MASTER SET move=0 WHERE ACCOUNT = '"+ACCOUNT+"' AND BILLNO='"+BILLNO+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }
        catch (SQLException se)
        {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }






    public boolean updatemasterMOVESS(String ACCOUNT, String BILLNO) {
        ContentValues values = new ContentValues();
        values.put("move", 0);
        long check = database.update("tbl_water", values,"ACCOUNT=? AND BILLNO=? ",  new String[]{ACCOUNT,BILLNO});
        database.close();
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }



    public boolean updatemasterMOVEoneSS(String ACCOUNT, String BILLNO) {
        ContentValues values = new ContentValues();
        values.put("move", 0);
        long check = database.update("MASTER", values,"ACCOUNT=? AND BILLNO=? ",  new String[]{ACCOUNT,BILLNO});
        database.close();
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }




    public boolean updatemasterMOVACCOUNTSS(String KhetID, String AREACODE, String ACCOUNT, String BILLNO) {
        ContentValues values = new ContentValues();
         values.put("printer", 0);
         values.put("CheckBill", 1);
         values.put("BatchFile", 1);
         values.put("VALIDATED", 1);
         values.put("BPRINTED", 1);
         values.put("First", 1);
        long check = database.update("tbl_water", values,"KhetID=?  and AREACODE=? and ACCOUNT=? AND BILLNO=?",  new String[]{KhetID,AREACODE,ACCOUNT,BILLNO});
        database.close();
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }



    public boolean updateMASTERSS(String PRESDATE, String ZONE, String AREACODE,String ACCOUNT, String BILLNO) {
        ContentValues values = new ContentValues();
        values.put("BILLDATE", PRESDATE);
        long check = database.update("MASTER", values,"ZONE=?  and AREACODE=? and ACCOUNT=? AND BILLNO=?",  new String[]{ZONE,AREACODE,ACCOUNT,BILLNO});
        database.close();
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }



    public String getPROSDATES() {
        String currency = "n/a";
        Cursor cursor = database.rawQuery("SELECT PRESDATE FROM tbl_water WHERE printer IS NULL LIMIT 1", null);
        if (cursor.moveToFirst()) {
            do {
                currency = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return currency;
    }




    //update expense
    public boolean updatemasterMOVACCOUNT(String KhetID, String AREACODE, String ACCOUNT, String billno) {
        fCheck=0;
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET printer='0', CheckBill = '0', BatchFile = '1', VALIDATED = '1', BPRINTED='1',  First = '1'  WHERE KhetID='" + KhetID + "'  and AREACODE='" + AREACODE + "' and ACCOUNT='" + ACCOUNT + "' AND BILLNO='"+ billno +"'";
            fstr=query;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            fCheck=1;
            con.close();
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        }

        if (fCheck == 0) {
            return false;
        } else {
            return true;
        }
    }



    public boolean updatBBCheckBill(String ACCOUNT, String billno) {
        fCheck=0;
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET  CheckBill = '1'  WHERE  ACCOUNT='" + ACCOUNT + "' AND BILLNO='"+ billno +"'";
            fstr=query;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            fCheck=1;
            con.close();
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        }

        if (fCheck == 0) {
            return false;
        } else {
            return true;
        }
    }







    public boolean updateMASTER(String PRESDATE, String khetID, String AREACODE,String ACCOUNT, String billno) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE MASTER set  BILLDATE='"+PRESDATE+"' WHERE ZONE='"+khetID+ "' and AREACODE='"+AREACODE+"' and ACCOUNT='"+ACCOUNT+"',BILLNO='"+billno+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }
        catch (SQLException se)
        {
        }
        return true;
    }


    public boolean updateMASTERTOTALDUE1(String TOTALDUE, String khetID, String AREACODE,String ACCOUNT, String billno) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE MASTER SET  TOTALDUE=N'"+TOTALDUE+"' WHERE ZONE='"+khetID+ "' and AREACODE='"+AREACODE+"' and ACCOUNT='"+ACCOUNT+"',BILLNO='"+billno+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }
        catch (SQLException se)
        {
        }
        return true;
    }




    public boolean updateMASTERS(String khetID, String AREACODE,String ACCOUNT, String billno) {
        try {
            Connection con = DB_CON.CONN();
            //String query = "UPDATE MASTER set MASTER.TOTALDUE=BB.TOTALDUE,MASTER.AVGCONSUM=BB.detive  from MASTER,BB where MASTER.ACCOUNT=BB.ACCOUNT andBB.KhetID='"+khetID+"' and MASTER.AREACODE='"+AREACODE+"' and ACCOUNT='"+ACCOUNT+"',AND BILLNO=N'"+billno+"'";
            String query = "UPDATE MASTER set MASTER.TOTALDUE=BB.TOTALDUE from MASTER,BB where MASTER.ACCOUNT=BB.ACCOUNT and BB.KhetID='"+khetID+"' and MASTER.AREACODE='"+AREACODE+"' and ACCOUNT='"+ACCOUNT+"',AND BILLNO=N'"+billno+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }
        catch (SQLException se){
        }
        return true;
    }




    public boolean updateMASTERSBill_no(String ACCOUNT, String billno,String date) {
        try {
            Connection con = DB_CON.CONN();
            //String query = "UPDATE MASTER set MASTER.TOTALDUE=BB.TOTALDUE,MASTER.AVGCONSUM=BB.detive  from MASTER,BB where MASTER.ACCOUNT=BB.ACCOUNT andBB.KhetID='"+khetID+"' and MASTER.AREACODE='"+AREACODE+"' and ACCOUNT='"+ACCOUNT+"',AND BILLNO=N'"+billno+"'";
            String query = "UPDATE MASTER SET BILLNO=N'"+ billno+ "',BILLDATE=N'"+ date +"'  WHERE  ACCOUNT='"+ACCOUNT+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }
        catch (SQLException se){
        }
        return true;
    }



    public Integer Updatemasterandtbl_water(String khetID, String AREACODE,String ACCOUNT, String billno) {
        Cursor cursor = database.rawQuery("UPDATE MASTER SET MASTER.TOTALDUE=tbl_water.TOTALDUE FROM MASTER,tbl_water WHERE MASTER.ACCOUNT=tbl_water.ACCOUNT AND tbl_water.KhetID='"+khetID+"' AND MASTER.AREACODE='"+AREACODE+"' AND ACCOUNT='"+ACCOUNT+"' AND BILLNO=N'"+billno+"'", null);
        int itemCount = cursor.getCount();
        cursor.close();
        database.close();
        return itemCount;
    }



    public boolean addBT(String PRESDATE, String Usr_id1, String android,String khetID, String AREACODE, String billno) {
        long check = 1;
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                String query="insert into BT(MICODE,TCODE,ACCOUNT,PREVREAD,PREVDATE,PRESREAD,PRESDATE,CONSUMP,Deviation,WATERBILL,MRENT,Sewage,TAX,Surcharge,TOTALDUE,Total_Bill,Dis_Amt,Amt, BILLNO,KhetID,AREACODE,R_Code,Arrears,RATEID,detive,DateBefore,BILLDATE,CONnew,AMOUNT,Ram_Month,SIZECODE,MTRMFGCODE,STATU,Lst_updt,Lst_usr,pc_nm) select MICODE,TCODE,ACCOUNT,PREVREAD,PREVDATE,PRESREAD,PRESDATE,Consumption,Deviation,WATERBILL,MRENT,Sewage,TAX,Surcharge,TOTALDUE,Total_Bill, Dis_Amt, Amt, BILLNO,KhetID,AREACODE,R_Code,Arrears,RATRID,detive,DateBefore, '"+PRESDATE+"',CONNew,0,TOTALDUE,SIZECODE,MTRMFGCODE,STATUS  ,Getdate(),N'"+Usr_id1+"',N'"+android+"'  from BB where KhetID='"+khetID+"' and AREACODE='"+AREACODE+"'  and BB.Move=0 AND BILLNO=N'"+billno+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                check = 1;
            }
            con.close();
        } catch (Exception ex) {
        }
        return check != -1;
    }





    public boolean addBTArrears(String Arrears,String khetID, String AREACODE, String billno) {
        long check = 1;
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                String query="Update BT SET Arrears=N'"+Arrears+"',STMobile='1' where KhetID='"+khetID+"' and AREACODE='"+AREACODE+"'  AND BILLNO=N'"+billno+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                check = 1;
            }
            con.close();
        } catch (Exception ex) {
        }
        return check != -1;
    }



    public String getPRESDATE(String BILLNO,String BILLDATE) {
        String billdate = "n/a";
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                String query="SELECT BILLDATE FROM BT WHERE BILLNO='"+BILLNO+"' AND BILLDATE='"+BILLDATE+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        billdate = String.valueOf(rs.getString("BILLDATE"));
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {
        }
        return billdate;
    }








    public boolean updateBT() {
        try {
            Connection con = DB_CON.CONN();
            String query = "update  bt set bt.callectid=master.callectid from master,BT where master.ACCOUNT=bt.ACCOUNT and bt.callectid is null";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }
        catch (SQLException se){
        }
        return true;
    }










    //update expense
    public boolean updateMaster(String TOTALDUE1, String  ACCOUNT) {
            try {
                Connection con = DB_CON.CONN();
                String query = "UPDATE Master SET TOTALDUE='"+TOTALDUE1+"',PCAREA='0' WHERE  ACCOUNT='"+ ACCOUNT +"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
                con.close();
            }catch (SQLException se) {
               // Log.e("ERROR", se.getMessage());
            }
            return true;
    }


    public boolean updateTOTALDUEBB(String TOTALDUE1, String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET TOTALDUE='"+ TOTALDUE1 +"' WHERE  ACCOUNT='"+ ACCOUNT +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }


    public boolean updateTOTALDUEBBBB(double TOTALDUE1, String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET TOTALDUE='"+ TOTALDUE1 +"' WHERE  ACCOUNT='"+ ACCOUNT +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }


    public boolean updateTOTALDUEBBB(double TOTALDUE1, String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET TOTALDUE='"+ TOTALDUE1 +"' WHERE  ACCOUNT='"+ ACCOUNT +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }


    public boolean updatePaymentOverpay(String Overpay, String  ACCOUNT,String billno) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE PAYMENT SET Overpay=N'"+ Overpay +"' WHERE  ACCOUNT='"+ ACCOUNT +"' AND BILLNO='"+billno+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }


    public boolean updateArrearsMaster(double Arrears, String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE MASTER SET Arrears=N'"+ Arrears +"' WHERE  ACCOUNT='"+ ACCOUNT +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }




    public boolean updateMaster22(String TOTALDUE1, String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE Master SET TOTALDUE='"+TOTALDUE1+"' WHERE  ACCOUNT='"+ ACCOUNT +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }



    public boolean UpdateTOTALDUE1(double TOTALDUE1, String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE Master SET TOTALDUE='"+TOTALDUE1+"' WHERE  ACCOUNT='"+ ACCOUNT +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }



    public boolean UpdatePaysdd(String acc) {
        ContentValues values = new ContentValues();
        values.put("pay", 1);
        long check = database.update("tbl_water", values,"ACCOUNT=? ",  new String[]{acc});
        database.close();

        //if data insert success, its return 1, if failed return -1 where ACCOUNT='" + ACCOUNT +"'";
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }



    public boolean updateMaster2(String TOTALDUE1, String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE Master SET TOTALDUE='"+TOTALDUE1+"',PCAREA='0' WHERE  ACCOUNT='"+ ACCOUNT +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }



    public boolean updateMaster223(String TOTALDUE1, String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE Master SET TOTALDUE='"+ TOTALDUE1 +"' WHERE  ACCOUNT='"+ ACCOUNT +"'";

            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);

            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }

    public boolean updateMaster223898(double TOTALDUE1, String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE Master SET TOTALDUE='"+TOTALDUE1+"' WHERE  ACCOUNT='"+ ACCOUNT +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }




    public boolean updateMPay(String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET pay='1' WHERE  ACCOUNT='"+ ACCOUNT +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }





    public String getTOTALDUESS(String ACCOUNT, String BILLNO) {
        String b = "0";
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                //  String query="select Cut_qty from AP_Category Where Cat_id = N'"+Cat_id+"'";
                String query="SELECT TOTAL_Amt FROM PAYMENT WHERE ACCOUNT='"+ACCOUNT+"' AND BILLNO='"+BILLNO+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        b = String.valueOf(rs.getString("TOTAL_Amt"));
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {

        }
        return b;
    }







    //get call
    public String getamotpay(String ACCOUNT, String BILLNO) {
        String TOTALDUE1 = "n/a";
        Cursor cursor = database.rawQuery("SELECT * FROM tbl_water  WHERE ACCOUNT LIKE'%" + ACCOUNT + "%' AND BILLNO LIKE '%"+ BILLNO +"%'", null);
        if (cursor.moveToFirst()) {
            do {
                TOTALDUE1 = cursor.getString(18);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return TOTALDUE1;
    }






    public String getTOTALDUESSsfdsdfsf(String ACCOUNT, String BILLNO) {
        String b = "0";
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                //  String query="select Cut_qty from AP_Category Where Cat_id = N'"+Cat_id+"'";
                String query="SELECT TOTAL_Amt FROM PAYMENT WHERE ACCOUNT='"+ACCOUNT+"' AND BILLNO='"+BILLNO+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        b = String.valueOf(rs.getString("TOTAL_Amt"));
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {

        }
        return b;
    }





    public boolean updateDBB(String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET BB.Arrears=Master.TOTALDUE from BB,Master  WHERE Master.account=BB.account  AND Master.ACCOUNT='"+ ACCOUNT +"'  AND BB.ENTERED='1'  AND BB.Checkbill='0'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }



    public boolean updateDBBD(String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET TOTALDUE=Total_Bill+Arrears WHERE ACCOUNT='"+ ACCOUNT +"'AND BB.ENTERED='1' AND BB.Checkbill='0'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }



    public boolean updateDBBT(String  ACCOUNT,String billno) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BT set Checks='2' where ACCOUNT='"+ ACCOUNT +"' and  Checks='0' and BILLNO<>'"+ billno +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }



    public boolean updatePayment(String TOTAL_Amt,String  ACCOUNT,String billno) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE  PAYMENT SET TOTAL_Amt=TOTAL_Amt+'"+ TOTAL_Amt +"' WHERE ACCOUNT='"+ ACCOUNT +"' and BILLNO='"+ billno +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }


    public boolean updateOverpay(Double Overpay,String  ACCOUNT,String billno) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE  PAYMENT SET Overpay=N'"+ Overpay +"' WHERE ACCOUNT='"+ ACCOUNT +"' and BILLNO='"+ billno +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }




    public boolean updateBILLNO(String  ACCOUNT,String billno) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BT SET TOTALDUE='2' WHERE ACCOUNT='"+ ACCOUNT +"' AND BILLNO<>'"+ billno +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }


    public boolean updateDBBTAMOUNTS(String date,String Paid_date,String ACCOUNT,String billno) {
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BT set AMOUNT=Total_Bill, PayArreas=Arrears, TOTAL=TOTALDUE, Ram_Month=0, PAYDATE='"+ date +"', PAY_Month='"+Paid_date+"', Paid='Yes', STATUS='1',Checks='1' WHERE ACCOUNT='"+ ACCOUNT +"' and BILLNO='"+ billno +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }



    public ArrayList<HashMap<String, String>> getPAY_NO(String rep) {
        ArrayList<HashMap<String, String>> product_category = new ArrayList<>();
        try {

            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                String query="Select Top 1 PAY_NO, substring(PAY_NO,9,9) asNo From PAYMENT  where substring(PAY_NO,1,5)=N'"+rep+"' Order by PAY_NO DESC";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("asNo", rs.getString("asNo"));
                        product_category.add(map);
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {
        }
        return product_category;
    }







    public String getPAY_NO2() {
        String b = "0";
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                //  String query="select Cut_qty from AP_Category Where Cat_id = N'"+Cat_id+"'";
                String query="Select PAY_NO From PAYMENT";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        b = String.valueOf(rs.getString("PAY_NO"));
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {

        }
        return b;
    }





    public boolean addPAYMENT(String  ACCOUNT, String PAY_NO, String Paid_date,String date,String TOTALBill_Amt,String aount,String Arreas_Amt,String paymony, String PSTATUS, String BILLNO,String Lst_usr) {
      //    /,TOTALBill_Amt,Arreas_Amt
        try {
            Connection con = DB_CON.CONN();
            String query = "Insert into PAYMENT(ACCOUNT,PAY_NO,Pay_Month,PAY_DATE,TOTALBill_Amt,Arreas_Amt,TOTAL_Amt,paymony,PSTATUS,Cmd,Bank,BILLNO,Location,Lst_updt,Lst_usr,pc_nm,UserID,UserNm)VALUES(N'"+ACCOUNT+"',N'"+ PAY_NO +"','"+Paid_date+"',N'"+date+"',N'"+TOTALBill_Amt+"',N'"+Arreas_Amt+"',N'"+aount+"',N'"+paymony+"',N'"+PSTATUS+"',1,0,N'"+BILLNO+"',0,N'"+date+"',N'"+Lst_usr+"','android','"+Lst_usr+"','"+Lst_usr+"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }



    public boolean addPAYMENTssdfsf(String  ACCOUNT, String PAY_NO, String Paid_date,String date,String TOTALBill_Amt,String aount,String Arreas_Amt,String paymony, String PSTATUS, String BILLNO,String Lst_usr) {
        //    /,TOTALBill_Amt,Arreas_Amt
        try {
            Connection con = DB_CON.CONN();
            String query = "Insert into PAYMENT(ACCOUNT,PAY_NO,Pay_Month,PAY_DATE,TOTALBill_Amt,Arreas_Amt,TOTAL_Amt,paymony,PSTATUS,Cmd,Bank,BILLNO,Location,Lst_updt,Lst_usr,pc_nm,UserID,UserNm)VALUES(N'"+ACCOUNT+"',N'"+ PAY_NO +"','"+Paid_date+"',N'"+date+"',N'"+TOTALBill_Amt+"',N'"+Arreas_Amt+"',N'"+aount+"',N'"+TOTALBill_Amt+"',N'"+PSTATUS+"',1,0,N'"+BILLNO+"',0,N'"+date+"',N'"+Lst_usr+"','android','"+Lst_usr+"','"+Lst_usr+"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }



    public boolean addPAYMENT221(String  ACCOUNT, String PAY_NO, String Paid_date,String date,String TOTALBill_Amt,String aount,String Arreas_Amt,String paymony, String PSTATUS, String BILLNO,String Lst_usr,String Overpay) {
        //    /,TOTALBill_Amt,Arreas_Amt
        try {
            Connection con = DB_CON.CONN();
            String query = "Insert into PAYMENT(ACCOUNT,PAY_NO,Pay_Month,PAY_DATE,TOTALBill_Amt,Arreas_Amt,TOTAL_Amt,paymony,PSTATUS,Cmd,Bank,BILLNO,Location,Lst_updt,Lst_usr,pc_nm,UserID,UserNm,Overpay)VALUES(N'"+ACCOUNT+"',N'"+ PAY_NO +"','"+Paid_date+"',N'"+date+"',N'"+TOTALBill_Amt+"',N'"+Arreas_Amt+"',N'"+aount+"',N'"+TOTALBill_Amt+"',N'"+PSTATUS+"',1,0,N'"+BILLNO+"',0,N'"+date+"',N'"+Lst_usr+"','android','"+Lst_usr+"','"+Lst_usr+"','"+Overpay+"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }




    public boolean addPAYMENT22(String  ACCOUNT, String PAY_NO, String Paid_date,String date,double TOTALBill_Amt,String aount,String Arreas_Amt,String paymony, String PSTATUS, String BILLNO,String Lst_usr) {
        //    /,TOTALBill_Amt,Arreas_Amt
        try {
            Connection con = DB_CON.CONN();
            String query = "Insert into PAYMENT(ACCOUNT,PAY_NO,Pay_Month,PAY_DATE,TOTALBill_Amt,Arreas_Amt,TOTAL_Amt,paymony,PSTATUS,Cmd,Bank,BILLNO,Location,Lst_updt,Lst_usr,pc_nm,UserID,UserNm)VALUES(N'"+ACCOUNT+"',N'"+ PAY_NO +"','"+Paid_date+"',N'"+date+"',N'"+TOTALBill_Amt+"',N'"+Arreas_Amt+"',N'"+aount+"',N'"+TOTALBill_Amt+"',N'"+PSTATUS+"',1,0,N'"+BILLNO+"',0,N'"+date+"',N'"+Lst_usr+"','android','"+Lst_usr+"','"+Lst_usr+"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }


    public boolean addPAYMENT2233(String  ACCOUNT, String PAY_NO, String Paid_date,String date,double TOTALBill_Amt,String aount,String Arreas_Amt,String paymony, String PSTATUS, String BILLNO,String Lst_usr) {
        //    /,TOTALBill_Amt,Arreas_Amt
        try {
            Connection con = DB_CON.CONN();
            String query = "Insert into PAYMENT(ACCOUNT,PAY_NO,Pay_Month,PAY_DATE,TOTALBill_Amt,Arreas_Amt,TOTAL_Amt,paymony,PSTATUS,Cmd,Bank,BILLNO,Location,Lst_updt,Lst_usr,pc_nm,UserID,UserNm)VALUES(N'"+ACCOUNT+"',N'"+ PAY_NO +"','"+Paid_date+"',N'"+date+"',N'"+TOTALBill_Amt+"',N'"+Arreas_Amt+"',N'"+aount+"',N'"+TOTALBill_Amt+"',N'"+PSTATUS+"',1,0,N'"+BILLNO+"',0,N'"+date+"',N'"+Lst_usr+"','android','"+Lst_usr+"','"+Lst_usr+"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }catch (SQLException se) {
        }
        return true;
    }




    public boolean addPAYMENT222(String  ACCOUNT, String PAY_NO,String Pay_Month){
        //    /,TOTALBill_Amt,Arreas_Amt
        try {
            Connection conn = DB_CON.CONN();
            String query = "Insert into PAYMENT(ACCOUNT,PAY_NO,Pay_Month)VALUES('"+ ACCOUNT +"',N'"+ PAY_NO +"',N'"+ Pay_Month +"')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
        }catch (SQLException se) {
        }
        return true;
    }




    //update expense
    public boolean updatemasterSV(String TOTALDUE1,  String  ACCOUNT) {
        try {
            Connection con = DB_CON.CONN();
            String query = "Update master  set TOTALDUE=" + TOTALDUE1 +"  where ACCOUNT='" + ACCOUNT +"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.close();
        }
        catch (SQLException se)
        {
            // Log.e("ERROR", se.getMessage());
        }
        return true;
    }





    //update category
    public boolean updateUpload(String acc,String vl) {
        ContentValues values = new ContentValues();
        values.put("UPLOAD", vl);
        values.put("printer", 0);
        long check = database.update("tbl_water", values,"ACCOUNT=? ",  new String[]{acc});
        database.close();

        //if data insert success, its return 1, if failed return -1 where ACCOUNT='" + ACCOUNT +"'";
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    //update category
    public boolean updatetbl_waterbv(String TOTALDUE1,String acc) {
        ContentValues values = new ContentValues();
        values.put("TOTALDUE1", TOTALDUE1);
        long check = database.update("tbl_water", values,"ACCOUNT=? ",  new String[]{acc});
        database.close();

        //if data insert success, its return 1, if failed return -1 where ACCOUNT='" + ACCOUNT +"'";
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean updateUploade(String acc,String vl) {
        ContentValues values = new ContentValues();
        values.put("UPLOAD", vl);
        long check = database.update("tbl_water", values,"ACCOUNT=? ",  new String[]{acc});
        database.close();

        //if data insert success, its return 1, if failed return -1 where ACCOUNT='" + ACCOUNT +"'";
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }  //update category


    public boolean updataxt(double tax,String ACCOUNT) {
        fCheck=0;
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET Tax='"+ tax +"' WHERE  ACCOUNT='" + ACCOUNT + "'";
            fstr=query;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            fCheck=1;
            con.close();
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        }

        if (fCheck == 0) {
            return false;
        } else {
            return true;
        }
    }



    public boolean updatotal_Bill(double total_Bill,String ACCOUNT) {
        fCheck=0;
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET Total_Bill='"+ total_Bill +"' WHERE  ACCOUNT='" + ACCOUNT + "'";
            fstr=query;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            fCheck=1;
            con.close();
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        }

        if (fCheck == 0) {
            return false;
        } else {
            return true;
        }
    }



    public boolean updatotalTOTALDUE(double TOTALDUE,String ACCOUNT) {
        fCheck=0;
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET TOTALDUE='"+ TOTALDUE +"' WHERE  ACCOUNT='" + ACCOUNT + "'";
            fstr=query;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            fCheck=1;
            con.close();
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        }

        if (fCheck == 0) {
            return false;
        } else {
            return true;
        }
    }






    public boolean updatotalTOTALDUEMaster(double TOTALDUE,String ACCOUNT) {
        fCheck=0;
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE MASTER SET TOTALDUE='"+ TOTALDUE +"' WHERE  ACCOUNT='" + ACCOUNT + "'";
            fstr=query;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            fCheck=1;
            con.close();
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        }

        if (fCheck == 0) {
            return false;
        } else {
            return true;
        }
    }





    public boolean updateUploads(String acc) {
        ContentValues values = new ContentValues();
        values.put("printer", 1);
        long check = database.update("tbl_water", values,"ACCOUNT=? ",  new String[]{acc});
        database.close();

        //if data insert success, its return 1, if failed return -1 where ACCOUNT='" + ACCOUNT +"'";
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    //update expense
    public boolean updatemaone(String ACCOUNT) {
        fCheck=0;
        try {
            Connection con = DB_CON.CONN();
            String query = "UPDATE BB SET printer='1' WHERE  ACCOUNT='" + ACCOUNT + "'";
            fstr=query;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            fCheck=1;
            con.close();
        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        }

        if (fCheck == 0) {
            return false;
        } else {
            return true;
        }
    }



    //delete product from cart
    public boolean deletetbl_water() {
        long check = database.delete("tbl_water", null,null);
        database.close();
        return check == 1;
    }





    public boolean addWaterBill2(String RENT,String ACCOUNT) {
        ContentValues values = new ContentValues();
        values.put("RENT", RENT);
        values.put("ACCOUNT", ACCOUNT);
        long check = database.insert("water", null, values);
        database.close();
        //if data insert success, its return 1, if failed return -1
        return check != -1;
    }


    public ArrayList<HashMap<String, String>> getAllWaterLoadSV(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        try {
            String RENT, ACCOUNT, Tcode, NAME, RATRID, PREVREAD, PREVDATE, PRESREAD, PRESDATE,
                    Consumption, Deviation, Detive, waterBill, Mrent, Sewage, Tax, Surcharge, Total_Bill,
                    TOTALDUE1, KhetID, AREACODE, ZONE, TOTALDUE, Arrears, Paid_date, ConNew,MTRNUMBER,Latitude,Longitude,BILLNO,move,CheckBill,VALIDATED,BPRINTED,First,WALKSEQ,pay,Arrears2;
           // String query = "UPDATE BB SET printer='0', CheckBill = '1', BatchFile = '1', VALIDATED = '1', BPRINTED='1',  First = '1'  WHERE KhetID='" + KhetID + "'  and AREACODE='" + AREACODE + "' and ACCOUNT='" + ACCOUNT + "' AND BILLNO='"+ billno +"'";

            Connection con = DB_CON.CONN();
            if (con == null) {
                //Toasty.error(MenuActivity.this, R.string.data_export_fail, Toast.LENGTH_SHORT).show();
            } else {
              //  String query = "SELECT MRENT.RENT, MASTER.ACCOUNT,MASTER.Tcode, MASTER.NAME, MASTER.RATRID,BB.PREVREAD, BB.PREVDATE, BB.PRESREAD, BB.PRESDATE, BB.Consumption,BB.Deviation, BB.Detive, BB.waterBill, BB.Mrent,BB.Sewage,BB.Tax,BB.Surcharge, BB.Total_Bill,BB.TOTALDUE AS TOTALDUE1,BB.KhetID,MASTER.AREACODE, MASTER.ZONE, MASTER.TOTALDUE,BB.Arrears,BB.Paid_date,BB.ConNew,MASTER.MTRNUMBER,MASTER.MTRWIDTH,MASTER.AVGCONSUM,BB.Latitude,BB.Longitude,BB.printer,BB.BILLNO,BB.move,BB.CheckBill,BB.VALIDATED,BB.BPRINTED,BB.First,BB.WALKSEQ,BB.pay FROM  MASTER,BB, MRENT WHERE  MASTER.SIZECODE = MRENT.SIZECODE AND  MASTER.ACCOUNT = BB.ACCOUNT AND MASTER.AREACODE LIKE '%"+s+"%' AND MASTER.Move=0  AND BB.STATUS='M' ORDER BY BB.ACCOUNT";
             //   String query = "SELECT MRENT.RENT, MASTER.ACCOUNT,MASTER.Tcode, MASTER.NAME, MASTER.RATRID,BB.PREVREAD, BB.PREVDATE, BB.PRESREAD, BB.PRESDATE, BB.Consumption,BB.Deviation, BB.Detive, BB.waterBill, BB.Mrent,BB.Sewage,BB.Tax,BB.Surcharge, BB.Total_Bill,BB.TOTALDUE AS TOTALDUE1,BB.KhetID,MASTER.AREACODE, MASTER.ZONE, MASTER.TOTALDUE,BB.Arrears,BB.Paid_date,BB.ConNew,MASTER.MTRNUMBER,MASTER.MTRWIDTH,MASTER.AVGCONSUM,BB.Latitude,BB.Longitude,BB.printer,BB.BILLNO,BB.move,BB.CheckBill,BB.VALIDATED,BB.BPRINTED,BB.First,BB.WALKSEQ,BB.pay FROM  MASTER,BB, MRENT WHERE  MASTER.SIZECODE = MRENT.SIZECODE AND  MASTER.ACCOUNT = BB.ACCOUNT AND MASTER.AREACODE = '"+ s +"' AND MASTER.Move=0  AND BB.STATUS='M' ORDER BY BB.ACCOUNT";
                String query = "SELECT MRENT.RENT, MASTER.ACCOUNT,MASTER.Tcode, MASTER.NAME, MASTER.RATRID,BB.PREVREAD, BB.PREVDATE, BB.PRESREAD, BB.PRESDATE, BB.Consumption,BB.Deviation, BB.Detive, BB.waterBill, BB.Mrent,BB.Sewage,BB.Tax,BB.Surcharge, BB.Total_Bill,BB.TOTALDUE AS TOTALDUE1,BB.KhetID,MASTER.AREACODE, MASTER.ZONE, MASTER.TOTALDUE,BB.Arrears,BB.Paid_date,BB.ConNew,MASTER.MTRNUMBER,MASTER.MTRWIDTH,MASTER.AVGCONSUM,BB.Latitude,BB.Longitude,BB.printer,BB.BILLNO,BB.move,BB.CheckBill,BB.VALIDATED,BB.BPRINTED,BB.First,BB.WALKSEQ,BB.pay,MASTER.Arrears as Arrears2 FROM  MASTER,BB, MRENT WHERE  MASTER.SIZECODE = MRENT.SIZECODE AND  MASTER.ACCOUNT = BB.ACCOUNT AND MASTER.AREACODE = '"+ s +"' AND MASTER.Move=0  AND BB.STATUS='M' ORDER BY BB.ACCOUNT";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("RENT", rs.getString("RENT"));
                        map.put("ACCOUNT", rs.getString("ACCOUNT"));
                        map.put("Tcode", rs.getString("Tcode"));
                        map.put("NAME", rs.getString("NAME"));
                        map.put("RATRID", rs.getString("RATRID"));
                        map.put("PREVREAD", rs.getString("PREVREAD"));
                        map.put("PREVDATE", rs.getString("PREVDATE"));
                        map.put("PRESREAD", rs.getString("PRESREAD"));
                        map.put("PRESDATE", rs.getString("PRESDATE"));
                        map.put("Consumption", rs.getString("Consumption"));
                        map.put("Deviation", rs.getString("Deviation"));
                        map.put("Detive", rs.getString("Detive"));
                        map.put("waterBill", rs.getString("waterBill"));
                        map.put("Mrent", rs.getString("Mrent"));
                        map.put("Sewage", rs.getString("Sewage"));
                        map.put("Tax", rs.getString("Tax"));
                        map.put("Surcharge", rs.getString("Surcharge"));
                        map.put("Total_Bill", rs.getString("Total_Bill"));
                        map.put("TOTALDUE1", rs.getString("TOTALDUE1"));
                        map.put("KhetID", rs.getString("KhetID"));
                        map.put("AREACODE", rs.getString("AREACODE"));
                        map.put("ZONE", rs.getString("ZONE"));
                        map.put("TOTALDUE", rs.getString("TOTALDUE"));
                        map.put("Arrears", rs.getString("Arrears"));
                        map.put("Paid_date", rs.getString("Paid_date"));
                        map.put("ConNew", rs.getString("ConNew"));
                        map.put("MTRNUMBER", rs.getString("MTRNUMBER"));
                        map.put("MTRWIDTH", rs.getString("MTRWIDTH"));
                        map.put("AVGCONSUM", rs.getString("AVGCONSUM"));
                        map.put("Latitude", rs.getString("Latitude"));
                        map.put("Longitude", rs.getString("Longitude"));
                        map.put("printer", rs.getString("printer"));
                        map.put("BILLNO", rs.getString("BILLNO"));
                        map.put("move", rs.getString("move"));
                        map.put("CheckBill", rs.getString("CheckBill"));
                        map.put("VALIDATED", rs.getString("VALIDATED"));
                        map.put("BPRINTED", rs.getString("BPRINTED"));
                        map.put("First", rs.getString("First"));
                        map.put("WALKSEQ", rs.getString("WALKSEQ"));
                        map.put("pay", rs.getString("pay"));
                        map.put("Arrears2", rs.getString("Arrears2"));
                        employee.add(map);
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {
        }
        return employee;

    }


    public ArrayList<HashMap<String, String>> getAllWaterView(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT *  FROM tbl_water WHERE ACCOUNT LIKE'" + s + "%'";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("RENT", cursor.getString(0));
                map.put("ACCOUNT", cursor.getString(1));
                map.put("Tcode", cursor.getString(2));
                map.put("NAME", cursor.getString(3));
                map.put("RATRID", cursor.getString(4));
                map.put("PREVREAD",cursor.getString(5));
                map.put("PREVDATE", cursor.getString(6));
                map.put("PRESREAD",cursor.getString(7));
                map.put("PRESDATE", cursor.getString(8));
                map.put("Consumption", cursor.getString(9));
                map.put("Deviation", cursor.getString(10));
                map.put("Detive", cursor.getString(11));
                map.put("waterBill",cursor.getString(12));
                map.put("Mrent", cursor.getString(13));
                map.put("Sewage",cursor.getString(14));
                map.put("Tax", cursor.getString(15));
                map.put("Surcharge", cursor.getString(16));
                map.put("Total_Bill", cursor.getString(17));
                map.put("TOTALDUE1", cursor.getString(18));
                map.put("KhetID", cursor.getString(19));
                map.put("AREACODE", cursor.getString(20));
                map.put("ZONE", cursor.getString(21));
                map.put("TOTALDUE", cursor.getString(22));
                map.put("Arrears",cursor.getString(23));
                map.put("Paid_date", cursor.getString(24));
                map.put("ConNew", cursor.getString(25));
                map.put("MTRNUMBER", cursor.getString(26));
                map.put("MTRWIDTH", cursor.getString(27));
                map.put("AVGCONSUM", cursor.getString(28));
                map.put("UPLOAD", cursor.getString(29));
                map.put("Latitude", cursor.getString(30));
                map.put("Longitude", cursor.getString(31));
                map.put("printer", cursor.getString(32));
                map.put("BILLNO", cursor.getString(33));
                map.put("pay", cursor.getString(42));
                map.put("Arrears2",cursor.getString(43));
                employee.add(map);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;

    }





    //get product image base 64
    public String getConsumption(String s) {
        String consumption = "n/a";
        Cursor cursor = database.rawQuery("SELECT consumption FROM tbl_water WHERE ACCOUNT LIKE'" + s + "%'", null);
        if (cursor.moveToFirst()) {
            do {
                consumption = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return consumption;
    }


    public ArrayList<HashMap<String, String>> getAllWaterViewrtd(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT pay  FROM tbl_water WHERE ACCOUNT LIKE'" + s + "%'";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("pay", cursor.getString(0));
                employee.add(map);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;

    }


    public ArrayList<HashMap<String, String>> getAllWaterViews(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT *  FROM tbl_water WHERE ACCOUNT LIKE'" + s + "%'";
        Cursor cursor = database.rawQuery(sqlAdd, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("RENT", cursor.getString(0));
                map.put("ACCOUNT", cursor.getString(1));
                map.put("Tcode", cursor.getString(2));
                map.put("NAME", cursor.getString(3));
                map.put("RATRID", cursor.getString(4));
                map.put("PREVREAD",cursor.getString(5));
                map.put("PREVDATE", cursor.getString(6));
                map.put("PRESREAD",cursor.getString(7));
                map.put("PRESDATE", cursor.getString(8));
                map.put("Consumption", cursor.getString(9));
                map.put("Deviation", cursor.getString(10));
                map.put("Detive", cursor.getString(11));
                map.put("waterBill",cursor.getString(12));
                map.put("Mrent", cursor.getString(13));
                map.put("Sewage",cursor.getString(14));
                map.put("Tax", cursor.getString(15));
                map.put("Surcharge", cursor.getString(16));
                map.put("Total_Bill", cursor.getString(17));
                map.put("TOTALDUE1", cursor.getString(18));
                map.put("KhetID", cursor.getString(19));
                map.put("AREACODE", cursor.getString(20));
                map.put("ZONE", cursor.getString(21));
                map.put("TOTALDUE", cursor.getString(22));
                map.put("Arrears",cursor.getString(23));
                map.put("Paid_date", cursor.getString(24));
                map.put("ConNew", cursor.getString(25));
                map.put("MTRNUMBER", cursor.getString(26));
                map.put("Arrears2",cursor.getString(43));

                employee.add(map);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;

    }





    public ArrayList<HashMap<String, String>> getRATE() {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT * FROM RATE WHERE Cnt='1'";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("CCODE", cursor.getString(0));
                map.put("CATNAME", cursor.getString(1));
                map.put("RATE1", cursor.getString(2));
                map.put("SLAB1", cursor.getString(3));
                map.put("RATE2", cursor.getString(4));
                map.put("SLAB2",cursor.getString(5));
                map.put("RATE3", cursor.getString(6));
                map.put("SLAB3",cursor.getString(7));
                map.put("RATE4", cursor.getString(8));
                map.put("SLAB4", cursor.getString(9));
                map.put("RATE5", cursor.getString(10));
                map.put("RTAX", cursor.getString(11));
                map.put("MINCONSUMP",cursor.getString(12));
                map.put("CURCODE", cursor.getString(13));
                map.put("CURNCYTYPE",cursor.getString(14));
                map.put("SURCHARGE", cursor.getString(15));
                map.put("SUR1", cursor.getString(16));
                map.put("SDAY1", cursor.getString(17));
                map.put("SUR2", cursor.getString(18));
                map.put("SDAY2", cursor.getString(19));
                map.put("SUR3", cursor.getString(20));
                map.put("SDAY3", cursor.getString(21));
                map.put("SEWER", cursor.getString(22));
                map.put("SLAB5",cursor.getString(23));
                map.put("VAT", cursor.getString(24));
                map.put("Zone", cursor.getString(25));
                map.put("No", cursor.getString(26));
                map.put("Cnt", cursor.getString(27));
                map.put("DIS", cursor.getString(28));
                map.put("Amt", cursor.getString(29));

                employee.add(map);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;

    }







    public ArrayList<HashMap<String, String>> getRATEbb1() {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT * FROM RATE WHERE Cnt='5'";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("CCODE", cursor.getString(0));
                map.put("CATNAME", cursor.getString(1));
                map.put("RATE1", cursor.getString(2));
                map.put("SLAB1", cursor.getString(3));
                map.put("RATE2", cursor.getString(4));
                map.put("SLAB2",cursor.getString(5));
                map.put("RATE3", cursor.getString(6));
                map.put("SLAB3",cursor.getString(7));
                map.put("RATE4", cursor.getString(8));
                map.put("SLAB4", cursor.getString(9));
                map.put("RATE5", cursor.getString(10));
                map.put("RTAX", cursor.getString(11));
                map.put("MINCONSUMP",cursor.getString(12));
                map.put("CURCODE", cursor.getString(13));
                map.put("CURNCYTYPE",cursor.getString(14));
                map.put("SURCHARGE", cursor.getString(15));
                map.put("SUR1", cursor.getString(16));
                map.put("SDAY1", cursor.getString(17));
                map.put("SUR2", cursor.getString(18));
                map.put("SDAY2", cursor.getString(19));
                map.put("SUR3", cursor.getString(20));
                map.put("SDAY3", cursor.getString(21));
                map.put("SEWER", cursor.getString(22));
                map.put("SLAB5",cursor.getString(23));
                map.put("VAT", cursor.getString(24));
                map.put("Zone", cursor.getString(25));
                map.put("No", cursor.getString(26));
                map.put("Cnt", cursor.getString(27));
                map.put("DIS", cursor.getString(28));
                map.put("Amt", cursor.getString(29));

                employee.add(map);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;

    }





    public ArrayList<HashMap<String, String>> getRATEbb6() {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT * FROM RATE WHERE Cnt='6'";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("CCODE", cursor.getString(0));
                map.put("CATNAME", cursor.getString(1));
                map.put("RATE1", cursor.getString(2));
                map.put("SLAB1", cursor.getString(3));
                map.put("RATE2", cursor.getString(4));
                map.put("SLAB2",cursor.getString(5));
                map.put("RATE3", cursor.getString(6));
                map.put("SLAB3",cursor.getString(7));
                map.put("RATE4", cursor.getString(8));
                map.put("SLAB4", cursor.getString(9));
                map.put("RATE5", cursor.getString(10));
                map.put("RTAX", cursor.getString(11));
                map.put("MINCONSUMP",cursor.getString(12));
                map.put("CURCODE", cursor.getString(13));
                map.put("CURNCYTYPE",cursor.getString(14));
                map.put("SURCHARGE", cursor.getString(15));
                map.put("SUR1", cursor.getString(16));
                map.put("SDAY1", cursor.getString(17));
                map.put("SUR2", cursor.getString(18));
                map.put("SDAY2", cursor.getString(19));
                map.put("SUR3", cursor.getString(20));
                map.put("SDAY3", cursor.getString(21));
                map.put("SEWER", cursor.getString(22));
                map.put("SLAB5",cursor.getString(23));
                map.put("VAT", cursor.getString(24));
                map.put("Zone", cursor.getString(25));
                map.put("No", cursor.getString(26));
                map.put("Cnt", cursor.getString(27));
                map.put("DIS", cursor.getString(28));
                map.put("Amt", cursor.getString(29));

                employee.add(map);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;

    }












    public ArrayList<HashMap<String, String>> getRATEs() {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT * FROM RATE WHERE Cnt='2'";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("CCODE", cursor.getString(0));
                map.put("CATNAME", cursor.getString(1));
                map.put("RATE1", cursor.getString(2));
                map.put("SLAB1", cursor.getString(3));
                map.put("RATE2", cursor.getString(4));
                map.put("SLAB2",cursor.getString(5));
                map.put("RATE3", cursor.getString(6));
                map.put("SLAB3",cursor.getString(7));
                map.put("RATE4", cursor.getString(8));
                map.put("SLAB4", cursor.getString(9));
                map.put("RATE5", cursor.getString(10));
                map.put("RTAX", cursor.getString(11));
                map.put("MINCONSUMP",cursor.getString(12));
                map.put("CURCODE", cursor.getString(13));
                map.put("CURNCYTYPE",cursor.getString(14));
                map.put("SURCHARGE", cursor.getString(15));
                map.put("SUR1", cursor.getString(16));
                map.put("SDAY1", cursor.getString(17));
                map.put("SUR2", cursor.getString(18));
                map.put("SDAY2", cursor.getString(19));
                map.put("SUR3", cursor.getString(20));
                map.put("SDAY3", cursor.getString(21));
                map.put("SEWER", cursor.getString(22));
                map.put("SLAB5",cursor.getString(23));
                map.put("VAT", cursor.getString(24));
                map.put("Zone", cursor.getString(25));
                map.put("No", cursor.getString(26));
                map.put("Cnt", cursor.getString(27));
                map.put("DIS", cursor.getString(28));
                map.put("Amt", cursor.getString(29));

                employee.add(map);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;

    }





    public ArrayList<HashMap<String, String>> getoffice() {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT * FROM Ap_Office";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Office_ID", cursor.getString(0));
                map.put("Office_Name", cursor.getString(1));
                map.put("Office_NameE", cursor.getString(2));
                map.put("Address1", cursor.getString(3));
                map.put("Address1E", cursor.getString(4));
                map.put("Address2",cursor.getString(5));
                map.put("Address2E", cursor.getString(6));
                map.put("Tel",cursor.getString(7));
                map.put("Fax", cursor.getString(8));
                map.put("E_mail", cursor.getString(9));
                map.put("Web_Site", cursor.getString(10));
                map.put("Place", cursor.getString(11));
                map.put("PlaceE",cursor.getString(12));
                map.put("Sign1", cursor.getString(13));
                map.put("Sign1E",cursor.getString(14));
                map.put("AppName", cursor.getString(15));
                map.put("Cal_sys", cursor.getString(16));
                map.put("Sign2", cursor.getString(17));
                employee.add(map);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;

    }




    public ArrayList<HashMap<String, String>> getoffices() {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT * FROM Ap_Office";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Office_ID", cursor.getString(0));
                map.put("Office_Name", cursor.getString(1));
                map.put("Office_NameE", cursor.getString(2));
                map.put("Address1", cursor.getString(3));
                map.put("Address1E", cursor.getString(4));
                map.put("Address2",cursor.getString(5));
                map.put("Address2E", cursor.getString(6));
                map.put("Tel",cursor.getString(7));
                map.put("Fax", cursor.getString(8));
                map.put("E_mail", cursor.getString(9));
                map.put("Web_Site", cursor.getString(10));
                map.put("Place", cursor.getString(11));
                map.put("PlaceE",cursor.getString(12));
                map.put("Sign1", cursor.getString(13));
                map.put("Sign1E",cursor.getString(14));
                map.put("AppName", cursor.getString(15));
                map.put("Cal_sys", cursor.getString(16));
                map.put("Sign2", cursor.getString(17));
                employee.add(map);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return employee;

    }







    public ArrayList<HashMap<String, String>> getZone() {
        ArrayList<HashMap<String, String>> ZONE = new ArrayList<>();
        String sqlAdd ="";
        sqlAdd="SELECT * FROM ZONE";
        Cursor cursor = database.rawQuery(sqlAdd, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ZONECODE", cursor.getString(0));
                map.put("ZONENAME", cursor.getString(1));
                map.put("ZONENAMEENG", cursor.getString(2));
                map.put("KHETCODE", cursor.getString(3));
                map.put("Lst_updt", cursor.getString(4));
                map.put("Lst_usr",cursor.getString(5));
                map.put("pc_nm", cursor.getString(6));
                map.put("Call",cursor.getString(7));
                ZONE.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return ZONE;
    }


    //get call
    public String getCall(String call) {
        String calls = "n/a";
        Cursor cursor = database.rawQuery("SELECT * FROM ZONE  WHERE ZONECODE='" + call + "'", null);
        if (cursor.moveToFirst()) {
            do {
                calls = cursor.getString(7);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return calls;
    }








    public boolean addUsers(String UserId, String UserName, String FullName) {
        ContentValues values = new ContentValues();
        values.put("UserId", UserId);
        values.put("UserName", UserName);
        values.put("FullName", FullName);

        long check = database.insert("tbl_users", null, values);
        database.close();

        //if data insert success, its return 1, if failed return -1
        return check != -1;
    }

    //get shop information
    public ArrayList<HashMap<String, String>> CheckLogins(String UID, String PASS) {
        ArrayList<HashMap<String, String>> userData = new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT UserID,Password FROM tbl_user WHERE  UserID='"+ UID.toString() +"' AND Password='"+ PASS.toString() +"'", null);

            if (cursor.moveToFirst()) {
                do {

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("UserID", cursor.getString(0));
                    map.put("Password", cursor.getString(1));
                    userData.add(map);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){

        }

        database.close();
        return userData;
    }



    public ArrayList<HashMap<String, String>> getAP_sale_item(String Usr_nm,String PWD) {
        ArrayList<HashMap<String, String>> product_category = new ArrayList<>();
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                String query="SELECT Usr_id, Usr_nm,PWD,Dist FROM AP_Users WHERE Usr_nm =N'"+Usr_nm+"' and PWD =N'"+PWD+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("Usr_id", rs.getString("Usr_id"));
                        map.put("Usr_nm", rs.getString("Usr_nm"));
                        map.put("PWD", rs.getString("PWD"));
                        map.put("Dist", rs.getString("Dist"));
                        product_category.add(map);
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {
        }
        return product_category;
    }





    //get shop information
    public ArrayList<HashMap<String, String>> UploadImagesEnroll() {
        ArrayList<HashMap<String, String>> enroll = new ArrayList<>();

        String sqlAdd ="";
        //sqlAdd="SELECT Image_Pinkbook_Immu_filename,Image_Pinkbook_Immu_url,Image_account_filename,Image_account_url FROM tbl_enrollment_members WHERE Approve_CCT_Member_Status=0";
        // cuongnv sua ngay 4/1/2021 cho đúng với trang thái Upload chứ không phải chỉ là trạng thái Approve
        sqlAdd="SELECT Image_Pinkbook_Immu_filename,Image_Pinkbook_Immu_url,Image_account_filename,Image_account_url,Image_enroll_paper_form_filename,Image_enroll_paper_form_url, BeneficarySignatureFilename_image, BeneficarySignatureUrl_image FROM tbl_enrollment_members WHERE Status_upload=0 and Approve_CCT_Member_Status=0 and (length(Image_Pinkbook_Immu_filename)>0 or length(Image_account_filename)>0 or length(Image_enroll_paper_form_filename)>0 or length(BeneficarySignatureFilename_image)>0 )";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {
            do {
                //HashMap<String, String> map = new HashMap<String, String>();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Image_Pinkbook_Immu_filename", cursor.getString(0));
                map.put("Image_Pinkbook_Immu_url", cursor.getString(1));
                map.put("Image_account_filename", cursor.getString(2));
                map.put("Image_account_url", cursor.getString(3));
                map.put("Image_enroll_paper_form_filename", cursor.getString(4));
                map.put("Image_enroll_paper_form_url", cursor.getString(5));
                map.put("BeneficarySignatureFilename_image", cursor.getString(6));
                map.put("BeneficarySignatureUrl_image", cursor.getString(7));


                enroll.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return enroll;
    }




    //get shop information
    public ArrayList<HashMap<String, String>> UploadImagesHousehold() {
        ArrayList<HashMap<String, String>> Data = new ArrayList<>();

        String sqlAdd ="";
        sqlAdd="SELECT img_filename,img_url,enum_sign_filename,enum_sign_url,resp_sign_filename,resp_sign_url, hh_img_filename, hh_img_url FROM tbl_information_tablet WHERE Status_upload= 1";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {
            do {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("img_filename", cursor.getString(0));
                map.put("img_url", cursor.getString(1));
                map.put("enum_sign_filename", cursor.getString(2));
                map.put("enum_sign_url", cursor.getString(3));
                map.put("resp_sign_filename", cursor.getString(4));
                map.put("resp_sign_url", cursor.getString(5));
                map.put("hh_img_filename", cursor.getString(6));
                map.put("hh_img_url", cursor.getString(7));

                Data.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return Data;
    }

    //get shop information
    public ArrayList<HashMap<String, String>> UploadImagesMember() {
        ArrayList<HashMap<String, String>> enroll = new ArrayList<>();

        String sqlAdd ="";
        //sqlAdd="SELECT img_filename,img_url,card_imgfilename_page1,card_img_url_page1 FROM tbl_member WHERE   ((julianday('2020-11-01') - julianday(Date(tbl_member.hh_birthdate)) <660) OR tbl_member.hh_pregnant_woman=1) AND  Status_Join_CTT=0";


        //sqlAdd="SELECT img_filename,img_url,card_imgfilename_page1,card_img_url_page1 FROM tbl_member WHERE   ((julianday('2020-11-01') - julianday(Date(tbl_member.hh_birthdate)) <660) OR tbl_member.hh_pregnant_woman=1) AND  Status_Join_CTT=0";
        //cuongnv sua ngay 4/1/2021 để select đúng những image cần upload
        sqlAdd=" SELECT img_filename,img_url,card_imgfilename_page1,card_img_url_page1 FROM tbl_member WHERE (hh_member_id in (SELECT hh_member_id from tbl_enrollment_members WHERE Approve_CCT_Member_Status=0 and Status_upload=0)) and Status_Join_CTT=1 and (length(Img_filename)>0 or length(card_imgfilename_page1)>0)";
        Cursor cursor = database.rawQuery(sqlAdd, null);

        if (cursor.moveToFirst()) {

            do {

                //HashMap<String, String> map = new HashMap<String, String>();

                HashMap<String, String> map = new HashMap<String, String>();

                // map.put("hh_member_id", cursor.getString(0));
                map.put("img_filename", cursor.getString(0));
                map.put("img_url", cursor.getString(1));
                map.put("card_imgfilename_page1", cursor.getString(2));
                map.put("card_img_url_page1", cursor.getString(3));


                enroll.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return enroll;
    }

    //get shop information

    public ArrayList<ContentValues> UploadEnroll() {
        ArrayList<ContentValues> enroll = new ArrayList<>();

        String sqlAdd ="";
        sqlAdd="SELECT hh_member_id,CCT_ID,hh_code,provice_id,district_id,village_id,unit_id,hh_num,hh_level,Name_of_community_staff,";
        sqlAdd +=" Community_staff_code,Date_of_registration,Month_of_pregnant_since1_9,Pregnant_pink_book,Child_have_immunization_card, ";
        sqlAdd +=" Image_Pinkbook_Immu_filename ,Image_Pinkbook_Immu_url, ";
        sqlAdd +=" Mother_of_child_proxy_previous,Name_of_nearest_hospital,Name_Agentregistration, ";
        sqlAdd +=" Relation_to_beneficiary,Agent_is_on_household_list,Who_is_agent,Why_Agent_is_not_on_household_list, ";
        // ";
        sqlAdd +=" Agent_phone_number, Agent_phone_type,Project_Checked_by,Project_Checked_date,Bank_name_reciepient,Account_name_reciepient,Account_number_reciepient,";
        sqlAdd +=" Image_account_filename,Image_account_url ";
        sqlAdd +=" FROM tbl_enrollment_members WHERE Status_upload=0 ";

        Cursor cursor = database.rawQuery(sqlAdd, null);
        //  Toasty.success(context,cursor.getCount(),Toasty.LENGTH_SHORT).show();
        if (cursor.moveToFirst()) {

            do {

                //   HashMap<String, String> map = new HashMap<String, String>(32, .85f);
                ContentValues map = new ContentValues(64);
                try {
                    map.put("hh_member_id", cursor.getString(0));
                    map.put("CCT_ID", cursor.getString(1));
                    map.put("hh_code", cursor.getString(2));
                    map.put("provice_id", cursor.getString(3));
                    map.put("district_id", cursor.getString(4));
                    map.put("village_id", cursor.getString(5));
                    map.put("unit_id", cursor.getString(6));
                    map.put("hh_num", cursor.getString(7));
                    map.put("hh_level", cursor.getString(8));

                    map.put("Name_of_community_staff", cursor.getString(9));
                    map.put("Community_staff_code", cursor.getString(10));
                    map.put("Date_of_registration", cursor.getString(11));

                    map.put("Month_of_pregnant_since1_9", cursor.getString(12));

                    map.put("Pregnant_pink_book", cursor.getString(13));
                    map.put("Child_have_immunization_card", cursor.getString(14));

                    map.put("Image_Pinkbook_Immu_filename", cursor.getString(15));
                    map.put("Image_Pinkbook_Immu_url", cursor.getString(16));

                    map.put("Mother_of_child_proxy_previous", cursor.getString(17));
                    map.put("Name_of_nearest_hospital", cursor.getString(18));
                    map.put("Name_Agentregistration", cursor.getString(19));

                    map.put("Relation_to_beneficiary", cursor.getString(20));

                    map.put("Agent_is_on_household_list", cursor.getString(21));
                    map.put("Who_is_agent", cursor.getString(22));
                    map.put("Why_Agent_is_not_on_household_list", cursor.getString(23));

                    map.put("Agent_phone_number", cursor.getString(24));
                    map.put("Agent_phone_type", cursor.getString(25));

                    map.put("Project_Checked_by", cursor.getString(26));
                    map.put("Project_Checked_date", cursor.getString(27));
                    map.put("Bank_name_reciepient", cursor.getString(28));
                    map.put("Account_name_reciepient", cursor.getString(29));
                    map.put("Account_number_reciepient", cursor.getString(30));
                    map.put("Image_account_filename", cursor.getString(31));
                    map.put("Image_account_url", cursor.getString(32));
                }
                catch (Exception e){
                    //Toasty.success(cct_program_menu.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    //Toasty.success(context,e.getMessage(),Toasty.LENGTH_SHORT).show();
                }


                enroll.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return enroll;
    }

    //get Information data
    public ArrayList<HashMap<String, String>> getLoadView() {
        ArrayList<HashMap<String, String>> information = new ArrayList<>();
        String sqlview ="";
        sqlview="SELECT ACCOUNT, NAME, PREVREAD, PREVDATE, Consumption,TOTALDUE FROM BB ORDER BY ACCOUNT";
      //  ftotal=0;
        Cursor cursor = database.rawQuery(sqlview, null);
      //  ftotal=cursor.getCount();
        if (cursor.moveToFirst()) {
            do {

                HashMap<String, String> map = new HashMap<String, String>();

                map.put("ACCOUNT", cursor.getString(0));
                map.put("NAME", cursor.getString(1));
                map.put("PREVREAD", cursor.getString(2));
                map.put("PREVDATE", cursor.getString(3));
                map.put("Consumption", cursor.getString(4));
                map.put("TOTALDUE", cursor.getString(5));

                information.add(map);
            } while (cursor.moveToNext());
        }

        return information;
    }


    //get Employee
    public ArrayList<HashMap<String, String>> getEmployee() {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {

            } else {
                String query = "Select ID,FirstName From Employee";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if (rs!=null){
                    while (rs.next()){
                        HashMap<String, String> map = new HashMap<String, String>();

                        map.put("ID", rs.getString("ID"));
                        map.put("FirstName", rs.getString("FirstName"));
                        employee.add(map);
                    }
                }
                rs.close();

            }
            con.close();
        } catch (Exception ex) {

        }

        return employee;
    }


    //get product data
    public ArrayList<HashMap<String, String>> getAllWater(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
            Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,WALKSEQ,KhetID,call FROM tbl_water WHERE AREACODE LIKE'%" + s + "%'  and PRESREAD=0 ORDER BY WALKSEQ ASC", null);
             if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("WALKSEQ", cursor.getString(6));
                    map.put("KhetID", cursor.getString(7));
                    map.put("call", cursor.getString(8));

                    product.add(map);
                } while (cursor.moveToNext());

            }

        cursor.close();
        database.close();
        return product;

    }









    //get product data
    public ArrayList<HashMap<String, String>> getAllWaterSS(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,RATRID,call FROM tbl_water WHERE AREACODE LIKE'" + s + "%' AND printer IS NOT  NULL ORDER BY ACCOUNT ", null);
        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("ACCOUNT", cursor.getString(0));
                map.put("NAME", cursor.getString(1));
                map.put("PREVREAD", cursor.getString(2));
                map.put("PREVDATE", cursor.getString(3));
                map.put("Consumption", cursor.getString(4));
                map.put("TOTALDUE", cursor.getString(5));
                map.put("RATRID", cursor.getString(6));
                map.put("call", cursor.getString(7));

                product.add(map);
            } while (cursor.moveToNext());

        }

        cursor.close();
        database.close();
        return product;

    }




    public ArrayList<HashMap<String, String>> getAllWaterSSbbvv(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,RATRID,call,WALKSEQ,pay FROM tbl_water WHERE AREACODE LIKE'" + s + "%'  AND  PRESREAD>0  ORDER BY WALKSEQ ASC", null);
        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("ACCOUNT", cursor.getString(0));
                map.put("NAME", cursor.getString(1));
                map.put("PREVREAD", cursor.getString(2));
                map.put("PREVDATE", cursor.getString(3));
                map.put("Consumption", cursor.getString(4));
                map.put("TOTALDUE", cursor.getString(5));
                map.put("RATRID", cursor.getString(6));
                map.put("call", cursor.getString(7));
                map.put("WALKSEQ", cursor.getString(8));
                map.put("pay", cursor.getString(9));
                product.add(map);
            } while (cursor.moveToNext());

        }

        cursor.close();
        database.close();
        return product;

    }






    public ArrayList<HashMap<String, String>> getAllWaterSSbbvvrepayment(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,RATRID,call,WALKSEQ,pay FROM tbl_water WHERE AREACODE LIKE'" + s + "%'  AND pay='1' AND printer IS NOT  NULL ORDER BY WALKSEQ ASC", null);
        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("ACCOUNT", cursor.getString(0));
                map.put("NAME", cursor.getString(1));
                map.put("PREVREAD", cursor.getString(2));
                map.put("PREVDATE", cursor.getString(3));
                map.put("Consumption", cursor.getString(4));
                map.put("TOTALDUE", cursor.getString(5));
                map.put("RATRID", cursor.getString(6));
                map.put("call", cursor.getString(7));
                map.put("WALKSEQ", cursor.getString(8));
                map.put("pay", cursor.getString(9));
                product.add(map);
            } while (cursor.moveToNext());

        }

        cursor.close();
        database.close();
        return product;

    }





    //get product data
    public ArrayList<HashMap<String, String>> getAllWaterexpent_list(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE FROM tbl_water WHERE Consumption => 0 and AREACODE LIKE'" + s + "%' ORDER BY ACCOUNT ", null);

        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("ACCOUNT", cursor.getString(0));
                map.put("NAME", cursor.getString(1));
                map.put("PREVREAD", cursor.getString(2));
                map.put("PREVDATE", cursor.getString(3));
                map.put("Consumption", cursor.getString(4));
                map.put("TOTALDUE", cursor.getString(5));

                product.add(map);
            } while (cursor.moveToNext());

        }

        cursor.close();
        database.close();
        return product;

    }










    //get product data
    public ArrayList<HashMap<String, String>> getAllWaterBill(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE FROM tbl_water WHERE AREACODE LIKE'" + s + "%' AND Consumption<>'0.0' AND UPLOAD <>'1' ORDER BY ACCOUNT ", null);

        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("ACCOUNT", cursor.getString(0));
                map.put("NAME", cursor.getString(1));
                map.put("PREVREAD", cursor.getString(2));
                map.put("PREVDATE", cursor.getString(3));
                map.put("Consumption", cursor.getString(4));
                map.put("TOTALDUE", cursor.getString(5));

                product.add(map);
            } while (cursor.moveToNext());

        }

        cursor.close();
        database.close();
        return product;

    }


    //get product data
    public ArrayList<HashMap<String, String>> getAllWaterPending(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,Consumption,TOTALDUE FROM tbl_water WHERE AREACODE LIKE'" + s + "%' AND UPLOAD='2' AND Consumption <>'0.0'  ORDER BY ACCOUNT ", null);

        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("ACCOUNT", cursor.getString(0));
                map.put("NAME", cursor.getString(1));
                map.put("Consumption", cursor.getString(2));
                map.put("TOTALDUE", cursor.getString(3));

                product.add(map);
            } while (cursor.moveToNext());

        }

        cursor.close();
        database.close();
        return product;

    }


    //get product data
    public ArrayList<HashMap<String, String>> getAllWaterViewSV(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
                //  Toasty.error(MenuActivity.this, R.string.data_export_fail, Toast.LENGTH_SHORT).show();
            } else {
                String query = "SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE FROM tbl_water WHERE ACCOUNT='" + s + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        HashMap<String, String> map = new HashMap<String, String>();

                        map.put("ACCOUNT", rs.getString("ACCOUNT"));
                        map.put("NAME", rs.getString("NAME"));
                        map.put("PREVREAD", rs.getString("PREVREAD"));
                        map.put("PREVDATE", rs.getString("PREVDATE"));
                        map.put("Consumption", rs.getString("Consumption"));
                        map.put("TOTALDUE", rs.getString("TOTALDUE"));

                        employee.add(map);
                    }
                }
                rs.close();

            }
            con.close();
        } catch (Exception ex) {

        }
        return employee;

    }



        //get product data
    public ArrayList<HashMap<String, String>> getAllWaterSV(String s) {
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
              //  Toasty.error(MenuActivity.this, R.string.data_export_fail, Toast.LENGTH_SHORT).show();
            } else {
                String query = "SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE FROM BB WHERE AREACODE='" + s + "' ORDER BY ACCOUNT ";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        HashMap<String, String> map = new HashMap<String, String>();

                        map.put("ACCOUNT", rs.getString("ACCOUNT"));
                        map.put("NAME", rs.getString("NAME"));
                        map.put("PREVREAD", rs.getString("PREVREAD"));
                        map.put("PREVDATE", rs.getString("PREVDATE"));
                        map.put("Consumption", rs.getString("Consumption"));
                        map.put("TOTALDUE", rs.getString("TOTALDUE"));

                        employee.add(map);
                    }
                }
                rs.close();

            }
            con.close();
        } catch (Exception ex) {

        }


    /*    Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE FROM BB WHERE AREACODE='" + s + "' ORDER BY ACCOUNT DESC", null);

        if (cursor.moveToFirst()) {

            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("ACCOUNT", cursor.getString(0));
                map.put("NAME", cursor.getString(1));
                map.put("PREVREAD", cursor.getString(2));
                map.put("PREVDATE", cursor.getString(3));
                map.put("Consumption", cursor.getString(4));
                map.put("TOTALDUE", cursor.getString(5));

                product.add(map);
            } while (cursor.moveToNext());

        }

        cursor.close();
        database.close();*/
        return employee;

    }

    //get product data
    public ArrayList<HashMap<String, String>> searchListWater(String s, String s2) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
       try{
           Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,WALKSEQ,KhetID,call FROM tbl_water where  (ACCOUNT LIKE '%" + s +"%' OR NAME LIKE '%" + s +"%') AND  AREACODE LIKE'%"+ s2 +"%'  and PRESREAD=0 ORDER BY  WALKSEQ ASC", null);
           if (cursor.moveToFirst()) {
               do {
                   HashMap<String, String> map = new HashMap<String, String>();
                   map.put("ACCOUNT", cursor.getString(0));
                   map.put("NAME", cursor.getString(1));
                   map.put("PREVREAD", cursor.getString(2));
                   map.put("PREVDATE", cursor.getString(3));
                   map.put("Consumption", cursor.getString(4));
                   map.put("TOTALDUE", cursor.getString(5));
                   map.put("WALKSEQ", cursor.getString(6));
                   map.put("KhetID", cursor.getString(7));
                   map.put("call", cursor.getString(8));
                   product.add(map);
               } while (cursor.moveToNext());
           }

       }catch (Exception e){

       }

        database.close();
        return product;
    }


    public ArrayList<HashMap<String, String>> searchListWaterdfgdfgdg(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{
            Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,WALKSEQ,KhetID,call FROM tbl_water where  (ACCOUNT LIKE '%" + s +"%' OR NAME LIKE '%" + s +"%')  and PRESREAD=0 ORDER  BY WALKSEQ ASC", null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("WALKSEQ", cursor.getString(6));
                    map.put("KhetID", cursor.getString(7));
                    map.put("call", cursor.getString(8));
                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }






    public ArrayList<HashMap<String, String>> searchListreceips(String s, String s2) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{

            Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,RATRID,call FROM tbl_water where  ( ACCOUNT LIKE '%"+ s +"%' OR  NAME LIKE '%"+ s +"%') AND AREACODE='"+ s2 +"'  order by ACCOUNT ASC", null);

            if (cursor.moveToFirst()) {

                do {
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("RATRID", cursor.getString(6));
                    map.put("call", cursor.getString(7));

                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }






    public ArrayList<HashMap<String, String>> searchListreceipsnnbb(String s, String s2) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{

            Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,RATRID,call,WALKSEQ FROM tbl_water where  ( ACCOUNT LIKE '%"+ s +"%' OR  NAME LIKE '%"+ s +"%') AND AREACODE='"+ s2 +"' AND PRESREAD>0   order by WALKSEQ ASC", null);

            if (cursor.moveToFirst()) {

                do {
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("RATRID", cursor.getString(6));
                    map.put("call", cursor.getString(7));
                    map.put("WALKSEQ", cursor.getString(8));
                    map.put("pay", cursor.getString(9));
                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }









    public ArrayList<HashMap<String, String>> searchListreceipsnnbbrepaymet(String s, String s2) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{

            Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,RATRID,call,WALKSEQ FROM tbl_water where  ( ACCOUNT LIKE '%"+ s +"%' OR  NAME LIKE '%"+ s +"%') AND AREACODE='"+ s2 +"' AND pay='1'   order by WALKSEQ ASC", null);

            if (cursor.moveToFirst()) {

                do {
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("RATRID", cursor.getString(6));
                    map.put("call", cursor.getString(7));
                    map.put("WALKSEQ", cursor.getString(8));
                    map.put("pay", cursor.getString(9));
                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }
















    //get product data
    public ArrayList<HashMap<String, String>> searchListexpent_list(String s, String s2) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{

            Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE FROM tbl_water where Consumption > 0 and ( ACCOUNT LIKE '%"+ s +"%' OR  NAME LIKE '%"+ s +"%') AND AREACODE='"+ s2 +"' order by ACCOUNT ASC", null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));

                    product.add(map);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
        }

        database.close();
        return product;
    }



    //get search_receipt_list_activity data
    public ArrayList<HashMap<String, String>> search_receipt_list_activity(String s, String s2) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{

            Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,RATRID,call FROM tbl_water where  ( ACCOUNT LIKE '%"+ s +"%' OR  NAME LIKE '%"+ s +"%') AND AREACODE='"+ s2 +"' AND  PREVREAD > 0 order by ACCOUNT ASC", null);

            if (cursor.moveToFirst()) {

                do {
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("RATRID", cursor.getString(6));
                    map.put("call", cursor.getString(7));

                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }




    public ArrayList<HashMap<String, String>> search_receipt_list_activityfff(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{

            Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,RATRID,call,WALKSEQ,pay FROM tbl_water WHERE (ACCOUNT LIKE '%" + s +"%' OR NAME LIKE '%" + s +"%') ORDER BY  WALKSEQ ASC", null);
           // Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,RATRID,call,WALKSEQ FROM tbl_water where  ( ACCOUNT LIKE '%"+ s +"%' OR  NAME LIKE '%"+ s +"%') AND AREACODE='"+ s +"' AND PRESREAD>0   order by WALKSEQ ASC", null);

            if (cursor.moveToFirst()) {

                do {
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("RATRID", cursor.getString(6));
                    map.put("call", cursor.getString(7));
                    map.put("WALKSEQ", cursor.getString(8));
                    map.put("pay", cursor.getString(9));
                    product.add(map);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
        }
        database.close();
        return product;
    }





    public ArrayList<HashMap<String, String>> search_receipt_list_activityfffrepayment(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{

            Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,RATRID,call,WALKSEQ,pay FROM tbl_water WHERE   PREVREAD > 0  AND (ACCOUNT LIKE '%"+ s +"%' OR  NAME LIKE '%"+ s +"%') AND pay='1'  order by WALKSEQ ASC", null);

            if (cursor.moveToFirst()) {

                do {
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("RATRID", cursor.getString(6));
                    map.put("call", cursor.getString(7));
                    map.put("WALKSEQ", cursor.getString(8));
                    map.put("pay", cursor.getString(9));
                    product.add(map);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
        }
        database.close();
        return product;
    }







    //get search_expensereport_lis data
    //get product data
    public ArrayList<HashMap<String, String>> search_expensereport_lis(String s, String s2) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{

            Cursor cursor = database.rawQuery("SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE FROM tbl_water where ( ACCOUNT LIKE '%"+ s +"%' OR  NAME LIKE '%"+ s +"%') AND AREACODE='"+ s2 +"' order by ACCOUNT ASC", null);

            if (cursor.moveToFirst()) {

                do {
                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));

                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }






    //get product data
    public ArrayList<HashMap<String, String>> searchListWaterGPS(String lat, String lng,String vl) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{
            String str="SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,WALKSEQ,KhetID,call,Latitude,Longitude FROM tbl_water where Latitude LIKE '"+ lat.toString() +"%' AND  Longitude LIKE '"+ lng.toString() +"%' AND AREACODE LIKE'"+ vl +"%' and PRESREAD=0 ORDER BY  WALKSEQ ASC";
          //  fstr=str;
            Cursor cursor = database.rawQuery(str, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("WALKSEQ", cursor.getString(6));
                    map.put("KhetID", cursor.getString(7));
                    map.put("call", cursor.getString(8));

                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }


    //get product data
    public ArrayList<HashMap<String, String>> searchListrecept_lists(String lat, String lng,String vl) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{
            String str="SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,Latitude,Longitude,RATRID,call FROM tbl_water where  Latitude LIKE '"+ lat.toString() +"%' AND  Longitude LIKE '"+ lng.toString() +"%' AND AREACODE='"+ vl +"' order by ACCOUNT ASC";
            //  fstr=str;
            Cursor cursor = database.rawQuery(str, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("RATRID", cursor.getString(6));
                    map.put("call", cursor.getString(7));
                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }







    //get product data
    public ArrayList<HashMap<String, String>> searchListrecept_listsbbb(String lat, String lng,String vl) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{
            String str="SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,Latitude,Longitude,RATRID,call,WALKSEQ,pay FROM tbl_water where  Latitude LIKE '"+ lat.toString() +"%' AND  Longitude LIKE '"+ lng.toString() +"%' AND AREACODE='"+ vl +"' AND pay='0' order by WALKSEQ ASC";
            //  fstr=str;
            Cursor cursor = database.rawQuery(str, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("RATRID", cursor.getString(6));
                    map.put("call", cursor.getString(7));
                    map.put("WALKSEQ", cursor.getString(8));
                    map.put("pay", cursor.getString(9));
                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }







    //get product data
    public ArrayList<HashMap<String, String>> searchListrecept_listsbbbrepaymet(String lat, String lng,String vl) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{
            String str="SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,Latitude,Longitude,RATRID,call,WALKSEQ,pay FROM tbl_water where  Latitude LIKE '"+ lat.toString() +"%' AND  Longitude LIKE '"+ lng.toString() +"%' AND AREACODE='"+ vl +"' AND pay='1' order by WALKSEQ ASC";
            //  fstr=str;
            Cursor cursor = database.rawQuery(str, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));
                    map.put("RATRID", cursor.getString(6));
                    map.put("call", cursor.getString(7));
                    map.put("WALKSEQ", cursor.getString(8));
                    map.put("pay", cursor.getString(9));
                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }









    //get product data
    public ArrayList<HashMap<String, String>> searchListexpent_list(String lat, String lng,String vl) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        try{
            String str="SELECT ACCOUNT, NAME,PREVREAD,PREVDATE,Consumption,TOTALDUE,Latitude,Longitude FROM tbl_water where Consumption > 0 and Latitude LIKE '"+ lat.toString() +"%' AND  Longitude LIKE '"+ lng.toString() +"%' AND AREACODE='"+ vl +"' order by ACCOUNT ASC";
            //  fstr=str;
            Cursor cursor = database.rawQuery(str, null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("ACCOUNT", cursor.getString(0));
                    map.put("NAME", cursor.getString(1));
                    map.put("PREVREAD", cursor.getString(2));
                    map.put("PREVDATE", cursor.getString(3));
                    map.put("Consumption", cursor.getString(4));
                    map.put("TOTALDUE", cursor.getString(5));

                    product.add(map);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){

        }

        database.close();
        return product;
    }






    public Double GETTOTAIL(String ACCOUNT) {
        double  a;
        a = 0;
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                //  String query="select Cut_qty from AP_Category Where Cat_id = N'"+Cat_id+"'";
                String query="SELECT TOTALDUE FROM  MASTER WHERE ACCOUNT =N'"+ACCOUNT+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        //       a = String.valueOf(rs.getString("Qty").toString());
                        a = Double.valueOf(rs.getString("TOTALDUE").toString());

                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {

        }
        return a;
    }





    //insert customer
    public boolean addWater(String ACCOUNT, String NAME, String PREVREAD, String PREVDATE,String Consumption,String TOTALDUE) {

        ContentValues values = new ContentValues();


        values.put("ACCOUNT", ACCOUNT);
        values.put("NAME", NAME);
        values.put("PREVREAD", PREVREAD);
        values.put("PREVDATE", PREVDATE);
        values.put("Consumption", Consumption);
        values.put("TOTALDUE", TOTALDUE);

        long check = database.insert("BB", null, values);
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }



    //insert customer
    public boolean addCustomer(String customer_name, String customer_cell, String customer_email, String customer_address) {

        ContentValues values = new ContentValues();


        values.put("customer_name", customer_name);
        values.put("customer_cell", customer_cell);
        values.put("customer_email", customer_email);
        values.put("customer_address", customer_address);

        long check = database.insert("customers", null, values);
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    //insert category
    public boolean addCategory(String category_name) {

        ContentValues values = new ContentValues();


        values.put("category_name", category_name);


        long check = database.insert("product_category", null, values);
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }




    //insert payment method
    public boolean addPaymentMethod(String payment_method_name) {

        ContentValues values = new ContentValues();


        values.put("payment_method_name", payment_method_name);


        long check = database.insert("payment_method", null, values);
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }



    //update category
    public boolean updateCategory(String category_id,String category_name) {

        ContentValues values = new ContentValues();


        values.put("category_name", category_name);


        long check = database.update("product_category", values,"category_id=? ",  new String[]{category_id});
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    //update payment method
    public boolean updatePaymentMethod(String payment_method_id,String payment_method_name) {

        ContentValues values = new ContentValues();


        values.put("payment_method_name", payment_method_name);


        long check = database.update("payment_method", values,"payment_method_id=? ",  new String[]{payment_method_id});
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }



    //update customer
    public boolean updateCustomer(String customer_id,String customer_name, String customer_cell, String customer_email, String customer_address) {

        ContentValues values = new ContentValues();


        values.put("customer_name", customer_name);
        values.put("customer_cell", customer_cell);
        values.put("customer_email", customer_email);
        values.put("customer_address", customer_address);

        long check = database.update("customers",  values," customer_id=? ",new String[]{customer_id});
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    //update shop information
    public boolean updateShopInformation(String shop_name, String shop_contact, String shop_email, String shop_address, String shop_currency,String tax) {


        String shop_id = "1";
        ContentValues values = new ContentValues();


        values.put("shop_name", shop_name);
        values.put("shop_contact", shop_contact);
        values.put("shop_email", shop_email);
        values.put("shop_address", shop_address);
        values.put("shop_currency", shop_currency);
        values.put("tax", tax);

        long check = database.update("shop", values, "shop_id=? ", new String[]{shop_id});
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    //insert products
    public boolean addProduct(String product_name, String product_code, String product_category, String product_description, String product_buy_price, String product_sell_price, String product_stock, String product_supplier, String product_image, String weight_unit_id, String product_weight) {

        ContentValues values = new ContentValues();


        values.put("product_name", product_name);
        values.put("product_code", product_code);
        values.put("product_category", product_category);
        values.put("product_description", product_description);
        values.put("product_buy_price", product_buy_price);
        values.put("product_sell_price", product_sell_price);

        values.put("product_supplier", product_supplier);
        values.put("product_image", product_image);
        values.put("product_stock", product_stock);
        values.put("product_weight_unit_id", weight_unit_id);

        values.put("product_weight", product_weight);


        long check = database.insert("products", null, values);
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    //insert products
    public boolean updateProduct(String product_name, String product_code, String product_category, String product_description, String product_buy_price, String product_sell_price, String product_stock, String product_supplier, String product_image, String weight_unit_id, String product_weight, String product_id) {

        ContentValues values = new ContentValues();


        values.put("product_name", product_name);
        values.put("product_code", product_code);
        values.put("product_category", product_category);
        values.put("product_description", product_description);
        values.put("product_buy_price", product_buy_price);
        values.put("product_sell_price", product_sell_price);

        values.put("product_supplier", product_supplier);
        values.put("product_image", product_image);
        values.put("product_stock", product_stock);
        values.put("product_weight_unit_id", weight_unit_id);

        values.put("product_weight", product_weight);


        long check = database.update("products", values, "product_id=?", new String[]{product_id});
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    //insert expense
    public boolean addExpense(String expense_name, String expense_amount, String expense_note, String date, String time) {

        ContentValues values = new ContentValues();


        values.put("expense_name", expense_name);
        values.put("expense_amount", expense_amount);
        values.put("expense_note", expense_note);
        values.put("expense_date", date);
        values.put("expense_time", time);


        long check = database.insert("expense", null, values);
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    //update expense
    public boolean updateExpense(String expense_id,String expense_name, String expense_amount, String expense_note, String date, String time) {

        ContentValues values = new ContentValues();


        values.put("expense_name", expense_name);
        values.put("expense_amount", expense_amount);
        values.put("expense_note", expense_note);
        values.put("expense_date", date);
        values.put("expense_time", time);


        long check = database.update("expense",  values,"expense_id=?",new String[]{expense_id});
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }


    //insert Suppliers
    public boolean addSuppliers(String suppliers_name, String suppliers_contact_person, String suppliers_cell, String suppliers_email, String suppliers_address) {

        ContentValues values = new ContentValues();


        values.put("suppliers_name", suppliers_name);
        values.put("suppliers_contact_person", suppliers_contact_person);
        values.put("suppliers_cell", suppliers_cell);
        values.put("suppliers_email", suppliers_email);
        values.put("suppliers_address", suppliers_address);

        long check = database.insert("suppliers", null, values);
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }



    //update Suppliers
    public boolean updateSuppliers(String suppliers_id,String suppliers_name, String suppliers_contact_person, String suppliers_cell, String suppliers_email, String suppliers_address) {

        ContentValues values = new ContentValues();


        values.put("suppliers_name", suppliers_name);
        values.put("suppliers_contact_person", suppliers_contact_person);
        values.put("suppliers_cell", suppliers_cell);
        values.put("suppliers_email", suppliers_email);
        values.put("suppliers_address", suppliers_address);

        long check = database.update("suppliers",  values,"suppliers_id=?",new String[]{suppliers_id});
        database.close();

        //if data insert success, its return 1, if failed return -1
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }




    //get product image base 64
    public String getProductImage(String product_id) {

        String image = "n/a";
        Cursor cursor = database.rawQuery("SELECT * FROM products WHERE product_id='" + product_id + "'", null);


        if (cursor.moveToFirst()) {
            do {


                image = cursor.getString(8);


            } while (cursor.moveToNext());
        }


        cursor.close();
        database.close();
        return image;
    }


    //get product weight unit name
    public String getWeightUnitName(String weight_unit_id) {

        String weight_unit_name = "n/a";
        Cursor cursor = database.rawQuery("SELECT * FROM product_weight WHERE weight_id=" + weight_unit_id + "", null);


        if (cursor.moveToFirst()) {
            do {


                weight_unit_name = cursor.getString(1);


            } while (cursor.moveToNext());
        }


        cursor.close();
        database.close();
        return weight_unit_name;
    }


    //get product weight unit name
    public String getSupplierName(String supplier_id) {

        String supplier_name = "n/a";
        Cursor cursor = database.rawQuery("SELECT * FROM suppliers WHERE suppliers_id=" + supplier_id + "", null);


        if (cursor.moveToFirst()) {
            do {


                supplier_name = cursor.getString(1);


            } while (cursor.moveToNext());
        }


        cursor.close();
        database.close();
        return supplier_name;
    }


    //get product weight unit name
    public String getCategoryName(String category_id) {

        String product_category = "n/a";
        Cursor cursor = database.rawQuery("SELECT * FROM product_category WHERE category_id=" + category_id + "", null);


        if (cursor.moveToFirst()) {
            do {


                product_category = cursor.getString(1);


            } while (cursor.moveToNext());
        }


        cursor.close();
        database.close();
        return product_category;
    }


    //Add product into cart
    public int addToCart(String product_id, String weight, String weight_unit, String price, int qty,String stock) {


        Cursor result = database.rawQuery("SELECT * FROM product_cart WHERE product_id='" + product_id + "'", null);
        if (result.getCount() >= 1) {

            return 2;

        } else {
            ContentValues values = new ContentValues();
            values.put("product_id", product_id);
            values.put("product_weight", weight);
            values.put("product_weight_unit", weight_unit);
            values.put("product_price", price);
            values.put("product_qty", qty);
            values.put("stock", stock);

            long check = database.insert("product_cart", null, values);


            database.close();


            //if data insert success, its return 1, if failed return -1
            if (check == -1) {
                return -1;
            } else {
                return 1;
            }
        }

    }


    //get cart product
    public ArrayList<HashMap<String, String>> getCartProduct() {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM product_cart", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("cart_id", cursor.getString(0));
                map.put("product_id", cursor.getString(1));
                map.put("product_weight", cursor.getString(2));
                map.put("product_weight_unit", cursor.getString(3));
                map.put("product_price", cursor.getString(4));
                map.put("product_qty", cursor.getString(5));
                map.put("stock", cursor.getString(6));


                product.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return product;
    }


    //insert order in order list
    public void insertOrder(String order_id, JSONObject obj) {

        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();
        ContentValues values3 = new ContentValues();

        try {
            String order_date = obj.getString("order_date");
            String order_time = obj.getString("order_time");
            String order_type = obj.getString("order_type");
            String order_payment_method = obj.getString("order_payment_method");
            String customer_name = obj.getString("customer_name");
            String tax = obj.getString("tax");
            String discount = obj.getString("discount");


            values.put("invoice_id", order_id);
            values.put("order_date", order_date);
            values.put("order_time", order_time);
            values.put("order_type", order_type);
            values.put("order_payment_method", order_payment_method);
            values.put("customer_name", customer_name);

            values.put("tax", tax);
            values.put("discount", discount);
            values.put(Constant.ORDER_STATUS, Constant.PENDING);


            database.insert("order_list", null, values);

            database.delete("product_cart", null, null);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {

            JSONArray result = obj.getJSONArray("lines");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String product_name = jo.getString("product_name"); //ref
                String product_weight = jo.getString("product_weight");
                String product_qty = jo.getString("product_qty");
                String product_price = jo.getString("product_price");
                String product_image = jo.getString("product_image");
                String product_order_date = jo.getString("product_order_date");


                String product_id = jo.getString("product_id");
                String stock = jo.getString("stock");
                int updated_stock=Integer.parseInt(stock)-Integer.parseInt(product_qty);


                values2.put("invoice_id", order_id);
                values2.put("product_name", product_name);
                values2.put("product_weight", product_weight);
                values2.put("product_qty", product_qty);
                values2.put("product_price", product_price);
                values2.put("product_image", product_image);
                values2.put("product_order_date", product_order_date);
                values2.put(Constant.ORDER_STATUS, Constant.PENDING);

                //for stock update
                values3.put("product_stock",updated_stock);


                //for order insert
                database.insert("order_details", null, values2);

                //updating stock
                database.update("products",values3,"product_id=?",new String[]{product_id});

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        database.close();
    }


    public ArrayList<HashMap<String, String>> getOrderList() {
        ArrayList<HashMap<String, String>> orderList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM order_list ORDER BY order_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("invoice_id", cursor.getString(1));
                map.put("order_date", cursor.getString(2));
                map.put("order_time", cursor.getString(3));
                map.put("order_type", cursor.getString(4));
                map.put("order_payment_method", cursor.getString(5));
                map.put("customer_name", cursor.getString(6));

                map.put("tax", cursor.getString(7));
                map.put("discount", cursor.getString(8));
                map.put(Constant.ORDER_STATUS, cursor.getString(cursor.getColumnIndex(Constant.ORDER_STATUS)));



                orderList.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return orderList;
    }


    public ArrayList<HashMap<String, String>> searchOrderList(String s) {
        ArrayList<HashMap<String, String>> orderList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM order_list WHERE customer_name LIKE '%" + s + "%' OR invoice_id LIKE '%" + s + "%'  OR order_status LIKE '%" + s + "%' ORDER BY order_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("invoice_id", cursor.getString(1));
                map.put("order_date", cursor.getString(2));
                map.put("order_time", cursor.getString(3));
                map.put("order_type", cursor.getString(4));
                map.put("order_payment_method", cursor.getString(5));
                map.put("customer_name", cursor.getString(6));

                map.put("tax", cursor.getString(7));
                map.put("discount", cursor.getString(8));
                map.put(Constant.ORDER_STATUS, cursor.getString(cursor.getColumnIndex(Constant.ORDER_STATUS)));


                orderList.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return orderList;
    }


    //get order history data
    public ArrayList<HashMap<String, String>> getOrderDetailsList(String order_id) {
        ArrayList<HashMap<String, String>> orderDetailsList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM order_details WHERE invoice_id='" + order_id + "' ORDER BY order_details_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("product_name", cursor.getString(2));
                map.put("product_weight", cursor.getString(3));
                map.put("product_qty", cursor.getString(4));
                map.put("product_price", cursor.getString(5));
                map.put("product_image", cursor.getString(6));

                orderDetailsList.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return orderDetailsList;
    }


    //get order history data
    public ArrayList<HashMap<String, String>> getAllSalesItems() {
        ArrayList<HashMap<String, String>> orderDetailsList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM order_details  WHERE order_status='"+Constant.COMPLETED+"' ORDER BY order_details_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("product_name", cursor.getString(2));
                map.put("product_weight", cursor.getString(3));
                map.put("product_qty", cursor.getString(4));
                map.put("product_price", cursor.getString(5));
                map.put("product_image", cursor.getString(6));
                map.put("product_order_date", cursor.getString(7));

                orderDetailsList.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return orderDetailsList;
    }


    //get order history data
    public ArrayList<HashMap<String, String>> getSalesReport(String type) {
        ArrayList<HashMap<String, String>> orderDetailsList = new ArrayList<>();
        Cursor cursor = null;
        if (type.equals("all")) {
            cursor = database.rawQuery("SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"'   ORDER BY order_details_id DESC", null);
        } else if (type.equals("daily")) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

            cursor = database.rawQuery("SELECT * FROM order_details  WHERE order_status='"+Constant.COMPLETED+"' AND product_order_date='" + currentDate + "' ORDER BY order_Details_id DESC", null);

        } else if (type.equals("monthly")) {

            String currentMonth = new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date());
            String sql = "SELECT * FROM order_details  WHERE order_status='"+Constant.COMPLETED+"' AND strftime('%m', product_order_date) = '" + currentMonth + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("yearly")) {

            String currentYear = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(new Date());
            Log.d("YEAR", currentYear);
            String sql = "SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"' AND strftime('%Y', product_order_date) = '" + currentYear + "' ";

            cursor = database.rawQuery(sql, null);

        }


        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("product_name", cursor.getString(2));
                map.put("product_weight", cursor.getString(3));
                map.put("product_qty", cursor.getString(4));
                map.put("product_price", cursor.getString(5));
                map.put("product_image", cursor.getString(6));
                map.put("product_order_date", cursor.getString(7));

                orderDetailsList.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return orderDetailsList;
    }


    //get order history data
    public ArrayList<HashMap<String, String>> getExpenseReport(String type) {
        ArrayList<HashMap<String, String>> orderDetailsList = new ArrayList<>();
        Cursor cursor = null;
        if (type.equals("all")) {
            cursor = database.rawQuery("SELECT * FROM expense  ORDER BY expense_id DESC", null);
        } else if (type.equals("daily")) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

            cursor = database.rawQuery("SELECT * FROM expense WHERE   expense_date='" + currentDate + "' ORDER BY expense_id DESC", null);

        } else if (type.equals("monthly")) {

            String currentMonth = new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date());
            String sql = "SELECT * FROM expense WHERE strftime('%m', expense_date) = '" + currentMonth + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("yearly")) {

            String currentYear = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(new Date());
            String sql = "SELECT * FROM expense WHERE strftime('%Y', expense_date) = '" + currentYear + "' ";

            cursor = database.rawQuery(sql, null);

        }


        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("expense_id", cursor.getString(cursor.getColumnIndex("expense_id")));
                map.put("expense_name", cursor.getString(cursor.getColumnIndex("expense_name")));
                map.put("expense_note", cursor.getString(cursor.getColumnIndex("expense_note")));
                map.put("expense_amount", cursor.getString(cursor.getColumnIndex("expense_amount")));
                map.put("expense_date", cursor.getString(cursor.getColumnIndex("expense_date")));
                map.put("expense_time", cursor.getString(cursor.getColumnIndex("expense_time")));

                orderDetailsList.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return orderDetailsList;
    }


    //calculate total price in month
    public float getMonthlySalesAmount(String month, String getYear) {
        float total_price = 0;
        Cursor cursor = null;
        String year = getYear;
        String sql = "SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"'  AND  strftime('%m', product_order_date) = '" + month + "' AND strftime('%Y', product_order_date) = '" + year + "'  ";
        cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                float price = Float.parseFloat(cursor.getString(5));
                int qty = Integer.parseInt(cursor.getString(4));
                float sub_total = price * qty;
                total_price = total_price + sub_total;
            } while (cursor.moveToNext());
        } else {
            total_price = 0;
        }
        cursor.close();
        database.close();

        Log.d("total_price", "" + total_price);
        return total_price;
    }


    //calculate total price in month
    public float getMonthlyExpenseAmount(String month, String getYear) {


        float total_cost = 0;
        Cursor cursor = null;


        String year = getYear;


        String sql = "SELECT * FROM expense WHERE strftime('%m', expense_date) = '" + month + "' AND strftime('%Y', expense_date) = '" + year + "'  ";

        cursor = database.rawQuery(sql, null);


        if (cursor.moveToFirst()) {
            do {

                float cost = Float.parseFloat(cursor.getString(3));

                total_cost = total_cost + cost;


            } while (cursor.moveToNext());
        } else {
            total_cost = 0;
        }
        cursor.close();
        database.close();

        Log.d("total_price", "" + total_cost);
        return total_cost;
    }


    //delete product from cart
    public boolean deleteProductFromCart(String id) {


        long check = database.delete("product_cart", "cart_id=?", new String[]{id});

        database.close();

        if (check == 1) {
            return true;
        } else {
            return false;
        }

    }


    //get product data
    public ArrayList<HashMap<String, String>> getTabProducts(String category_id) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM products WHERE product_category = '"+category_id+"' ORDER BY product_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("product_id", cursor.getString(0));
                map.put("product_name", cursor.getString(1));
                map.put("product_code", cursor.getString(2));
                map.put("product_category", cursor.getString(3));
                map.put("product_description", cursor.getString(4));
                map.put("product_buy_price", cursor.getString(5));
                map.put("product_sell_price", cursor.getString(6));
                map.put("product_supplier", cursor.getString(7));
                map.put("product_image", cursor.getString(8));
                map.put("product_stock", cursor.getString(9));
                map.put("product_weight_unit_id", cursor.getString(10));
                map.put("product_weight", cursor.getString(11));


                product.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return product;
    }



    //get cart item count
    public int getCartItemCount() {

        Cursor cursor = database.rawQuery("SELECT * FROM product_cart", null);
        int itemCount = cursor.getCount();


        cursor.close();
        database.close();
        return itemCount;
    }


    //delete product from cart
    public void updateProductQty(String id, String qty) {

        ContentValues values = new ContentValues();

        values.put("product_qty", qty);

        long check = database.update("product_cart", values, "cart_id=?", new String[]{id});


    }


    //get product name
    public String getProductName(String product_id) {

        String product_name = "n/a";
        Cursor cursor = database.rawQuery("SELECT * FROM products WHERE product_id='" + product_id + "'", null);


        if (cursor.moveToFirst()) {
            do {


                product_name = cursor.getString(1);


            } while (cursor.moveToNext());
        }


        cursor.close();
        database.close();
        return product_name;
    }


    //get product name
    public String getCurrency() {
        String currency = "n/a";
        Cursor cursor = database.rawQuery("SELECT * FROM shop", null);
        if (cursor.moveToFirst()) {
            do {
                currency = cursor.getString(5);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return currency;
    }




    //get product name
    public String getCallss(String zone) {
        String Call = "n/a";
        Cursor cursor = database.rawQuery("SELECT * FROM ZONE WHERE ZONECODE like '%"+zone+"%'", null);
        if (cursor.moveToFirst()) {
            do {
                Call = cursor.getString(7);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return Call;
    }


    //calculate total price of product
    public double getTotalPrice() {


        double total_price = 0;

        Cursor cursor = database.rawQuery("SELECT * FROM product_cart", null);
        if (cursor.moveToFirst()) {
            do {

                double price = Double.parseDouble(cursor.getString(4));
                int qty = Integer.parseInt(cursor.getString(5));
                double sub_total = price * qty;
                total_price = total_price + sub_total;


            } while (cursor.moveToNext());
        } else {
            total_price = 0;
        }
        cursor.close();
        database.close();
        return total_price;
    }



    //calculate total discount of product
    public double getTotalDiscount(String type) {


        double total_discount = 0;
        Cursor cursor = null;


        if (type.equals("monthly")) {

            String currentMonth = new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date());

            String sql = "SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND strftime('%m', order_date) = '" + currentMonth + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("yearly")) {

            String currentYear = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(new Date());
            String sql = "SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND strftime('%Y', order_date) = '" + currentYear + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("daily")) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

            cursor = database.rawQuery("SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND   order_date='" + currentDate + "' ORDER BY order_id DESC", null);

        } else {
            cursor = database.rawQuery("SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'", null);

        }

        if (cursor.moveToFirst()) {
            do {

                double discount = Double.parseDouble(cursor.getString(cursor.getColumnIndex("discount")));
                total_discount = total_discount + discount;


            } while (cursor.moveToNext());
        } else {
            total_discount = 0;
        }
        cursor.close();
        database.close();
        return total_discount;
    }



    //calculate total discount of product
    public double getTotalDiscountForGraph(String type,int currentYear) {


        double total_discount = 0;
        Cursor cursor = null;


        if (type.equals("monthly")) {

            String currentMonth = new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date());

            String sql = "SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND strftime('%m', order_date) = '" + currentMonth + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("yearly")) {

            String sql = "SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND strftime('%Y', order_date) = '" + currentYear + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("daily")) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

            cursor = database.rawQuery("SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND   order_date='" + currentDate + "' ORDER BY order_id DESC", null);

        } else {
            cursor = database.rawQuery("SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'", null);

        }

        if (cursor.moveToFirst()) {
            do {

                double discount = Double.parseDouble(cursor.getString(cursor.getColumnIndex("discount")));
                total_discount = total_discount + discount;


            } while (cursor.moveToNext());
        } else {
            total_discount = 0;
        }
        cursor.close();
        database.close();
        return total_discount;
    }


    //calculate total tax of product
    public double getTotalTax(String type) {


        double total_tax = 0;
        Cursor cursor = null;
        if (type.equals("monthly")) {

            String currentMonth = new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date());

            String sql = "SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND  strftime('%m', order_date) = '" + currentMonth + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("yearly")) {

            String currentYear = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(new Date());
            String sql = "SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND strftime('%Y', order_date) = '" + currentYear + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("daily")) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

            cursor = database.rawQuery("SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND   order_date='" + currentDate + "' ORDER BY order_id DESC", null);

        } else {
            cursor = database.rawQuery("SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"' ", null);

        }

        if (cursor.moveToFirst()) {
            do {

                double tax = Double.parseDouble(cursor.getString(cursor.getColumnIndex("tax")));
                total_tax = total_tax + tax;


            } while (cursor.moveToNext());
        } else {
            total_tax = 0;
        }
        cursor.close();
        database.close();
        return total_tax;
    }


    //calculate total tax of product
    public double getTotalTaxForGraph(String type,int currentYear) {


        double total_tax = 0;
        Cursor cursor = null;


        if (type.equals("monthly")) {

            String currentMonth = new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date());

            String sql = "SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND  strftime('%m', order_date) = '" + currentMonth + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("yearly")) {

            String sql = "SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND strftime('%Y', order_date) = '" + currentYear + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("daily")) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

            cursor = database.rawQuery("SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"'  AND   order_date='" + currentDate + "' ORDER BY order_id DESC", null);

        } else {
            cursor = database.rawQuery("SELECT * FROM order_list WHERE order_status='"+Constant.COMPLETED+"' ", null);

        }

        if (cursor.moveToFirst()) {
            do {

                double tax = Double.parseDouble(cursor.getString(cursor.getColumnIndex("tax")));
                total_tax = total_tax + tax;


            } while (cursor.moveToNext());
        } else {
            total_tax = 0;
        }
        cursor.close();
        database.close();
        return total_tax;
    }



    //calculate total price of product
    public double getTotalOrderPrice(String type) {


        double total_price = 0;
        Cursor cursor = null;


        if (type.equals("monthly")) {

            String currentMonth = new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date());

            String sql = "SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"'  AND strftime('%m', product_order_date) = '" + currentMonth + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("yearly")) {

            String currentYear = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(new Date());
            String sql = "SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"'  AND  strftime('%Y', product_order_date) = '" + currentYear + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("daily")) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

            cursor = database.rawQuery("SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"'  AND   product_order_date='" + currentDate + "' ORDER BY order_Details_id DESC", null);

        }



        else {
            cursor = database.rawQuery("SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"' ", null);

        }

        if (cursor.moveToFirst()) {
            do {

                double price = Double.parseDouble(cursor.getString(5));
                int qty = Integer.parseInt(cursor.getString(4));
                double sub_total = price * qty;
                total_price = total_price + sub_total;


            } while (cursor.moveToNext());
        } else {
            total_price = 0;
        }
        cursor.close();
        database.close();
        return total_price;
    }



    //calculate total price of product
    public double getTotalOrderPriceForGraph(String type,int currentYear) {


        double total_price = 0;
        Cursor cursor = null;


        if (type.equals("monthly")) {

            String currentMonth = new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date());

            String sql = "SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"'  AND strftime('%m', product_order_date) = '" + currentMonth + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("yearly")) {

            String sql = "SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"'  AND  strftime('%Y', product_order_date) = '" + currentYear + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("daily")) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

            cursor = database.rawQuery("SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"'  AND   product_order_date='" + currentDate + "' ORDER BY order_Details_id DESC", null);

        }



        else {
            cursor = database.rawQuery("SELECT * FROM order_details WHERE order_status='"+Constant.COMPLETED+"' ", null);

        }

        if (cursor.moveToFirst()) {
            do {

                double price = Double.parseDouble(cursor.getString(5));
                int qty = Integer.parseInt(cursor.getString(4));
                double sub_total = price * qty;
                total_price = total_price + sub_total;


            } while (cursor.moveToNext());
        } else {
            total_price = 0;
        }
        cursor.close();
        database.close();
        return total_price;
    }


    //calculate total price of product
    public double totalOrderPrice(String invoice_id) {


        double total_price = 0;


        Cursor cursor = database.rawQuery("SELECT * FROM order_details WHERE invoice_id='" + invoice_id + "'", null);


        if (cursor.moveToFirst()) {
            do {

                double price = Double.parseDouble(cursor.getString(5));
                int qty = Integer.parseInt(cursor.getString(4));
                double sub_total = price * qty;
                total_price = total_price + sub_total;


            } while (cursor.moveToNext());
        } else {
            total_price = 0;
        }
        cursor.close();
        database.close();
        return total_price;
    }


    //calculate total price of expense
    public double getTotalExpense(String type) {


        double total_cost = 0;
        Cursor cursor = null;


        if (type.equals("monthly")) {

            String currentMonth = new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date());

            String sql = "SELECT * FROM expense WHERE strftime('%m', expense_date) = '" + currentMonth + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("yearly")) {

            String currentYear = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(new Date());
            String sql = "SELECT * FROM expense WHERE strftime('%Y', expense_date) = '" + currentYear + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("daily")) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

            cursor = database.rawQuery("SELECT * FROM expense WHERE   expense_date='" + currentDate + "' ORDER BY expense_id DESC", null);

        } else {
            cursor = database.rawQuery("SELECT * FROM expense", null);

        }

        if (cursor.moveToFirst()) {
            do {

                double expense = Double.parseDouble(cursor.getString(3));

                total_cost = total_cost + expense;


            } while (cursor.moveToNext());
        } else {
            total_cost = 0;
        }
        cursor.close();
        database.close();
        return total_cost;
    }



    //calculate total price of expense
    public double getTotalExpenseForGraph(String type,int currentYear) {


        double total_cost = 0;
        Cursor cursor = null;


        if (type.equals("monthly")) {

            String currentMonth = new SimpleDateFormat("MM", Locale.ENGLISH).format(new Date());

            String sql = "SELECT * FROM expense WHERE strftime('%m', expense_date) = '" + currentMonth + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("yearly")) {


            String sql = "SELECT * FROM expense WHERE strftime('%Y', expense_date) = '" + currentYear + "' ";

            cursor = database.rawQuery(sql, null);

        } else if (type.equals("daily")) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

            cursor = database.rawQuery("SELECT * FROM expense WHERE   expense_date='" + currentDate + "' ORDER BY expense_id DESC", null);

        } else {
            cursor = database.rawQuery("SELECT * FROM expense", null);

        }

        if (cursor.moveToFirst()) {
            do {

                double expense = Double.parseDouble(cursor.getString(3));

                total_cost = total_cost + expense;


            } while (cursor.moveToNext());
        } else {
            total_cost = 0;
        }
        cursor.close();
        database.close();
        return total_cost;
    }



    //get customer data
    public ArrayList<HashMap<String, String>> getCustomers() {
        ArrayList<HashMap<String, String>> customer = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM customers ORDER BY customer_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("customer_id", cursor.getString(0));
                map.put("customer_name", cursor.getString(1));
                map.put("customer_cell", cursor.getString(2));
                map.put("customer_email", cursor.getString(3));
                map.put("customer_address", cursor.getString(4));


                customer.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return customer;
    }


    //get order type data
    public ArrayList<HashMap<String, String>> getOrderType() {
        ArrayList<HashMap<String, String>> order_type = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM order_type ORDER BY order_type_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("order_type_id", cursor.getString(0));
                map.put("order_type_name", cursor.getString(1));
                order_type.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return order_type;
    }



    //get order type data
    public ArrayList<HashMap<String, String>> getPaymentMethod() {
        ArrayList<HashMap<String, String>> payment_method = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM payment_method ORDER BY payment_method_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("payment_method_id", cursor.getString(0));
                map.put("payment_method_name", cursor.getString(1));
                payment_method.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return payment_method;
    }



    //get customer data
    public ArrayList<HashMap<String, String>> searchCustomers(String s) {
        ArrayList<HashMap<String, String>> customer = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM customers WHERE customer_name LIKE '%" + s + "%' ORDER BY customer_id DESC", null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("customer_id", cursor.getString(0));
                map.put("customer_name", cursor.getString(1));
                map.put("customer_cell", cursor.getString(2));
                map.put("customer_email", cursor.getString(3));
                map.put("customer_address", cursor.getString(4));


                customer.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return customer;
    }


    //get customer data
    public ArrayList<HashMap<String, String>> searchSuppliers(String s) {
        ArrayList<HashMap<String, String>> customer = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM suppliers WHERE suppliers_name LIKE '%" + s + "%' ORDER BY suppliers_id DESC", null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("suppliers_id", cursor.getString(0));
                map.put("suppliers_name", cursor.getString(1));
                map.put("suppliers_contact_person", cursor.getString(2));
                map.put("suppliers_cell", cursor.getString(3));
                map.put("suppliers_email", cursor.getString(4));
                map.put("suppliers_address", cursor.getString(5));
                customer.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return customer;
    }


    //get shop information
    public ArrayList<HashMap<String, String>> getShopInformation() {
        ArrayList<HashMap<String, String>> shop_info = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM shop", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("shop_name", cursor.getString(1));
                map.put("shop_contact", cursor.getString(2));
                map.put("shop_email", cursor.getString(3));
                map.put("shop_address", cursor.getString(4));
                map.put("shop_currency", cursor.getString(5));
                map.put("tax", cursor.getString(6));
                shop_info.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return shop_info;
    }


    //get product data
    public ArrayList<HashMap<String, String>> getProducts() {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM products ORDER BY product_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("product_id", cursor.getString(0));
                map.put("product_name", cursor.getString(1));
                map.put("product_code", cursor.getString(2));
                map.put("product_category", cursor.getString(3));
                map.put("product_description", cursor.getString(4));
                map.put("product_buy_price", cursor.getString(5));
                map.put("product_sell_price", cursor.getString(6));
                map.put("product_supplier", cursor.getString(7));
                map.put("product_image", cursor.getString(8));
                map.put("product_stock", cursor.getString(9));
                map.put("product_weight_unit_id", cursor.getString(10));
                map.put("product_weight", cursor.getString(11));
                product.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return product;
    }


    //get product data
    public ArrayList<HashMap<String, String>> getProductsInfo(String product_id) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM products WHERE product_id='" + product_id + "'", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("product_id", cursor.getString(0));
                map.put("product_name", cursor.getString(1));
                map.put("product_code", cursor.getString(2));
                map.put("product_category", cursor.getString(3));
                map.put("product_description", cursor.getString(4));
                map.put("product_buy_price", cursor.getString(5));
                map.put("product_sell_price", cursor.getString(6));
                map.put("product_supplier", cursor.getString(7));
                map.put("product_image", cursor.getString(8));
                map.put("product_stock", cursor.getString(9));
                map.put("product_weight_unit_id", cursor.getString(10));
                map.put("product_weight", cursor.getString(11));
                product.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return product;
    }


    //get product data
    public ArrayList<HashMap<String, String>> getAllExpense() {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM expense ORDER BY expense_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("expense_id", cursor.getString(cursor.getColumnIndex("expense_id")));
                map.put("expense_name", cursor.getString(cursor.getColumnIndex("expense_name")));
                map.put("expense_note", cursor.getString(cursor.getColumnIndex("expense_note")));
                map.put("expense_amount", cursor.getString(cursor.getColumnIndex("expense_amount")));
                map.put("expense_date", cursor.getString(cursor.getColumnIndex("expense_date")));
                map.put("expense_time", cursor.getString(cursor.getColumnIndex("expense_time")));
                product.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return product;
    }
    //get product category data
    public ArrayList<HashMap<String, String>> getDistrict(String s) {
        ArrayList<HashMap<String, String>> product_category = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ZONECODE,ZONENAME FROM ZONE WHERE ZONECODE LIKE '%"+s+"%'", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ZONECODE", cursor.getString(0));
                map.put("ZONENAME", cursor.getString(1));
                product_category.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return product_category;
    }

    //get product category data
    public ArrayList<HashMap<String, String>> getVill(String s) {
        ArrayList<HashMap<String, String>> product_category = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Khet_ID,Khet_NameLao FROM KHET WHERE ZONECODE LIKE '%"+ s +"%' ORDER BY Khet_ID", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Khet_ID", cursor.getString(0));
                map.put("Khet_NameLao", cursor.getString(1));

                product_category.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return product_category;
    }

    //get product category data
    public ArrayList<HashMap<String, String>> getProductCategory() {
        ArrayList<HashMap<String, String>> product_category = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ZONECODE,ZONENAME FROM ZONE ORDER BY ZONECODE", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("category_id", cursor.getString(0));
                map.put("category_name", cursor.getString(1));
                product_category.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return product_category;
    }


    //get product category data
    public ArrayList<HashMap<String, String>> getProductCategory2(String s) {
        ArrayList<HashMap<String, String>> product_category = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Khet_ID,Khet_NameLao FROM KHET WHERE ZONECODE='"+ s.toString() +"' ORDER BY Khet_ID", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("category_id", cursor.getString(0));
                map.put("category_name", cursor.getString(1));
                product_category.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return product_category;
    }



    //get product category data
    public ArrayList<HashMap<String, String>> searchProductCategory(String s) {
        ArrayList<HashMap<String, String>> product_category = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM product_category WHERE category_name LIKE '%" + s + "%' ORDER BY category_id DESC ",  null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("category_id", cursor.getString(0));
                map.put("category_name", cursor.getString(1));
                product_category.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return product_category;
    }


    //get product payment method
    public ArrayList<HashMap<String, String>> searchPaymentMethod(String s) {
        ArrayList<HashMap<String, String>> payment_method= new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM payment_method WHERE payment_method_name LIKE '%" + s + "%' ORDER BY payment_method_id DESC ",  null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("payment_method_id", cursor.getString(0));
                map.put("payment_method_name", cursor.getString(1));

                payment_method.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return payment_method;
    }



    //get product supplier data
    public ArrayList<HashMap<String, String>> getProductSupplier() {
        ArrayList<HashMap<String, String>> product_suppliers = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM suppliers", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("suppliers_id", cursor.getString(0));
                map.put("suppliers_name", cursor.getString(1));

                product_suppliers.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return product_suppliers;
    }


    //get product supplier data
    public ArrayList<HashMap<String, String>> getWeightUnit() {
        ArrayList<HashMap<String, String>> product_weight_unit = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM product_weight", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("weight_id", cursor.getString(0));
                map.put("weight_unit", cursor.getString(1));

                product_weight_unit.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return product_weight_unit;
    }

    //get product data
    public ArrayList<HashMap<String, String>> searchExpense(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM expense WHERE expense_name LIKE '%" + s + "%' ORDER BY expense_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("expense_id", cursor.getString(cursor.getColumnIndex("expense_id")));
                map.put("expense_name", cursor.getString(cursor.getColumnIndex("expense_name")));
                map.put("expense_note", cursor.getString(cursor.getColumnIndex("expense_note")));
                map.put("expense_amount", cursor.getString(cursor.getColumnIndex("expense_amount")));
                map.put("expense_date", cursor.getString(cursor.getColumnIndex("expense_date")));
                map.put("expense_time", cursor.getString(cursor.getColumnIndex("expense_time")));


                product.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return product;
    }


    //get product data
    public ArrayList<HashMap<String, String>> getSearchProducts(String s) {
        ArrayList<HashMap<String, String>> product = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM products WHERE product_name LIKE '%" + s + "%' OR product_code LIKE '%" + s + "%' ORDER BY product_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("product_id", cursor.getString(0));
                map.put("product_name", cursor.getString(1));
                map.put("product_code", cursor.getString(2));
                map.put("product_category", cursor.getString(3));
                map.put("product_description", cursor.getString(4));
                map.put("product_buy_price", cursor.getString(5));
                map.put("product_sell_price", cursor.getString(6));
                map.put("product_supplier", cursor.getString(7));
                map.put("product_image", cursor.getString(8));
                map.put("product_stock", cursor.getString(9));
                map.put("product_weight_unit_id", cursor.getString(10));
                map.put("product_weight", cursor.getString(11));


                product.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return product;
    }


    //Add product into cart
    public int addToCart(String product_name, String price, String weight, int qty, String base64Image, String ref, String tva_tx, String product_id) {

        Cursor result = database.rawQuery("SELECT * FROM cart WHERE product_name='" + product_name + "' AND price='" + price + "' AND weight='" + weight + "'", null);
        if (result.getCount() >= 1) {

            return 2;
        } else {
            ContentValues values = new ContentValues();
            values.put("product_name", product_name);
            values.put("price", price);
            values.put("weight", weight);
            values.put("qty", qty);
            values.put("image", base64Image);

            values.put("ref", ref); //desc
            values.put("tva_tx", tva_tx);
            values.put("fk_product", product_id);


            long check = database.insert("cart", null, values);

            database.close();


            //if data insert success, its return 1, if failed return -1
            if (check == -1) {
                return -1;
            } else {
                return 1;
            }

        }

    }


    //get suppliers data
    public ArrayList<HashMap<String, String>> getSuppliers() {
        ArrayList<HashMap<String, String>> supplier = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM suppliers ORDER BY suppliers_id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();


                map.put("suppliers_id", cursor.getString(0));
                map.put("suppliers_name", cursor.getString(1));
                map.put("suppliers_contact_person", cursor.getString(2));
                map.put("suppliers_cell", cursor.getString(3));
                map.put("suppliers_email", cursor.getString(4));
                map.put("suppliers_address", cursor.getString(5));


                supplier.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return supplier;
    }


    //delete customer
    public boolean deleteCustomer(String customer_id) {


        long check = database.delete("customers", "customer_id=?", new String[]{customer_id});

        database.close();

        if (check == 1) {
            return true;
        } else {
            return false;
        }

    }


    //delete category
    public boolean deleteCategory(String category_id) {


        long check = database.delete("product_category", "category_id=?", new String[]{category_id});

        database.close();

        if (check == 1) {
            return true;
        } else {
            return false;
        }

    }


    //delete payment method
    public boolean deletePaymentMethod(String payment_method_id) {


        long check = database.delete("payment_method", "payment_method_id=?", new String[]{payment_method_id});

        database.close();

        if (check == 1) {
            return true;
        } else {
            return false;
        }

    }



    //delete order
    public boolean updateOrder(String invoiceId,String orderStatus) {


        ContentValues contentValues=new ContentValues();
        contentValues.put(Constant.ORDER_STATUS,orderStatus);

        long check = database.update(Constant.orderList, contentValues,"invoice_id=?", new String[]{invoiceId});
        database.update(Constant.orderDetails, contentValues,"invoice_id=?", new String[]{invoiceId});



        database.close();

        if (check == 1) {
            return true;
        } else {
            return false;
        }

    }



    //delete order
    public boolean deleteOrder(String invoice_id) {


        long check = database.delete("order_list", "invoice_id=?", new String[]{invoice_id});
        long check2 = database.delete("order_details", "invoice_id=?", new String[]{invoice_id});

        database.close();

        if (check == 1) {
            return true;
        } else {
            return false;
        }

    }


    //delete product
    public boolean deleteProduct(String product_id) {


        long check = database.delete("products", "product_id=?", new String[]{product_id});
        long check2 = database.delete("product_cart", "product_id=?", new String[]{product_id});

        database.close();

        if (check == 1) {
            return true;
        } else {
            return false;
        }

    }


    //delete product
    public boolean deleteExpense(String expense_id) {


        long check = database.delete("expense", "expense_id=?", new String[]{expense_id});

        database.close();

        if (check == 1) {
            return true;
        } else {
            return false;
        }

    }


    //delete supplier
    public boolean deleteSupplier(String customer_id) {


        long check = database.delete("suppliers", "suppliers_id=?", new String[]{customer_id});

        database.close();

        if (check == 1) {
            return true;
        } else {
            return false;
        }

    }





    public ArrayList<HashMap<String, String>> getBB(String s){
        ArrayList<HashMap<String, String>> employee = new ArrayList<>();
        try {
            Connection con = DB_CON.CONN();
            if (con == null) {
            } else {
                String query = "SELECT MRENT.RENT, MASTER.ACCOUNT,MASTER.Tcode, MASTER.NAME, MASTER.RATRID,BB.PREVREAD, BB.PREVDATE, BB.PRESREAD, BB.PRESDATE, BB.Consumption,BB.Deviation, BB.Detive, BB.waterBill, BB.Mrent,BB.Sewage,BB.Tax,BB.Surcharge, BB.Total_Bill,BB.TOTALDUE AS TOTALDUE1,BB.KhetID,MASTER.AREACODE, MASTER.ZONE, MASTER.TOTALDUE,BB.Arrears,BB.Paid_date,BB.ConNew,MASTER.MTRNUMBER,MASTER.MTRWIDTH,MASTER.AVGCONSUM,BB.Latitude,BB.Longitude   FROM  MASTER,BB, MRENT WHERE  MASTER.SIZECODE = MRENT.SIZECODE AND  MASTER.ACCOUNT = BB.ACCOUNT AND BB.STATUS ='M'  AND MASTER.AREACODE = '0804'\n" +
                                "ORDER BY BB.ACCOUNT";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs!=null){
                    while (rs.next()){
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("RENT", rs.getString("RENT"));
                        map.put("ACCOUNT", rs.getString("ACCOUNT"));
                        map.put("Tcode", rs.getString("Tcode"));
                        map.put("NAME", rs.getString("NAME"));
                        map.put("RATRID", rs.getString("RATRID"));
                        map.put("PREVREAD", rs.getString("PREVREAD"));
                        map.put("PREVDATE", rs.getString("PREVDATE"));
                        employee.add(map);
                    }
                }
                rs.close();
            }
            con.close();
        } catch (Exception ex) {
        }
        return employee;
    }







}