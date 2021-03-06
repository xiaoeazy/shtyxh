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
@Table(name = "kg_link")
public class KgLink extends BaseDTO {

     public static final String FIELD_ID = "id";
     public static final String FIELD_LINK_NAME = "linkName";
     public static final String FIELD_LINK_URL = "linkUrl";


     @Id
     @GeneratedValue
     private Long id;

     @NotEmpty
     @Length(max = 45)
     private String linkName;

     @NotEmpty
     @Length(max = 200)
     private String linkUrl;


     public void setId(Long id){
         this.id = id;
     }

     public Long getId(){
         return id;
     }

     public void setLinkName(String linkName){
         this.linkName = linkName;
     }

     public String getLinkName(){
         return linkName;
     }

     public void setLinkUrl(String linkUrl){
         this.linkUrl = linkUrl;
     }

     public String getLinkUrl(){
         return linkUrl;
     }

     }
