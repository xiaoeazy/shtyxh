package cn.huan.kindergarten.dto;

import java.util.Date;

/**Auto Generated By HTed Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.huan.HTed.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.huan.HTed.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "kg_assessment_activity")
public class KgAssessmentActivity extends BaseDTO {

     public static final String FIELD_ID = "id";
     public static final String FIELD_ASSESSMENT_TYPE_ID = "assessmentTypeId";
     public static final String FIELD_ATTRIBUTEID = "attributeid";
     public static final String FIELD_ASSESSMENT_ACTIVITY_NAME = "assessmentActivityName";
     public static final String FIELD_ASSESSMENT_ACTIVITY_CONTENT = "assessmentActivityContent";
     public static final String FIELD_FINISHED = "finished";


     @Id
     @GeneratedValue
     private Long id;
     
     private Boolean finished;

     private Long assessmentTypeId;
     
     private String attributeid;

     public KgAssessmentActivity() {
		super();
	}
     

	public KgAssessmentActivity(Long id) {
		super();
		this.id = id;
	}


	@NotEmpty
     @Length(max = 45)
     private String assessmentActivityName;

     private Date createdate;
     
     @Length(max = 65535)
     private String assessmentActivityContent;

     @Transient
     private KgAssessmentType kgAssessmentType;
     
     @Transient
     private String newsSimpleAssessmentActivityName;
     
     public String getAttributeid() {
		return attributeid;
	}

	public void setAttributeid(String attributeid) {
		this.attributeid = attributeid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public KgAssessmentType getKgAssessmentType() {
		return kgAssessmentType;
	 }

	public void setKgAssessmentType(KgAssessmentType kgAssessmentType) {
		this.kgAssessmentType = kgAssessmentType;
	 }

	public void setId(Long id){
         this.id = id;
     }

     public Long getId(){
         return id;
     }

     public void setAssessmentTypeId(Long assessmentTypeId){
         this.assessmentTypeId = assessmentTypeId;
     }

     public Long getAssessmentTypeId(){
         return assessmentTypeId;
     }

     public void setAssessmentActivityName(String assessmentActivityName){
         this.assessmentActivityName = assessmentActivityName;
     }

     public String getAssessmentActivityName(){
         return assessmentActivityName;
     }

     public void setAssessmentActivityContent(String assessmentActivityContent){
         this.assessmentActivityContent = assessmentActivityContent;
     }

     public String getAssessmentActivityContent(){
         return assessmentActivityContent;
     }

	public String getNewsSimpleAssessmentActivityName() {
		return newsSimpleAssessmentActivityName;
	}

	public void setNewsSimpleAssessmentActivityName(String newsSimpleAssessmentActivityName) {
		this.newsSimpleAssessmentActivityName = newsSimpleAssessmentActivityName;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
     
     

     }
