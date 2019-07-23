package com.estate.paging;

public class PageRequest implements Pageble{

	private Integer page;
	private Integer maxPageItem;
	private Sorter sorter;
	
	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}
	
	@Override
	public Integer getPage() {
		// TODO Auto-generated method stub
		return page;
	}

	//phần tử đầu tiên bắt đầu của danh sách mà ta trả về ở csdl (20 item : page 1 star 0, page 2 star 10)
	@Override
	public Integer getOffset() {
		if(page != null && maxPageItem != null) {
			return (page - 1) * maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		// TODO Auto-generated method stub
		return maxPageItem;
	}

	@Override
	public Sorter getSorter() {
		
		return sorter;
	}

}
