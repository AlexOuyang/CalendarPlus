import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class EventListHandler{

	private ObjectInputStream objIn; 
	private StaticEventList staticList;
	private DynamicEventList dynamicList;

	public void EventListInputStream(String filename) throws IOException {
		FileInputStream fi = new FileInputStream(filename);
		this.objIn = new ObjectInputStream(fi);
	}
	
	public StaticEventList readStaticEventList() {
		StaticEventList staticList = null; 
		try {
			staticList = (StaticEventList) this.objIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	    return staticList;
	}

	
	
	public DynamicEventList readDynamicEventList() {
		DynamicEventList dynamicList = null; 
		try {
			this.setDynamicList((DynamicEventList) this.objIn.readObject());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	    return dynamicList;
	}

	public StaticEventList getStaticList() {
		return staticList;
	}

	public void setStaticList(StaticEventList staticList) {
		this.staticList = staticList;
	}

	public DynamicEventList getDynamicList() {
		return dynamicList;
	}

	public void setDynamicList(DynamicEventList dynamicList) {
		this.dynamicList = dynamicList;
	}
	
	//Create a static event to add to the static event list
	public boolean createStaticEvent(String id,String name, Time startTime,Time endTime, boolean isStatic, boolean isPeriodic, boolean isFinished, String comment) throws CalendarError{
		Slot slot = new Slot(id,startTime, endTime);
		boolean check = true;
		StaticEvent staticEvent = new StaticEvent(id, name, slot, isStatic, isPeriodic, isFinished, comment);
		check = staticList.addEvent(staticEvent);
		return check;
	}
	
	public boolean removeStaticEvent(String Id) throws CalendarError{
		boolean check = true;
		check = staticList.removeEventList(Id);
		return check;
	};
	
	//Create a dynamic event to add to the dynamic event list
	public void createDynamicEvent(){}
	
	//Dynamic sort algorithm
	public void dynamicSort(){}
	
	//key is date
	public ArrayList<StaticEvent> getStaticEventsByKey(String key) throws CalendarError{
		return staticList.addEventList(key);
	}
	
	public ArrayList<DynamicEvent> getDynamicEventsByKey(String key){
		return null;
	}
}
/*
string key = "12 Feb 2016"

ArrayList<StaticEvent>() events = EventListHandler.getStaticEventsByDateKey(string dateKey);
ArrayList<DynamicEvent>() events = EventListHandler.getDynamicEventsByDateKey(string key);


DateKey is now DDMMYYY
Id is now DateKey + Name + startTime

StaticEvent se;

string id = se.getID();

EventHandler.removeEventByID(id);

*/
