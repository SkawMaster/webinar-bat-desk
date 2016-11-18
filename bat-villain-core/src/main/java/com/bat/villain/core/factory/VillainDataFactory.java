package com.bat.villain.core.factory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bat.villain.core.constant.VillainConstant;
import com.bat.villain.core.entity.Villain;
import com.bat.villain.core.enumerate.VillainStatusEnum;

public final class VillainDataFactory {

	public static Villain createWithId(long id,String name,String description) {
		final Villain villain = new Villain();
		villain.setId(id);
		villain.setName(name);
		villain.setDescription(description);
		villain.setStatus(VillainStatusEnum.FREE);
		villain.setCreationDate(new Date());
		villain.setModificationDate(null);
		return villain;
	}
	
	public static Villain createDefault() {
		return createWithId(VillainConstant.TEST_ID,VillainConstant.TEST_NAME, VillainConstant.TEST_DESCRIPTION);
	}
	
	public static Map<Long,Villain> createMap() {
		final Map<Long,Villain> usersMap = new HashMap<>(); 
		usersMap.put(VillainConstant.TEST_ID, createWithId(VillainConstant.TEST_ID,VillainConstant.TEST_NAME, VillainConstant.TEST_DESCRIPTION));
		usersMap.put(VillainConstant.TEST_2_ID, createWithId(VillainConstant.TEST_2_ID,VillainConstant.TEST_2_NAME, VillainConstant.TEST_2_DESCRIPTION));
		return usersMap;
	}
	
	public static List<Villain> createList() {
		final List<Villain> list = new ArrayList<>();
		list.add(createWithId(VillainConstant.TEST_ID,VillainConstant.TEST_NAME, VillainConstant.TEST_DESCRIPTION));
		list.add(createWithId(VillainConstant.TEST_2_ID,VillainConstant.TEST_2_NAME, VillainConstant.TEST_2_DESCRIPTION));
		return list;
	}
	
}
