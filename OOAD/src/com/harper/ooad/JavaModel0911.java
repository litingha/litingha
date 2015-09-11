package com.harper.ooad;


import java.util.ArrayList;
import java.util.List;

public class JavaModel0911 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Child c = new Child();
		c.addPerson(new Parents());
		c.addPerson(new GrandParents());
		Thread t = new Thread(c);
		t.start();
	}			
}



interface wakeUpEvent{
	public void wakeUpAction();
}

class Child implements Runnable{		
	
	    private List<wakeUpEvent> list = new ArrayList<wakeUpEvent>();
	    
		public void addPerson(wakeUpEvent e){
			list.add(e);
		}
		
		public void wakeUp(){
			for(int i = 0;i < list.size();i++){
				wakeUpEvent event = list.get(i);
				event.wakeUpAction();
			}
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				Thread.sleep(5000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			this.wakeUp();
		}
		
	}
class Parents implements wakeUpEvent{
		@Override
		public void wakeUpAction() {
			// TODO Auto-generated method stub
			System.out.println("Parents--->feed");
		}
}
class GrandParents implements wakeUpEvent{

	@Override
	public void wakeUpAction() {
		// TODO Auto-generated method stub
		System.out.println("GrandParents--->hug");
	}
	
}