package com.sist.manager;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.*;
public class DataCollection {
   
   public void rentInfoData()
   {
       RentDAO dao=RentDAO.newInstance();
       try
         {
            // 사이트 연결
            
            Document doc=Jsoup.connect("https://rentinjeju.com/search_car").get();
            // 태그를 읽어 온다 => 구분 (class,id) => selector
            Elements carName=doc.select("a[style*=text-decoration:none]");
            Elements carSize=doc.select("div.container div[class*=small text-title] span:nth-of-type(5n-4)");
            Elements seater=doc.select("div.container div[class*=small text-title] span:nth-of-type(5n-3)");
            Elements fuelType=doc.select("div.container div[class*=small text-title] span:nth-of-type(5n-2)");
            Elements gearType=doc.select("div.container div[class*=small text-title] span:nth-of-type(5n-1)");
            Elements brand=doc.select("div.container div[class*=small text-title] span:nth-of-type(5n)");
            Elements price=doc.select("span[class*=bolder text-primary]");
            Elements link=doc.select("div.container div.col-5 a");
            
            //Elements poster=doc.select("div.container div.col-3 img");
            
            
            for(int i=0;i<carName.size();i++)
            {
               
               String l = (link.get(i).attr("href"));
               int idx = l.indexOf("=");
               
               l = l.substring(idx+1);
              
               RentInfoVO vo=new RentInfoVO();
               vo.setCarName(carName.get(i).text());
               vo.setCarSize(carSize.get(i).text());
               vo.setSeater(seater.get(i).text());
               vo.setFuelType(fuelType.get(i).text());
               vo.setGearType(gearType.get(i).text());
               vo.setBrand(brand.get(i).text());
               vo.setCid(Integer.parseInt(l));
               vo.setLink(link.get(i).attr("href"));
               System.out.println("vo안링크: "+vo.getLink());
               
               System.out.println(i+1);
               System.out.println("차이름: "+carName.get(i).text());
               System.out.println("차크기: "+carSize.get(i).text()); 
               System.out.println("인승: "+seater.get(i).text()); 
               System.out.println("연료: "+fuelType.get(i).text()); 
               System.out.println("기어: "+gearType.get(i).text()); 
               System.out.println("브랜드: "+brand.get(i).text());
               System.out.println("차ID: "+l);
               //System.out.println("링크: "+link.get(i).attr("href"));
               //System.out.println("가격: "+price.get(i).text().replace(",", "").replace("원", ""));
               //System.out.println("가격: "+price);
               if (i > price.size()-1) {
                  vo.setPrice(0);
               System.out.println("값없음");
            } else {
               vo.setPrice(Integer.parseInt(price.get(i).text().replace(",", "").replace("원", "")));
               System.out.println("가격: "+price.get(i).text().replace(",", "").replace("원", ""));
            }
               
            
             Document doc2 = Jsoup.connect("https://rentinjeju.com/search_car/select_rentcar?cid="+vo.getCid()).get();
             Elements poster2=doc2.select("div.container div.col-3 img");
             vo.setPoster(poster2.attr("src"));
             //Elements option=doc2.select("div.small:nth-child(n+2)");
             System.out.println("이미지소스: "+poster2.attr("src"));
      
             System.out.println("저장 완료");
             dao.RentInfoInsert(vo);
            }
         }catch(Exception ex) {}
         
   }
   public static void main(String[] args) {
     
      DataCollection dc=new DataCollection();
      
          dc.rentInfoData();// 30*10 => 300
     
   }

}