package cn.huan.kindergarten.dto;

/**Auto Generated By HTed Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.huan.HTed.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;
import com.huan.HTed.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "kg_allonetext")
public class KgAllonetext extends BaseDTO {

     public static final String FIELD_ID = "id";
     public static final String FIELD_TITLE = "title";
     public static final String FIELD_TYPECODE = "typecode";
     public static final String FIELD_INTRODUCTION = "content";


     @Id
     @GeneratedValue
     private Long id;

     @NotEmpty
     @Length(max = 45)
     private String title;

     @NotEmpty
     @Length(max = 45)
     private String typecode;

     @Length(max = 65535)
     private String content;


   

     public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title){
         this.title = title;
     }

     public String getTitle(){
         return title;
     }

     public void setTypecode(String typecode){
         this.typecode = typecode;
     }

     public String getTypecode(){
         return typecode;
     }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

   

     }
