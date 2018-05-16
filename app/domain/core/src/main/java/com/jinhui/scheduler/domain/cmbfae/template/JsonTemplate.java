package com.jinhui.scheduler.domain.cmbfae.template;

import com.alibaba.fastjson.annotation.JSONField;




/**招银前海的json 文件输出模板
 * @author jinhui
 *
 * @param <T>
 */
public class JsonTemplate <T>{
	

	
	@JSONField(ordinal = 1)
	private T content;


	/** summarize 或者detail
	 */
	@JSONField(ordinal = 2)
	private String type;


	public T getContent() {
		return content;
	}


	public JsonTemplate(T content, String type) {
		super();
		this.content = content;
		this.type = type;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setContent(T content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "JsonTemplate [content=" + content + ", type=" + type + "]";
	}

	


	
	
}
