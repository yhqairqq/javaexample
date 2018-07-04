package com.ako.example.jdk.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yanghuanqing@wdai.com on 11/09/2017.
 */
public class StringTest {


    public static void main(String[] args) {
        String after = "select id, user_name,login_name,mobile,maturity_grade,recover_amount,CONCAT(recover_time,' ',recover_time_min)recover_time,source,belong_employee,roo_uid \n" +
                "from (select c_id as id, user_name,login_name,mobile,maturity_grade,SUM(recover_amonut)recover_amount,MAX(recover_time)as recover_time,recover_time_min,source,belong_employee,roo_uid \n" +
                "\tfrom  qc.crm_customer_receive_detail \n" +
                "\twhere maturity_grade = ifnull(?,maturity_grade) \n" +
                "\tand source = ifnull(?,source)\n" +
                "\t\tand city=ifnull(?,city) \n" +
                "\t\tand org_id IN (?)\n" +
                "\t\tand belong_employee = ifnull(?,belong_employee) \n" +
                "\t\tand balance >= ifnull(?,balance)\n" +
                "\t\tand balance <= ifnull(?,balance) \n" +
                "\t\tand current_tender_amount >= ifnull(?,current_tender_amount) \n" +
                "\t\tand current_tender_amount <= ifnull(?,current_tender_amount)\n" +
                "\t\t\tand recover_time >= ifnull(?,'2017-02-01') \n" +
                "\t\t\tand recover_time <= ifnull(?,'2099-01-01') \n" +
                "\t\t\tand (roo_uid = ifnull(?,roo_uid) \n" +
                "\t\t\t\tor login_name = ifnull(?,login_name) \n" +
                "\t\t\t\tor user_name = ifnull(?,user_name) \n" +
                "\t\t\tor mobile = ifnull(?,mobile))group by roo_uid)aaa";

        String before = "select id, user_name,login_name,mobile,maturity_grade,recover_amount,CONCAT(recover_time,' ',recover_time_min)recover_time,source,belong_employee,roo_uid \n" +
                "from (select c_id as id, user_name,login_name,mobile,maturity_grade,SUM(recover_amonut)recover_amount,MAX(recover_time)as recover_time,recover_time_min,source,belong_employee,roo_uid \n" +
                "from  qc.crm_customer_receive_detail \n" +
                "where maturity_grade = ifnull(#{maturity_grade},maturity_grade) \n" +
                "        and source = ifnull(#{source},source)\n" +
                "\t\tand city=ifnull(#{city},city) \n" +
                "\t\tand org_id IN (#{org_id})\n" +
                "\t\tand belong_employee = ifnull(#{belong_employee},belong_employee) \n" +
                "\t\tand balance >= ifnull(#{balance_min},balance)\n" +
                "\t\tand balance <= ifnull(#{balance_max},balance) \n" +
                "\t\tand current_tender_amount >= ifnull(#{current_tender_amount_min},current_tender_amount) \n" +
                "\t\tand current_tender_amount <= ifnull(#{current_tender_amount_max},current_tender_amount)\n" +
                "\t\t\tand recover_time >= ifnull(#{recover_time_min},'2017-02-01') \n" +
                "\t\t\tand recover_time <= ifnull(#{recover_time_max},'2099-01-01') \n" +
                "\t\t\tand (roo_uid = ifnull(#{user_search},roo_uid) \n" +
                "\t\t\tor login_name = ifnull(#{user_search},login_name) \n" +
                "\t\t\tor user_name = ifnull(#{user_search},user_name) \n" +
                "\t\t\tor mobile = ifnull(#{user_search},mobile))group by roo_uid)aaa";


//        Map<String,String> allMaps = new HashMap<>();
//        allMaps.put("maturity_grade","1");
//        allMaps.put("source","1");
//        allMaps.put("city","1");
//        allMaps.put("org_id","1");
//        allMaps.put("belong_employee","1");
//        allMaps.put("balance","1");
//        allMaps.put("current_tender_amount","1");
//        allMaps.put("recover_time","1");
//        allMaps.put("roo_uid","1");
//        allMaps.put("user_name","1");
//        allMaps.put("mobile","1");
//
//
//


//        Map<String,String> paramMaps = new HashMap<>();
//        paramMaps.put("current_tender_amount_min","asdfasdf");
//        paramMaps.put("recover_time_min","asdfasdf");
//        paramMaps.put("user_search","asdfasdf");
//        filterSql(text,paramMaps);
//        findMachers(text);
        List<String> props =new ArrayList<>();
        props.add("user_search");
        props.add("recover_time_min");
        props.add("recover_time_max");
        props.add("current_tender_amount_min");
        props.add("org_id");
        Map<String,String> addMap = new HashMap<>();
        addMap.put("user_search","asdfadsf");
        replaceIfnullExpress(before,after,props,addMap);
    }



    public static void replaceIfnullExpress(String beforeSql,String afterSql,List<String> props,Map<String,String> addMap){
        String before = "ifnull\\(#\\{%s\\},([^\\)]*?)\\)";
        String beforereplace =  "ifnull\\(#\\{%s\\},[^\\)]*\\)";
        String after = "ifnull\\(\\?,%s\\)";
        String afterReplace = "";
        for(String prop:props){
           List<String> matchKeys =  findMachers(String.format(before,prop),beforeSql);
           if(matchKeys!=null){
               for(String key:matchKeys){
                   String afterGenRegex = String.format(after,key);
                   //替换方式  如果有值 替换成当前值 如果无值 替换成key
                   if(addMap.get(prop) == null){
                       afterSql =  afterSql.replaceAll(afterGenRegex,key);
                   }else{
                       afterSql =  afterSql.replaceAll(afterGenRegex,"?");
                   }
               }
           }
        }
        System.out.println(afterSql);
    }
    public static List<String> findMachers(String regex,String text){
        List<String> matchKeys = null;

       Matcher matcher =  Pattern.compile(regex).matcher(text);

       while(matcher.find()){
           if(matchKeys == null){
               matchKeys = new ArrayList<>();
           }
           matchKeys.add(matcher.group(1).trim());
       }
       return matchKeys;
    }



    public  static String findMachers(String oriSql){

        String returnSql = oriSql;
        String extractModel = "ifnull\\(\\?,([^\\)]*?)\\)";
        String mark = "\\?";
            int count = 0;
            int begin = 0;
           for(;;){
               int curr = oriSql.indexOf(mark,begin);
               if(curr > -1){
                   String defaultValue = null;
                   Matcher matcher =   Pattern.compile(extractModel).matcher(returnSql.substring(begin));
                   if (matcher.find()){
                       System.out.println((++count)+":"+matcher.group(1).trim());
                   }
               }
           }

    }


    public  static String filterSql(String oriSql, Map<String,String> paramMaps){

        String returnSql = oriSql;

        for(Map.Entry<String, String> entry:paramMaps.entrySet()){
                String extractModel = "ifnull\\(#\\{%s\\},([^\\)]*?)\\)";
                String replaceModel =  "ifnull\\(#\\{%s\\},[^\\)]*\\)";
                String extractModelRegex = String.format(extractModel,entry.getKey());
                String replaceModelRegex = String.format(replaceModel,entry.getKey());


               if(entry.getValue() !=null){
                   returnSql = returnSql.replaceAll(replaceModelRegex,entry.getValue());
               }else{
                   String defaultValue = null;
                    Matcher matcher =   Pattern.compile(extractModelRegex).matcher(returnSql);
                   if(matcher.find()){
                       defaultValue = matcher.replaceAll(matcher.group(1).trim());
                       returnSql.replaceAll(replaceModelRegex,defaultValue);
                       break;
                   }
               }

        }

        return returnSql;
    }
    public  static String filterSql(String oriSql, String prop,String replace){

        String returnSql = oriSql;

            String extractModel = "ifnull\\(#\\{%s\\},([^\\)]*?)\\)";
            String replaceModel =  "ifnull\\(#\\{%s\\},[^\\)]*\\)";
            String extractModelRegex = String.format(extractModel,prop);
            String replaceModelRegex = String.format(replaceModel,prop);
            if(replace!=null){
                returnSql = returnSql.replaceAll(replaceModelRegex,replace);
            }else{
                String defaultValue = null;
                Matcher matcher =   Pattern.compile(extractModelRegex).matcher(returnSql);
                if(matcher.find()){
                    defaultValue = matcher.replaceAll(matcher.group(1).trim());
                    returnSql.replaceAll(replaceModelRegex,defaultValue);
                }
            }
        return returnSql;
    }
}
