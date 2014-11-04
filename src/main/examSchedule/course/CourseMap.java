package main.examSchedule.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.examSchedule.exceptions.ElementDoesNotExistException;

public class CourseMap
{
	private HashMap<String, List<CourseLecturePair>> courseMap;

	public CourseMap()
	{
		courseMap = new HashMap<String, List<CourseLecturePair>>();
	}
	
	/**
	 * Adds a course with an uninitialized list of CourseLecturePairs only if the course key does not exist in the map
	 * @param courseName
	 */
	public void addCourse(String courseName)
	{
		if(!courseMap.containsKey(courseName))
			courseMap.put(courseName, new ArrayList<CourseLecturePair>());
	}
	
	public void addLecture(String courseName, String lectureName)
	{
		if(!courseMap.containsKey(courseName)) //The addLecture() method will create a blank list of lectures for the course before loading it if this is the case. 
			addCourse(courseName);
		courseMap.get(courseName).add(new CourseLecturePair(courseName, lectureName));
	}
	
	/**
	 * Returns the course lecture pair corresponding to the course and lecture names
	 * @param courseName
	 * @param lectureName
	 * @return
	 */
	public CourseLecturePair getCourseLecturePair(String courseName, String lectureName)
	{
		if(!courseMap.containsKey(courseName)) throw new ElementDoesNotExistException("Could not find course in course map");
		
		for (CourseLecturePair pair : courseMap.get(courseName))
		{
			if (pair.getLecture().getLectureName().equals(lectureName))
				return pair;
		}
		
		throw new ElementDoesNotExistException("Could not find lecture: " + lectureName + " in course map");
	}
	
	public void incrementLectureSize(String courseName, String lectureName)
	{
		getCourseLecturePair(courseName, lectureName).getLecture().incrementClassSize();
	}

	public HashMap<String, List<CourseLecturePair>> getCourseMap()
	{
		return courseMap;
	}
	
}