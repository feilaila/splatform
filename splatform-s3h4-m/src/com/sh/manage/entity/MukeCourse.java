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
@Table(name="muke_course")
public class MukeCourse  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -4025325275332161751L;

	private Integer id;
     
	private Integer typeId;
     
	private Integer uid;
     
	private String name;
    
	private String info;
	
	private String title;
   
	private String img;
    
	private String createTime;

	//附件id
	private Integer videoId;
	/**
	 * 状态 0待审核  1已审核  2 已下线 ;默认为0
	 */
	private Integer status;
	
    // Constructors

    /** default constructor */
    public MukeCourse() {
    }

    
    /** full constructor */
    public MukeCourse(Integer id, Integer typeId, Integer uid, String name,
			String info, String img, String createTime,Integer videoId,Integer status) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.uid = uid;
		this.name = name;
		this.info = info;
		this.img = img;
		this.createTime = createTime;
		this.videoId = videoId;
		this.status = status;
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
    
    @Column(name="type_id")
    public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
        
    @Column(name="sys_user_id")
    public Integer getUid() {
        return this.uid;
    }
    
	public void setUid(Integer uid) {
        this.uid = uid;
    }
      
    @Column(name="name", length=100)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="title", length=100)
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="info")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name="img")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name="video_id")
	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}