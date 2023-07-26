package readBookTest;

import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import readBookTest.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Date 2022/12/26
 */
public class test2 {

    
    public static void main(String[] args) {

        String nextPage = "954065";
        String body = HttpUtil.get("https://m.1qishu.com/du/13/13539/"+nextPage+".html");

        org.jsoup.nodes.Document doc = Jsoup.parseBodyFragment(body);
        Element contentElement = doc.getElementsByClass("content").get(0);


        String content = contentElement.html();
        System.out.println(content);

//        List<Double> d = Arrays.asList(9.0, 9.5, 9.0, 10.0, 9.0, 10.0, 9.0, 9.0, 9.0, 8.0, 8.0, 10.0, 8.5, 9.0, 8.0,10.0,10.0,10.0,10.0,10.0);
//
//
//        Double avg = d.stream().reduce(0.0, (x, y) -> x + y) / d.size();
//
//
//        System.out.println(avg);


    }
}
