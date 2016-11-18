package com.bat.villain.core.validation;

import com.bat.villain.core.entity.Villain;
import com.bat.villain.core.enumerate.VillainStatusEnum;

public enum VillainValidation {

	INSTANCE;

	public boolean isNull(Villain villain) {
		return (villain == null);
	}

	public boolean isNotNull(Villain villain) {
		return (villain != null);
	}

	public boolean isValid(Villain villain) {
		return (isNotNull(villain) && villain.getId() != null);
	}

	public boolean isDetained(Villain villain) {
		return isValid(villain) && (VillainStatusEnum.DETAINED.equals(villain.getStatus()));
	}
	
	public boolean isDead(Villain villain) {
		return isValid(villain) && (VillainStatusEnum.DEAD.equals(villain.getStatus()));
	}
	
}
