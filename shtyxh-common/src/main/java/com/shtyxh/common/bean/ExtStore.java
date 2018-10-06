package com.shtyxh.common.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ExtStore {
	
	//JsonString ="{start:"+start+",limit:"+limit+",totalProperty:"+size+",success:true"+",results:"+jsonArray+"}";
	// 返回状态编码
	
    @JsonInclude(Include.NON_NULL)
    private Integer start;

    // 数量
    @JsonInclude(Include.NON_NULL)
    private Integer limit;

    // 总数
    @JsonInclude(Include.NON_NULL)
    private Integer totalProperty;
    
    
    //数据
    @JsonInclude(Include.NON_NULL)
    private List<?> results;

    // 成功标识
    private boolean success = true;

    
    public ExtStore(boolean success) {
        setSuccess(success);
    }

    public ExtStore(Integer start ,Integer limit,Integer totalProperty,List<?> results) {
    	
       setStart(start);
       setLimit(limit);
       setTotalProperty(totalProperty);
       setResults(results);
    }
    
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getTotalProperty() {
		return totalProperty;
	}

	public void setTotalProperty(Integer totalProperty) {
		this.totalProperty = totalProperty;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
