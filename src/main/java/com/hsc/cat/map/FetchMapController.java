package com.hsc.cat.map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.cat.controller.SkillController;
import com.hsc.cat.utilities.JSONOutputEnum;
import com.hsc.cat.utilities.JSONOutputModel;




@RestController
public class FetchMapController {

	
	private static final Logger LOGGER = (Logger) LogManager.getLogger(FetchMapController.class);
	@Autowired
	private FetchMapService fetchMapService;
	
	
	@ResponseBody
	@RequestMapping(value="/fetchMapNew",method=RequestMethod.POST,produces = "application/json",consumes="application/json")
	@CrossOrigin
	public JSONOutputModel  fetchMap(@RequestBody NewFetchMapVO newFetchMapVO) {
		
		LOGGER.debug("Inside new fetch map controller");
		JSONOutputModel output=new JSONOutputModel();
		
		FetchMapTO fetchMapTO=fetchMapService.fetchMap(newFetchMapVO);
		
		output.setData(fetchMapTO);
		
		if(fetchMapTO!=null && fetchMapTO.getSkillMapResponse()!=null &&( !fetchMapTO.getListOfselfReviews().isEmpty() ||!fetchMapTO.getListOfpeerReviews().isEmpty())) {
			
			output.setMessage("Map fetched successfully");
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}
		else {
			output.setMessage("No data found");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		return output;
	}
}
