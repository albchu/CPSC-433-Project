package examSchedule.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import examSchedule.exceptions.ElementDoesNotExistException;

public class CourseMap
{
	private HashMap<String, List<Lecture>> courseMap;

	public CourseMap()
	{
		courseMap = new HashMap<String, List<Lecture>>();
	}
	
	/**
	 * Adds a course with an uninitialized list of CourseLecturePairs only if the course key does not exist in the map
	 * @param courseName
	 */
	public void addCourse(String courseName)
	{
		if(!courseMap.containsKey(courseName))
			courseMap.put(courseName, new ArrayList<Lecture>());
	}
	
	public void addLecture(String courseName, String lectureName)
	{
		if(!courseMap.containsKey(courseName)) //throw new ElementDoesNotExistException("Could not find course: " + courseName + " in course map");
			addCourse(courseName);
		courseMap.get(courseName).add(new Lecture(courseName, lectureName));
	}
	
	/**
	 * Returns the course lecture lecture corresponding to the course and lecture names
	 * @param courseName
	 * @param lectureName
	 * @return
	 */
	public Lecture getLecture(String courseName, String lectureName)
	{
		if(!courseMap.containsKey(courseName)) //throw new ElementDoesNotExistException("Could not find course: " + courseName + " in course map");
			addCourse(courseName);
//		System.out.println("Searching in course: " + courseName);
		for (Lecture lecture : courseMap.get(courseName))
		{
			if (lecture.getLectureName().equals(lectureName))
				return lecture;
		}
		addLecture(courseName, lectureName);
		return getLecture(courseName, lectureName);	//TODO: this is ugly, fucking fix this albert. dont let it stay recursion
	}
	
	public List<Lecture> getLectures(String courseName)
	{
		if(!courseMap.containsKey(courseName)) throw new ElementDoesNotExistException("Could not find course in course map");
		List<Lecture> lectures = new ArrayList<Lecture>();
		for (Lecture lecture : courseMap.get(courseName))
			lectures.add(lecture);
		return lectures;
	}
	
	public void updateExamLength(String courseName, String lectureName, int examLength)
	{
		getLecture(courseName, lectureName).setExamLength(examLength);
	}
	
	public void updateInstructor(String courseName, String lectureName, String instructor)
	{
		getLecture(courseName, lectureName).setInstructor(instructor);
	}
	
	public void incrementLectureSize(String courseName, String lectureName)
	{
		getLecture(courseName, lectureName).incrementClassSize();
	}

	public HashMap<String, List<Lecture>> getCourseMap()
	{
		return courseMap;
	}
	
	/** 
	 * Returns all lectures in one massive list
	 * @return
	 */
	public List<Lecture> getAllLectures()
	{
		List<Lecture> allLectures = new ArrayList<Lecture>();
		for(String courseName : courseMap.keySet())
			allLectures.addAll(this.getLectures(courseName));
		return allLectures;
	}
	
	/**
	 * Returns a set of all course keys in the map
	 * @return
	 */
	public Set<String> getCourses()
	{
		return courseMap.keySet();
	}
}
