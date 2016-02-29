package com.cse110.apk404.myCalendar.eventListHandler;

import java.util.*;


public class EventListHandler {


    private static StaticEventList staticList;
    private static DynamicEventList dynamicList;
    private static ArrayList<CalendarEvent> events; //list to store all events in one given day


    public static ArrayList<CalendarEvent> getEvents() {
        return events;
    }

    public static void setEvents(ArrayList<CalendarEvent> events) {
        EventListHandler.events = events;
    }

    public static ArrayList<CalendarEvent> getEventsByDate(String dateKey) throws CalendarError {
        if (dateKey == null)
            throw new CalendarError("Null Event");

        events = new ArrayList<CalendarEvent>();
        ArrayList<StaticEvent> staticArrayList = staticList.getList();
        if (staticArrayList != null) {
            for (int i = 0; i < staticArrayList.size(); i++) {
                if (staticArrayList.get(i).getDateKey().contains(dateKey)) {
                    events.add(staticArrayList.get(i));
                }
            }
        }
        ArrayList<DynamicEvent> dynamicArrayList = dynamicList.getList();
        if (dynamicArrayList != null) {
            for (int i = 0; i < dynamicArrayList.size(); i++) {
                if (dynamicArrayList.get(i).getDateKey().contains(dateKey)) {
                    events.add(dynamicArrayList.get(i));
                }
            }
        }

        return events;
    }

    public static void initStaticList() {
        staticList = new StaticEventList();
    }

    public static void initDynamicList() {
        dynamicList = new DynamicEventList();
    }

    public static StaticEventList getStaticList() {
        return staticList;
    }

    public static void setStaticList(StaticEventList staticList) {
        EventListHandler.staticList = staticList;
    }

    public static DynamicEventList getDynamicList() {
        return dynamicList;
    }

    public static void setDynamicList(DynamicEventList dynamicList) {
        EventListHandler.dynamicList = dynamicList;
    }


    public static boolean checkValidTime(CalendarDate startTime, CalendarDate endTime) {
        if (startTime.getYear() != endTime.getYear() || startTime.getMonth() != endTime.getMonth() ||
                startTime.getDay() != endTime.getDay() ||
                endTime.getHour() - startTime.getHour() <= 0) {
            //System.out.println(startTime.getHour() - endTime.getHour());
            return false;
        }
        return true;
    }

    //Create a static event to add to the static event list
    public static boolean createStaticEvent(String name, String location, CalendarDate startTime, CalendarDate endTime,
                                     boolean isStatic, boolean isPeriodic, boolean isFinished, String description, String color) throws CalendarError {
        //check if start and end times are valid
        boolean check = false;
        Random randomno = new Random();
        if (!checkValidTime(startTime, endTime)) {
            System.out.println("Fail");
            return false;
        }
        //check if event is static
        if (isStatic == false)
            return false;
        //set the DateKey String used for getting all the events in one day
        String dateKey = startTime.DateKey();
        StaticEvent staticEvent = new StaticEvent(dateKey, name, location, startTime, endTime, isStatic,
                isPeriodic, isFinished, description, color);
        staticEvent.setId(Integer.parseInt(staticEvent.getStartTime().getMonth() + staticEvent.getStartTime().getDay() + "" +
                staticEvent.getStartTime().getHour() + "" + randomno.nextInt(9999)));
        check = staticList.addEvent(staticEvent);
        if (!check)
            System.out.println("Fail");
        return (check);
    }


    public static boolean removeEventById(int temp) throws CalendarError {
        boolean check = true;
        if (staticList == null) {
            check = false;
            return check;
        }
        check = staticList.removeEventById(temp);
        return check;
    }



    //Create a dynamic event to add to the dynamic event list
    public static void createDynamicEvent(String name, int estimatedLength, boolean isStatic,
                                   CalendarDate deadline, boolean isFinished, String description) {
        return;
    }


    //Dynamic sort algorithm
    /*public boolean dynamicSort(){
		
		Comparator<CalendarEvent> comparator = new Comparator<CalendarEvent>(){

			@Override
			public int compare(CalendarEvent o1, CalendarEvent o2) {
				return Long.compare(o1.getStartTime().time(), o2.getStartTime().time());
			}
				
		};
				
		PriorityQueue<DynamicEvent> currDynamicEList = new PriorityQueue<DynamicEvent>(comparator);
		PriorityQueue<StaticEvent> currStaticEList = new PriorityQueue<StaticEvent>(comparator);
		ArrayList<StaticEvent> staticArrayList = staticList.getList();
		ArrayList<DynamicEvent> dynamicArrayList = null;
		if(dynamicList!=null){
		dynamicArrayList = dynamicList.getList();}
		ArrayList<StaticEvent> freeList = new ArrayList<StaticEvent>();  ///////////////////////////////////////////////DEBUG??///////////////
		if(staticArrayList != null){
		for(int i=0; i<staticArrayList.size();i++){
			if(!staticArrayList.get(i).isFinished()){
				currStaticEList.add(staticArrayList.get(i));
			}
		}}
		if(dynamicArrayList != null){
		for(int i=0; i<dynamicArrayList.size();i++){
			if(!dynamicArrayList.get(i).isFinished()){
				currDynamicEList.add(dynamicArrayList.get(i));
			}
		}}
		while (!currStaticEList.isEmpty()){
			System.out.println(currStaticEList.poll().getStartTime().time());
		}
		return false;
	}
	
	private PriorityQueue<StaticEvent> createStaticQueue(PriorityQueue<StaticEvent> currStaticEList){
		return null;
		
	}*/


}



/*
ArrayList<StaticEvent>() events = EventListHandler.getStaticEventsByDateKey(string dateKey); ...DONE
ArrayList<DynamicEvent>() events = EventListHandler.getDynamicEventsByDateKey(string dateKey);
StaticEvent se;
string id = se.getId();
EventHandler.removeEventById(id); ...DONE

setEventFinished(String Id)
addColor field to staticEvent.... Done
*/