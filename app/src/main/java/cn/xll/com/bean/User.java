package cn.xll.com.bean;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/3/27.
 */
public class User {

    /**
     * msg : 登录成功
     * obj : {"district_id":1,"id":1,"password":"1234","role_id":1,"username":"admin"}
     * pageCount : 0
     * success : true
     */

    private String msg;
    /**
     * district_id : 1
     * id : 1
     * password : 1234
     * role_id : 1
     * username : admin
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
        private int district_id;
        private int id;
        private String password;
        private int role_id;
        private String username;

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
