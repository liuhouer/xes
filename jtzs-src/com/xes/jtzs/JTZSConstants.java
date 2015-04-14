package com.xes.jtzs;

import java.util.HashMap;
import java.util.Map;

public class JTZSConstants {
	public static String TOKEN = "token_"; 
	/**
	 * 默认女图片
	 */
	public static String DEFOULT_IMG_WOMAN = "/images/defaultF.png";
	/**
	 * 默认男图片
	 */
	public static String DEFOULT_IMG_MAN = "/images/defaultM.png";
	/**
	 * 默认上传图片
	 */
	public static String DEFOULT_UPLOAD_IMG = "/images/nipic.png";
	
	/**
	 * 全局分页每页条数
	 */
	public static Integer publicRange = 5;
	/**
	 * 全局最多分页数
	 */
	public static Integer publicSplit = 20; 
	
	/** 公共状态 */
	public static enum Pubilc {
		ENABLED("启用",1),DISABLE("禁用",0);
	    
	    private final String name;  
	    private final int index;  
	    private Pubilc(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(int index) {  
	        for (Pubilc c : Pubilc.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	/** 删除状态 */
	public static enum IsDel {
		DELETE("是",1),UNDELETE("否",0);
	    
	    private final String name;  
	    private final int index;  
	    private IsDel(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(int index) {  
	        for (IsDel c : IsDel.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	/** 消息是否已读 */
	public static enum SystemMessageIsRead {
		READ("已读",1),UNREAD("未读",0);
		
		private final String name;  
		private final int index;  
		private SystemMessageIsRead(String name, int index) {  
			this.name = name;  
			this.index = index;  
		}  
		public static String getName(int index) {  
			for (SystemMessageIsRead c : SystemMessageIsRead.values()) {  
				if (c.getIndex() == index) {  
					return c.name;  
				}  
			}  
			return null;  
		}  
		public String getName() {  
			return name;  
		}  
		public int getIndex() {  
			return index;  
		}  
	}
	
	/** 满意状态 */
	public static enum SatisfiedStatus {
		SATISFIED("满意",1),UNSATISFIED("不满意",0);
	    
	    private final String name;  
	    private final int index;  
	    private SatisfiedStatus(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(int index) {  
	        for (SatisfiedStatus c : SatisfiedStatus.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	
	/** 所属平台 */
	public static enum Platform {
		ANDROID("Android",0),IOS("IOS",1);
	    
	    private final String name;  
	    private final int index;  
	    private Platform(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(int index) {  
	        for (Platform c : Platform.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	/** 发送状态码  */
	public static enum SendStatus {
		WEIFASONG("未发送",0),YIFASONG("已发送",1),DONGJIE("已冻结",2);
	    
	    private final String name;  
	    private final int index;  
	    private SendStatus(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(int index) {  
	        for (Status c : Status.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	/** 状态冻结 */
	public static enum Status {
		NORMAL("正常",1),FREEZE("冻结",0);
	    
	    private final String name;  
	    private final int index;  
	    private Status(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(int index) {  
	        for (Status c : Status.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	/** 问题是否冻结 */
	public static enum QuestionIsLock {
		UNLOCK("正常",0),LOCK("冻结",1);
	    
	    private final String name;  
	    private final int index;  
	    private QuestionIsLock(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(int index) {  
	        for (QuestionIsLock c : QuestionIsLock.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	/** 违规状态 */
	public static enum ReportResult {
		VIOLATION("违规",1),NOVIOLATION("未违规",0);
		
		private final String name;  
		private final int index;  
		private ReportResult(String name, int index) {  
			this.name = name;  
			this.index = index;  
		}  
		public static String getName(int index) {  
			for (ReportResult c : ReportResult.values()) {  
				if (c.getIndex() == index) {  
					return c.name;  
				}  
			}  
			return null;  
		}  
		public String getName() {  
			return name;  
		}  
		public int getIndex() {  
			return index;  
		}  
	}
	
	/** 审核状态 */
	public static enum AuditState {
		TREATED("已处理",1),UNTREATED("未处理",0);
		
		private final String name;  
		private final int index;  
		private AuditState(String name, int index) {  
			this.name = name;  
			this.index = index;  
		}  
		public static String getName(int index) {  
			for (AuditState c : AuditState.values()) {  
				if (c.getIndex() == index) {  
					return c.name;  
				}  
			}  
			return null;  
		}  
		public String getName() {  
			return name;  
		}  
		public int getIndex() {  
			return index;  
		}  
	}
	
	/** 问题状态 */
	public static enum QuestionStatus {
		NOANSWER("无人作答",0),NOWANSWER("正在作答",1),ENDANSWER("作答完毕",2);
		private final String name;  
		private final int index;  
		private QuestionStatus(String name, int index) {  
			this.name = name;  
			this.index = index;  
		}  
		public static String getName(int index) {  
			for (QuestionStatus c : QuestionStatus.values()) {  
				if (c.getIndex() == index) {  
					return c.name;  
				}  
			}  
			return null;  
		}  
		public String getName() {  
			return name;  
		}  
		public int getIndex() {  
			return index;  
		}  
	}
	
	/** 来源类型 */
	public static enum QuestionSourceType {
		NORMAL("学生提问",0),EXPERT("专家作答",1),QUIT("老师放弃",2);
		private final String name;  
		private final int index;  
		private QuestionSourceType(String name, int index) {  
			this.name = name;  
			this.index = index;  
		}  
		public static String getName(int index) {  
			for (QuestionSourceType c : QuestionSourceType.values()) {  
				if (c.getIndex() == index) {  
					return c.name;  
				}  
			}  
			return null;  
		}  
		public String getName() {  
			return name;  
		}  
		public int getIndex() {  
			return index;  
		}  
	}
	
	/** 学生是否放弃提问 */
	public static enum QuestionIsQuit {
		NORMAL("正常",0),QUIT("放弃",1);
		private final String name;  
		private final int index;  
		private QuestionIsQuit(String name, int index) {  
			this.name = name;  
			this.index = index;  
		}  
		public static String getName(int index) {  
			for (QuestionIsQuit c : QuestionIsQuit.values()) {  
				if (c.getIndex() == index) {  
					return c.name;  
				}  
			}  
			return null;  
		}  
		public String getName() {  
			return name;  
		}  
		public int getIndex() {  
			return index;  
		}  
	}
	
	/** 性别 */
	public static enum Sex {
		MEN("男",1),WOMEN("女",0);
	    
	    private final String name;  
	    private final int index;  
	    private Sex(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(int index) {  
	        for (Sex c : Sex.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	/** 答题状态 */
	public static enum AnswerStatus {
		QUIT("老师放弃",0),FINISH("作答完毕",1);
	    
	    private final String name;  
	    private final int index;  
	    private AnswerStatus(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(int index) {  
	        for (AnswerStatus c : AnswerStatus.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	/** 积分类型 */
	public static enum ScoreType {
		ADD("添加",1),DEL("扣除",2);
		
		private final String name;  
		private final int index;  
		private ScoreType(String name, int index) {  
			this.name = name;  
			this.index = index;  
		}  
		public static String getName(int index) {  
			for (ScoreType c : ScoreType.values()) {  
				if (c.getIndex() == index) {  
					return c.name;  
				}  
			}  
			return null;  
		}  
		public String getName() {  
			return name;  
		}  
		public int getIndex() {  
			return index;  
		}  
	}
	
	
	/** 年部 */
	public static enum Division {
		PRIMARY("小学部",1), MIDDLE( "初中部",2),HIGH("高中部",3);
	    
	    private final String name;  
	    private final int index;  
	    private Division(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(int index) {  
	        for (Division c : Division.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	/** 空闲时间 */
	public static enum FreeCycle {
		Monday("星期一","1000000"), Tuesday( "星期二","0100000"),Wednesday("星期三","0010000"),
		Thursday("星期四","0001000"), Friday( "星期五","0000100"),Saturday("星期六","0000010"),Sunday("星期日","0000001");
	    
	    private final String name;  
	    private final String index;  
	    private FreeCycle(String name, String index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    public static String getName(String index) {  
	        for (FreeCycle c : FreeCycle.values()) {  
	            if (c.getIndex().equals(index)) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public String getIndex() {  
	        return index;  
	    }  
	}
	
	public static enum CommonRuleType{
		StudentEveryDayLogin("学生端每日登陆",1),
		StudentFirstRegist("学生首次注册",2),
		SystemOpenTime("系统开放时间",3),
		FreeQuestion("免费提问",4),//学生免费提问
		QuestionScore("提问扣分",5),
		Expert("专家作答",6),
		StudentComment("学生评论",7),
		TeacherQuitAnswer("老师放弃作答",8),
		StudentShareQuestion("学生分享问题",9),
		StudentShareSoftware("学生分享软件",10),
		StudentInviteFriends("学生邀请好友",11),
		TeacherShare("老师分享",12);
	    private final int index;
	    private final String name;
	    private CommonRuleType(String name,int index) {
    	    this.name = name;   
	        this.index = index;  
	    }
	    public static String getName(int index) {  
	        for (CommonRuleType c : CommonRuleType.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	}
	
	/** 学生 */
	public static final Integer ROLE_STUDENT = 0;
	/** 老师 */
	public static final Integer ROLE_TEACHER = 1;
	/** 专家 */
	public static final Integer ROLE_EXPERT = 2;
	/** 指定人员发送 */
	public static final Integer ROLE_SOMEONE = 3;
	
	/** 获取验证码状态  注册+修改密码 */
	public static final String ZHUCE = "0";
	public static final String XIUGAIMIMA = "1";
	
	/** 注册完后是否完善了个人信息*/
	public static final String UNPROFILE = "0";//未完善
	public static final String PROFILE = "1";  //完善了 
	
	public static final class ErrorCode{
		/** 操作成功 */
//		public static final String ls000 = "ls000";
		/** 密码错误 */
//		public static final String ls001 = "ls001";
		/** 问题已被回答 */
		public static final String ls002 = "ls002";
		/** 问题正在回答 */
		public static final String ls003 = "ls003";
		/** 答案不存在 */
		public static final String ls004 = "ls004";
		/** 此账号已超过有效期，无法登陆 **/
		public static final String ls005 = "ls005";
		
		/** 操作成功 */
//		public static final String xs000 = "xs000";
		/** 密码错误 */
//		public static final String xs001 = "xs001";
		/** 不能操作别人的问题 */
		public static final String xs002 = "xs002";
		/** 已回答的问题不能放弃 */
		public static final String xs003 = "xs003";
		/** 不能重复评论一道题 */
		public static final String xs004 = "xs004";
		/** 老师今天太辛苦，已经休息了 */
		public static final String xs005 = "xs005";
		/** 学生积分不够 */
		public static final String xs006 = "xs006";
		/** 问题不存在 */
		public static final String xs007 = "xs007";
		/** 问题正在作答，不能放弃 */
		public static final String xs008 = "xs008";

		/** 操作成功 */
		public static final String gg000 = "gg000";
		/** 用户名密码错误 */
		public static final String gg001 = "gg001";
		/** 手机号已注册 */
		public static final String gg002 = "gg002";
		/** 手机号不存在 */
		public static final String gg003 = "gg003";
		/** 验证码错误 */
		public static final String gg004 = "gg004";
		/** 登录密码错误 */
		public static final String gg005 = "gg005";
		/** 密码不能为空 */
		public static final String gg006 = "gg006";
		/** 密码必须在6-18位之间 */
		public static final String gg007 = "gg007";
		/** 用户不存在 */
		public static final String gg008 = "gg008";
		/** 用户已冻结 */
		public static final String gg009 = "gg009";
		/** 活动已删除 */
		public static final String gg010 = "gg010";
		/** 活动已结束 */
		public static final String gg011 = "gg011";
		/** 活动不能重复作答 */
		public static final String gg012 = "gg012";
		/** 活动未开始 */
		public static final String gg013 = "gg013";
		/** 反馈内容不能为空 */
		public static final String gg014 = "gg014";
		/** 内容字数过长 */
		public static final String gg015 = "gg015";
		/** 系统错误 */
		public static final String gg500 = "gg500";
		
		public static Map<String, String> msgMap = new HashMap<String,String>();
		static{
			msgMap.put(gg000, "操作成功");
			msgMap.put(gg001, "用户名密码错误");
			msgMap.put(gg002, "手机号已注册");
			msgMap.put(gg003, "手机号不存在");
			msgMap.put(gg004, "验证码错误");
			msgMap.put(gg005, "登录密码错误");
			msgMap.put(gg006, "密码不能为空");
			msgMap.put(gg007, "密码必须在6-18位之间");
			msgMap.put(gg008, "用户不存在");
			msgMap.put(gg009, "用户已冻结");
			msgMap.put(gg010, "活动已删除");
			msgMap.put(gg011, "活动已结束");
			msgMap.put(gg012, "活动不能重复作答");
			msgMap.put(gg013, "活动未开始");
			msgMap.put(gg014, "反馈内容不能为空");
			msgMap.put(gg015, "内容字数过长");
			msgMap.put(gg500, "系统错误");
			
			msgMap.put(ls002, "问题已被回答");
			msgMap.put(ls003, "问题正在回答");
			msgMap.put(ls004, "答案不存在");
			msgMap.put(ls005, "此账号已超过有效期，无法登陆");
			
			msgMap.put(xs002, "问题正在回答");
			msgMap.put(xs003, "已回答的问题不能放弃");
			msgMap.put(xs004, "不能重复评论一道题");
			msgMap.put(xs005, "老师今天太辛苦，已经休息了");
			msgMap.put(xs006, "你的积分不够了");
			msgMap.put(xs007, "问题不存在");
			msgMap.put(xs008, "问题正在作答，不能放弃");
		}
		public static String getMag(String code){
			return msgMap.get(code)==null? "" : msgMap.get(code);
		}
	}
	
}
