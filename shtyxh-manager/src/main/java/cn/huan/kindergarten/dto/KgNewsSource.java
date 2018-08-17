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
@Table(name = "kg_newssource")
public class KgNewsSource extends BaseDTO {

     public static final String FIELD_ID = "id";
     public static final String FIELD_SOURCENAME = "sourcename";


     @Id
     @GeneratedValue
     private Long id;

     @NotEmpty
     @Length(max = 45)
     private String sourcename;
     
     @Transient
     private Integer count ;


     public KgNewsSource() {
		super();
	}
     

	public KgNewsSource(Long id) {
		super();
		this.id = id;
	}


	public void setId(Long id){
         this.id = id;
     }

     public Long getId(){
         return id;
     }

     public void setSourcename(String sourcename){
         this.sourcename = sourcename;
     }

     public String getSourcename(){
         return sourcename;
     }

	 public Integer getCount() {
			return count;
	 }
	
	 public void setCount(Integer count) {
			this.count = count;
	 }
     
     

     }