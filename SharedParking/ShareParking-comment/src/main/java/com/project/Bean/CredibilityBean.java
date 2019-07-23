package com.project.Bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)//链式表达
public class CredibilityBean {
	private int id;
	private MemberBean m_id;
	private int ordersum;
	private int accusesum;

}
