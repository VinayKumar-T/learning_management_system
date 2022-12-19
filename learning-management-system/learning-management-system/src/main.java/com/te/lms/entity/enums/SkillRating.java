package com.te.lms.entity.enums;

public enum SkillRating {
	BEGINNER("BEGINNER"),INTERMEDIATE("INTERMEDIATE"),ADVANCE("ADVANCE"),EXPERT("EXPERT");
	private final String skillRtaing;

	private SkillRating(String skillRtaing) {
		this.skillRtaing = skillRtaing;
	}

	public String getSkillRtaing() {
		return skillRtaing;
	}
	
}
