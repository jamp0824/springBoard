package ino.web.freeBoard.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

    private Criteria cri;
    private int totalCount;
    private int startPage;
    private int endPage;
    //이전 버튼 생성 여부
    private boolean prev;
    //다음 버튼 생성 여부
    private boolean next;
    private int displayPageNum = 10;

    public Criteria getCri() {
        return cri;
    }
    public void setCri(Criteria cri) {
        this.cri = cri;
    }

    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
       //게시글 셋팅할때 calcData()메서드를 호출하여 페이징 관련 버튼 계산을 한다.
        calcData();
    }
    //페이징의 버튼들을 생성하는 계산식을 만들었다. 끝페이지 번호, 시작 페이지 번호,이전, 다음 버튼들을 구한다.
    private void calcData() {
        //Criteria cri.getPage():현재 페이지 번호
        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
        //끝 페이지 번호 = (현재 페이지 번호/화면에 보여질 페이지 번호의 갯수)* 화면에 보여질 페이지 번호
        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;
        	//Totoal Count :총게시글 수		//Criteria cri.getPerPageNum(): 한페이지당 보여줄 게시글의 갯수
        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        //이전 버튼 생성 여부 = 시작 페이지 번호 ==1? false:true 이전 버튼은 시작 페이지 번호가 1이 아니면 생기면 된다
        prev = startPage == 1 ? false : true;
        //다음 버튼 생성 여부 = 끝 페이지 번호* 한페이지당 보여줄 게시글의 갯수< 총게시글의 수 ? true : false
        next = endPage * cri.getPerPageNum() < totalCount ? true : false;
        //int endPage:화면에 보여질 마지막 페이지 번호, 끝 페이지 번호
    }

    public String makeQuery(int page) {
		UriComponents uriComponents =
		UriComponentsBuilder.newInstance()
						    .queryParam("page", page)
							.queryParam("perPageNum", cri.getPerPageNum())
							.build();

		return uriComponents.toUriString();
	}

    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }
    public int getDisplayPageNum() {
        return displayPageNum;
        		//화면 하단에 보여지는 페이지 버튼의 수
    }
    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }
    public String makeSearch(int page)
	{

	 UriComponents uriComponents =
	            UriComponentsBuilder.newInstance()
	            .queryParam("page", page)
	            .queryParam("perPageNum", cri.getPerPageNum())
	            .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
	            .queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
	            .build();
	    return uriComponents.toUriString();
	}

	private String encoding(String keyword) {
		if(keyword == null || keyword.trim().length() == 0) {
			return "";
		}

		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			return "";
		}
	}
}



