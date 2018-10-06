package com.shtyxh.manager.dto;

import java.math.BigInteger;
import java.util.List;

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
@Table(name = "kg_assessment_type")
public class KgAssessmentType extends BaseDTO {

     public static final String FIELD_ID = "id";
     public static final String FIELD_ASSESSMENT_TYPE_NAME = "assessmentTypeName";


     @Id
     @GeneratedValue
     private Long id;

     @NotEmpty
     @Length(max = 45)
     private String assessmentTypeName;
     
     @Transient
     private Integer count ;

     @Transient
     private List<KgAssessmentActivity> assessmentActivityList;
     
     
     public KgAssessmentType() {
 		super();
 	}

     
     public KgAssessmentType(Long id) {
		super();
		this.id = id;
	}

	
	public List<KgAssessmentActivity> getAssessmentActivityList() {
		return assessmentActivityList;
	}

	public void setAssessmentActivityList(List<KgAssessmentActivity> assessmentActivityList) {
		this.assessmentActivityList = assessmentActivityList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setId(Long id){
         this.id = id;
     }

     public Long getId(){
         return id;
     }

     public void setAssessmentTypeName(String assessmentTypeName){
         this.assessmentTypeName = assessmentTypeName;
     }

     public String getAssessmentTypeName(){
         return assessmentTypeName;
     }

     }