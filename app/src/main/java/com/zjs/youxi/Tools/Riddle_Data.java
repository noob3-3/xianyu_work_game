package com.zjs.youxi.Tools;

import java.util.List;

public class Riddle_Data {

    /**
     * status : 0
     * msg : ok
     * result : {"pagesize":1,"classid":0,"list":[{"content":"随风而行（打一成语）","answer":"见风使舵"}]}
     */

    private int status;
    private String msg;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * pagesize : 1
         * classid : 0
         * list : [{"content":"随风而行（打一成语）","answer":"见风使舵"}]
         */

        private int pagesize;
        private int classid;
        private List<ListBean> list;

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getClassid() {
            return classid;
        }

        public void setClassid(int classid) {
            this.classid = classid;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * content : 随风而行（打一成语）
             * answer : 见风使舵
             */

            private String content;
            private String answer;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }
        }
    }
}
