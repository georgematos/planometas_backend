package br.com.zipext.plr.utils;

import java.util.ArrayList;
import java.util.List;

public class PaginationJsGridObject<T> {
	public List<T> data = new ArrayList<>();
	public Long itemsCount;

	public PaginationJsGridObject(List<T> data, Long itemsCount) {
		this.data = data;
		this.itemsCount = itemsCount;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Long getItemsCount() {
		return itemsCount;
	}

	public void setItemsCount(Long itemsCount) {
		this.itemsCount = itemsCount;
	}

}