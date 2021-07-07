package ino.web.freeBoard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ino.web.freeBoard.dto.FreeBoardDataDto;
import ino.web.freeBoard.dto.FreeBoardDto;
import ino.web.freeBoard.mapper.FreeBoardMapper;
import ino.web.freeBoard.util.Criteria;
import ino.web.freeBoard.util.FileUtils;
import ino.web.freeBoard.util.SearchCriteria;

@Service
public class FreeBoardService {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/*
	public List freeBoardList(){
		return sqlSessionTemplate.selectList("freeBoardGetList");
	}
	*/
	/*
	public List freeBoardList2(){
		return sqlSessionTemplate.selectList("freeBoardGetList2");
	}
	*/
	public void freeBoardInsertPro(FreeBoardDto dto){
		sqlSessionTemplate.insert("freeBoardInsertPro",dto);
	}
	
	public FreeBoardDto getDetailByNum(int num){
		return sqlSessionTemplate.selectOne("freeBoardDetailByNum", num);
	}
	
	public void deleteBoard(int num){
		sqlSessionTemplate.delete("deleteBoard", num);
	}

	public void update(FreeBoardDto boardDto) {
		
		sqlSessionTemplate.update("modifyBoard", boardDto);
	}
	
	//게시물 목록 조회
	public List<FreeBoardDto> list(SearchCriteria scri) {
		
		return sqlSessionTemplate.selectList("listPage", scri);
	}
	
	//게시물 총 갯수
	public int listCount(SearchCriteria scri) {

		return sqlSessionTemplate.selectOne("listCount", scri);
	}
	
	//파일처리
	@Resource(name = "fileUtils")
	private FileUtils fileutils;
	
	public void freeBoardWrite(FreeBoardDto boardDto, MultipartHttpServletRequest freeRequest) {
			
	}

	//자료 추가
	public void freeBoardWritePro(FreeBoardDataDto boardDto, MultipartHttpServletRequest freeRequest) throws Exception{
		sqlSessionTemplate.insert("freeBoardWritePro", boardDto);

		List<Map<String, Object>> list;
		list = fileutils.parseInsertFileInfo(boardDto, freeRequest);
		int size = list.size();
		for(int i=0; i<size; i++) {
			sqlSessionTemplate.insert("insertFile", list.get(i));
			}
	}
	
	//데이터 파일 리스트
	public List<FreeBoardDataDto> Datalist(SearchCriteria scri) {
		
		return sqlSessionTemplate.selectList("dataPage", scri);
	}
	
	//게시물 총 갯수
	public int DatalistCount(SearchCriteria scri) {

		return sqlSessionTemplate.selectOne("dataListCount", scri);
	}
	
	//디테일
	public FreeBoardDataDto freeBoardDataDetailByNum(int num){
		return sqlSessionTemplate.selectOne("freeBoardDataDetailByNum", num);
	}
	
	//삭제
	public void deleteDataBoard(int num){
		sqlSessionTemplate.delete("deleteDataBoard", num);
		sqlSessionTemplate.delete("deleteFileBoard", num);
		
	}
	
	//첨부파일	조회
	public List<Map<String, Object>> selectFileList(int num) {
		
		return sqlSessionTemplate.selectList("selectFileList", num);
	}
	
	//첨부파일 다운로드
	public Map<String, Object> selectFileInfo(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("selectFileInfo", map);
	}
	
	//데이타 정보수정
	public void dataUpdate(Map<String, Object> map ,FreeBoardDataDto boardDto, String[] files, String[] fileNames, MultipartHttpServletRequest freeRequest) throws Exception {
		sqlSessionTemplate.update("dataUpdate", boardDto);
		
		List<Map<String, Object>> list = fileutils.parseUpdateFileInfo(boardDto, files, fileNames, freeRequest);
		
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				sqlSessionTemplate.insert("insertFile", tempMap);
			}else {
				sqlSessionTemplate.update("updateFile", tempMap);

			}
		}
	}
	
	//첨부파일 수정
	
	
}
