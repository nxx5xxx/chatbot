package com.okbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okbs.collections.OpinionDocument;
import com.okbs.repository.OpinionRepository;

@Service
public class OpinionServiceImpl implements OpinionService {
	@Autowired
	OpinionRepository opinionRepository;
	
	
	@Override
	public String getOpinions(String data) {
		List<OpinionDocument> dataList = opinionRepository.findAll();
		List<String> tmpList = new ArrayList<>();
		for(OpinionDocument tmpData : dataList) {
			String[] tmpString = tmpData.getOpinions().split(" , ");
			for(int i=0;i<tmpString.length;i++) {
				tmpList.add(tmpString[i]);
			}
		}
		//Opinions 뽑아내서 tmpList에 넣음
		//이제 그 tmpList에서 하나씩 뽑아다가 data에 indexOf해보고 -1보다 크면 (들어가있으면) 임시 String값에 추가
		String returnData= "";
//		System.out.println("data값 : "+data);
		for(String tmpData : tmpList) {
//			System.out.println("tmpData테스트 : "+tmpData);
//			System.out.println(data.contains(tmpData));
			if(data.contains(tmpData)) {
//				System.out.println("트루");
				if(returnData.length()<=0) {
					returnData+= "<span class='opinions' onclick='tmpBtn(this)'>"+tmpData+"</span>";
				}else {
					returnData+= " , " + "<span class='opinions' onclick='tmpBtn(this)'>" +tmpData +"</span>";
				}
			}
		}
//		System.out.println(tmpList.toString());
//		System.out.println("returnData테스트 : " + returnData);
		return returnData;
	}
}
