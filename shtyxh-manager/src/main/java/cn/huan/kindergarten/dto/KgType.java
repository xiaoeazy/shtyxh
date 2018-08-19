package cn.huan.kindergarten.dto;

import java.util.List;

/**Auto Generated By HTed Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.huan.HTed.mybatis.annotation.ExtensionAttribute;
import com.huan.HTed.system.dto.BaseDTO;
@ExtensionAttribute(disable=true)
@Table(name = "kg_type")
public class KgType extends BaseDTO {

     public static final String FIELD_ID = "id";
     public static final String FIELD_TYPENAME = "typename";
     public static final String FIELD_SHOWINDEX = "showindex";
     public static final String FIELD_ENTRANCEIMAGEPATH = "entranceimagepath";
     public static final String FIELD_SHOWENTRANCE = "showentrance";
     public static final String FIELD_PARENTID = "parentid";
     public static final String FIELD_RELATETYPE = "relatetype";
     public static final String FIELD_RELATETYPEID = "relatetypeid";
     public static final String FIELD_HIDDEN = "hidden";

     @Id
     @GeneratedValue
     private Long id;

     @NotEmpty
     @Length(max = 45)
     private String typename;

     private Boolean showindex;

     @Length(max = 200)
     private String entranceimagepath;

     private Boolean showentrance;

     private Long parentid;

     private Integer relatetype;  //0 正常type  1. 单页面内容    2.多列表内容

     private Long relatetypeid;
     
     private Boolean hidden;

     @Transient
     private List<KgType> childType;
     
     @Transient
     private Integer count ;
     
     @Transient
     private List<KgNews> newsList;
     
     @Transient
     private KgType parentType;

     public KgType() {
		super();
	}

	public KgType(Long id) {
		super();
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<KgNews> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<KgNews> newsList) {
		this.newsList = newsList;
	}

	public void setTypename(String typename){
         this.typename = typename;
     }

     public String getTypename(){
         return typename;
     }

   
     public void setEntranceimagepath(String entranceimagepath){
         this.entranceimagepath = entranceimagepath;
     }

     public String getEntranceimagepath(){
         return entranceimagepath;
     }


     public void setParentid(Long parentid){
         this.parentid = parentid;
     }

     public Long getParentid(){
         return parentid;
     }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getShowindex() {
		return showindex;
	}

	public void setShowindex(Boolean showindex) {
		this.showindex = showindex;
	}

	public Boolean getShowentrance() {
		return showentrance;
	}

	public void setShowentrance(Boolean showentrance) {
		this.showentrance = showentrance;
	}

	public Integer getRelatetype() {
		return relatetype;
	}

	public void setRelatetype(Integer relatetype) {
		this.relatetype = relatetype;
	}

	public Long getRelatetypeid() {
		return relatetypeid;
	}

	public void setRelatetypeid(Long relatetypeid) {
		this.relatetypeid = relatetypeid;
	}

	public List<KgType> getChildType() {
		return childType;
	}

	public void setChildType(List<KgType> childType) {
		this.childType = childType;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public KgType getParentType() {
		return parentType;
	}

	public void setParentType(KgType parentType) {
		this.parentType = parentType;
	}

  

     }
