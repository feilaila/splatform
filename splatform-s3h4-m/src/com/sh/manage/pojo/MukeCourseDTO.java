package com.sh.manage.pojo;

// default package


/**
 * TMzAttachment entity. @author MyEclipse Persistence Tools
 */
public class MukeCourseDTO  implements java.io.Serializable {


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
	
	
	private String userName;
	
	private String cTypeName;
	
    // Constructors

    /** default constructor */
    public MukeCourseDTO() {
    }

    
    /** full constructor */
    public MukeCourseDTO(Integer id, Integer typeId, Integer uid, String name,
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

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    
    public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
        
    public Integer getUid() {
        return this.uid;
    }
    
	public void setUid(Integer uid) {
        this.uid = uid;
    }
      

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getcTypeName() {
		return cTypeName;
	}


	public void setcTypeName(String cTypeName) {
		this.cTypeName = cTypeName;
	}   	
	
}