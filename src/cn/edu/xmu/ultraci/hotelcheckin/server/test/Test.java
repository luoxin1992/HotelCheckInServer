package cn.edu.xmu.ultraci.hotelcheckin.server.test;

import cn.edu.xmu.ultraci.hotelcheckin.server.dto.TypeDTO;
import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		TypeDTO typeDto = new TypeDTO();
		for (int i = 1; i <= 5; i++) {
			typeDto.addType(i, "房型" + i, 100.0 * i, 99.8 * i);
		}
		System.out.println(JSONObject.fromObject(typeDto));
	}
}
