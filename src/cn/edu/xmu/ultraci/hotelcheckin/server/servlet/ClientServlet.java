package cn.edu.xmu.ultraci.hotelcheckin.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.constant.Action;
import cn.edu.xmu.ultraci.hotelcheckin.server.constant.ErrorCode;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Base;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Heartbeat;
import cn.edu.xmu.ultraci.hotelcheckin.server.factory.BaseFactory;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IAuthService;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IConfService;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IQueryService;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IRoomService;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.ISystemService;
import cn.edu.xmu.ultraci.hotelcheckin.server.util.ParamUtil;
import net.sf.json.JSONObject;

/**
 * 客户端请求入口
 * 
 * @author LuoXin
 *
 */
@WebServlet(name = "ClientServlet.do", urlPatterns = { "/ClientServlet.do" })
public class ClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LogManager.getLogger();

	private IAuthService authServ;
	private IConfService confServ;
	private IQueryService queryServ;
	private IRoomService roomServ;
	private ISystemService systemServ;

	@Override
	public void init() throws ServletException {
		super.init();

		authServ = (IAuthService) BaseFactory.getInstance(IAuthService.class);
		confServ = (IConfService) BaseFactory.getInstance(IConfService.class);
		queryServ = (IQueryService) BaseFactory.getInstance(IQueryService.class);
		roomServ = (IRoomService) BaseFactory.getInstance(IRoomService.class);
		systemServ = (ISystemService) BaseFactory.getInstance(ISystemService.class);

		// 将服务端实际目录写入配置文件中
		confServ.setConf("root", getServletContext().getRealPath("/"));
		logger.info("real path of server is " + confServ.getConf("root"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 不支持以GET方式请求
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameterMap());
		Map<String, String> params = ParamUtil.convertParams(request.getParameterMap());
		if (authServ.doAuth(params.get("random"), params.get("signature"))) {
			// 鉴权成功，提供服务
			String respJson = buildResponse(parseRequest(params));
			try (PrintWriter pr = response.getWriter()) {
				pr.write(respJson);
				pr.flush();
			}
		} else {
			// 鉴权失败
			logger.warn("authentication failure, invalid request from " + request.getRemoteAddr());
			try (PrintWriter pr = response.getWriter()) {
				pr.write(JSONObject.fromObject(new Base(ErrorCode.AUTH_FAIL)).toString());
				pr.flush();
			}
		}
	}

	private Base parseRequest(Map<String, String> params) {
		switch (params.get("action")) {
		case Action.HEARTBEAT:
			return systemServ.heartbeat(params);
		case Action.INIT:
			return systemServ.init(params);
		case Action.LOGIN:
			break;
		case Action.LOGOUT:
			break;
		case Action.SMS:
			break;
		case Action.QUERY_MEMBER:
			break;
		case Action.QUERY_TYPE:
			break;
		case Action.QUERY_FLOOR:
			break;
		case Action.QUERY_STATUS:
			break;
		case Action.QUERY_ROOM:
			break;
		case Action.QUERY_INFO:
			
			break;
		case Action.CHECK_IN:
			break;
		case Action.CHECK_OUT:
			break;
		case Action.PAY_RESULT:
			break;
		default:
			break;
		}
		return null;
	}

	private <T extends Base> String buildResponse(T resp) {
		return JSONObject.fromObject(resp).toString();
	}

}
