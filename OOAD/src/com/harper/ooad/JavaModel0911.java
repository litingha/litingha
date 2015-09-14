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
		String []observers = ProMg.getProperty("observer").split(",");
		for(String s : observers){
			try {
				//Class.forName(xxx.xx.xx) 返回的是一个类, .newInstance() 后才创建一个对象
				c.addPerson((wakeUpEvent)(Class.forName(s)).newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
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
class ProMg{
	private static Properties property = new Properties();
	static{
		try{
			property.load(JavaModel0911.class.getClassLoader().getResourceAsStream("com/harper/ooad/observer.properties"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static String getProperty(String key){
		return property.getProperty(key);
	}
}
