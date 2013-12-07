package com.bimoku.util;

import java.io.Serializable;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.bimoku.common.bean.Book;
import com.bimoku.common.bean.BookAmazon;
import com.bimoku.common.bean.BookDB;
import com.bimoku.common.bean.BookDD;
import com.bimoku.common.bean.BookDetail;
import com.bimoku.common.bean.BookJD;
import com.bimoku.common.bean.BookPub;


/**
 * @INTRO 对象关系映射
 * @author LPM
 * @version last version v0.1.2
 * @Date 2013-8-20
 *
 */
@SuppressWarnings("serial")
public class RelationMapper implements Serializable{
	
	public static String DD_NET = "DD";//铛铛
	public static String DB_NET = "DB";//豆瓣
	public static String AM_NET = "AM";//亚马逊
	public static String JD_NET = "JD";//京东
	public static String PUB_NET = "PUB";//中国互动
	
	
	private String DD;
	private String DB;
	private String AM;
	private String JD;
	private String PUB;
	
	
	
	public String getDD() {
		return DD;
	}

	public void setDD(String dD) {
		DD = dD;
	}

	public String getDB() {
		return DB;
	}

	public void setDB(String dB) {
		DB = dB;
	}

	public String getAM() {
		return AM;
	}

	public void setAM(String aM) {
		AM = aM;
	}

	
	
	public String getJD() {
		return JD;
	}

	public void setJD(String jD) {
		JD = jD;
	}

	public String getPUB() {
		return PUB;
	}

	public void setPUB(String pUB) {
		PUB = pUB;
	}

	/**
	 * 关系跟新
	 * @param book
	 * @param detail
	 * @return
	 * @throws JSONException
	 */
	public static String update(Book book,BookDetail detail) throws JSONException{
		String relation = book.getRelationship();
		RelationMapper mapper = RelationMapper.str2Bean(relation);
		return bean2Json(mapperBuild(mapper, detail));
	}
	
	public static String builtWithClass(BookDetail detail) throws JSONException{
		RelationMapper mapper = new RelationMapper();
		return bean2Json(mapperBuild(mapper, detail));
	}

	/**
	 * 对象转成json字符串
	 * @param mapper
	 * @return
	 * @throws JSONException
	 */
	private static String bean2Json(RelationMapper mapper) throws JSONException{
		
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject(); 
		
		jo.put(DD_NET, mapper.getDD()==null?"":mapper.getDD());
		jo.put(DB_NET, mapper.getDB()==null?"":mapper.getDB());
		jo.put(AM_NET, mapper.getAM()==null?"":mapper.getAM());
		jo.put(JD_NET, mapper.getJD()==null?"":mapper.getJD());
		jo.put(PUB_NET, mapper.getPUB()==null?"":mapper.getPUB());
		
		ja.put(jo);
		ja.put(new JSONObject());
		return jo.toString();
	}
	
	/**
	 * 白字符串转成对象
	 * @param str
	 * @return
	 * @throws JSONException
	 */
	public static RelationMapper str2Bean(String str) throws JSONException{
		//预先处理
		str = (str==null || "".equals(str))?"[]":"["+str+"]"; 
		
		JSONArray ja = new JSONArray(str);
		JSONObject jo = ja.getJSONObject(0);//new JSONObject(str); 
		RelationMapper mapper = new RelationMapper();
		
		mapper.setAM(jo.get(AM_NET)==null?"":jo.get(AM_NET).toString());
		mapper.setDD(jo.get(DD_NET)==null?"":jo.get(DD_NET).toString());
		mapper.setDB(jo.get(DB_NET)==null?"":jo.get(DB_NET).toString());
		mapper.setJD(jo.get(JD_NET)==null?"":jo.get(JD_NET).toString());
		mapper.setPUB(jo.get(PUB_NET)==null?"":jo.get(PUB_NET).toString());
		
		return mapper;
	}
	
	/**
	 * mapper 构建
	 * @param mapper
	 * @param detail
	 * @return
	 */
	private static RelationMapper mapperBuild(RelationMapper mapper,BookDetail detail){
		if(detail instanceof BookDD){
			mapper.setDD(detail.getId()+"");
		}else if(detail instanceof BookAmazon){
			mapper.setAM(detail.getId()+"");
		}else if(detail instanceof BookDB){
			mapper.setDB(detail.getId()+"");
		}else if(detail instanceof BookPub){
			mapper.setPUB(detail.getId()+"");
		}else if (detail instanceof BookJD) {
			mapper.setJD(detail.getId()+"");
		}
		return mapper;
	}

	@Override
	public String toString() {
		return "RelationMapper [DD=" + DD + ", DB=" + DB + ", AM=" + AM
				+ ", JD=" + JD + ", PUB=" + PUB + "]";
	}
	
}
