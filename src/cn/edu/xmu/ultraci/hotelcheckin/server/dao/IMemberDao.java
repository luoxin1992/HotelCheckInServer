package cn.edu.xmu.ultraci.hotelcheckin.server.dao;

import java.util.List;

import cn.edu.xmu.ultraci.hotelcheckin.server.po.Member;

/**
 * 会员表DAO操作接口
 * 
 * @author LuoXin
 *
 */
public interface IMemberDao extends IBaseDao {

	/**
	 * 新增会员信息
	 * 
	 * @param model 要新增的内容
	 * @return 操作结果
	 */
	public boolean createMember(Member model);

	/**
	 * 更新会员信息
	 * 
	 * @param model 要更新的内容
	 * @return 操作结果
	 */
	public boolean updateMember(Member model);

	/**
	 * 删除会员信息
	 * 
	 * @param id 要删除的ID
	 * @return 操作结果
	 */
	public boolean deleteMember(int id);

	/**
	 * 查询所有会员信息
	 * 
	 * @return 查询结果
	 */
	public List<Member> retrieveAllMember();

	/**
	 * 根据ID查询会员信息
	 * 
	 * @param id 要查询的ID
	 * @return 查询结果
	 */
	public Member retrieveMemberById(int id);
}
