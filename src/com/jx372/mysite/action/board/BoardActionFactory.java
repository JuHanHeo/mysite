package com.jx372.mysite.action.board;

import com.jx372.web.action.Action;
import com.jx372.web.action.ActionFactory;
import com.jx372.web.util.WebUtils;


public class BoardActionFactory extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("writeform".equals(actionName)){			
			action = new WriteFormAction();
		} else if("write".equals(actionName)){
			action = new WriteAction();
		} else if("delete".equals(actionName)){
			action = new DeleteAction();
		} 
//		else if("search".equals(actionName)){
//			action = new SearchAction();
//		} 
		else if("view".equals(actionName)){
			action = new ViewAction();
		} else if("modifyform".equals(actionName)){
			action = new ModifyFormAction();
		} else if("modify".equals(actionName)){
			action = new ModifyAction();
		} 
		else{
			action = new ListAction();
		}
		
		return action;
	}

}
