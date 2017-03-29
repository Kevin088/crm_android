package cn.xll.com.bean;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/3/29.
 */
public class District {

    /**
     * msg : 成功
     * obj : {"dictName":"安平1","id":1}
     * pageCount : 0
     * success : true
     */

    private String msg;
    /**
     * dictName : 安平1
     * id : 1
     */

    private ObjBean obj;
    private int pageCount;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ObjBean {
        private String dictName;
        private int id;

        public String getDictName() {
            return dictName;
        }

        public void setDictName(String dictName) {
            this.dictName = dictName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
