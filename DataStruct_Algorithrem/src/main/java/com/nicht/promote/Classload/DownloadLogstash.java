package com.nicht.promote.Classload;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

/**
 * 批量下载log
 */
public class DownloadLogstash {
    public static void main(String[] args) throws Exception {

        String dockerUrl = "http://logstash.tuniu.org:8000/docker/";
        String tomcatUrl = "http://logstash.tuniu.org:8000/tomcat/app/";
        String tomcatBase = "pmf-cpn-prd";
        //String tomcatBase = "pmf-pmf-prd";
        //String tomcatBase = "tof-ord-prdtask";
        //String tomcatBase = "dbj-bank-prd";
        //String tomcatBase = "bmw-bma-prd";
        //String tomcatBase = "qms-qms-prd";
        // String tomcatBase = "tof-ord-prd1";

        String logDate="2023-12-07";

        String savePath = "/Users/Downloads/"+tomcatBase;

//        LocalDate starDate = LocalDate.parse("2023-06-20");
//        LocalDate endDate = LocalDate.parse("2023-06-29");
//        while (endDate.compareTo(starDate)>=0){
//            logDate = starDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
//            dockerLogDownload(dockerUrl,tomcatBase,logDate,savePath);
//            starDate = starDate.plusDays(1);
//        }
        dockerLogDownload(dockerUrl,tomcatBase,logDate,savePath);
        //tomcatLogDownload(tomcatUrl,tomcatBase,logDate,savePath);

    }

    private static Elements findA(Document document,String date){
        Elements resultElements = new Elements();
        Elements elements = document.getElementsByTag("a");
        elements.forEach( e ->{
            String href = e.attr("abs:href");
            String text = e.text();
            if(text.equals("../")){

            }else if(!text.equals("../")&&text.endsWith("/")){
                try {
                    resultElements.addAll(findA(Jsoup.connect(href).get(),date));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else if(text.contains(date)){
                resultElements.add(e);
            }
        });
        return resultElements;
    }

    private static void tomcatLogDownload(String baseUrl,String tomcatBase,String date,String savePath) throws Exception{

        Document document = Jsoup.connect(baseUrl+"/" + tomcatBase).header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                .maxBodySize(0)
                .timeout(600000)
                .get();

        Elements elements = findA(document,date);
        elements.forEach(e ->{
            try {
                download(savePath,e.attr("abs:href"));
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        });
    }

    private static void dockerLogDownload(String baseUrl,String tomcatBase,String date,String savePath) throws IOException {
        //docker
        Document document = Jsoup.connect(baseUrl).header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko)  Safari/537.36")
                .maxBodySize(0)
                .timeout(600000)
                .execute().parse();

        Elements elements = document.getElementsByTag("a");

        if(null != elements && elements.size() > 0){
            Iterator<Element> it = elements.iterator();
            while(it.hasNext()){
                Element element = it.next();
                String aText = element.text();
                if(aText.contains(tomcatBase)){
                    String URL=element.attr("abs:href");
                    Document document1 = Jsoup.connect(URL).get();
                    Elements elements1 = document1.getElementsByTag("a");
                    Iterator<Element> it1 = elements1.iterator();
                    while(it1.hasNext()){
                        Element element1 = it1.next();
                        String aText1 = element1.text();
                        if(!aText1.startsWith("..")){
                            Iterator<Element> it2 = null;
                            String URL1=element1.attr("abs:href");
                            Document document2 = Jsoup.connect(URL1).get();
                            Elements elements2 = document2.getElementsByTag("a");
                            it2 = elements2.iterator();
                            while(it2.hasNext()){
                                Element element2 = it2.next();
                                String URL2=element2.attr("abs:href");
                                Document document3 = Jsoup.connect(URL2).get();
                                Elements elements3 = document3.getElementsByTag("a");
                                Iterator<Element> it3 = elements3.iterator();
                                while(it3.hasNext()){
                                    Element element3 = it3.next();
                                    String aText3 = element3.text();
                                    if(!aText3.startsWith("..")){
                                        String URL3=element3.attr("abs:href");
                                        Document document4 = Jsoup.connect(URL3).get();
                                        Elements elements4 = document4.getElementsByTag("a");
                                        Iterator<Element> it4 = elements4.iterator();
                                        while(it4.hasNext()){
                                            Element element4 = it4.next();
                                            String aText4 = element4.text();
                                            if(!aText4.startsWith("..") && aText4.contains(date)){
                                                String downloadUrl =  element4.attr("abs:href");
                                                download(savePath,downloadUrl);
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }

                }
            }
        }

        System.out.println(elements.size());
    }

    private static void download(String basePath ,String url) throws MalformedURLException {
        int bytesum = 0;
        int byteread = 0;

        System.out.println(url);
        URL url1 = new URL(url);

        try {

            URLConnection conn = url1.openConnection();
            InputStream inStream = conn.getInputStream();
            File file=new File(basePath);
            if(!file.exists()&&!file.isDirectory()){
                file.mkdir();
            }
            FileOutputStream fs = new FileOutputStream(new File(basePath+url.substring(url.lastIndexOf("/"))));

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
