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
@Table(name = "sys_func")
public class Func extends BaseDTO {

     public static final String FIELD_ID = "id";
     public static final String FIELD_FUNC_NAME = "funcName";


     @Id
     @GeneratedValue
     private Long id;

     @NotEmpty
     @Length(max = 45)
     private String funcName;


     public void setId(Long id){
         this.id = id;
     }

     public Long getId(){
         return id;
     }

     public void setFuncName(String funcName){
         this.funcName = funcName;
     }

     public String getFuncName(){
         return funcName;
     }

     }
