package com.mvc.common;

public class Pagination {
    // ��ʼλ��
    private int start;
    // һ��ȡ�õ�����
    private int size;
    // Ҫȡ��ҳ��
    private int currentPage = 1;
    // �ܵļ�¼ҳ��
    private int totalPage   = 0;
    // �ܵļ�¼����
    private int totalRecord;
    
    //��ǰ��id
    private int id = 0;

    public int getTotalRecord() {
        return totalRecord;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        // ��ȡҳ��
        this.totalPage = totalRecord % size == 0 ? totalRecord / size
                : totalRecord / size + 1;
    }

    public Pagination() {
    }

    public Pagination(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStart() {
        this.start = (currentPage - 1) * size;
        return start;
    }
}