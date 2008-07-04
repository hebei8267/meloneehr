package cn.hb.entity.hr.personnel;

/** 培训信息 */
public class Training {
    public Training() {
    }

    /** 编号 */
    private String id;

    /** 课程名称 */
    private String trainingName;
    /** 名称 */
    private String name;

    /** 开始日期 */
    private String startDate;

    /** 结束时间 */
    private String endDate;
    /** 培训机构 */
    private String trainingOrganization;

    /** 培训地点 */
    private String trainingSite;

    /** 详细描述 */
    private String note;

    /** 个人基本信息 */
    private Person Person;

    /**
     * 取得编号
     * 
     * @return 编号
     */

    public String getId() {
        return id;
    }

    /**
     * 取得课程名称
     * 
     * @return 课程名称
     */

    public String getTrainingName() {
        return trainingName;
    }

    /**
     * 取得名称
     * 
     * @return 名称
     */

    public String getName() {
        return name;
    }

    /**
     * 取得开始日期
     * 
     * @return 开始日期
     */

    public String getStartDate() {
        return startDate;
    }

    /**
     * 取得结束时间
     * 
     * @return 结束时间
     */

    public String getEndDate() {
        return endDate;
    }

    /**
     * 取得培训地点
     * 
     * @return 培训地点
     */

    public String getTrainingSite() {
        return trainingSite;
    }

    /**
     * 取得详细描述
     * 
     * @return 详细描述
     */

    public String getNote() {
        return note;
    }

    /**
     * 取得个人基本信息
     * 
     * @return 个人基本信息
     */

    public String getPerson() {
        return Person;
    }

    /**
     * 设置编号
     * 
     * @param id 编号
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 设置课程名称
     * 
     * @param trainingName 课程名称
     */

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    /**
     * 设置名称
     * 
     * @param name 名称
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置开始日期
     * 
     * @param startDate 开始日期
     */

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 设置结束时间
     * 
     * @param endDate 结束时间
     */

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 设置培训地点
     * 
     * @param trainingSite 培训地点
     */

    public void setTrainingSite(String trainingSite) {
        this.trainingSite = trainingSite;
    }

    /**
     * 设置详细描述
     * 
     * @param note 详细描述
     */

    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 设置个人基本信息
     * 
     * @param Person 个人基本信息
     */

    public void setPerson(String Person) {
        this.Person = Person;
    }

}
