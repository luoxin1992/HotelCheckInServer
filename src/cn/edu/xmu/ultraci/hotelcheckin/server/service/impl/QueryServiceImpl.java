package cn.edu.xmu.ultraci.hotelcheckin.server.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.constant.ErrorCode;
import cn.edu.xmu.ultraci.hotelcheckin.server.constant.LogTemplate;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IFloorDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IMemberDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.ITypeDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.BaseDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.FloorDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.InfoDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.MemberDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.RoomDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.StatusDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.TypeDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.factory.BaseFactory;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.FloorPO;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.MemberPO;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.TypePO;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IQueryService;
import cn.edu.xmu.ultraci.hotelcheckin.server.util.StringUtil;

public class QueryServiceImpl implements IQueryService {

	private static Logger logger = LogManager.getLogger();

	@Override
	public MemberDTO queryMember(Map<String, String> params) {
		String device = params.get("device");
		String cardid = params.get("cardid");
		if (!StringUtil.isBlank(device) && !StringUtil.isBlank(cardid)) {
			IMemberDao memberDao = (IMemberDao) BaseFactory.getInstance(IMemberDao.class);
			MemberPO member = memberDao.retrieveMemberByCardId(cardid);
			if (member != null) {
				MemberDTO relModel = new MemberDTO();
				relModel.setId(member.getId());
				// 返回会员信息时隐藏部分敏感信息
				relModel.setName(StringUtil.shieldPartionStr(member.getName(), 1, 2));
				relModel.setIdcard(StringUtil.shieldPartionStr(member.getIdcard(), 6, 13));
				relModel.setMobile(StringUtil.shieldPartionStr(member.getMobile(), 3, 6));
				logger.info(String.format(LogTemplate.QUERY_MEMBER_OK, device, cardid));
				return relModel;
			} else {
				logger.warn(
						String.format(LogTemplate.QUERY_MEMBER_FAIL_NO_SUCH_CARD, device, cardid));
				return (MemberDTO) new BaseDTO(ErrorCode.QUERY_MEMBER_FAIL_NO_SUCH_CARD);
			}
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (MemberDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public TypeDTO queryType(Map<String, String> params) {
		String device = params.get("device");
		if (StringUtil.isBlank(device)) {
			TypeDTO retModel = new TypeDTO();
			ITypeDao typeDao = (ITypeDao) BaseFactory.getInstance(ITypeDao.class);
			List<TypePO> types = typeDao.retrieveAllType();
			if (types != null) {
				for (TypePO type : types) {
					retModel.addType(type.getId(), type.getName(), type.getDeposit(),
							type.getPrice());
				}
				logger.info(String.format(LogTemplate.QUERY_TYPE_OK, device, types.size()));
			}
			return retModel;
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (TypeDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public FloorDTO queryFloor(Map<String, String> params) {
		String device = params.get("device");
		if (StringUtil.isBlank(device)) {
			FloorDTO retModel = new FloorDTO();
			IFloorDao floorDao = (IFloorDao) BaseFactory.getInstance(IFloorDao.class);
			List<FloorPO> floors = floorDao.retrieveAllFloor();
			if (floors != null) {
				for (FloorPO floor : floors) {
					retModel.addFloor(floor.getId(), floor.getName());
				}
				logger.info(String.format(LogTemplate.QUERY_FLOOR_OK, device, floors.size()));
			}
			return retModel;
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (FloorDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public StatusDTO queryStatus(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomDTO queryRoom(Map<String, String> params) {
		// TODO Auto-generated method stub
		String device = params.get("device");
		String cardid = params.get("cardid");
		if (!StringUtil.isBlank(device) && !StringUtil.isBlank(cardid)) {
			RoomDTO retModel = new RoomDTO();
			// 房间信息

			// 入住信息

			// 入住者信息
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (RoomDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
		return null;
	}

	@Override
	public InfoDTO queryInfo(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
