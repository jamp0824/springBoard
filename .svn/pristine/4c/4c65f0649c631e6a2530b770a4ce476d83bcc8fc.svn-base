package ino.web.freeBoard.dto;

public class Criteria {
    //현재 페이지 번호
    private int page;
    //한 페이지당 보여줄 게시글의 갯수
    private int perPageNum;
    //특정 페이지의 게시글 시작번호, 게시글 시작행 번호
    private int rowStart;
	private int rowEnd;
    public int getPageStart() {
    	//현재 페이지의 게시글 시작 번호 = (현재 페이지 번호-1)*페이지당 보여줄 게시글 갯수
        return (this.page-1)*perPageNum + 1;
    }
    //기본 생성자
    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }

    public int getPage() {
        return page;
    }
    //페이지가 음수값이 되지 않게 설정. 음수가 되면 1페이지르르 나타낸다.
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
            return;
        } else {
            this.page = page;
        }
    }
    //페이지당 보여줄 게시글 수가 변하지 않게 설정했다.
    public int getPerPageNum() {
        return perPageNum;
    }
    public void setPerPageNum(int pageCount) {
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
            return;
        } else {
            this.perPageNum = pageCount;
        }
    }
    public int getRowStart() {
		rowStart = ((page - 1) * perPageNum) + 1;
		return rowStart;
	}

	public int getRowEnd() {
		rowEnd = rowStart + perPageNum - 1;
		return rowEnd;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
				+ "]";
	}

}


