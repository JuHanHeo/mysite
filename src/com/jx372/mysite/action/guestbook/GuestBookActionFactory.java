package com.jx372.mysite.action.guestbook;

import com.jx372.web.action.Action;
import com.jx372.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		System.out.println(actionName);
		if("add".equals(actionName)){
			action = new AddAction();
		}else if("delete".equals(actionName)){
			action = new DeleteAction();
		}else if("deletecon".equals(actionName)){
			action = new DeleteConfirmAction();
		}else{
			//index.jsp로
			action = new ListAction();
		}
		return action;
	}

}
