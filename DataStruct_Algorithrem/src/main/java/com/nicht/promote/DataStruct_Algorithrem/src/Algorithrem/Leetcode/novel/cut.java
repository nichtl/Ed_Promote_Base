package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.novel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.AccessLevel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date 2023/9/25
 */
public class cut {

    public static void main(String[] args) {
        String path = "/Users/xujian8/IdeaProjects/wx2/Ed_Promote_Base/DataStruct_Algorithrem/src/main/java/com/nicht/promote/DataStruct_Algorithrem/src/Algorithrem/Leetcode/novel/Cii9D1Z-H0-IRyuWAAAhln0WnxoAABXwwP4bswAACGu60.xlsx";

        String url = "http://crm.tuniu-sit.org/nebula-app/benefitActivity/ajaxImportCustByExcelFile.htm?fileMode=1";

        Map<String , Object>  formData = new HashMap<String , Object>();

         formData.put("fileMode","1");
         formData.put("excelFile",new File(path));
        HttpResponse  response =  HttpUtil.createPost(url).form(formData).execute();

        System.out.println( response.body() );

//        String downloadUrl = "http://m.kujiang.com/book/36101/145688447";
//
//        String parentPath = "/Users/xujian8/IdeaProjects/wx2/Ed_Promote_Base/DataStruct_Algorithrem/src/main/java/com/nicht/promote/DataStruct_Algorithrem/src/Algorithrem/Leetcode/novel";
//
//        String content = HttpUtil.get(downloadUrl,3000);
//        org.jsoup.nodes.Document doc = Jsoup.parseBodyFragment(content);
//
//        Elements titleMsg = doc.getElementsByClass("title-msg");
//        String title =    titleMsg.get(0).text();
//
//
//        Elements nextChapterEle =  doc.getElementsByClass("next-chapter ");
//
//
//        String next_chapter = "";
//
//        Element ele =   doc.getElementById("readArticle");
//
//        Elements p_eles = ele.getElementsByTag("p");
//        List<String> sb =  new ArrayList<>();
//
//        for (Element el: p_eles) {
//
//            for(Node cle : el.childNodes() ){
//                if(cle  instanceof TextNode ){
//                    sb.add(((TextNode) cle).text());
//                }
//            }
//        }
//        File file =   FileUtil.file(parentPath,title+".txt");
//
//        FileUtil.writeLines(sb,file, StandardCharsets.UTF_8);

    }




    public  void  createLocalNovelText(String  url,String savePath){

       // org.jsoup.nodes.Document doc = Jsoup.parseBodyFragment(content);


    }





}
