package net.dzikoysk.server;

import net.dzikoysk.server.module.ChatModule;
import net.dzikoysk.server.module.SchedulerModule;

public class ModuleManager {

	private ChatModule chatModule;
	private SchedulerModule schedulerModule;

	public void create(){
		chatModule = new ChatModule();
		schedulerModule = new SchedulerModule();
	}

	public void enable(){
		chatModule.start();
	}


}
