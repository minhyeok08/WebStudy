package com.sist.main;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.*;
public class RentMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentMainDAO dao=RentMainDAO.newInstance();
		try
		{
			// 사이트 연결
			Document doc=Jsoup.connect("https://rentinjeju.com/search_car").get();
			// 태그를 읽어 온다 => 구분 (class,id) => selector
			Elements carID=doc.select("div#index_search_car a");
			Elements name=doc.select("div#index_search_car a");
			Elements sort=doc.select("div#index_search_car a");
			Elements poster=doc.select("div#index_search_car a");
			/*
			 * Elements name=doc.select("div.top_list_slide div.info_inner_wrap p.desc");
			 * Elements sort=doc.select("div.top_list_slide img.center-croping"); Elements
			 * poster=doc.select("div.top_list_slide a");
			 */
			// 태그와 태그 사이 값 , 태그 안에 속성 값 읽기
			//		text()			attr()	=> a, href
			// 속성 선택자 => img[src*=""]
			for(int i=0;i<carID.size();i++)
			{
				System.out.println(i+1);
				System.out.println("차번호: "+carID.get(i).text());
				/* System.out.println("차이름: "+subject.get(i).text()); */
				//System.out.println("이미지: "+poster.get(i).attr("data-lazy"));
				//System.out.println("인승: "+link.get(i).attr("href"));
				//System.out.println("===============================");
				RentMainVO vo=new RentMainVO();
				vo.setCarID(carID.get(i).text());
				//vo.setSubject(subject.get(i).text());
				//String p=poster.get(i).attr("data-lazy");
				//p=p.replace("&", "#");
				//vo.setPoster(p);
				//vo.setLink("https://www.mangoplate.com"+link.get(i).attr("href"));
				dao.RentMainInsert(vo);
			}
			System.out.println("저장 완료");
		}catch(Exception ex) {}
	}

}
