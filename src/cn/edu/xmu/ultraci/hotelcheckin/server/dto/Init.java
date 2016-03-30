package cn.edu.xmu.ultraci.hotelcheckin.server.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 对“初始化”请求的响应
 * 
 * @author LuoXin
 *
 */
public class Init extends Base implements Serializable {
	private static final long serialVersionUID = -4763259596966441943L;

	private Upgrade upgrade;
	private String notice;
	private List<Advertisement> ad;

	public Init() {
		upgrade = new Upgrade();
		ad = new ArrayList<Advertisement>();
	}

	public Upgrade getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(Upgrade upgrade) {
		this.upgrade = upgrade;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public List<Advertisement> getAd() {
		return ad;
	}

	public void setAd(List<Advertisement> ad) {
		this.ad = ad;
	}

}