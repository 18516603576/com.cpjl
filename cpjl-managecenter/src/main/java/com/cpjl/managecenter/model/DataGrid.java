package com.cpjl.managecenter.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据列表返回格式封装实体。
 * @author zhuangchao
 * @param <T> 数据列表类型
 */
public class DataGrid<T> implements java.io.Serializable {

	private static final long serialVersionUID = -4353059775338989141L;

	// 总数量
	private Integer numRows;
	// 当前分页记录列表
	private Collection<T> items;
	// 标识字段
	private String identified = "id";
	// 统计信息
	private Map<String, Object> stats = new HashMap<String, Object>();

	public DataGrid() {

	}

	public DataGrid(Collection<T> items) {
		this(items, items.size());
	}

	public DataGrid(Collection<T> items, Integer numRows) {
		this.items = items;
		this.numRows = numRows;
	}

	public DataGrid(Collection<T> items, Integer numRows, String identified) {
		this(items, items.size());
		this.identified = identified;
	}

	public Integer getNumRows() {
		return numRows;
	}

	public void setNumRows(Integer numRows) {
		this.numRows = numRows;
	}

	public Collection<T> getItems() {
		return items;
	}

	public void setItems(Collection<T> items) {
		this.items = items;
	}

	public String getIdentified() {
		return identified;
	}

	public void setIdentified(String identified) {
		this.identified = identified;
	}

	public Map<String, Object> getStats() {
		return stats;
	}

	public void setStats(Map<String, Object> stats) {
		this.stats = stats;
	}

	/**
	 * 增加统计信息
	 * @param name
	 * @param value
	 */
	public void addStat(String name, Object value) {
		this.stats.put(name, value);
	}

	/**
	 * 返回一个空的数据列表。
	 * @return
	 */
	public static <T> DataGrid<T> empty() {
		Collection<T> items = Collections.emptyList();
		return new DataGrid<T>(items);
	}
}
