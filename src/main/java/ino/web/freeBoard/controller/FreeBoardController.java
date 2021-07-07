package ino.web.freeBoard.controller;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SocketUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.ls.LSInput;

import ino.web.freeBoard.dto.FreeBoardDataDto;
import ino.web.freeBoard.dto.FreeBoardDto;
import ino.web.freeBoard.service.FreeBoardService;
import ino.web.freeBoard.util.Criteria;
import ino.web.freeBoard.util.PageMaker;
import ino.web.freeBoard.util.SearchCriteria;



@Controller
public class FreeBoardController {

	@Autowired
	private FreeBoardService freeBoardService;

	/*
	@RequestMapping("/main.ino")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();


		List list = freeBoardService.freeBoardList();
//		List list = freeBoardService.freeBoardList2();

		mav.setViewName("boardMain");
		mav.addObject("freeBoardList",list);
	return mav;
	}
	*/

	//메인페이지
	@RequestMapping("/main.ino")
	public ModelAndView main(HttpServletRequest request, SearchCriteria scri){
		ModelAndView mav = new ModelAndView();
		List<FreeBoardDto> list = freeBoardService.list(scri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(freeBoardService.listCount(scri));

	//	System.out.println("end = "+pageMaker.getEndPage());
		//System.out.println("start = "+pageMaker.getStartPage());

		mav.setViewName("boardMain");

		mav.addObject("freeBoardList",list);
		mav.addObject("pageMaker", pageMaker);

		//System.out.println("prev = "+pageMaker.get());
		//System.out.println(" = "+pageMaker.getStartPage());


		//System.out.println("tostring = "+cri.toString());

	return mav;
	}


	//insert 뷰페이지
	@RequestMapping("/freeBoardInsert.ino")
	public String freeBoardInsert(){


		return "freeBoardInsert";
	}


	//insert 기능
	@RequestMapping(value = "/freeBoardInsertPro.ino")
	public String freeBoardInsertPro(HttpServletRequest request, FreeBoardDto dto){
		ModelAndView mav = new ModelAndView();

		freeBoardService.freeBoardInsertPro(dto);


		return "redirect:/freeBoardDetail.ino?num="+dto.getNum();
	}

	//삭제
	@RequestMapping("/BoardDelete.ino")
	public String deleteBoard(FreeBoardDto boardDto) {


	//	System.out.println("NUm = "+boardDto.getNum());
		freeBoardService.deleteBoard(boardDto.getNum());

		return "redirect:/main.ino";
	}
	/*
	@RequestMapping("/freeBoardModify.ino")
	public String modifyView(@RequestParam int num, Model model ) {
		FreeBoardDto fbd= freeBoardService.getDetailByNum(num);

		model.addAttribute("name", fbd.getName());
		model.addAttribute("title", fbd.getTitle());
		model.addAttribute("regdate", fbd.getRegdate());
		model.addAttribute("content", fbd.getContent());

		return "freeBoardModify.ino";
	}

	*/

	//디테일 뷰페이지
	@RequestMapping("/freeBoardDetail.ino")
	public ModelAndView freeBoardDetail(HttpServletRequest request){

		int num = Integer.parseInt(request.getParameter("num"));
		FreeBoardDto dto = freeBoardService.getDetailByNum(num);

		//System.out.println(dto.toString());

		return new ModelAndView("freeBoardDetail", "freeBoardDto", dto);
	}

	//수정
	@RequestMapping(value="/modifyBoard.ino")
	public String modifyBoard(FreeBoardDto boardDto) {

		freeBoardService.update(boardDto);

		//System.out.println("cc="+boardDto.toString());

		return "redirect:/main.ino";
	}


	//수정페이지
	@RequestMapping("/freeBoardModify.ino")
	public ModelAndView freeBoardModify(HttpServletRequest request){

		int num = Integer.parseInt(request.getParameter("num"));
		FreeBoardDto dto = freeBoardService.getDetailByNum(num);

		//System.out.println(dto.toString());

		return new ModelAndView("freeBoardModify", "freeBoardDto", dto);
	}

	//자료실 메인
	@RequestMapping("/freeBoardData.ino")
	public ModelAndView freeBoardData(HttpServletRequest request, SearchCriteria scri){
		ModelAndView mav = new ModelAndView();
		List<FreeBoardDataDto> list = freeBoardService.Datalist(scri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(freeBoardService.DatalistCount(scri));

	//	System.out.println("end = "+pageMaker.getEndPage());
		//System.out.println("start = "+pageMaker.getStartPage());

		mav.setViewName("freeBoardData");

		mav.addObject("freeBoardData",list);
		mav.addObject("pageMaker", pageMaker);

		//System.out.println("prev = "+pageMaker.get());
		//System.out.println(" = "+pageMaker.getStartPage());


		//System.out.println("tostring = "+cri.toString());
		//System.out.println("dGETROWStart="+scri.getRowStart());
		//System.out.println("dGETROWEnd="+scri.getRowEnd());

	return mav;
	}

	//write 뷰페이지
	@RequestMapping("/freeBoardWrite.ino")
	public String freeBoardWrite() {

		return "freeBoardWrite";
	}

	//write 기능
	@RequestMapping(value = "/freeBoardWritePro.ino", method = RequestMethod.POST)
	public String freeBoardWritePro(FreeBoardDataDto boardDto, MultipartHttpServletRequest freeRequest) throws Exception{
		ModelAndView mav = new ModelAndView();



		freeBoardService.freeBoardWritePro(boardDto, freeRequest);

		//System.out.println("toString = "+boardDto.toString());

		return "redirect:/freeBoardDataDetailByNum.ino?num="+boardDto.getNum();
	}

	//자료실 디테일 뷰페이지
	@RequestMapping("/freeBoardDataDetail.ino")
	public ModelAndView freeBoardDataDetail(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();

		int num = Integer.parseInt(request.getParameter("num"));
		FreeBoardDataDto dto = freeBoardService.freeBoardDataDetailByNum(num);
		List<Map<String,Object>> fileList = freeBoardService.selectFileList(num);
		mav.addObject("file", fileList);
		//System.out.println(dto.toString());

		return new ModelAndView("freeBoardDataDetail", "freeBoardDataDto", dto);
	}

	//자료실 삭제
	@RequestMapping("/BoardDataDelete.ino")
	public String BoardDataDelete(FreeBoardDataDto boardDto) {


	//	System.out.println("NUm = "+boardDto.getNum());
		//freeBoardService.deleteBoard(boardDto.getNum());
		freeBoardService.deleteDataBoard(boardDto.getNum());
		return "redirect:/freeBoardData.ino";
	}


	//디테일페이지
	/*
	@RequestMapping("/freeBoardDataDetailByNum.ino")
	public ModelAndView freeBoardDataDetailByNum(FreeBoardDataDto boardDto, HttpServletRequest request, SearchCriteria scri){
		ModelAndView mav = new ModelAndView();
		int num = Integer.parseInt(request.getParameter("num"));
		FreeBoardDataDto dto = freeBoardService.freeBoardDataDetailByNum(num);

		mav.addObject("freeBoardDataDetailByNum", freeBoardService.freeBoardDataDetailByNum(boardDto.getNum()));
		mav.addObject("scri", scri);

		List<Map<String, Object>> fileList = freeBoardService.selectFileList(boardDto.getNum());
		mav.addObject("file", fileList);

		return new ModelAndView("freeBoardDataDetail", "freeBoardDataDto", dto);
	}
	*/

	@RequestMapping("/freeBoardDataDetailByNum.ino")
	public String freeBoardDataDetailByNum(FreeBoardDataDto boardDto, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {

		model.addAttribute("freeBoardDataDetailByNum", freeBoardService.freeBoardDataDetailByNum(boardDto.getNum()));
		model.addAttribute("scri", scri);

		List<Map<String, Object>> fileList = freeBoardService.selectFileList(boardDto.getNum());
		model.addAttribute("file", fileList);

		return "freeBoardDataDetail";
	}

	//파일 다운로드
	@RequestMapping("/fileDown.ino")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = freeBoardService.selectFileInfo(map);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");

		System.out.println("storedFileName = "+ storedFileName);
		System.out.println("originalFileName = "+ originalFileName);


		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\free\\file\\"+storedFileName));
		System.out.println("fileByte = "+fileByte);

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	// 게시판 수정뷰
		@RequestMapping(value = "/freeBoardUpdate.ino")
		public String updateView(FreeBoardDataDto dataDto, @ModelAttribute("scri") SearchCriteria scri, Model model)
				throws Exception {

			model.addAttribute("dataUpdate", freeBoardService.freeBoardDataDetailByNum(dataDto.getNum()));
			model.addAttribute("scri", scri);

			List<Map<String, Object>> fileList = freeBoardService.selectFileList(dataDto.getNum());

			model.addAttribute("file", fileList);
			return "freeBoardUpdate";
		}

		// 게시판 수정
		@RequestMapping(value = "/dataUpdate.ino")
		public String update(Map<String, Object> map,
							FreeBoardDataDto boardDto,
							 @ModelAttribute("scri") SearchCriteria scri,
							 @RequestParam(value="fileNoDel[]") String[] files,
							 @RequestParam(value="fileNameDel[]") String[] fileNames,
							 MultipartHttpServletRequest freeRequest, Model model) throws Exception {

			freeBoardService.dataUpdate(map, boardDto, files, fileNames, freeRequest);

			model.addAttribute("page", scri.getPage());
			model.addAttribute("perPageNum", scri.getPerPageNum());
			model.addAttribute("searchType", scri.getSearchType());
			model.addAttribute("keyword", scri.getKeyword());

			return "redirect:/freeBoardData.ino";
		}

		//게시글 삭제

}
