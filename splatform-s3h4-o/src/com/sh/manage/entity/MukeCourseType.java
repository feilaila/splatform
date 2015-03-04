package com.sh.manage.entity;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TMzAttachment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="muke_course_type")
public class MukeCourseType  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -4025325275332161751L;

	private Integer id;
          
	private Integer number;
     
	private String name;
    	
	private String remark;
   
	private String img;
    
    // Constructors

    /** default constructor */
    public MukeCourseType() {
    }

    
    /** full constructor */
    public MukeCourseType(Integer id, Integer number, String name,
			String remark, String img) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.remark = remark;
		this.img = img;
	}
   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
    
    
    
    
    
    
    
    
    @Column(name="name", length=100)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    

	@Column(name="img")
	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}

	@Column(name="number")
	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name="remark")
	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	


    
    


}